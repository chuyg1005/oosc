import controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // 加载fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
        loader.setController(new LoginController(primaryStage));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        // 设置controller的scene对象
//        LoginController controller = loader.getController();
//        controller.setStage(primaryStage);
        // 显示场景
        primaryStage.setTitle("外卖软件");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("takeout.png")));
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
