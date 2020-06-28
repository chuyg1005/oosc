package server;

import bean.CartItem;
import bean.Item;
import bean.OrderItem;
import dao.*;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import user.Customer;
import user.Manager;
import user.Provider;

import java.util.List;

public class RealServer implements Server {
    private SqlSession session;
    private CustomerDao customerDao;
    private ProviderDao providerDao;
    private ManagerDao managerDao;
    private GoodsDao goodsDao;
    private CartDao cartDao;
    private OrderDao orderDao;

    public RealServer() {
        SqlSessionFactory factory = new SqlSessionFactoryBuilder()
                .build(getClass().getResourceAsStream("/config.xml"));
        session = factory.openSession(true);
        customerDao = session.getMapper(CustomerDao.class);
        providerDao = session.getMapper(ProviderDao.class);
        goodsDao = session.getMapper(GoodsDao.class);
        cartDao = session.getMapper(CartDao.class);
        orderDao = session.getMapper(OrderDao.class);
        managerDao = session.getMapper(ManagerDao.class);
    }

    @Override
    public Customer findCustomer(String name, String password) {
        return customerDao.findCustomer(name, password);
    }

    @Override
    public boolean addCustomer(Customer customer) {
        try {
            customerDao.addCustomer(customer);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public Provider findProvider(String name, String password) {
        return providerDao.findProvider(name, password);
    }

    @Override
    public boolean addProvider(Provider provider) {
        try {
            providerDao.addProvider(provider);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public List<String> findAllProviders() {
        return providerDao.findAllProviders();
    }

    @Override
    public Manager findManager(String name, String password) {
        return managerDao.findManager(name, password);
    }

    @Override
    public boolean addManager(Manager manager) {
        try {
            managerDao.addManager(manager);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public List<Item> findGoods(String name) {
        return goodsDao.findByProvider(name);
    }

    @Override
    public boolean addGoods(String name, Item item) {
        try {
            goodsDao.addItem(name, item);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public List<CartItem> findCart(String name) {
        return cartDao.findByCustomer(name);
    }

    @Override
    public void clearCart(String name) {
        cartDao.clearByCustomer(name);
    }

    @Override
    public void deleteCartItem(String cName, String pName, String gName) {
        cartDao.deleteByKey(cName, pName, gName);
    }

    @Override
    public void updateAmount(String cName, String pName, String gName, Integer amount) {
        cartDao.updateAmount(cName, pName, gName, amount);
    }

    @Override
    public boolean addCartItem(String name, CartItem item) {
        try {
            cartDao.addItem(name, item);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public List<OrderItem> findOrderByCustomer(String name) {
        return orderDao.findByCustomer(name);
    }

    @Override
    public List<OrderItem> findOrderByProvider(String name) {
        session.clearCache();
        return orderDao.findByProducer(name);
    }

    @Override
    public List<OrderItem> findNDays(Integer n) {
        return orderDao.findNDays(n);
    }

    @Override
    public void addOrderItem(OrderItem item) {
        orderDao.addItem(item);
    }
}
