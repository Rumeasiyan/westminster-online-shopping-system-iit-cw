import java.util.ArrayList;

interface ShoppingManager {
    void addProduct(Product product);
    void removeProduct(String productId);
    void printProductList();
    void saveFile();
    void loadFile();
}
