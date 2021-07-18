package com.safecornerscoffee.mocha.mapper;

import com.safecornerscoffee.mocha.domain.order.Order;
import com.safecornerscoffee.mocha.domain.order.OrderLine;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderMapper {

    Long nextId();
    Order getOrderById(Long id);
    List<Order> getOrdersByAccountId(Long accountId);
    List<Order> getOrders();

    void insertOrder(Order order);
    void updateOrder(Order order);
    void deleteOrder(Long id);

    List<OrderLine> getOrderLinesByOrderId(Long orderId);
    void insertOrderLine(OrderLine orderLine);
}
