package state;

import controller.LoginController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import user.User;

import java.io.IOException;

public abstract class State {
    protected final String page;
    public State nextState;

    protected State(String page) {
        this.page = page;
    }

    public void setNextState(State nextState) {
        this.nextState = nextState;
    }

    public State getNextState() {
        return nextState;
    }

    public void next(Stage stage, User user) {
        nextState.update(stage, user);
    }

    protected void update(Stage stage, User user) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(page));
            loader.setController(new LoginController(stage));
            Parent root = loader.load();
//            Controller controller = loader.getController();
//            controller.setStage(stage);
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
