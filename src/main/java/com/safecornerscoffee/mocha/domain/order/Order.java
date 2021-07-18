package com.safecornerscoffee.mocha.domain.order;

import com.safecornerscoffee.mocha.domain.Account;
import com.safecornerscoffee.mocha.domain.Address;
import com.safecornerscoffee.mocha.domain.cart.Cart;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(of={"id"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
public class Order {
    private Long id;
    private Account account;
    private LocalDateTime orderDate;
    private Address address;
    private OrderStatus status;

    private int totalPrice;

    private List<OrderLine> orderLines = new ArrayList<>();

    public static Order createOrder(Long id, Account account, Cart cart) {
        Order order = new Order();
        order.setId(id);
        order.setOrderDate(LocalDateTime.now());

        order.setAccount(account);
        order.setAddress(account.getAddress());

        //todo cartItem to OrderLine
        OrderLine orderLine = new OrderLine();
        addOrderLine(orderLine);

        order.setStatus(OrderStatus.ORDER);

        return order;
    }

    private static void addOrderLine(OrderLine orderLine) {
    }

    public void cancel() {
        setStatus(OrderStatus.CANCEL);
    }

}
