import java.util.ArrayList;

public class ShoppingCart {
    private final ArrayList<Electronics> electronics;
    private final ArrayList<Clothing> clothing;

    public ShoppingCart(ArrayList<Electronics> electronics, ArrayList<Clothing> clothing) {
        this.electronics = electronics;
        this.clothing = clothing;
    }

    public void addProduct(Product product) {
        if (product instanceof Electronics) {
            this.electronics.add((Electronics) product);
        } else if (product instanceof Clothing) {
            this.clothing.add((Clothing) product);
        }
    }

    public void removeProduct(String productId) {
        for (Electronics electronic : this.electronics) {
            if (electronic.getProductId().equals(productId)) {
                this.electronics.remove(electronic);
                return;
            }
        }
        for (Clothing cloth : this.clothing) {
            if (cloth.getProductId().equals(productId)) {
                this.clothing.remove(cloth);
                return;
            }
        }
        System.out.println("Product not found!");
    }

    public double calculateTotalPrice() {
        double totalPrice = 0;
        for (Electronics electronic : this.electronics) {
            totalPrice += electronic.getPrice();
        }
        for (Clothing cloth : this.clothing) {
            totalPrice += cloth.getPrice();
        }
        return totalPrice;
    }
}
