package controller;

import bean.Item;
import bean.OrderItem;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import state.LoginState;
import state.ProviderState;
import user.Provider;

import java.sql.Date;
import java.util.List;

public class ProviderController extends Controller {
    private Provider provider;
    @FXML
    private Label userName;
    @FXML
    private TableView<Item> itemTable;
    @FXML
    private TableView<OrderItem> orderTable;
    @FXML
    private TextField itemName;
    @FXML
    private TextField itemPrice;
    @FXML
    private TextField itemDesc;

    public ProviderController(Stage stage, Provider provider) {
        super(ProviderState.getInstance(), stage);
        this.provider = provider;
        state.setNextState(LoginState.getInstance());
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    private void initItemTable() {
        TableColumn<Item, String> name = new TableColumn<>("商品名称");
        TableColumn<Item, Double> price = new TableColumn<>("商品价格");
        TableColumn<Item, String> desc = new TableColumn<>("商品描述");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        itemTable.getColumns().setAll(name, price, desc);
        updateItemTable();
    }

    private void updateItemTable() {
        List<Item> goods = common.findGoods(provider.getName());
//        System.out.println(goods.size());
        itemTable.getItems().setAll(goods);
    }

    private void initOrderTable() {
        TableColumn<OrderItem, String> cName = new TableColumn<>("顾客名称");
        TableColumn<OrderItem, String> gName = new TableColumn<>("商品名称");
        TableColumn<OrderItem, Double> price = new TableColumn<>("商品单价");
        TableColumn<OrderItem, Integer> amount = new TableColumn<>("商品数量");
        TableColumn<OrderItem, Double> total = new TableColumn<>("结算价");
        TableColumn<OrderItem, Date> date = new TableColumn<>("订单日期");
        cName.setCellValueFactory(new PropertyValueFactory<>("cName"));
        gName.setCellValueFactory(new PropertyValueFactory<>("gName"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        total.setCellValueFactory(new PropertyValueFactory<>("total"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        orderTable.getColumns().setAll(cName, gName, price, amount, total, date);
        updateOrderTable();
    }

    public void updateOrderTable() {
        List<OrderItem> orders = provider.findOrders();
//        System.out.println(orders);
        orderTable.getItems().setAll(orders);
    }

    public void initialize() {
        userName.setText(provider.getName());
        initItemTable();
        initOrderTable();
    }

    public void logout() {
        state.next(stage, null);
    }

    public void addItem() {
        try {
            Item item = new Item(itemName.getText(), Double.parseDouble(itemPrice.getText()), itemDesc.getText());
            if(provider.addGoods(item)) {
                updateItemTable();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("商品已经存在!");
                alert.showAndWait();
            }
        } catch(Exception ex) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("数据格式错误!");
            alert.showAndWait();
        }
    }
}
