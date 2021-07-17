package com.safecornerscoffee.mocha.service;

import com.safecornerscoffee.mocha.domain.Item;
import com.safecornerscoffee.mocha.domain.Product;
import com.safecornerscoffee.mocha.dto.CreateItemCommand;
import com.safecornerscoffee.mocha.dto.UpdateItemCommand;
import com.safecornerscoffee.mocha.mapper.ItemMapper;
import com.safecornerscoffee.mocha.mapper.ProductMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ItemServiceTest {

    @InjectMocks
    ItemService itemService;

    @Mock
    ItemMapper itemMapper;

    @Mock
    ProductMapper productMapper;


    @Test
    public void getItemById() {
        //given
        Long productId = 1L;
        Product product = Product.builder()
                    .name("Guatemala Natural Decaf")
                    .description("Guatemala Natural Decaf")
                    .build();
        product.setId(productId);

        Long itemId = 1L;
        Item item = Item.builder()
                .product(product)
                .price(13000)
                .stock(10)
                .build();
        item.setId(itemId);

        given(itemMapper.getItemById(itemId)).willReturn(item);

        //when
        Item other = itemService.getItemById(itemId);

        //then
        then(itemMapper).should().getItemById(itemId);
        assertThat(other).isEqualTo(item);
    }

    @Test
    public void getItems() {
        //given
        Long productId = 1L;
        Product product = Product.builder()
                .name("Guatemala Natural Decaf")
                .description("Guatemala Natural Decaf")
                .build();
        product.setId(productId);

        Item item = Item.builder()
                .product(product)
                .price(13000)
                .stock(10)
                .build();
        item.setId(1L);
        Item other = Item.builder()
                .product(product)
                .price(13000)
                .stock(10)
                .build();
        item.setId(2L);
        List<Item> items = Arrays.asList(item, other);

        given(itemMapper.getItems()).willReturn(items);

        //when
        List<Item> foundItems = itemService.getItems();

        //then
        then(itemMapper).should(atMostOnce()).getItems();
        assertThat(foundItems).hasSameSizeAs(items);
        assertThat(foundItems).contains(items.toArray(new Item[0]));

    }

    @Test
    public void createItem() {
        //given
        CreateItemCommand command = CreateItemCommand.builder()
                .name("Guatemala Natural Decaf")
                .description("Guatemala Natural Decaf")
                .price(13000)
                .stock(10)
                .build();

        doAnswer(invocationOnMock -> {
            Product product = invocationOnMock.getArgument(0, Product.class);
            product.setId(1L);
            return null;
        }).when(productMapper).insertProduct(any(Product.class));

        doAnswer(invocationOnMock -> {
            Item item = invocationOnMock.getArgument(0, Item.class);
            item.setId(1L);
            return null;
        }).when(itemMapper).insertItem(any(Item.class));

        //when
        Long itemId = itemService.createItem(command);

        //then
        then(productMapper).should().insertProduct(any(Product.class));
        then(itemMapper).should().insertItem(any(Item.class));
        assertThat(itemId).isNotNull();
    }

    @Test
    public void updateItem() {
        //given
        Long itemId = 1L;
        UpdateItemCommand command = UpdateItemCommand.builder()
                .itemId(itemId)
                .name("Guatemala Natural Decaf")
                .description("Guatemala Natural Decaf")
                .stock(120)
                .price(8500)
                .build();

        //when
        itemService.updateItem(command);

        //then
        then(itemMapper).should().getItemById(itemId);
        then(productMapper).should().updateProduct(any(Product.class));
        then(itemMapper).should().updateItem(any(Item.class));
    }

    @Test
    public void deleteItem() {
        //given
        Long itemId = 1L;
        //when
        itemService.deleteItem(itemId);

        //then
        then(itemMapper).should(times(1)).deleteItem(itemId);
    }


}