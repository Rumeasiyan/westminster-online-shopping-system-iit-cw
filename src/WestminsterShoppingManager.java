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
        for (ArrayList<Product> productArrayList : this.products) {
            for (Product product : productArrayList) {
                if (product.getProductId().equals(productId)) {
                    productArrayList.remove(product);
                    System.out.println("Product removed successfully!");
                    return;
                }
            }
        }
        System.out.println("Product not found!");
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
