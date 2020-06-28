import server.RealServer;
import server.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.stream.Stream;

public class ServerApp {
    public static void main(String[] args) {
        Server server = new RealServer();
        Class<? extends Server> clazz = server.getClass();
        try (ServerSocket serverSocket = new ServerSocket(6527)) {
            while (true) {
                Socket socket = serverSocket.accept();
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

                String name = ois.readUTF();
                Object[] objs = (Object[]) ois.readObject();

                Object result = null;
                if(objs != null) {
                    Method method = clazz.getMethod(name, Stream.of(objs).map(Object::getClass).toArray(Class[]::new));
                    result = method.invoke(server, objs);
                } else {
                    Method method = clazz.getMethod(name);
                    result = method.invoke(server);
                }

                oos.writeObject(result);
                socket.close();

                // 清空缓存
            }
        } catch (IOException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
