import java.util.ArrayList;

public class User {
    private String userName;
    private String password;
    private ArrayList<Product> purchaseHistory;

    public User(String userName, String password, ArrayList<Product> purchaseHistory) {
        this.userName = userName;
        this.password = password;
        this.purchaseHistory = purchaseHistory;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Product> getPurchaseHistory() {
        return purchaseHistory;
    }

    public void setPurchaseHistory(ArrayList<Product> purchaseHistory) {
        this.purchaseHistory = purchaseHistory;
    }
}
