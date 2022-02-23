package utcn.dll;

import utcn.bll.ClientAccount;
import utcn.bll.DeliveryServiceProcessing;
import utcn.bll.Order;

import java.io.*;

/**
 * Clasa Serializator, se ocupa cu serializarea serviciul de procesare a livrarilor si deserializarea lor.
 */
public class Serializator  {

    public static void serialize(DeliveryServiceProcessing deliveryServiceProcessing){

        try {
            FileOutputStream file = new FileOutputStream ("f.txt");
            ObjectOutputStream out = new ObjectOutputStream (file);
            out.writeObject(deliveryServiceProcessing);
            out.writeInt(ClientAccount.getIdGen());
            out.writeInt(Order.getIdGenerator());
            out.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static DeliveryServiceProcessing deserialize(){
        DeliveryServiceProcessing deliveryServiceProcessing= new DeliveryServiceProcessing();
        try {
            FileInputStream file = new FileInputStream ("f.txt");
            ObjectInputStream in = new ObjectInputStream (file);
            deliveryServiceProcessing=(DeliveryServiceProcessing)in.readObject();
            ClientAccount.setIdGen(in.readInt());
            Order.setIdGenerator(in.readInt());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return deliveryServiceProcessing;
    }
}
