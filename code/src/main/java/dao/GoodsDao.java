package dao;

import bean.Item;

import java.util.List;

public interface GoodsDao {
    /**
     * 根据商家名查询该商家的所有商品
     *
     * @return 返回该商家的所有商品列表
     */
    List<Item> findByProvider(String name);

    /**
     * 给商家name添加商品
     * 不成功抛出异常
     */
    void addItem(String name, Item item);
}
