import java.util.ArrayList;

public class User {
    private String userName;
    private String password;
    private ArrayList<Electronics> purchaseHistoryElectronics;
    private ArrayList<Clothing> purchaseHistoryClothing;

    public User(String userName, String password, ArrayList<Electronics> purchaseHistoryElectronics, ArrayList<Clothing> purchaseHistoryClothing) {
        this.userName = userName;
        this.password = password;
        this.purchaseHistoryElectronics = purchaseHistoryElectronics;
        this.purchaseHistoryClothing = purchaseHistoryClothing;
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

    public ArrayList<Electronics> getPurchaseHistoryElectronics() {
        return purchaseHistoryElectronics;
    }

    public void setPurchaseHistoryElectronics(ArrayList<Electronics> purchaseHistoryElectronics) {
        this.purchaseHistoryElectronics = purchaseHistoryElectronics;
    }

    public ArrayList<Clothing> getPurchaseHistoryClothing() {
        return purchaseHistoryClothing;
    }

    public void setPurchaseHistoryClothing(ArrayList<Clothing> purchaseHistoryClothing) {
        this.purchaseHistoryClothing = purchaseHistoryClothing;
    }
}
