import java.util.ArrayList;

public class WestminsterShoppingManager implements ShoppingManager{
    private final ArrayList<Electronics> electronicsProducts;
    private final ArrayList<Clothing> clothingProducts;

    public WestminsterShoppingManager(ArrayList<Electronics> electronicsProducts, ArrayList<Clothing> clothingProducts) {
        this.electronicsProducts = electronicsProducts;
        this.clothingProducts = clothingProducts;
    }

    @Override
    public void addProduct(Product product) {
        if (product instanceof Electronics) {
            if (this.electronicsProducts.size() < 50) {
                this.electronicsProducts.add((Electronics) product);
                System.out.println("Product added successfully!");
            } else {
                System.out.println("Maximum limit is reached!");
            }
        } else if (product instanceof Clothing) {
            this.clothingProducts.add((Clothing) product);
        }
    }

    @Override
    public void removeProduct(String productId) {
        for (Product product : this.products) {
            if (product.getProductId().equals(productId)) {
                this.products.remove(product);
                System.out.println("Product removed successfully!");
                return;
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
