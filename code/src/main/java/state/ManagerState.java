package state;

import controller.ManagerController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import user.Manager;
import user.User;

import java.io.IOException;

public class ManagerState extends State {

    private ManagerState() {
        super("/fxml/manager.fxml");
    }

    public static State getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    protected void update(Stage stage, User user) {
        try {
//            System.out.println(LoginState.getInstance());
            FXMLLoader loader = new FXMLLoader(getClass().getResource(page));
            loader.setController(new ManagerController(stage, (Manager) user));
            Parent root = loader.load();
//            CustomerController controller = loader.getController();
//            controller.setStage(stage);
//            controller.setCustomer((Customer) user);
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static class Holder {
        private static final State INSTANCE = new ManagerState();
    }
}
