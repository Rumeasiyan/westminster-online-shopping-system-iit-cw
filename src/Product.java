public abstract class Product implements Comparable<Product> {
    private String productId;
    private String productName;
    private int noOfAvailableItems;
    private double price;
    private String productType;

    public Product(String productId, String productName, int noOfAvailableItems, double price, String productType) {
        this.productId = productId;
        this.productName = productName;
        this.noOfAvailableItems = noOfAvailableItems;
        this.price = price;
        this.productType = productType;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getNoOfAvailableItems() {
        return noOfAvailableItems;
    }

    public void setNoOfAvailableItems(int noOfAvailableItems) {
        this.noOfAvailableItems = noOfAvailableItems;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public void printProductDetails() {
        System.out.println("Product ID: " + this.productId);
        System.out.println("Product Name: " + this.productName);
        System.out.println("No of Available Items: " + this.noOfAvailableItems);
        System.out.println("Price: " + this.price);
        System.out.println("Product Type: " + this.productType);
    }

    //reference: https://www.geeksforgeeks.org/how-to-sort-an-arraylist-of-objects-by-property-in-java/
    @Override
    public int compareTo(Product product) {
        return this.productId.compareTo(product.productId);
    }
}
