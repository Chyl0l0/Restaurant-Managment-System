package utcn.presentation;

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

public class Client {
    private final static int SIZE = 20;
    private HBox root;

    private Button buttonAdd = new Button("ADD");
    private Button buttonSearch = new Button("SEARCH");
    private Button buttonOrder = new Button("ORDER");
    private Button buttonLogout = new Button("LOG OUT");
    private TextField fieldTitle = new TextField();
    private TextField fieldRating = new TextField();
    private TextField fieldCalories = new TextField();
    private TextField fieldProtein = new TextField();
    private TextField fieldFat = new TextField();
    private TextField fieldSodium = new TextField();
    private TextField fieldPrice = new TextField();
    private TextField fieldTotalPrice = new TextField();

    private TableView<MenuItem> products =new TableView<>();
    private TableView<MenuItem> orderedProducts =new TableView<>();
    public Client() {
        root=new HBox(20);
        root.setStyle("-fx-background-color: #292A2D");
        root.setPadding(new Insets(5));
        VBox leftSide = new VBox(35);
        HBox menu = new HBox(15);

        GridPane gridPane=new GridPane();
        gridPane.setVgap(20);
        gridPane.setHgap(20);

        Text textTitle =new Text("Title");
        style(textTitle);
        gridPane.add(textTitle,0,0);
        style(fieldTitle);
        gridPane.add(fieldTitle,1,0);

        Text textRating =new Text("Rating");
        style(textRating);
        gridPane.add(textRating,0,1);
        style(fieldRating);
        gridPane.add(fieldRating,1,1);

        Text textCalories =new Text("Calories");
        style(textCalories);
        gridPane.add(textCalories,0,2);
        style(fieldCalories);
        gridPane.add(fieldCalories,1,2);

        Text textProtein =new Text("Protein");
        style(textProtein);
        gridPane.add(textProtein,0,3);
        style(fieldProtein);
        gridPane.add(fieldProtein,1,3);

        Text textFat =new Text("Fat");
        style(textFat);
        gridPane.add(textFat,0,4);
        style(fieldFat);
        gridPane.add(fieldFat,1,4);

        Text textSodium =new Text("Sodium");
        style(textSodium);
        gridPane.add(textSodium,0,5);
        style(fieldSodium);
        gridPane.add(fieldSodium,1,5);

        Text textPrice =new Text("Price");
        style(textPrice);
        gridPane.add(textPrice,0,6);
        style(fieldPrice);
        gridPane.add(fieldPrice,1,6);

        Text textComposite =new Text("Total Price");
        style(textComposite);
        gridPane.add(textComposite,0,7);
        style(fieldTotalPrice);
        gridPane.add(fieldTotalPrice,1,7);
        fieldTotalPrice.setDisable(true);
        HBox editButtons = new HBox(10);
        gridPane.setAlignment(Pos.CENTER);

        style(buttonAdd);
        style(buttonOrder);
        style(buttonSearch);
        editButtons.getChildren().addAll(buttonAdd, buttonOrder, buttonSearch);
        style(buttonLogout);
        leftSide.getChildren().addAll(menu,gridPane,editButtons,buttonLogout);
        initTableView(products);
        initTableView(orderedProducts);
        VBox tables= new VBox(20);
        tables.setAlignment(Pos.CENTER);
        Text textProducts= new Text("Products");
        Text textOrders= new Text("Order");
        style(textProducts);
        style(textOrders);
        tables.getChildren().addAll(textProducts,products,textOrders,orderedProducts);
        root.getChildren().addAll(leftSide, tables);

    }

    public HBox getRoot() {
        return root;
    }
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
        tableView.setPrefWidth(800);
        tableView.setPrefHeight(600);
        tableView.setStyle("-fx-font-size: 14");
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TableColumn<MenuItem, String> colTitle = new TableColumn<>("Title");
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        TableColumn<MenuItem, Double> colRating = new TableColumn<>("Rating");
        colRating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        TableColumn<MenuItem, Integer> colCalories = new TableColumn<>("Calories");
        colCalories.setCellValueFactory(new PropertyValueFactory<>("calories"));
        TableColumn<MenuItem, Integer> colProtein = new TableColumn<>("Protein");
        colProtein.setCellValueFactory(new PropertyValueFactory<>("protein"));
        TableColumn<MenuItem, Integer> colFat = new TableColumn<>("Fat");
        colFat.setCellValueFactory(new PropertyValueFactory<>("fat"));
        TableColumn<MenuItem, Integer> colSodium = new TableColumn<>("Sodium");
        colSodium.setCellValueFactory(new PropertyValueFactory<>("sodium"));
        TableColumn<MenuItem, Integer> colPrice = new TableColumn<>("Price");
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        tableView.getColumns().addAll(colTitle, colRating, colCalories, colProtein, colFat, colSodium, colPrice);
    }
    public void addSearchHandler(EventHandler<ActionEvent> eventHandler) {
        buttonSearch.setOnAction(eventHandler);
    }
    public void addAddHandler(EventHandler<ActionEvent> eventHandler) {
        buttonAdd.setOnAction(eventHandler);
    }
    public void addOrderHandler(EventHandler<ActionEvent> eventHandler) {
        buttonOrder.setOnAction(eventHandler);
    }
    public void addLogoutHandler(EventHandler<ActionEvent> eventHandler) {buttonLogout.setOnAction(eventHandler); }

    public TextField getFieldTitle() {
        return fieldTitle;
    }
    public TextField getFieldRating() {
        return fieldRating;
    }
    public TextField getFieldCalories() {
        return fieldCalories;
    }
    public TextField getFieldProtein() {
        return fieldProtein;
    }
    public TextField getFieldFat() {
        return fieldFat;
    }
    public void setFieldFat(TextField fieldFat) {
        this.fieldFat = fieldFat;
    }
    public TableView getProducts() {
        return products;
    }
    public TableView getOrders() {
        return orderedProducts;
    }

    public TextField getFieldSodium() {
        return fieldSodium;
    }
    public TextField getFieldPrice() {
        return fieldPrice;
    }

    public TextField getFieldTotalPrice() {
        return fieldTotalPrice;
    }

    public void setFieldTotalPrice(TextField fieldTotalPrice) {
        this.fieldTotalPrice = fieldTotalPrice;
    }
}
