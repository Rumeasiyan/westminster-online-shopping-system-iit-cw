import java.util.ArrayList;

interface ShoppingManager {
    void addProduct(Product product);
    void removeProduct(Product product);
    void printProductList();
    void saveFile();
    void loadFile();
}
