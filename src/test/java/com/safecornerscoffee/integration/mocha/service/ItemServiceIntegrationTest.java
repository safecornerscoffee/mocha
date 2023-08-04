package com.safecornerscoffee.integration.mocha.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.safecornerscoffee.mocha.domain.Item;
import com.safecornerscoffee.mocha.domain.Product;
import com.safecornerscoffee.mocha.dto.CreateItemCommand;
import com.safecornerscoffee.mocha.dto.UpdateItemCommand;
import com.safecornerscoffee.mocha.mapper.ItemMapper;
import com.safecornerscoffee.mocha.mapper.ProductMapper;
import com.safecornerscoffee.mocha.service.ItemService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
    "file:src/main/webapp/WEB-INF/applicationContext.xml",
    "file:src/main/webapp/WEB-INF/securityContext.xml"
})
@Transactional
public class ItemServiceIntegrationTest {

    @Autowired
    ItemService itemService;

    @Autowired
    ItemMapper itemMapper;
    @Autowired
    ProductMapper productMapper;

    Product product;

    @Before
    public void setup() {
        product = Product.builder()
                .name("Guatemala Natural Decaf")
                .description("Guatemala Natural Decaf")
                .build();

        productMapper.insertProduct(product);
    }

    @Test
    public void getItemById() {
        //given
        Item item = Item.builder()
                .product(product)
                .price(13000)
                .stock(10)
                .build();
        itemMapper.insertItem(item);

        //when
        Item other = itemMapper.getItemById(item.getId());

        //then
        assertThat(other).isEqualTo(item);
    }

    @Test
    public void getItems() {
        //given
        Item item = Item.builder()
                .product(product)
                .price(13000)
                .stock(10)
                .build();
        Item other = Item.builder()
                .product(product)
                .price(13000)
                .stock(10)
                .build();
        List<Item> items = Arrays.asList(item, other);
        items.forEach(itemMapper::insertItem);

        //when
        List<Item> founds = itemMapper.getItems();

        //then
        assertThat(founds).hasSameSizeAs(items);
        assertThat(founds).containsAll(items);

    }

    @Test
    public void createItem() {
        //given
        CreateItemCommand command = CreateItemCommand.builder()
                .name("Cold Brew Organic Green Leaf")
                .description("Cold Brew Organic Green Leaf")
                .price(5500)
                .stock(10)
                .build();

        //when
        Long itemId = itemService.createItem(command);

        //then
        Item item = itemMapper.getItemById(itemId);
        assertThat(item.getPrice()).isEqualTo(command.getPrice());
        assertThat(item.getStock()).isEqualTo(command.getStock());

        assertThat(item.getProduct()).isNotNull();
        assertThat(item.getProduct().getName()).isEqualTo(command.getName());
        assertThat(item.getProduct().getSlug()).isEqualTo(command.getName().toLowerCase().replace(' ', '-'));
        assertThat(item.getProduct().getDescription()).isEqualTo(command.getDescription());

    }

    @Test
    public void updateItem() {
        //given
        Item item = Item.builder()
                .product(product)
                .price(13000)
                .stock(10)
                .build();
        itemMapper.insertItem(item);

        //when
        UpdateItemCommand command = UpdateItemCommand.builder()
                .itemId(item.getId())
                .name("Guatemala Decaf")
                .description("Guatemala Decaf")
                .price(8500)
                .stock(12)
                .build();

        itemService.updateItem(command);

        //then
        Item other = itemMapper.getItemById(item.getId());

        assertThat(other.getId()).isEqualTo(item.getId());
        assertThat(other.getPrice()).isEqualTo(command.getPrice());
        assertThat(other.getStock()).isEqualTo(command.getStock());
        assertThat(other.getProduct().getName()).isEqualTo(command.getName());
        assertThat(other.getProduct().getDescription()).isEqualTo((command.getDescription()));
    }

    @Test
    public void shouldThrowExceptionWhenUpdatingItemIsNotExists() {
        //given
        Long itemId = 100L;

        //when
        UpdateItemCommand command = UpdateItemCommand.builder()
                .itemId(itemId)
                .name("Guatemala Decaf")
                .description("Guatemala Decaf")
                .price(8500)
                .stock(12)
                .build();

        assertThatThrownBy(() -> {
            itemService.updateItem(command);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void deleteItem() {
        //given
        Item item = Item.builder()
                .product(product)
                .price(13000)
                .stock(10)
                .build();
        itemMapper.insertItem(item);

        //when
        itemService.deleteItem(item.getId());

        //then
        Item deleted = itemMapper.getItemById(item.getId());

        assertThat(deleted).isNull();
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenDeletingItemIsNotExists() {
        //given
        Long itemId = 100L;

        //when
        assertThatThrownBy(() -> {
            itemService.deleteItem(itemId);
        }).describedAs("should throw Exception, when item doesn't exists.")
                .isInstanceOf(IllegalArgumentException.class);
    }
}