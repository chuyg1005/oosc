package dao;

import user.Customer;

public interface CustomerDao {
    /**
     * 查找顾客名为name，密码为password的用户
     *
     * @return 存在返回用户，否则返回null
     */
    Customer findCustomer(String name, String password);

    /**
     * 添加顾客customer
     * 插入已经存在的顾客会触发异常
     */
    void addCustomer(Customer customer);
}
