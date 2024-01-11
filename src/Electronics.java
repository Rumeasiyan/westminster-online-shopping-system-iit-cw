public class Electronics extends Product {
    private String brand;
    private int warrantyPeriod;

    public Electronics(String productId, String productName, int noOfAvailableItems, double price, String productType, String brand, int warrantyPeriod) {
        super(productId, productName, noOfAvailableItems, price, "Electronics");
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    @Override
    public void printProductDetails() {
        super.printProductDetails();
        System.out.println("Brand: " + this.brand);
        System.out.println("Warranty Period: " + this.warrantyPeriod);
    }
}
