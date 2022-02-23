package utcn.presentation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import utcn.bll.MenuItem;
import utcn.bll.Order;

import java.time.LocalDateTime;
import java.util.Observable;
import java.util.Observer;

public class Employee implements Observer {
    private final static int SIZE = 20;
    private VBox root;

    private Button buttonAdd = new Button("ADD");
    private Button buttonSearch = new Button("SEARCH");
    private Button buttonOrder = new Button("ORDER");
    private Button buttonLogout = new Button("LOG OUT");
    private ObservableList<Order> observableList= FXCollections.observableArrayList();

    private TableView<Order> orders =new TableView<>();

    public Employee() {
        root=new VBox(20);
        root.setStyle("-fx-background-color: #292A2D");
        root.setPadding(new Insets(5));
        VBox leftSide = new VBox(35);
        HBox menu = new HBox(15);
        initTableView(orders);
        orders.setItems(observableList);
        VBox tables= new VBox(20);
        tables.setAlignment(Pos.CENTER);
        Text textProducts= new Text("Products");
        Text textOrders= new Text("Order");
        style(textProducts);
        style(textOrders);
        tables.getChildren().addAll(textOrders,orders);
        style(buttonLogout);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll( tables,buttonLogout);

    }

    public VBox getRoot() {
        return root;
    }
    public void addLogoutHandler(EventHandler<ActionEvent> eventHandler) {buttonLogout.setOnAction(eventHandler); }
    private void style(Text text) {
        text.setFont(new Font(SIZE));
        text.setFill(Color.web("#CCCCCC"));
        text.setTextAlignment(TextAlignment.CENTER);
    }

    private void style(TextField field) {
        field.setPrefWidth(200);
        field.setPrefHeight(30);
        field.setStyle("-fx-background-color: #6e7179; -fx-text-fill: #ffffff");
        field.setFont(new Font(SIZE));

    }
    private void style(ComboBox comboBox) {
        comboBox.setPrefWidth(350);
        comboBox.setPrefHeight(30);
        comboBox.setStyle("-fx-background-color: #6e7179; -fx-text-fill: #ffffff ; -fx-font-size:20 ");
    }

    private void style(Button button) {
        button.setStyle("-fx-background-color: #53585c; -fx-text-fill: #ffffff ");
        button.setFont(new Font(SIZE));
        button.setOnMouseEntered(mouseEvent -> button.setStyle("-fx-background-color: #5AAEEE ; -fx-text-fill: #ffffff"));
        button.setOnMouseExited(mouseEvent -> button.setStyle("-fx-background-color: #53585c ; -fx-text-fill: #ffffff"));
        button.setPrefWidth(120);
        button.setPrefHeight(40);
    }
    private void initTableView(TableView tableView){
        tableView.setPrefWidth(500);
        tableView.setPrefHeight(450);
        tableView.setStyle("-fx-font-size: 14");
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TableColumn<Order, Integer> col1 = new TableColumn<>("Order ID");
        col1.setCellValueFactory(new PropertyValueFactory<>("orderID"));
        TableColumn<Order, Integer> col2 = new TableColumn<>("Client ID");
        col2.setCellValueFactory(new PropertyValueFactory<>("clientID"));
        TableColumn<Order, LocalDateTime> col3 = new TableColumn<>("Date");
        col3.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        tableView.getColumns().addAll(col1, col2, col3);
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("COMANDA NOUA");
        System.out.println((Order)arg);
        observableList.add((Order)arg);
        orders.refresh();
    }
}
