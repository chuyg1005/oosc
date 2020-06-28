package controller;

import bean.Item;
import server.Server;
import server.ServerHandler;
import user.*;

import java.lang.reflect.Proxy;
import java.util.List;

public class Common {
    private Server server;

    public Common() {
        server = (Server) Proxy.newProxyInstance(getClass().getClassLoader(),
                new Class[]{Server.class}, new ServerHandler());
    }

    public Customer findCustomer(String name, String password) {
        if (server.findCustomer(name, password) == null) return null;
        if (name.startsWith("vip1-")) return new Vip1Customer(name, password);
        else if (name.startsWith("vip2-")) return new Vip2Customer(name, password);
        else return new Customer(name, password);
    }

    public Provider findProvider(String name, String password) {
        return server.findProvider(name, password);
    }

    public Manager findManager(String name, String password) {
        return server.findManager(name, password);
    }

    public Customer addCustomer(Customer customer) {
        if(!server.addCustomer(customer)) return null;
        String name = customer.getName();
        String password = customer.getPassword();
        if (name.startsWith("vip1-")) return new Vip1Customer(name, password);
        else if (name.startsWith("vip2-")) return new Vip2Customer(name, password);
        else return new Customer(name, password);
    }

    public Provider addProvider(Provider provider) {
        return server.addProvider(provider) ? provider : null;
    }

    public Manager addManager(Manager manager) {
        return server.addManager(manager) ? manager : null;
    }

    public List<Item> findGoods(String name) {
        return server.findGoods(name);
    }

    public List<String> findAllProviders() {
        return server.findAllProviders();
    }
}
