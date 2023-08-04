package com.safecornerscoffee.integration.mocha.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.safecornerscoffee.mocha.domain.Item;
import com.safecornerscoffee.mocha.domain.Product;
import com.safecornerscoffee.mocha.mapper.ItemMapper;
import com.safecornerscoffee.mocha.mapper.ProductMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
    "file:src/main/webapp/WEB-INF/applicationContext.xml",
    "file:src/main/webapp/WEB-INF/securityContext.xml"
})
@Transactional
public class ItemMapperTest {

    @Autowired
    ProductMapper productMapper;
    @Autowired
    ItemMapper itemMapper;

    @Test
    public void getItemById() {
        //given
        Product product = createProduct("Ethiopia Yirgacheffe");
        productMapper.insertProduct(product);
        Item item = Item.builder().product(product).stock(20).price(5700).build();
        itemMapper.insertItem(item);

        //when
        Item other = itemMapper.getItemById(item.getId());

        //then
        assertThat(other).isNotNull();
        assertThat(other.getId()).isEqualTo(item.getId());
        assertThat(other.getPrice()).isEqualTo(item.getPrice());
        assertThat(other.getStock()).isEqualTo(item.getStock());

        assertThat(other.getProduct().getId()).isEqualTo(product.getId());
        assertThat(other.getProduct().getName()).isEqualTo(product.getName());
        assertThat(other.getProduct().getSlug()).isEqualTo(product.getSlug());
        assertThat(other.getProduct().getDescription()).isEqualTo(product.getDescription());

    }

    @Test
    public void getItems() {
        //given
        Product product = createProduct("Ethiopia Yirgacheffe");
        productMapper.insertProduct(product);
        Item item = Item.builder().product(product).stock(20).price(5700).build();
        itemMapper.insertItem(item);

        //when
        List<Item> items = itemMapper.getItems();

        //then
        assertThat(items).hasSize(1);

        Item other = items.get(0);
        assertThat(other.getId()).isEqualTo(item.getId());
        assertThat(other.getPrice()).isEqualTo(item.getPrice());
        assertThat(other.getStock()).isEqualTo(item.getStock());

        assertThat(other.getProduct().getId()).isEqualTo(product.getId());
        assertThat(other.getProduct().getName()).isEqualTo(product.getName());
        assertThat(other.getProduct().getSlug()).isEqualTo(product.getSlug());
        assertThat(other.getProduct().getDescription()).isEqualTo(product.getDescription());

    }

    @Test
    public void insertItem() {
        //given
        Product product = createProduct("Ethiopia Yirgacheffe");
        productMapper.insertProduct(product);
        Item item = Item.builder().product(product).stock(20).price(5700).build();

        //when
        itemMapper.insertItem(item);

        //then
        assertThat(item.getId()).isNotNull();
    }

    @Test
    public void updateItem() {
        //given
        Product product = createProduct("Ethiopia Yirgacheffe");
        productMapper.insertProduct(product);
        Item item = Item.builder().product(product).stock(20).price(5700).build();
        itemMapper.insertItem(item);

        //when
        item.setStock(10);
        item.setPrice(6300);
        itemMapper.updateItem(item);

        //then
        Item other = itemMapper.getItemById(item.getId());
        assertThat(other.getStock()).isEqualTo(10);
        assertThat(other.getPrice()).isEqualTo(6300);

    }

    @Test
    public void deleteItem() {
        //given
        Product product = createProduct("Ethiopia Yirgacheffe");
        productMapper.insertProduct(product);
        Item item = Item.builder().product(product).stock(20).price(5700).build();
        itemMapper.insertItem(item);

        //when
        itemMapper.deleteItem(item.getId());

        //then
        Item other = itemMapper.getItemById(item.getId());
        assertThat(other).isNull();

    }

    private Product createProduct(String name) {
        return Product.builder()
                .name(name)
                .description(name)
                .build();
    }
}