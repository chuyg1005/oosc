package strategy;

import controller.Common;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import state.CustomerState;
import state.State;
import user.Customer;

public class CustomerStrategy extends Strategy {
    protected CustomerStrategy(Common common, State state) {
        super(common, state);
    }

    @Override
    public void login(String name, String password, Stage stage) {
        Customer customer = common.findCustomer(transName(name), password);
        if (customer != null) {
            state.setNextState(CustomerState.getInstance());
            state.next(stage, customer);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("没有这个顾客!");
            alert.showAndWait();
        }
    }

    @Override
    public void register(String name, String password, Stage stage) {
        if(name.toLowerCase().startsWith("vip")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("顾客名不能以vip开头!");
            alert.showAndWait();
            return;
        }
        Customer customer = common.addCustomer(createCustomer(name, password));
        if (customer != null) {
            state.setNextState(CustomerState.getInstance());
            state.next(stage, customer);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("顾客名已经存在!");
            alert.showAndWait();
        }
    }

    protected String transName(String name) {
        return name;
    }

    protected Customer createCustomer(String name, String password) {
        return new Customer(transName(name), password);
    }
}
