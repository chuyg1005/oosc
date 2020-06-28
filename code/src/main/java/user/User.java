package user;

import server.Server;
import server.ServerHandler;

import java.io.Serializable;
import java.lang.reflect.Proxy;

public abstract class User implements Serializable {
    protected String name;
    protected String password;
    protected Server server;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.server = (Server) Proxy.newProxyInstance(getClass().getClassLoader(),
                new Class[]{Server.class}, new ServerHandler());
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
