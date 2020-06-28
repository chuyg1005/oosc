package dao;

import user.Manager;

public interface ManagerDao {
    Manager findManager(String name, String password);

    void addManager(Manager manager);
}
