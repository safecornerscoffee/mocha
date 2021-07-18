package com.safecornerscoffee.mocha.domain.order;

import com.safecornerscoffee.mocha.domain.*;
import com.safecornerscoffee.mocha.domain.cart.Cart;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderTest {

    Account account;
    List<Item> items;

    @Before
    public void setup() {
        account = createAccount(1L, "mocha");
        items = new ArrayList<>();
        items.add(createItem(1L, createProduct(1L, "Kenya Othaya AAA"), 30, 18000));
        items.add(createItem(2L, createProduct(2L, "Ethiopia Yirgacheffe G1"), 20, 15000));
    }

    @Test
    public void createOrder() {
        //given
        Cart cart = new Cart();
        cart.addItem(items.get(0), 3);
        cart.addItem(items.get(1), 4);

        //when
        Order order = Order.createOrder(1L , account, cart);

        //then
        assertThat(order.getId()).isEqualTo(1L);
        assertThat(order.getOrderDate()).isNotNull();
        assertThat(order.getAccount()).isEqualTo(account);
        assertThat(order.getAddress()).isEqualTo(account.getAddress());
        assertThat(order.getStatus()).isEqualTo(OrderStatus.ORDER);
        assertThat(order.getOrderLines()).hasSameSizeAs(cart.getCartItems());

    }

    @Test
    public void cancelOrder() {
        //given
        Cart cart = new Cart();
        cart.addItem(items.get(0), 2);
        Order order = Order.createOrder(1L, account, cart);

        //when
        order.cancel();

        ///then
        assertThat(order.getStatus()).isEqualTo(OrderStatus.CANCEL);
    }

    @Test
    public void calculateTotalPrice() {
        //given
        Cart cart = new Cart();
        cart.addItem(items.get(0), 3);
        cart.addItem(items.get(1), 4);
        Order order = Order.createOrder(1L, account, cart);

        //when
        int totalPrice = order.calculateTotalPrice();

        //then
        assertThat(order.getOrderLines()).hasSize(2);
        assertThat(totalPrice).isEqualTo(cart.calculateTotalPrice());

    }

    private Account createAccount(Long accountId, String name) {
        Address address = new Address("address1", "address2", "city", "state", "06332");
        Account account = Account.builder()
                .email(name + "@safecornerscoffee.com")
                .password(name)
                .name(new Name(name, "coffee"))
                .address(address)
                .phoneNumber("010-3333-3333")
                .build();
        account.setId(accountId);
        return account;
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