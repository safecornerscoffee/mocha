package com.safecornerscoffee.integration.mocha.service;

import com.safecornerscoffee.mocha.domain.*;
import com.safecornerscoffee.mocha.domain.cart.Cart;
import com.safecornerscoffee.mocha.domain.order.Order;
import com.safecornerscoffee.mocha.domain.order.OrderStatus;
import com.safecornerscoffee.mocha.mapper.AccountMapper;
import com.safecornerscoffee.mocha.mapper.ItemMapper;
import com.safecornerscoffee.mocha.mapper.OrderMapper;
import com.safecornerscoffee.mocha.mapper.ProductMapper;
import com.safecornerscoffee.mocha.service.OrderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/web/WEB-INF/applicationContext.xml")
@Transactional
public class OrderServiceIntegrationTest {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderMapper orderMapper;
    @Autowired
    ProductMapper productMapper;
    @Autowired
    ItemMapper itemMapper;
    @Autowired
    AccountMapper accountMapper;

    Account account;
    List<Item> items;
    Cart cart;

    @Before
    public void setup() {
        account = createAccount("mocha");
        items = new ArrayList<>();
        items.add(createItem(createProduct("Kenya Othaya AAA"), 30, 18000));
        items.add(createItem(createProduct("Ethiopia Yirgacheffe G1"), 20, 15000));

        cart = new Cart();
        cart.addItem(items.get(0), 3);
        cart.addItem(items.get(1), 4);
    }

    @Test
    public void placeOrder() {
        //when
        Long orderId = orderService.placeOrder(account.getId(), cart);

        //then
        Order order = orderMapper.getOrderById(orderId);

        assertThat(order).isNotNull();
    }

    @Test
    public void cancelOrder() {
        //given
        Long orderId = orderService.placeOrder(account.getId(), cart);

        //when
        orderService.cancelOrder(orderId);

        //then
        Order order = orderService.getOrderById(orderId);
        assertThat(order.getStatus()).isEqualTo(OrderStatus.CANCEL);
    }

    @Test
    public void getOrderById() {
        //given
        Long orderId = orderMapper.nextId();
        Order order = Order.createOrder(orderId, account, cart);
        orderMapper.insertOrder(order);
        order.getOrderLines().forEach(orderMapper::insertOrderLine);

        //when
        Order other = orderService.getOrderById(orderId);

        //then
        assertThat(other).isEqualTo(order);
    }

    @Test
    public void getOrdersByAccountId() {
        //given
        Long orderId = orderMapper.nextId();
        Order order = Order.createOrder(orderId, account, cart);
        orderMapper.insertOrder(order);
        order.getOrderLines().forEach(orderMapper::insertOrderLine);

        //when
        List<Order> orders = orderService.getOrdersByAccountId(account.getId());

        //then
        assertThat(orders).contains(order);
    }

    @Test
    public void getOrders() {
        //given
        Order order = Order.createOrder(orderMapper.nextId(), account, cart);
        orderMapper.insertOrder(order);
        order.getOrderLines().forEach(orderMapper::insertOrderLine);

        Order other = Order.createOrder(orderMapper.nextId(), account, cart);
        orderMapper.insertOrder(other);
        other.getOrderLines().forEach(orderMapper::insertOrderLine);

        //when
        List<Order> orders = orderService.getOrders();

        //then
        assertThat(orders).contains(order, other);
    }


    private Account createAccount(String name) {
        Address address = new Address("address1", "address2", "city", "state", "06332");
        Account account = Account.builder()
                .email(name + "@safecornerscoffee.com")
                .password(name)
                .name(new Name(name, "coffee"))
                .address(address)
                .phoneNumber("010-3333-3333")
                .build();
        accountMapper.insertAccount(account);
        return account;
    }

    private Product createProduct(String name) {
        Product product = Product.builder()
                .name(name)
                .description(name)
                .build();
        productMapper.insertProduct(product);
        return product;
    }

    private Item createItem(Product product, int quantity, int price) {
        Item item = Item.builder()
                .product(product)
                .stock(quantity)
                .price(price)
                .build();
        itemMapper.insertItem(item);
        return item;
    }

}