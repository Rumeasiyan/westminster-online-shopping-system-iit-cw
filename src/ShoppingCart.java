import java.util.ArrayList;

public class ShoppingCart {
    private static ArrayList<Product> products = new ArrayList<>();

    public ShoppingCart(ArrayList<Product> products) {
        ShoppingCart.products = products;
    }

    public static void addProduct(Product product) {
        products.add(product);
        System.out.println("Product added successfully!");
    }

    public static void removeProduct(Product product) {
        products.remove(product);
        System.out.println("Product removed successfully!");
    }

    public static ArrayList<Product> getProducts() {
        return products;
    }

    public static double calculateTotalPrice() {
        double totalPrice = 0;
        for (Product product : products) {
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }
}
