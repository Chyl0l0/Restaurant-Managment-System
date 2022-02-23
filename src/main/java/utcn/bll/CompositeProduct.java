package utcn.bll;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clasa BaseProduct, este clasa care stocheaza informatii despre un produs normal ,care este format din titlu, rating, calorii, proteina, grasime, sodiu si pret si extinde interfata MenuItem.
 */
public class CompositeProduct implements MenuItem, Serializable {
    private String title;
    private double rating;
    private int calories;
    private int protein;
    private int fat;
    private int sodium;
    private int price;

    private ArrayList<MenuItem> baseProducts;

    public CompositeProduct() {
    }
    public CompositeProduct(String title, ArrayList<MenuItem> baseProducts) {
        this.title = title;
        this.baseProducts = baseProducts;
        this.price = computePrice();
    }
    public CompositeProduct(String title) {
        this.title = title;
        this.baseProducts = new ArrayList<MenuItem>();
    }
    public void addProduct(MenuItem item){
        baseProducts.add(item);
        computePrice();
        computeMacros();
    }

    public void computeMacros(){
        this.rating=0;
        this.calories=0;
        this.protein=0;
        this.fat=0;
        this.sodium=0;
        for (MenuItem item: baseProducts) {
            rating +=item.getRating();
            calories +=item.getCalories();
            protein +=item.getProtein();
            fat +=item.getFat();
            sodium +=item.getSodium();
        }
        rating/=baseProducts.size();
    }
    @Override
    public int computePrice() {
        int priceSum =0;
        for (MenuItem item: baseProducts) {
            priceSum +=item.getPrice();
        }
        this.price= priceSum;
        return this.price;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public double getRating() {
        return rating;
    }

    @Override
    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public int getCalories() {
        return calories;
    }

    @Override
    public void setCalories(int calories) {
        this.calories = calories;
    }

    @Override
    public int getProtein() {
        return protein;
    }

    @Override
    public void setProtein(int protein) {
        this.protein = protein;
    }

    @Override
    public int getFat() {
        return fat;
    }

    @Override
    public void setFat(int fat) {
        this.fat = fat;
    }

    @Override
    public int getSodium() {
        return sodium;
    }

    @Override
    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public void setPrice(int price) {
        this.price = price;
    }

    public ArrayList<MenuItem> getBaseProducts() {
        return baseProducts;
    }

    public void setBaseProducts(ArrayList<MenuItem> baseProducts) {
        this.baseProducts = baseProducts;
    }

    public String getCompositeProductsString(){
        StringBuilder stringBuilder=new StringBuilder();
        for (MenuItem menuItem : baseProducts){
            stringBuilder.append("\t").append(menuItem).append("\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        return
                title+
                        " " + rating +
                        " " + calories +
                        " " + protein +
                        " " + fat +
                        " " + sodium +
                        " " + price +
                        " " + baseProducts;
    }

}
