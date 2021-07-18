package com.safecornerscoffee.mocha.service;

import com.safecornerscoffee.mocha.domain.*;
import com.safecornerscoffee.mocha.domain.cart.Cart;
import com.safecornerscoffee.mocha.domain.order.Order;
import com.safecornerscoffee.mocha.domain.order.OrderLine;
import com.safecornerscoffee.mocha.domain.order.OrderStatus;
import com.safecornerscoffee.mocha.mapper.AccountMapper;
import com.safecornerscoffee.mocha.mapper.OrderMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @InjectMocks
    OrderService orderService;

    @Mock
    OrderMapper orderMapper;
    @Mock
    AccountMapper accountMapper;

    Account account;
    List<Item> items;
    Cart cart;

    @Before
    public void setup() {
        account = createAccount(1L, "mocha");
        items = new ArrayList<>();
        items.add(createItem(1L, createProduct(1L, "Kenya Othaya AAA"), 30, 18000));
        items.add(createItem(2L, createProduct(2L, "Ethiopia Yirgacheffe G1"), 20, 15000));

        cart = new Cart();
        cart.addItem(items.get(0), 3);
        cart.addItem(items.get(1), 4);
    }

    @Test
    public void placeOrder() {
        //given
        given(accountMapper.getAccountById(anyLong())).willReturn(account);
        Cart cart = new Cart();
        cart.addItem(items.get(0), 3);

        will(invocationOnMock -> {
            Order order = invocationOnMock.getArgument(0, Order.class);
            assertThat(order.getOrderLines()).hasSize(1);
            order.setId(1L);
            return null;
        }).given(orderMapper).insertOrder(any(Order.class));

        //when
        Long orderId = orderService.placeOrder(account.getId(), cart);

        //then
        then(accountMapper).should().getAccountById(account.getId());
        then(orderMapper).should().insertOrder(any(Order.class));
        then(orderMapper).should(times(cart.size())).insertOrderLine(any(OrderLine.class));
        assertThat(orderId).isNotNull();
    }

    @Test
    public void cancelOrder() {
        //given
        Order order = createOrder(1L);
        given(orderMapper.getOrderById(anyLong())).willReturn(order);

        //when
        orderService.cancelOrder(order.getId());

        //then
        then(orderMapper).should().updateOrder(order);
        assertThat(order.getStatus()).isEqualTo(OrderStatus.CANCEL);
    }


    private Order createOrder(Long orderId) {
        Order order = Order.createOrder(1L, account, cart);
        order.setId(orderId);
        return order;
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