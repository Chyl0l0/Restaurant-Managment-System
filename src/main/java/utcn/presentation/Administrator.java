package utcn.presentation;
import javafx.collections.FXCollections;
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
import javafx.event.ActionEvent;
import utcn.bll.MenuItem;

/**
 *
 */

public class Administrator {
    private final static int SIZE = 20;
    private HBox root;

    private Button buttonAdd = new Button("ADD");
    private Button buttonEdit= new Button("EDIT");
    private Button buttonDelete= new Button("DELETE");
    private Button buttonGenerate= new Button("GENERATE");
    private Button buttonImport= new Button("IMPORT");
    private Button buttonComposite= new Button("ADD COMPOSITE");
    private Button buttonLogout= new Button("LOG OUT");
    private TextField fieldTitle = new TextField();
    private TextField fieldRating = new TextField();
    private TextField fieldCalories = new TextField();
    private TextField fieldProtein = new TextField();
    private TextField fieldFat = new TextField();
    private TextField fieldSodium = new TextField();
    private TextField fieldPrice = new TextField();
    private TextField fieldComposite = new TextField();
    private TextField fieldVal1 = new TextField();
    private TextField fieldVal2 = new TextField();
    private TextField fieldImport = new TextField("products.csv");
    private TableView<MenuItem> tableView=new TableView<>();
    VBox tables= new VBox(20);
    String[] reports= {"time interval", "number of products ordered", "clients orders and  minimum amount", "day"};
    ComboBox<String> comboBox= new ComboBox(FXCollections.observableArrayList(reports));
    public Administrator() {
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

        Text textComposite =new Text("Composite");
        style(textComposite);
        gridPane.add(textComposite,0,7);
        style(fieldComposite);
        gridPane.add(fieldComposite,1,7);

        HBox editButtons = new HBox(10);
        gridPane.setAlignment(Pos.CENTER);

        style(buttonAdd);
        style(buttonDelete);
        style(buttonEdit);
        style(buttonComposite);
        editButtons.getChildren().addAll(buttonAdd,buttonDelete,buttonEdit);
        style(comboBox);

        Text textReports= new Text("Reports");
        leftSide.setAlignment(Pos.CENTER);
        style(textReports);
        textReports.setFont(new Font(25));
        style(buttonGenerate);
        buttonGenerate.setPrefWidth(150);

        GridPane gridReports=new GridPane();
        gridReports.setVgap(20);
        gridReports.setHgap(20);

        Text textVal1 =new Text("Value 1");
        style(textVal1);
        gridReports.add(textVal1,0,0);
        style(fieldVal1);
        gridReports.add(fieldVal1,1,0);

        Text textVal2 =new Text("Value 2");
        style(textVal2);
        gridReports.add(textVal2,0,1);
        style(fieldVal2);
        gridReports.add(fieldVal2,1,1);
        gridReports.setAlignment(Pos.CENTER);

        GridPane gridImport=new GridPane();
        gridImport.setVgap(20);
        gridImport.setHgap(20);
        Text textImport =new Text("Import");
        style(textImport);
        gridImport.add(textImport,0,0);
        style(fieldImport);
        gridImport.add(fieldImport,1,0);
        gridImport.setAlignment(Pos.CENTER);
        style(buttonImport);
        buttonComposite.setPrefWidth(200);
        style(buttonLogout);
        leftSide.setAlignment(Pos.CENTER);
        leftSide.getChildren().addAll(menu,gridPane,editButtons,buttonComposite,textReports,comboBox,gridReports,buttonGenerate ,gridImport,buttonImport,buttonLogout);
        TableView dummyTable= new TableView();
        dummyTable.setPrefWidth(800);
        dummyTable.setPrefHeight(600);
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
        tables.setAlignment(Pos.CENTER);
        Text textProducts= new Text("Products");
        Text textReports2 = new Text("Reports");
        style(textProducts);
        style(textReports2);
        tables.getChildren().addAll(textProducts,tableView,textReports2,dummyTable);

        tableView.getColumns().addAll(colTitle, colRating, colCalories, colProtein, colFat, colSodium, colPrice);
        root.getChildren().addAll(leftSide,tables);

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
    public void addEditHandler(EventHandler<ActionEvent> eventHandler) {
        buttonEdit.setOnAction(eventHandler);
    }
    public void addAddHandler(EventHandler<ActionEvent> eventHandler) {
        buttonAdd.setOnAction(eventHandler);
    }
    public void addDeleteHandler(EventHandler<ActionEvent> eventHandler) {
        buttonDelete.setOnAction(eventHandler);
    }
    public void addImportHandler(EventHandler<ActionEvent> eventHandler) {
        buttonImport.setOnAction(eventHandler);
    }
    public void addLogoutHandler(EventHandler<ActionEvent> eventHandler) {buttonLogout.setOnAction(eventHandler); }
    public void addCompositeHandler(EventHandler<ActionEvent> eventHandler) {buttonComposite.setOnAction(eventHandler); }
    void addGenerateHandler(EventHandler<ActionEvent> eventHandler) {
        buttonGenerate.setOnAction(eventHandler);
    }
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
    public TableView getTableView() {
        return tableView;
    }
    public TextField getFieldImport() {
        return fieldImport;
    }
    public TextField getFieldSodium() {
        return fieldSodium;
    }
    public TextField getFieldPrice() {
        return fieldPrice;
    }
    public TextField getFieldComposite() {
        return fieldComposite;
    }
    public TextField getFieldVal1() {
        return fieldVal1;
    }
    public TextField getFieldVal2() {
        return fieldVal2;
    }
    public VBox getTables() {
        return tables;
    }
    public ComboBox<String> getComboBox() {
        return comboBox;
    }
}
