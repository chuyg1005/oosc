package dao;

import bean.OrderItem;

import java.util.List;

public interface OrderDao {
    List<OrderItem> findByProducer(String name);

    List<OrderItem> findByCustomer(String name);

    List<OrderItem> findNDays(Integer n);

    void addItem(OrderItem item);
}
