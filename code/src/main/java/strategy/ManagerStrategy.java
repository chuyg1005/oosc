package strategy;

import controller.Common;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import state.ManagerState;
import state.State;
import user.Manager;

public class ManagerStrategy extends Strategy{
    protected ManagerStrategy(Common common, State state) {
        super(common, state);
    }

    @Override
    public void login(String name, String password, Stage stage) {
        Manager manager = common.findManager(name, password);
        if (manager != null) {
            state.setNextState(ManagerState.getInstance());
            state.next(stage, manager);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("没有这个管理员!");
            alert.showAndWait();
        }
    }

    @Override
    public void register(String name, String password, Stage stage) {
        Manager manager = common.addManager(new Manager(name, password));
        if (manager != null) {
            state.setNextState(ManagerState.getInstance());
            state.next(stage, manager);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("管理员名已经存在!");
            alert.showAndWait();
        }
    }
}
