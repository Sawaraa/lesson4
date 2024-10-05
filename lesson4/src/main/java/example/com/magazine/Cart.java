package example.com.magazine;

public class Cart {

    private String title;
    private double price;

    public Cart(String title, double price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }
}
