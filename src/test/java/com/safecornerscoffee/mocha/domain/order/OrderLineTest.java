package com.safecornerscoffee.mocha.domain.order;

import com.safecornerscoffee.mocha.domain.Item;
import com.safecornerscoffee.mocha.domain.Product;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderLineTest {

    @Test
    public void calculatePrice() {
        //given
        Product product = createProduct(1L, "Kenya Othaya AAA");
        Item item = createItem(1L, product, 30, 18000);
        Order order = new Order();
        OrderLine line = OrderLine.createOrderLine(1, order, item, 3);

        assertThat(line.calculatePrice()).isEqualTo(item.getPrice() * line.getQuantity());
    }


    private Product createProduct(Long productId, String name) {
        Product product = Product.builder()
                .name("Ethiopia Mordecofe Organic")
                .description("Floral, sweet aroma and peach flavor. Garden Coffees, grown in small holder plots (usually measured in terms of trees rather than hectares) along with other crops.")
                .build();
        product.setId(productId);
        return product;
    }

    private Item createItem(Long itemId, Product product, int quantity, int price) {
        Item item = Item.builder().product(product).stock(quantity).price(price).build();
        item.setId(itemId);
        return item;
    }
}