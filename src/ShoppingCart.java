import java.util.ArrayList;

public class ShoppingCart {
    private final ArrayList<Product> products;

    public ShoppingCart(ArrayList<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        if (this.products.size() < 10) {
            this.products.add(product);
            System.out.println("Product added successfully!");
        } else {
            System.out.println("Maximum limit is reached!");
        }
    }

    public void removeProduct(Product product) {
        this.products.remove(product);
        System.out.println("Product removed successfully!");
    }

    public double calculateTotalPrice() {
        double totalPrice = 0;
        for (Product product : this.products) {
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }
}
