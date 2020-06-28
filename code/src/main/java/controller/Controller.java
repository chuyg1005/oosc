package controller;

import javafx.stage.Stage;
import state.State;

public class Controller {
    protected Stage stage;
    protected State state;
    protected Common common = new Common();

    public Controller(State state, Stage stage) {
        this.state = state;
        this.stage = stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
