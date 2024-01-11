import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class WestminsterShoppingManager implements ShoppingManager {
    private final ArrayList<Product> products;

    private int totalProducts = 0;

    public WestminsterShoppingManager() {
        this.products = new ArrayList<>();
    }

    public WestminsterShoppingManager(ArrayList<Product> products) {
        this.products = products;
    }

    public void findTotalProducts() {
        for (Product product : this.products) {
            this.totalProducts += product.getNoOfAvailableItems();
        }
    }

    @Override
    public void addProduct(Product product) {
        findTotalProducts();
        if (this.totalProducts < 50) {
            this.products.add(product);
            System.out.println("Product added successfully!");
        } else {
            System.out.println("Maximum limit is reached!");
        }
    }

    @Override
    public void removeProduct(String productId) {
        for (Product product : this.products) {
            if (product.getProductId().equals(productId)) {
                System.out.println("Details of the product to be removed:");
                product.printProductDetails();
                this.products.remove(product);
                System.out.println("Product removed successfully!");
                findTotalProducts();
                System.out.println("Total number of products left in the system: " + this.totalProducts);
                return;
            }
        }
        System.out.println("Product not found!");
    }

    @Override
    public void printProductList() {
        Collections.sort(this.products);
        for (Product product : this.products) {
            product.printProductDetails();
        }
    }

    //reference: https://attacomsian.com/blog/java-write-object-to-file
    @Override
    public void saveFile() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("products.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            for (Product product : this.products) {
                objectOutputStream.writeObject(product);
            }
        } catch (IOException e) {
            System.out.println("Error occurred while saving the file!");
        }
    }

    //reference: https://attacomsian.com/blog/java-read-object-from-file
    //reference: https://stackoverflow.com/questions/20086784/java-how-to-read-file-into-arraylist-of-objects
    @Override
    public void loadFile() {
        try {
            FileInputStream fileInputStream = new FileInputStream("products.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            ArrayList<Product> products1 = (ArrayList<Product>) objectInputStream.readObject();

            products.clear();
            products.addAll(products1);
            System.out.println("File loaded successfully!");

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error occurred while loading the file!");
        }
    }
}
