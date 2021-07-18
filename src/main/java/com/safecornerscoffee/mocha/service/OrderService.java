package com.safecornerscoffee.mocha.service;

import com.safecornerscoffee.mocha.domain.Account;
import com.safecornerscoffee.mocha.domain.cart.Cart;
import com.safecornerscoffee.mocha.domain.order.Order;
import com.safecornerscoffee.mocha.domain.order.OrderLine;
import com.safecornerscoffee.mocha.mapper.AccountMapper;
import com.safecornerscoffee.mocha.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderMapper orderMapper;
    private final AccountMapper accountMapper;

    public Long placeOrder(Long accountId, Cart cart) {
        Account account = accountMapper.getAccountById(accountId);

        Order order = Order.createOrder(1L, account, cart);

        orderMapper.insertOrder(order);

        for (OrderLine orderLine : order.getOrderLines()) {
            orderMapper.insertOrderLine(orderLine);
        }

        return order.getId();
    }

    public void cancelOrder(Long orderId) {
        Order order = orderMapper.getOrderById(orderId);

        order.cancel();

        orderMapper.updateOrder(order);
    }

    public Order getOrderById(Long id) {
        return orderMapper.getOrderById(id);
    }

    public List<Order> getOrdersByAccountId(Long accountId) {
        return orderMapper.getOrdersByAccountId(accountId);
    }

    public List<Order> getOrders() {
        return orderMapper.getOrders();
    }
}
