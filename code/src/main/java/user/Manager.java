package user;

import bean.OrderItem;

import java.util.List;

public class Manager extends User{
    public Manager(String name, String password) {
        super(name, password);
    }

    public List<OrderItem> findLastWeek() {
        return server.findNDays(7);
    }

    public List<OrderItem> findLastMonth() {
        return server.findNDays(30);
    }
}
