package user;

import bean.CartItem;
import bean.OrderItem;

import java.sql.Date;
import java.util.List;

/**
 * 顾客类
 */
public class Customer extends User {
    public Customer(String name, String password) {
        super(name, password);
    }

    /**
     * 查询顾客的购物车
     */
    public List<CartItem> findCart() {
        return server.findCart(name);
    }

    /**
     * 查询顾客的历史订单
     */
    public List<OrderItem> findOrders() {
        return server.findOrderByCustomer(name);
    }

    /**
     * 清空顾客的购物车
     */
    public void clearCart() {
        server.clearCart(name);
    }

    /**
     * 将商品添加到顾客的购物车
     */
    public void addCartItem(List<CartItem> items) {
        items.forEach(item -> server.addCartItem(name, item));
    }

    /**
     * 删除购物车中的指定商品
     */
    public void deleteCartItem(List<CartItem> items) {
        items.forEach(item -> server.deleteCartItem(name, item.getPName(), item.getGName()));
    }

    /**
     * 修改购物车中的商品数量
     */
    public void updateCartItemAmount(String pName, String gName, int amount) {
        server.updateAmount(name, pName, gName, amount);
    }

    /**
     * 对订单进行结算
     */
    public void calculate(List<CartItem> items) {
        // 生成订单
        items.forEach(item ->
                server.addOrderItem(OrderItem.builder()
                        .cName(name).pName(item.getPName()).gName(item.getGName())
                        .price(item.getPrice()).amount(item.getAmount())
                        .total(purchase(item.getPrice(), item.getAmount()))
                        .date(new Date(System.currentTimeMillis()))
                        .build()));
        // 删除购物车中的项目
        deleteCartItem(items);
    }

    protected double purchase(double price, int amount) {
        return price * amount;
    }
}
