package state;

import controller.ProviderController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import user.Provider;
import user.User;

import java.io.IOException;

public class ProviderState extends State {

    private ProviderState() {
        super("/fxml/provider.fxml");
    }

    public static State getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    protected void update(Stage stage, User user) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(page));
            loader.setController(new ProviderController(stage, (Provider)user));
            Parent root = loader.load();
//            ProviderController controller = loader.getController();
//            controller.setStage(stage);
//            controller.setProvider((Provider) user);
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static class Holder {
        private static final State INSTANCE = new ProviderState();
    }
}
