package com.safecornerscoffee.integration.mocha.mapper;

import com.safecornerscoffee.mocha.domain.*;
import com.safecornerscoffee.mocha.domain.cart.Cart;
import com.safecornerscoffee.mocha.domain.order.Order;
import com.safecornerscoffee.mocha.domain.order.OrderLine;
import com.safecornerscoffee.mocha.mapper.AccountMapper;
import com.safecornerscoffee.mocha.mapper.ItemMapper;
import com.safecornerscoffee.mocha.mapper.OrderMapper;
import com.safecornerscoffee.mocha.mapper.ProductMapper;
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
public class OrderMapperTest {

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
    public void nextId() {
        //when
        Long id = orderMapper.nextId();

        //then
        assertThat(id).isNotNull();
    }

    @Test
    public void getOrderById() {
        //given
        Long orderId = orderMapper.nextId();
        Order order = Order.createOrder(orderId, account, cart);
        orderMapper.insertOrder(order);
        order.getOrderLines().forEach(orderMapper::insertOrderLine);

        //when
        Order other = orderMapper.getOrderById(order.getId());

        //then
        assertThat(other.getId()).isEqualTo(order.getId());
        assertThat(other.getOrderDate()).isEqualTo(order.getOrderDate());
        assertThat(other.getAddress()).isEqualTo(order.getAddress());
        assertThat(other.getStatus()).isEqualTo(order.getStatus());
        assertThat(other.getTotalPrice()).isEqualTo(order.calculateTotalPrice());
        assertThat(other.getAccount()).isEqualTo(order.getAccount());
        assertThat(other.getAddress()).isEqualTo(order.getAddress());
        assertThat(other.getOrderLines()).hasSameSizeAs(order.getOrderLines());
        assertThat(other.getOrderLines()).containsAll(order.getOrderLines());

        Item item = order.getOrderLines().get(0).getItem();
        Item otherItem = other.getOrderLines().get(0).getItem();

        assertThat(otherItem).isEqualTo(item);
        assertThat(otherItem.getId()).describedAs("item.id").isEqualTo(item.getId());
        assertThat(otherItem.getPrice()).describedAs("item.price").isEqualTo(item.getPrice());
        assertThat(otherItem.getStock()).describedAs("item.stock").isEqualTo(item.getStock());
        assertThat(otherItem.getProduct().getId()).describedAs("product.stock").isEqualTo(item.getProduct().getId());
        assertThat(otherItem.getProduct().getName()).describedAs("product.name").isEqualTo(item.getProduct().getName());
        assertThat(otherItem.getProduct().getSlug()).describedAs("product.slug").isEqualTo(item.getProduct().getSlug());
        assertThat(otherItem.getProduct().getDescription()).describedAs("product.description").isEqualTo(item.getProduct().getDescription());

    }

    @Test
    public void getOrdersByAccountId() {
        //given
        Long orderId = orderMapper.nextId();
        Order order = Order.createOrder(orderId, account, cart);
        orderMapper.insertOrder(order);
        order.getOrderLines().forEach(orderMapper::insertOrderLine);

        //when
        List<Order> orders = orderMapper.getOrdersByAccountId(account.getId());

        //then
        Order other = orders.get(0);
        assertThat(other.getId()).isEqualTo(order.getId());
        assertThat(other.getOrderDate()).isEqualTo(order.getOrderDate());
        assertThat(other.getAddress()).isEqualTo(order.getAddress());
        assertThat(other.getStatus()).isEqualTo(order.getStatus());
        assertThat(other.getTotalPrice()).isEqualTo(order.calculateTotalPrice());
        assertThat(other.getAccount()).isEqualTo(order.getAccount());
        assertThat(other.getAddress()).isEqualTo(order.getAddress());
        assertThat(other.getOrderLines()).hasSameSizeAs(order.getOrderLines());
        assertThat(other.getOrderLines()).containsAll(order.getOrderLines());

        Item item = order.getOrderLines().get(0).getItem();
        Item otherItem = other.getOrderLines().get(0).getItem();

        assertThat(otherItem).isEqualTo(item);
        assertThat(otherItem.getId()).describedAs("item.id").isEqualTo(item.getId());
        assertThat(otherItem.getPrice()).describedAs("item.price").isEqualTo(item.getPrice());
        assertThat(otherItem.getStock()).describedAs("item.stock").isEqualTo(item.getStock());
        assertThat(otherItem.getProduct().getId()).describedAs("product.stock").isEqualTo(item.getProduct().getId());
        assertThat(otherItem.getProduct().getName()).describedAs("product.name").isEqualTo(item.getProduct().getName());
        assertThat(otherItem.getProduct().getSlug()).describedAs("product.slug").isEqualTo(item.getProduct().getSlug());
        assertThat(otherItem.getProduct().getDescription()).describedAs("product.description").isEqualTo(item.getProduct().getDescription());

    }

    @Test
    public void getOrders() {
        //given
        Long orderId = orderMapper.nextId();
        Order order = Order.createOrder(orderId, account, cart);
        orderMapper.insertOrder(order);
        order.getOrderLines().forEach(orderMapper::insertOrderLine);

        //when
        List<Order> orders = orderMapper.getOrders();

        //then
        Order other = orders.get(0);
        assertThat(other.getId()).isEqualTo(order.getId());
        assertThat(other.getOrderDate()).isEqualTo(order.getOrderDate());
        assertThat(other.getAddress()).isEqualTo(order.getAddress());
        assertThat(other.getStatus()).isEqualTo(order.getStatus());
        assertThat(other.getTotalPrice()).isEqualTo(order.calculateTotalPrice());
        assertThat(other.getAccount()).isEqualTo(order.getAccount());
        assertThat(other.getAddress()).isEqualTo(order.getAddress());
        assertThat(other.getOrderLines()).hasSameSizeAs(order.getOrderLines());
        assertThat(other.getOrderLines()).containsAll(order.getOrderLines());

        Item item = order.getOrderLines().get(0).getItem();
        Item otherItem = other.getOrderLines().get(0).getItem();

        assertThat(otherItem).isEqualTo(item);
        assertThat(otherItem.getId()).describedAs("item.id").isEqualTo(item.getId());
        assertThat(otherItem.getPrice()).describedAs("item.price").isEqualTo(item.getPrice());
        assertThat(otherItem.getStock()).describedAs("item.stock").isEqualTo(item.getStock());
        assertThat(otherItem.getProduct().getId()).describedAs("product.stock").isEqualTo(item.getProduct().getId());
        assertThat(otherItem.getProduct().getName()).describedAs("product.name").isEqualTo(item.getProduct().getName());
        assertThat(otherItem.getProduct().getSlug()).describedAs("product.slug").isEqualTo(item.getProduct().getSlug());
        assertThat(otherItem.getProduct().getDescription()).describedAs("product.description").isEqualTo(item.getProduct().getDescription());

    }

    @Test
    public void insertOrder() {
        //given
        Long orderId = orderMapper.nextId();
        Order order = Order.createOrder(orderId, account, cart);

        //when
        orderMapper.insertOrder(order);

        //then
        assertThat(order.getId()).isNotNull();
    }

    @Test
    public void updateOrder() {
        //given
        Cart cart = new Cart();
        Long orderId = orderMapper.nextId();
        Order order = Order.createOrder(orderId, account, cart);

        //when
        orderMapper.updateOrder(order);

        //then
        Order other =orderMapper.getOrderById(1000L);
        assertThat(other).isNotNull();
    }

    @Test
    public void deleteOrder() {
        //given
        Cart cart = new Cart();
        Long orderId = orderMapper.nextId();
        Order order = Order.createOrder(orderId, account, cart);
        orderMapper.insertOrder(order);

        //when
        orderMapper.deleteOrder(order.getId());

        //then
        Order other = orderMapper.getOrderById(order.getId());

        assertThat(other).isNull();

    }

    @Test
    public void getOrderLinesByOrderId() {
        //given
        Long orderId = orderMapper.nextId();
        Order order = Order.createOrder(orderId, account, cart);
        orderMapper.insertOrder(order);
        order.getOrderLines().forEach(orderMapper::insertOrderLine);

        //when
        List<OrderLine> orderLines = orderMapper.getOrderLinesByOrderId(order.getId());

        //then
        assertThat(orderLines).hasSameSizeAs(order.getOrderLines());
        assertThat(orderLines).containsAll(order.getOrderLines());

        assertThat(orderLines.get(0).getOrderId()).isEqualTo(order.getId());

        Item item = order.getOrderLines().get(0).getItem();
        Item other = orderLines.get(0).getItem();

        assertThat(other).isNotNull();
        assertThat(other.getId()).describedAs("item.id").isEqualTo(item.getId());
        assertThat(other.getPrice()).describedAs("item.price").isEqualTo(item.getPrice());
        assertThat(other.getStock()).describedAs("item.stock").isEqualTo(item.getStock());
        assertThat(other.getProduct().getId()).isEqualTo(item.getProduct().getId());
        assertThat(other.getProduct().getName()).isEqualTo(item.getProduct().getName());
        assertThat(other.getProduct().getSlug()).isEqualTo(item.getProduct().getSlug());
        assertThat(other.getProduct().getDescription()).isEqualTo(item.getProduct().getDescription());

    }

    @Test
    public void insertOrderLine() {
        //given
        Long orderId = orderMapper.nextId();
        Order order = Order.createOrder(orderId, account, cart);
        orderMapper.insertOrder(order);

        //when
        order.getOrderLines().forEach(orderMapper::insertOrderLine);

        //then
        for (OrderLine line : order.getOrderLines()) {
            assertThat(line.getId()).isNotNull();
        }
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