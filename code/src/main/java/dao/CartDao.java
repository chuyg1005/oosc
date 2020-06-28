package dao;

import bean.CartItem;

import java.util.List;

public interface CartDao {
    /**
     * 获取顾客name的购物车
     */
    List<CartItem> findByCustomer(String name);

    /**
     * 清空顾客name的购物车
     */
    void clearByCustomer(String name);

    /**
     * 清空一个项目
     */
    void deleteByKey(String cName, String pName, String gName);

    /**
     * 修改项目数量为amount
     */
    void updateAmount(String cName, String pName, String gName, int amount);

    /**
     * 添加商品到购物车
     */
    void addItem(String name, CartItem item);
}
