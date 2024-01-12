import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class WestminsterShoppingManager implements ShoppingManager {
    private static ArrayList<Product> products;

    private int totalProducts = 0;

    public WestminsterShoppingManager() {
        products = new ArrayList<>();
    }

    public WestminsterShoppingManager(ArrayList<Product> products) {
        WestminsterShoppingManager.products = products;
    }

    public void findTotalProducts() {
        this.totalProducts = 0;
        for (Product product : products) {
            this.totalProducts += product.getNoOfAvailableItems();
        }
    }

    public int getTotalProducts() {
        findTotalProducts();
        return totalProducts;
    }

    @Override
    public void addProduct(Product product) {
        findTotalProducts();
        if (this.totalProducts + product.getNoOfAvailableItems() < 50) {
            products.add(product);
            System.out.println("Product added successfully!");
        } else {
            System.out.println("Can't add the Product. Maximum limit is reached!");
        }
    }

    public static ArrayList<Product> getProducts() {
        return products;
    }

    @Override
    public void removeProduct(String productId) {
        for (Product product : products) {
            if (product.getProductId().equals(productId)) {
                System.out.println("Details of the product to be removed:");
                product.printProductDetails();
                products.remove(product);
                System.out.println("Product removed successfully!");
                findTotalProducts();
                System.out.println("\nTotal number of products left in the system: " + this.totalProducts);
                return;
            }
        }
        System.out.println("Product not found!");
    }

    @Override
    public void printProductList() {
        if (products.isEmpty()) {
            System.out.println("\nNo products found!");
            return;
        }
        Collections.sort(products);
        for (Product product : products) {
            product.printProductDetails();
        }
    }

    @Override
    public void saveFile() {
        try {
            FileWriter fileWriter = new FileWriter("products.txt");
            //clear the file
            fileWriter.write("");

            for (Product product : products) {
                String content = product.contentStoreFile();
                fileWriter.write(String.valueOf(content) + System.lineSeparator());
            }

            fileWriter.close();

            System.out.println("File saved successfully!");
        } catch (IOException e) {
            System.out.println("Error occurred while saving the file!");
        }
    }

    @Override
    public void loadFile() {
        products.clear();
        try {
            File file = new File("products.txt");
            Scanner fileReader = new Scanner(file);

            while (fileReader.hasNextLine()) {
                String productId = fileReader.nextLine();
                String productName = fileReader.nextLine();
                String noOfAvailableItems = fileReader.nextLine();
                String price = fileReader.nextLine();
                String productType = fileReader.nextLine();
                int noOfAvailableItemsInt = Integer.parseInt(noOfAvailableItems);
                double priceDouble = Double.parseDouble(price);

                if (productType.equals("Electronics")) {
                    String brand = fileReader.nextLine();
                    String warrantyPeriod = fileReader.nextLine();
                    if (fileReader.hasNextLine()) {
                        fileReader.nextLine();
                    }
                    int warrantyPeriodInt = Integer.parseInt(warrantyPeriod);
                    addProduct(new Electronics(productId, productName, noOfAvailableItemsInt, priceDouble, brand, warrantyPeriodInt));
                } else if (productType.equals("Clothing")) {
                    String color = fileReader.nextLine();
                    String size = fileReader.nextLine();
                    if (fileReader.hasNextLine()) {
                        fileReader.nextLine();
                    }
                    addProduct(new Clothing(productId, productName, noOfAvailableItemsInt, priceDouble, color, size));
                }
            }

        } catch (IOException e) {
            System.out.println("Error occurred while loading the file!");
        }
    }

    private void addToList() {
    }
}
