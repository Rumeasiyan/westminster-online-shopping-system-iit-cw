import java.util.ArrayList;

public class WestminsterShoppingManager implements ShoppingManager{
    private final ArrayList<ArrayList<Product>> products;

    public WestminsterShoppingManager(ArrayList<ArrayList<Product>> products) {
        this.products = products;
    }

    @Override
    public void addProduct(Product product) {
        if (this.products.size() < 50) {
            if (product instanceof Electronics) {
                this.products.get(0).add(product);
                System.out.println("Product added successfully!");
            } else if (product instanceof Clothing) {
                this.products.get(1).add(product);
                System.out.println("Product added successfully!");
            }
        } else {
            System.out.println("Maximum limit is reached!");
        }
    }

    @Override
    public void removeProduct(String productId) {

    }

    @Override
    public void printProductList() {

    }

    @Override
    public void saveFile() {

    }

    @Override
    public void loadFile() {

    }
}
