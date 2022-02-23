package utcn.bll;

import java.io.Serializable;

/**
 * Clasa BaseProduct, este clasa care stocheaza informatii despre un produs normal ,care este format din titlu, rating, calorii, proteina, grasime, sodiu si pret si extinde interfata MenuItem.
 */
public class BaseProduct implements MenuItem, Serializable {
    private String title;
    private double rating;
    private int calories;
    private int protein;
    private int fat;
    private int sodium;
    private int price;

    public BaseProduct() {
    }

    public BaseProduct(String title, int rating, int calories, int protein, int fat, int sodium, int price) {
        this.title = title;
        this.rating = rating;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.sodium = sodium;
        this.price = price;
    }

    public BaseProduct(String[] strings) {
        this.title=strings[0];
        this.rating= Double.parseDouble(strings[1]);
        this.calories=Integer.parseInt(strings[2]);
        this.protein=Integer.parseInt(strings[3]);
        this.fat=Integer.parseInt(strings[4]);
        this.sodium=Integer.parseInt(strings[5]);
        this.price=Integer.parseInt(strings[6]);
    }

    @Override
    public int computePrice()
    {
        return this.price;
    }

    public String getTitle() {
        return title;
    }

    public double getRating() {
        return rating;
    }

    public int getCalories() {
        return calories;
    }

    public int getProtein() {
        return protein;
    }

    public int getFat() {
        return fat;
    }

    public int getSodium() {
        return sodium;
    }

    public int getPrice() {
        return price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    public void setPrice(int price) {
        this.price = price;
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
                " " + price ;
    }
}
