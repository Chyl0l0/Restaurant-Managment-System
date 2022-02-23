package utcn.bll;

import java.util.ArrayList;
import java.util.Map;

/**
 * @invariant isWellFormed
 */
public interface IDeliveryServiceProcessing {
    /**
     * imports products from csv
     * @param path
     * @pre path!=null
     *
     */
    public void importProducts(String path);

    /**
     * adds a new product
     * @param menuItem
     * @pre menuItem!=null
     */
    public void addProduct(MenuItem menuItem);

    /**
     * removes a product
     * @param title
     * @pre menuItem!=null
     */
    public void deleteProduct(String title);

    /**
     * edit a product
     * @param menuItem
     * @pre menuItem!=null
     */
    public void modifyProduct( MenuItem menuItem);

    /**
     * create a new composite product or, if it exists it adds the product to the existing one
     * @param title
     * @param productTitle
     * @pre title!=null
     * @pre productTitle!=null
     */
    public void createCompositeProduct(String title, String productTitle);

    /**
     * generates a report in a time interval
     * @param lowerBound
     * @param upperBound
     * @return ArrayList Order
     * @pre lowerBound > 0
     * @pre upperBound > 0
     * @pre lowerBound '>' upperBound
     * @post ArraList Order != null
     */
    public ArrayList<Order> generateTimeReport(int lowerBound,int upperBound);

    /**
     * generates an report based on the clients that have ordered more than a specified number of times and the value of the order was higher than a specified amount
     * @param ordersCount
     * @param value
     * @return ArrayList Order
     * @post ArraList Order != null
     */
    public ArrayList<Order> generateFreqOrders(int ordersCount,int value);

    /**
     * generates an report based on the products ordered more than a specified number of times so far
     * @param number
     * @return ArrayList Order
     * @post ArraList Order != null
     */
    public ArrayList<MenuItem> generateFreqProducts(int number);

    /**
     * generates an report based on the products ordered within a specified day with the number of times they have been ordered.
     * @param day integer
     * @return Map MenuItem,Long
     * @pre day bigger 1 and day less than7
     * @post Map MenuItem,Long
     */
    public Map<MenuItem,Long> generateProductsByDay(int day);

    /**
     * creates a new order
     * @param order
     * @param orderedItems
     * @pre order!=nul
     * @pre orderedItems!=null
     */
    public void createOrder(Order order, ArrayList<MenuItem> orderedItems);

    /**
     * searchs for products based on filters
     * @param filters
     * @return ArrayList MenuItem
     * @pre filters!=null
     */
    public ArrayList<MenuItem> searchProducts(MenuItem filters);


}
