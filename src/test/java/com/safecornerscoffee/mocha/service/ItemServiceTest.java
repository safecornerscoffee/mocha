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
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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
        UpdateItemCommand command = UpdateItemCommand.builder()
                .itemId(itemId)
                .name("Guatemala Decaf")
                .description("Guatemala Decaf")
                .price(8500)
                .stock(12)
                .build();

        itemService.updateItem(command);

        //then
        then(itemMapper).should().getItemById(itemId);
        then(productMapper).should().updateProduct(any(Product.class));
        then(itemMapper).should().updateItem(any(Item.class));

        assertThat(item.getId()).isEqualTo(itemId);
        assertThat(item.getPrice()).isEqualTo(command.getPrice());
        assertThat(item.getStock()).isEqualTo(command.getStock());
        assertThat(item.getProduct().getName()).isEqualTo(command.getName());
        assertThat(item.getProduct().getDescription()).isEqualTo((command.getDescription()));
    }

    @Test
    public void shouldThrowExceptionWhenUpdatingItemIsNotExists() {
        //given
        Long itemId = 1L;
        given(itemMapper.getItemById(itemId)).willReturn(null);

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
    public void throwIllegalArgumentExceptionWhenDeletingItemIsNotExists() {
        //given
        Long itemId = 1L;
        given(itemMapper.getItemById(itemId)).willReturn(null);

        //when
        assertThatThrownBy(() -> {
            itemService.deleteItem(itemId);
        }).describedAs("should throw Exception, when item doesn't exists.")
        .isInstanceOf(IllegalArgumentException.class);
    }
    
    @Test
    public void deleteItem() {
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
        itemService.deleteItem(itemId);

        //then
        then(itemMapper).should().getItemById(itemId);
        then(itemMapper).should(times(1)).deleteItem(itemId);
    }


}