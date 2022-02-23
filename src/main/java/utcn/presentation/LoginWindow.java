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

public class LoginWindow {
    private final static int SIZE = 20;
    private VBox root;


    private Button buttonEmployee = new Button("LOG IN");
    private Button buttonRegister = new Button("REGISTER");
    private TextField fieldUser = new TextField();
    private PasswordField fieldPass = new PasswordField();


    public LoginWindow() {
        root = new VBox(20);
        root.setStyle("-fx-background-color: #292A2D");
        root.setPadding(new Insets(5));
        HBox bottom = new HBox(15);
        VBox menu = new VBox(15);
        root.setAlignment(Pos.CENTER);
        bottom.setAlignment(Pos.CENTER);
        GridPane gridPane = new GridPane();
        gridPane.setVgap(20);
        gridPane.setHgap(20);

        Text textTitle = new Text("Username");
        style(textTitle);
        gridPane.add(textTitle, 0, 0);
        style(fieldUser);
        gridPane.add(fieldUser, 1, 0);

        Text textRating = new Text("Password");
        style(textRating);
        gridPane.add(textRating, 0, 1);
        style(fieldPass);
        gridPane.add(fieldPass, 1, 1);


        style(buttonEmployee);
        style(buttonRegister);
        bottom.getChildren().addAll(buttonEmployee,buttonRegister);
        root.getChildren().addAll(gridPane, bottom);

    }

    public VBox getRoot() {
        return root;
    }

    private void style(Text text) {
        text.setFont(new Font(SIZE));
        text.setFill(Color.web("#CCCCCC"));
        text.setTextAlignment(TextAlignment.CENTER);
    }

    private void style(TextField field) {
        field.setPrefWidth(400);
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

    private void initTableView(TableView tableView) {
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
    public void addLoginHandler(EventHandler<ActionEvent> eventHandler) {
        buttonEmployee.setOnAction(eventHandler);
    }

    public void addRegisterHandler(EventHandler<ActionEvent> eventHandler) {
        buttonRegister.setOnAction(eventHandler);
    }

    public TextField getFieldUser() {
        return fieldUser;
    }

    public TextField getFieldPass() {
        return fieldPass;
    }
}
