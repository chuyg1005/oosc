package controller;

import bean.OrderItem;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import state.LoginState;
import state.ProviderState;
import user.Manager;

import java.sql.Date;
import java.util.List;

public class ManagerController extends Controller {
    private Manager manager;
    @FXML
    private Label userName;
    @FXML
    private TableView<OrderItem> orderTable;
    @FXML
    private ComboBox<String> period;

    public ManagerController(Stage stage, Manager manager) {
        super(ProviderState.getInstance(), stage);
        this.manager = manager;
        state.setNextState(LoginState.getInstance());
    }

    public void logout() {
        state.next(stage, null);
    }

    public void initialize() {
        period.getItems().addAll("最近一周", "最近一月");
        userName.setText(manager.getName());
        period.setValue("最近一周");
        initOrderTable();
    }

    public void initOrderTable() {
        TableColumn<OrderItem, String> pName = new TableColumn<>("商家名称");
        TableColumn<OrderItem, String> cName = new TableColumn<>("顾客名称");
        TableColumn<OrderItem, String> gName = new TableColumn<>("商品名称");
        TableColumn<OrderItem, Double> price = new TableColumn<>("商品单价");
        TableColumn<OrderItem, Integer> amount = new TableColumn<>("商品数量");
        TableColumn<OrderItem, Double> total = new TableColumn<>("结算价");
        TableColumn<OrderItem, Date> date = new TableColumn<>("订单日期");
        pName.setCellValueFactory(new PropertyValueFactory<>("pName"));
        cName.setCellValueFactory(new PropertyValueFactory<>("cName"));
        gName.setCellValueFactory(new PropertyValueFactory<>("gName"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        total.setCellValueFactory(new PropertyValueFactory<>("total"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        orderTable.getColumns().setAll(pName,cName, gName, price, amount, total, date);
        updateOrderTable();
    }

    public void changePeriod() {
//        System.out.println("change period");
        updateOrderTable();
    }

    public void updateOrderTable() {
        List<OrderItem> items = null;
        if("最近一周".equals(period.getValue())) {
            items = manager.findLastWeek();
        } else if ("最近一月".equals(period.getValue())) {
            items = manager.findLastMonth();
        }
//        System.out.println("items = " + items);
        orderTable.getItems().setAll(items);
    }
}
