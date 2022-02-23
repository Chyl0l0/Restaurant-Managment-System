package utcn.dll;

import utcn.bll.BaseProduct;
import utcn.bll.CompositeProduct;
import utcn.bll.MenuItem;
import utcn.bll.Order;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Clasa FileWriter, se ocupa cu lucrul de fisiere text, avand metodele readCSV care citeste un fisier CSV si creaza o lista de BaseProducts si generateBill, care genereaza o factura
 */
public class FileWriter {
    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    public static List<BaseProduct> readCSV(String path) {
        List<BaseProduct> list=null;
        try (Stream<String> stream = Files.lines(Paths.get(path))) {

            list= stream
                    .skip(1)
                    .map(line -> line.split(","))
                    .map(BaseProduct::new)
                    .filter(distinctByKey(BaseProduct::getTitle))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
    public static void generateBill(Order order, ArrayList<MenuItem> menuItems){
        File file= new File("ID"+order.getOrderID()+"_"+order.getClientID()+".txt");
        try {
            java.io.FileWriter fileWriter=new java.io.FileWriter(file);

            fileWriter.write(order.toString()+"\n");
            int price=0;
            for (MenuItem item:menuItems){
                fileWriter.write(item.toString()+"\n");
                price+=item.getPrice();
                if (item instanceof CompositeProduct){
                   fileWriter.write(((CompositeProduct) item).getCompositeProductsString());
                }
            }
            fileWriter.write("Total Price ="+ price + "\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}


