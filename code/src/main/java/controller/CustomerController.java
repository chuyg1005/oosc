package controller;

import bean.OrderItem;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import state.CustomerState;
import state.LoginState;
import user.Customer;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerController extends Controller {
    private Customer customer;
    @FXML
    private Label userName;
    @FXML
    private ListView<String> shopTable;
    @FXML
    private TableView<Item> itemTable;
    @FXML
    private TableView<CartItem> cartTable;
    @FXML
    private TableView<OrderItem> orderTable;

    public CustomerController(Stage stage, Customer customer) {
        super(CustomerState.getInstance(), stage);
        state.setNextState(LoginState.getInstance());
        this.customer = customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void logout() {
        state.next(stage, null);
    }

    public void initShopTable() {
        List<String> shops = common.findAllProviders();
//        System.out.println(shops);
        shopTable.getItems().setAll(shops);
        shopTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                List<bean.Item> goods = common.findGoods(newValue);
                itemTable.getItems().setAll(goods.stream()
                        .map(item -> new Item(false, item.getName(), item.getPrice(), item.getDescription()))
                        .collect(Collectors.toList()));
            }
        });
    }

    public void initItemTable() {
        TableColumn<Item, Boolean> select = new TableColumn<>("选择");
        TableColumn<Item, String> name = new TableColumn<>("商品名称");
        TableColumn<Item, Double> price = new TableColumn<>("商品价格");
        TableColumn<Item, String> desc = new TableColumn<>("商品描述");
        select.setCellValueFactory(cell -> {
            Item item = cell.getValue();
            SimpleBooleanProperty prop = new SimpleBooleanProperty(item.isSelected());
            prop.addListener((observable, oldValue, newValue) -> item.setSelected(newValue));
            return prop;
        });
        select.setCellFactory(value -> new CheckBoxTableCell<>());
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        desc.setCellValueFactory(new PropertyValueFactory<>("desc"));
        itemTable.getColumns().setAll(select, name, price, desc);
    }

    public void initCartTable() {
        TableColumn<CartItem, Boolean> select = new TableColumn<>("选择");
        TableColumn<CartItem, String> pName = new TableColumn<>("商家名称");
        TableColumn<CartItem, String> gName = new TableColumn<>("商品名称");
        TableColumn<CartItem, Double> price = new TableColumn<>("商品价格");
        TableColumn<CartItem, Integer> amount = new TableColumn<>("商品数量");
        select.setCellValueFactory(cell -> {
            CartItem item = cell.getValue();
            SimpleBooleanProperty prop = new SimpleBooleanProperty(item.isSelected());
            prop.addListener((observable, oldValue, newValue) -> item.setSelected(newValue));
            return prop;
        });
        select.setCellFactory(value -> new CheckBoxTableCell<>());
        pName.setCellValueFactory(new PropertyValueFactory<>("pName"));
        gName.setCellValueFactory(new PropertyValueFactory<>("gName"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        amount.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        amount.setOnEditCommit(value -> {
            CartItem item = value.getRowValue();
            item.setAmount(Math.max(value.getNewValue(), 1));
            customer.updateCartItemAmount(item.getPName(), item.getGName(), item.getAmount());
            updateCartTable();
        });
        cartTable.getColumns().setAll(select, pName, gName, price, amount);
        updateCartTable();
    }

    public void updateCartTable() {
        List<bean.CartItem> cart = customer.findCart();
        cartTable.getItems().setAll(cart.stream()
                .map(item -> new CartItem(false, item.getPName(), item.getGName(), item.getPrice(), item.getAmount()))
                .collect(Collectors.toList()));
    }

    public void clearCart() {
        customer.clearCart();
        updateCartTable();
    }

    public void deleteCartItems() {
        List<bean.CartItem> cart = cartTable.getItems().stream()
                .filter(item -> item.isSelected())
                .map(item -> new bean.CartItem(item.getPName(), item.getGName(), item.getPrice(), item.getAmount()))
                .collect(Collectors.toList());
        customer.deleteCartItem(cart);
        updateCartTable();
    }

    public void initOrderTable() {
        TableColumn<OrderItem, String> pName = new TableColumn<>("商家名称");
        TableColumn<OrderItem, String> gName = new TableColumn<>("商品名称");
        TableColumn<OrderItem, Double> price = new TableColumn<>("商品单价");
        TableColumn<OrderItem, Integer> amount = new TableColumn<>("商品数量");
        TableColumn<OrderItem, Double> total = new TableColumn<>("结算价");
        TableColumn<OrderItem, Date> date = new TableColumn<>("订单日期");
        pName.setCellValueFactory(new PropertyValueFactory<>("pName"));
        gName.setCellValueFactory(new PropertyValueFactory<>("gName"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        total.setCellValueFactory(new PropertyValueFactory<>("total"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        orderTable.getColumns().setAll(pName, gName, price, amount, total, date);
        updateOrderTable();
    }

    public void updateOrderTable() {
        List<OrderItem> orders = customer.findOrders();
//        System.out.println(orders);
        orderTable.getItems().setAll(orders);
    }

    public void calculate() {
        List<bean.CartItem> cart = cartTable.getItems().stream()
                .filter(item -> item.isSelected())
                .map(item -> new bean.CartItem(item.pName, item.getGName(), item.getPrice(), item.getAmount()))
                .collect(Collectors.toList());
        customer.calculate(cart);
        updateCartTable();
        updateOrderTable();
    }

    public void initialize() {
        userName.setText(customer.getName());
        initItemTable();
        initShopTable();
        initCartTable();
        initOrderTable();
    }

    public void updateCart() {
        List<bean.CartItem> items = itemTable.getItems().stream()
                .filter(item -> item.isSelected())
                .map(item -> new bean.CartItem(shopTable.getSelectionModel().getSelectedItem(), item.name, item.price, 1))
                .collect(Collectors.toList());
        customer.addCartItem(items);
        updateCartTable();
    }

    @Data
    @AllArgsConstructor
    public static class Item {
        boolean selected;
        String name;
        double price;
        String desc;
    }

    @Data
    @AllArgsConstructor
    public static class CartItem {
        boolean selected;
        String pName;
        String gName;
        double price;
        int amount;
    }
}

