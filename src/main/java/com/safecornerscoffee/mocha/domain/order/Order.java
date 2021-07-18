package com.safecornerscoffee.mocha.domain.order;

import com.safecornerscoffee.mocha.domain.Account;
import com.safecornerscoffee.mocha.domain.Address;
import com.safecornerscoffee.mocha.domain.cart.Cart;
import com.safecornerscoffee.mocha.domain.cart.CartItem;
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

        List<OrderLine> lines = new ArrayList<>();
        for (CartItem cartItem : cart.getCartItems()) {
            OrderLine line = OrderLine.createOrderLine(order.getId(),lines.size() + 1, cartItem.getItem(), cartItem.getQuantity());
            lines.add(line);
        }
        order.setOrderLines(lines);
        order.calculateTotalPrice();
        order.setStatus(OrderStatus.ORDER);

        return order;
    }


    public void cancel() {
        setStatus(OrderStatus.CANCEL);
    }

    public int getTotalPrice() {
        calculateTotalPrice();
        return totalPrice;
    }

    public int calculateTotalPrice() {
        this.totalPrice = getOrderLines().stream().mapToInt(OrderLine::calculatePrice).sum();
        return totalPrice;
    }

}
