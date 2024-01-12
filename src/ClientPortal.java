import java.awt.*;
import javax.swing.*;

public class ClientPortal {
    public static void runGui() {
        JFrame frame = new JFrame();
        frame.setTitle("Westminster Shopping Manager");

        JLabel label = new JLabel("Select Product Category:");
        frame.add(label);

        JComboBox categoryDropdown = new JComboBox(new String[] {"All", "Electronics", "Clothing"});
        frame.add(categoryDropdown);

        frame.setSize(1300, 800);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
