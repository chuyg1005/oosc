package server;

import bean.CartItem;
import bean.Item;
import bean.OrderItem;
import user.Customer;
import user.Manager;
import user.Provider;

import java.io.Serializable;
import java.util.List;

public interface Server extends Serializable {
    Customer findCustomer(String name, String password);

    boolean addCustomer(Customer customer);

    Provider findProvider(String name, String password);

    boolean addProvider(Provider provider);

    List<String> findAllProviders();

    Manager findManager(String name, String password);

    boolean addManager(Manager manager);

    List<Item> findGoods(String name);

    boolean addGoods(String name, Item item);

    List<CartItem> findCart(String name);

    void clearCart(String name);

    void deleteCartItem(String cName, String pName, String gName);

    void updateAmount(String cName, String pName, String gName, Integer amount);

    boolean addCartItem(String name, CartItem item);

    List<OrderItem> findOrderByCustomer(String name);

    List<OrderItem> findOrderByProvider(String name);

    List<OrderItem> findNDays(Integer n);

    void addOrderItem(OrderItem item);
}
