package utcn.bll;

public interface MenuItem {
    public int computePrice();

    public String getTitle();
    public double getRating();
    public int getCalories();
    public int getProtein();
    public int getFat();
    public int getSodium();
    public int getPrice();

    public void setTitle(String title);
    public void setRating(double rating);
    public void setCalories(int calories);
    public void setProtein(int protein);
    public void setFat(int fat);
    public void setSodium(int sodium);
    public void setPrice(int price);
}
