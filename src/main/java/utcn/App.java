package utcn;

import utcn.bll.DeliveryServiceProcessing;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utcn.dll.Serializator;
import utcn.presentation.*;


/**
 * JavaFX App
 */
public class App extends Application {
    DeliveryServiceProcessing deliveryServiceProcessing= Serializator.deserialize();

    @Override
    public void start(Stage stage) {
        Administrator administrator =new Administrator();
        Client client=new Client();
        Employee employee=new Employee();
        LoginWindow loginWindow=new LoginWindow();
        stage.setTitle("Food Delivery Manager");
        Scene[] scenes=new Scene[4];
        scenes[0] = new Scene(administrator.getRoot(),1280,1300);
        scenes[1] = new Scene(client.getRoot(),1280,1300);
        scenes[2] = new Scene(employee.getRoot(),500,500);
        scenes[3]  = new Scene(loginWindow.getRoot(),550,200);

        Controller controller= new Controller(deliveryServiceProcessing,administrator,client,employee,loginWindow,stage,scenes);
        Stage stageClient= new Stage();
        Stage stageEmployee= new Stage();
        Stage stageLogin= new Stage();

        stage.setScene(scenes[3]);
        stage.show();
    }
    @Override
    public void stop(){
        Serializator.serialize(deliveryServiceProcessing);
    }

    public static void main(String[] args) {
        launch();


    }

}