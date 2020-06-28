package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import state.CustomerState;
import state.LoginState;
import strategy.Strategy;

/**
 * 用户登录界面
 */
public class LoginController extends Controller {
    private Strategy strategy;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private TextField username;
    @FXML
    private TextField password;

    public LoginController(Stage stage) {
        super(LoginState.getInstance(), stage);
        state.setNextState(CustomerState.getInstance());
        strategy = Strategy.newStrategy("顾客", common, state);
//        System.out.println("login...");
    }

    public void initialize() {
        comboBox.getItems().addAll("顾客", "vip1-顾客", "vip2-顾客", "商家", "管理员");
        comboBox.setValue("顾客");
    }

    public void update() {
        System.out.println("update");
    }

    public void login() {
        strategy.login(username.getText(), password.getText(), stage);
    }

    public void register() {
        if (username.getText().isEmpty() || password.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("用户名和密码不可为空!");
            alert.showAndWait();
            return;
        }
        strategy.register(username.getText(), password.getText(), stage);
    }

    public void changeState() {
        strategy = Strategy.newStrategy(comboBox.getValue(), common, state);
    }

}
