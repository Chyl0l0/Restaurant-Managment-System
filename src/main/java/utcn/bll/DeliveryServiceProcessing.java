package utcn.bll;
import utcn.dll.FileWriter;
import java.io.Serializable;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
/**
 * Clasa DeliveryServiceProcessing implementeaza interfata IDeliveryServiceProcessing ce asigura logica de procesare a datelor (produse, comenzi), ea implementeaza algoritmica necesara manipularii, filtrarii modificarii, adaugarii si stergerii de produse si comenzi.
 * @invariant isWellFormed
 */
public class DeliveryServiceProcessing extends Observable implements IDeliveryServiceProcessing, Serializable {
    Map<Order, ArrayList<MenuItem>> orders;
    ArrayList<MenuItem> menuItems;
    ArrayList<ClientAccount> clientAccounts;
    AdminAccount adminAccount;
    EmployeeAccount employeeAccount;

    public DeliveryServiceProcessing() {

        adminAccount=new AdminAccount();
        employeeAccount=new EmployeeAccount();
        clientAccounts=new ArrayList<>();
        menuItems = new ArrayList<>();
        orders = new HashMap<>();
        clientAccounts = new ArrayList<>();

    }
    @Override
    public void importProducts(String path) {
        assert path!=null : "Path is null";
        assert isWellFormed();
        menuItems.clear();
        menuItems.addAll(FileWriter.readCSV(path));
        assert isWellFormed();
    }

    @Override
    public void addProduct(MenuItem menuItem) {
        assert menuItem!=null : "menuItem is null";
        assert isWellFormed();
        menuItems.add(menuItem);
        assert isWellFormed();
    }

    @Override
    public void deleteProduct(String title) {
        assert title!=null :"Title is null";
        assert isWellFormed();
        menuItems.removeIf(p -> p.getTitle()
                .replaceAll("\\s", "")
                .equals(title.replaceAll("\\s", "")));
        assert isWellFormed();
    }

    @Override
    public void modifyProduct(MenuItem menuItem) {
        assert menuItem!=null : "menuItem is null";
        assert isWellFormed();
        MenuItem item = findProductByTitle(menuItem.getTitle());
        if (item != null) {
            if (menuItem.getPrice() != -1)
                item.setPrice(menuItem.getPrice());
            if (menuItem.getCalories() != -1)
                item.setCalories(menuItem.getCalories());
            if (menuItem.getProtein() != -1)
                item.setProtein(menuItem.getProtein());
            if (menuItem.getFat() != -1)
                item.setFat(menuItem.getFat());
            if (menuItem.getRating() != -1.0)
                item.setRating(menuItem.getRating());
            if (menuItem.getSodium() != -1)
                item.setSodium(menuItem.getSodium());
        }
        assert isWellFormed();
    }

    @Override
    public void createCompositeProduct(String title, String productTitle) {
        assert title!=null : "title is null";
        assert productTitle!=null : "productTitle is null";
        assert isWellFormed();
        MenuItem composite = findProductByTitle(title);
        MenuItem productToAdd = findProductByTitle(productTitle);
        if (composite == null) {
            composite = new CompositeProduct(title);
            menuItems.add(composite);
        }
        if (composite instanceof CompositeProduct && productToAdd != null) {
            ((CompositeProduct) composite).addProduct(productToAdd);
        }
        assert isWellFormed();
    }

    @Override
    public ArrayList<Order> generateTimeReport(int lowerBound, int upperBound) {
        assert lowerBound > 0 || upperBound > 0 || lowerBound < upperBound : "Bounds not correct";
        assert isWellFormed();
        return orders.keySet().stream()
                .filter(p -> p.getOrderDate().getHour() >= lowerBound && p.getOrderDate().getHour() <= upperBound)
                .collect(Collectors.toCollection(ArrayList::new));
    }


    @Override
    public ArrayList<MenuItem> generateFreqProducts(int number) {
        assert isWellFormed();
        Map<MenuItem, Long> map = orders.values().stream()
                .flatMap(List::stream)
                .collect(Collectors.toList())
                .stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        ArrayList<MenuItem> list = map.entrySet()
                .stream()
                .filter(p-> p.getValue() >= number)
                .map(Map.Entry::getKey)
                .collect(Collectors.toCollection(ArrayList::new));
        System.out.println(list);
        assert isWellFormed();
        return list;
    }

    @Override
    public Map<MenuItem, Long> generateProductsByDay(int day) {
        assert day>= 1 && day <=7: "Not a week day";
        Map<Order,ArrayList<MenuItem>> mapFilteredByDay= orders.entrySet()
                .stream()
                .filter(p-> p.getKey().getOrderDate().getDayOfWeek().getValue()==day)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        Map<MenuItem, Long> map = mapFilteredByDay.values().stream()
                .flatMap(List::stream)
                .collect(Collectors.toList())
                .stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(map);
        assert isWellFormed();
        return map;
    }

    @Override
    public ArrayList<Order> generateFreqOrders(int ordersCount, int value) {
        assert isWellFormed();
        Map<Integer,Long> map=orders.keySet()
                .stream()
                .collect(Collectors.groupingBy(p->p.getClientID(),Collectors.counting()));
        ArrayList<Order> list=orders.entrySet()
                .stream()
                .filter(p-> calculateOrderPrice(p.getValue())>=value && map.get(p.getKey().getClientID())>=ordersCount)
                .map(Map.Entry::getKey)
                .collect(Collectors.toCollection(ArrayList::new));

        System.out.println(list);
        System.out.println(map);
        assert isWellFormed();
        return list;
    }

    public MenuItem findProductByTitle(String title) {
        return menuItems.stream().filter(p -> p.getTitle()
                .replaceAll("\\s", "")
                .equals(title.replaceAll("\\s", "")))
                .findAny()
                .orElse(null);
    }
    public int calculateOrderPrice(ArrayList<MenuItem> menuItems){
        int price=0;
        for (MenuItem menuItem: menuItems){
            price+=menuItem.getPrice();
        }
        return price;
    }
    @Override
    public void createOrder(Order order, ArrayList<MenuItem> orderedItems) {
        assert order!=null :"order is null";
        assert orderedItems!=null :"orderedItems is null";
        assert isWellFormed();
        ArrayList<MenuItem> list = new ArrayList<>(orderedItems);
        orders.put(order, list);
        setChanged();
        notifyObservers(order);
        for (Map.Entry<Order, ArrayList<MenuItem>>entry : orders.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
        FileWriter.generateBill(order, orderedItems);
        assert isWellFormed();

    }

    @Override
    public ArrayList<MenuItem> searchProducts(MenuItem filters) {
        assert filters!=null : "filters is null";
        assert isWellFormed();
        return menuItems.stream()
                .filter(p -> filters.getTitle().equals("") || p.getTitle().toLowerCase().contains(filters.getTitle().toLowerCase()))
                .filter(p -> filters.getRating() == -1.0 || filters.getRating() == p.getRating())
                .filter(p -> filters.getCalories() == -1 || filters.getCalories() == p.getCalories())
                .filter(p -> filters.getProtein() == -1 || filters.getProtein() == p.getProtein())
                .filter(p -> filters.getFat() == -1 || filters.getFat() == p.getFat())
                .filter(p -> filters.getSodium() == -1 || filters.getSodium() == p.getSodium())
                .filter(p -> filters.getPrice() == -1 || filters.getPrice() == p.getPrice())
                .collect(Collectors.toCollection(ArrayList::new));

    }

    public Map<Order, ArrayList<MenuItem>> getOrders() {
        assert isWellFormed();
        return orders;
    }

    public ArrayList<MenuItem> getMenuItems() {
        assert isWellFormed();
        return menuItems;
    }

    public ArrayList<ClientAccount> getClientAccounts() {
        assert isWellFormed();
        return clientAccounts;
    }

    public AdminAccount getAdminAccount() {
        assert isWellFormed();
        return adminAccount;
    }

    public EmployeeAccount getEmployeeAccount() {
        assert isWellFormed();
        return employeeAccount;
    }
    protected boolean isWellFormed(){
        if (orders==null)
            return false;
        if (menuItems==null)
            return false;
        if (clientAccounts==null)
            return false;
        if (adminAccount==null)
            return false;
        if (employeeAccount==null)
            return false;
        return true;
    }

    @Override
    public String toString() {
        assert isWellFormed();
        return "DeliveryServiceProcessing{" +
                "orders=" + orders +
                ", menuItems=" + menuItems +
                ", clientAccounts=" + clientAccounts +
                ", adminAccount=" + adminAccount +
                ", employeeAccount=" + employeeAccount +
                '}';
    }
}
