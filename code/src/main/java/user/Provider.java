package user;

import bean.Item;
import bean.OrderItem;

import java.util.List;

public class Provider extends User {
    public Provider(String name, String password) {
        super(name, password);
    }

    public boolean addGoods(Item item) {
        return server.addGoods(name, item);
    }

    public List<OrderItem> findOrders() {
        return server.findOrderByProvider(name);
    }
}
