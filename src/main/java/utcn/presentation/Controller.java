package utcn.presentation;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import utcn.bll.*;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Controller {
    private DeliveryServiceProcessing deliveryServiceProcessing;
    private Administrator administrator;
    private Client client;
    private Employee employee;
    private LoginWindow loginWindow;
    private ArrayList<MenuItem> menuItems;
    private ArrayList<MenuItem> currentOrder;
    private ClientAccount currentClient;
    private Stage stage;
    private Scene[] scenes;
    public Controller(DeliveryServiceProcessing deliveryServiceProcessing, Administrator administrator, Client client, Employee employee, LoginWindow loginWindow, Stage stage, Scene[] scenes) {
        this.deliveryServiceProcessing = deliveryServiceProcessing;
        this.administrator = administrator;
        this.client= client;
        this.employee=employee;
        deliveryServiceProcessing.addObserver(this.employee);
        this.currentOrder= new ArrayList<>();
        this.loginWindow=loginWindow;
        this.stage=stage;
        this .scenes= scenes;
        loginWindow.addLoginHandler(new LoginHandler());
        loginWindow.addRegisterHandler(new RegisterHandler());
        administrator.addImportHandler(new ImportHandler());
        administrator.addDeleteHandler(new DeleteHandler());
        administrator.addAddHandler(new AddHandler());
        administrator.addEditHandler(new EditHandler());
        administrator.addCompositeHandler(new CompositeHandler());
        administrator.addGenerateHandler(new GenerateHandler());
        administrator.addLogoutHandler(new LogoutHandler());
        client.addAddHandler(new AddToOrderHandler());
        client.addOrderHandler(new OrderHandler());
        client.addSearchHandler(new SearchHandler());
        client.addLogoutHandler(new LogoutHandler());
        employee.addLogoutHandler(new LogoutHandler());
        menuItems = deliveryServiceProcessing.getMenuItems();
        updateAllTables();
    }
    private class ImportHandler implements EventHandler{
        @Override
        public void handle(Event event) {
            deliveryServiceProcessing.importProducts(administrator.getFieldImport().getText());
            menuItems = deliveryServiceProcessing.getMenuItems();
            updateAllTables();
        }
    }
    private class DeleteHandler implements EventHandler{
        @Override
        public void handle(Event event) {
            deliveryServiceProcessing.deleteProduct(administrator.getFieldTitle().getText());
            menuItems=deliveryServiceProcessing.getMenuItems();
            updateAllTables();
        }
    }
    private class AddHandler implements EventHandler{
        @Override
        public void handle(Event event) {
            String[] product=exctractProductString();
            deliveryServiceProcessing.addProduct(new BaseProduct(product));
            menuItems=deliveryServiceProcessing.getMenuItems();
            updateAllTables();
        }
    }
    private class EditHandler implements EventHandler{
        @Override
        public void handle(Event event) {
            String[] product=exctractProductString();
            deliveryServiceProcessing.modifyProduct(new BaseProduct(product));
            menuItems=deliveryServiceProcessing.getMenuItems();
            updateAllTables();
        }
    }
    private class CompositeHandler implements EventHandler{
        @Override
        public void handle(Event event) {
            deliveryServiceProcessing.createCompositeProduct(administrator.getFieldComposite().getText(),administrator.getFieldTitle().getText());;
            menuItems=deliveryServiceProcessing.getMenuItems();
            updateAllTables();
        }
    }
    private class AddToOrderHandler implements EventHandler{
        @Override
        public void handle(Event event) {
            MenuItem menuItem= deliveryServiceProcessing.findProductByTitle(client.getFieldTitle().getText());
            if (menuItem != null) {
                currentOrder.add(menuItem);
                updateTable(client.getOrders(),currentOrder);
                client.getOrders().refresh();
                int price=0;
                for (MenuItem item : currentOrder){
                    price+=item.computePrice();
                }
                client.getFieldTotalPrice().setText(String.valueOf(price));
            }
        }
    }
    private class OrderHandler implements EventHandler{
        @Override
        public void handle(Event event) {
            if (currentOrder.size()!=0){
                deliveryServiceProcessing.createOrder(new Order(currentClient.getClientID(), LocalDateTime.now()),currentOrder);
            }
            currentOrder.clear();
            updateTable(client.getOrders(),currentOrder);
            client.getFieldTotalPrice().setText("");
            client.getOrders().refresh();
        }
    }
    private class SearchHandler implements EventHandler{
        @Override
        public void handle(Event event) {
            String[] product= new String[7];
            product[0]=client.getFieldTitle().getText();
            product[1]=client.getFieldRating().getText().equals("")?"-1.0":client.getFieldRating().getText();
            product[2]=client.getFieldCalories().getText().equals("")?"-1":client.getFieldCalories().getText();
            product[3]=client.getFieldProtein().getText().equals("")?"-1":client.getFieldProtein().getText();
            product[4]=client.getFieldFat().getText().equals("")?"-1":client.getFieldFat().getText();
            product[5]=client.getFieldSodium().getText().equals("")?"-1":client.getFieldSodium().getText();
            product[6]=client.getFieldPrice().getText().equals("")?"-1":client.getFieldPrice().getText();
            ArrayList<MenuItem> list= deliveryServiceProcessing.searchProducts(new BaseProduct(product));
            updateTable(client.getProducts(),list);
            client.getProducts().refresh();
        }
    }
    private class GenerateHandler implements EventHandler{
        @Override
        public void handle(Event event) {
            if (administrator.getTables().getChildren().size()==4) administrator.getTables().getChildren().remove(3);
            switch (administrator.getComboBox().getValue()){
                case "time interval":
                    ArrayList<Order> orderArrayList= deliveryServiceProcessing.generateTimeReport(Integer.parseInt(administrator.getFieldVal1().getText()),Integer.parseInt(administrator.getFieldVal2().getText()));
                    if (orderArrayList.size()>0) administrator.getTables().getChildren().add(createGenericTable(orderArrayList));
                    break;
                case "number of products ordered":
                    ArrayList<MenuItem>list = deliveryServiceProcessing.generateFreqProducts(Integer.parseInt(administrator.getFieldVal1().getText()));
                    if (list.size()>0) administrator.getTables().getChildren().add(createGenericTable(list));
                    break;
                case "clients orders and  minimum amount":
                    ArrayList<Order> orderArrayList2=deliveryServiceProcessing.generateFreqOrders(Integer.parseInt(administrator.getFieldVal1().getText()),Integer.parseInt(administrator.getFieldVal2().getText()));
                    if (orderArrayList2.size()>0) administrator.getTables().getChildren().add(createGenericTable(orderArrayList2));
                    break;
                case "day":
                    Map<MenuItem,Long> map=deliveryServiceProcessing.generateProductsByDay(Integer.parseInt(administrator.getFieldVal1().getText()));
                    if (map.size()>0) {
                        ArrayList<Report> reports=new ArrayList<>();
                        for (Map.Entry<MenuItem,Long> m:map.entrySet())
                            reports.add(new Report(m.getKey(), m.getValue()));
                        TableView tableView= createGenericTable(reports);
                        tableView.getColumns().remove(8);
                        administrator.getTables().getChildren().add(tableView);
                    }
                    break;
            }
        }
    }
    private void updateAllTables(){
        updateTable(administrator.getTableView(),menuItems);
        updateTable(client.getProducts(),menuItems);
        administrator.getTableView().refresh();
        client.getProducts().refresh();
    }
    private void updateTable(TableView tableView, ArrayList<MenuItem> products) {
        ObservableList observableList = FXCollections.observableArrayList(products);
        tableView.setItems(observableList);
    }
    private String[] exctractProductString(){
        String[] product= new String[7];
        product[0]=administrator.getFieldTitle().getText();
        product[1]=administrator.getFieldRating().getText().equals("")?"-1.0":administrator.getFieldRating().getText();
        product[2]=administrator.getFieldCalories().getText().equals("")?"-1":administrator.getFieldCalories().getText();
        product[3]=administrator.getFieldProtein().getText().equals("")?"-1":administrator.getFieldProtein().getText();
        product[4]=administrator.getFieldFat().getText().equals("")?"-1":administrator.getFieldFat().getText();
        product[5]=administrator.getFieldSodium().getText().equals("")?"-1":administrator.getFieldSodium().getText();
        product[6]=administrator.getFieldPrice().getText().equals("")?"-1":administrator.getFieldPrice().getText();
        return  product;
    }
    private static <T,S> TableColumn<T,S> createTableColumn(Class<S> type, Field field, String text) {
        TableColumn<T, S> col =  new TableColumn<T,S>(text);
        col.setCellValueFactory(cellData -> {
            try { ;
                field.setAccessible(true);
                SimpleObjectProperty<S> property = new SimpleObjectProperty<S>((S)(field.get(cellData.getValue())));
                return property;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return col ;
    }
    private TableView createGenericTable(Object object ) {
        TableView tableView=new TableView();
        List<?> objects = (List<?>) object;
        Field[] fields = objects.get(0).getClass().getDeclaredFields();

        if (tableView.getColumns().size()==0) {
            for (Field field : fields) {
                field.setAccessible(true);
                Object value;
                try {
                    tableView.getColumns().add(createTableColumn(objects.getClass(), field, field.getName()));
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
            }
        }
        ObservableList observableList = FXCollections.observableList(objects);
        tableView.setItems(observableList);
        tableView.setPrefWidth(800);
        tableView.setPrefHeight(600);
        tableView.setStyle("-fx-font-size: 14");
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        return tableView;
    }
    private class LoginHandler implements EventHandler{
        @Override
        public void handle(Event event) {
            if (loginWindow.getFieldUser().getText().equals(deliveryServiceProcessing.getAdminAccount().getUsername()) && loginWindow.getFieldPass().getText().equals(deliveryServiceProcessing.getAdminAccount().getPassword())) {
                stage.setScene(scenes[0]);
            }
            else if (loginWindow.getFieldUser().getText().equals(deliveryServiceProcessing.getEmployeeAccount().getUsername()) && loginWindow.getFieldPass().getText().equals(deliveryServiceProcessing.getEmployeeAccount().getPassword())) {
                stage.setScene(scenes[2]);
            }
            else{
                for (ClientAccount clientAccount: deliveryServiceProcessing.getClientAccounts()){
                    if (clientAccount.getUsername().equals(loginWindow.getFieldUser().getText()) && clientAccount.getPassword().equals(loginWindow.getFieldPass().getText())) {
                        currentClient=clientAccount;
                        stage.setScene(scenes[1]);
                        break;
                    }
                }
            }
        }
    }
    private class RegisterHandler implements EventHandler{
        @Override
        public void handle(Event event) {
            deliveryServiceProcessing.getClientAccounts().add(new ClientAccount(loginWindow.getFieldUser().getText(),loginWindow.getFieldPass().getText()));
        }
    }
    private class LogoutHandler implements EventHandler{
        @Override
        public void handle(Event event) {
            stage.setScene(scenes[3]);

        }
    }
    private class Report{
        private String title;
        private double rating;
        private int calories;
        private int protein;
        private int fat;
        private int sodium;
        private int price;
        private long bought;
        public Report(MenuItem menuItem, long bought) {
            title=menuItem.getTitle();
            rating=menuItem.getRating();
            calories=menuItem.getCalories();
            protein=menuItem.getProtein();
            fat=menuItem.getFat();
            sodium=menuItem.getSodium();
            price=menuItem.getPrice();
            this.bought=bought;
        }
    }
}
