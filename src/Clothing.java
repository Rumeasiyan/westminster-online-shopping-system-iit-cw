public class Clothing extends Product {
    private String size;
    private String color;

    public Clothing(String productId, String productName, int noOfAvailableItems, double price, String size, String color) {
        super(productId, productName, noOfAvailableItems, price, "Clothing");
        this.size = size;
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public void printProductDetails() {
        super.printProductDetails();
        System.out.println("Size: " + this.size);
        System.out.println("Color: " + this.color);
    }
}
