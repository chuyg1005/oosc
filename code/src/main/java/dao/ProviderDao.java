package dao;

import user.Provider;

import java.util.List;

public interface ProviderDao {
    Provider findProvider(String name, String password);

    void addProvider(Provider provider);

    List<String> findAllProviders();
}
