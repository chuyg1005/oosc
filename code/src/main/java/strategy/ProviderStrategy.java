package strategy;

import controller.Common;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import state.ProviderState;
import state.State;
import user.Provider;

public class ProviderStrategy extends Strategy {
    protected ProviderStrategy(Common common, State state) {
        super(common, state);
    }

    @Override
    public void login(String name, String password, Stage stage) {
        Provider provider = common.findProvider(name, password);
        if (provider != null) {
            state.setNextState(ProviderState.getInstance());
            state.next(stage, provider);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("没有这个商家!");
            alert.showAndWait();
        }
    }

    @Override
    public void register(String name, String password, Stage stage) {
        Provider provider = common.addProvider(new Provider(name, password));
        if (provider != null) {
            state.setNextState(ProviderState.getInstance());
            state.next(stage, provider);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("商家名已经存在!");
            alert.showAndWait();
        }
    }
}
