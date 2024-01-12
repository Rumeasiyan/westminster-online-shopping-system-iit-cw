import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

//reference: https://www.youtube.com/watch?v=Kmgo00avvEw

public class ClientPortal {
    //    public static void runGui() {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("Westminster Shopping Manager");
        frame.setLayout(new FlowLayout());

        JPanel panelOne = new JPanel();
        panelOne.setLayout(new FlowLayout());

        JLabel label = new JLabel("Select Product Category:");
        panelOne.add(label);

        //reference: https://www.geeksforgeeks.org/java-swing-jcombobox-examples/
        JComboBox categoryDropdown = new JComboBox(new String[]{"All", "Electronics", "Clothing"});
        panelOne.add(categoryDropdown);

        JPanel panelTwo = new JPanel();

        JPanel panelThree = new JPanel();
        panelThree.setLayout(new FlowLayout());

        final String[] selectedCategory = {"All"};
        categoryDropdown.addActionListener(e -> {
            selectedCategory[0] = (String) categoryDropdown.getSelectedItem();
            panelThree.removeAll();
            JTable table = createTable(selectedCategory);
            JScrollPane scrollPane = new JScrollPane(table);
            panelThree.add(scrollPane);
            panelThree.revalidate();
            panelThree.repaint();
        });

        JButton button = new JButton("Shopping Cart");
        button.setLayout(new FlowLayout(FlowLayout.TRAILING));
        panelTwo.setLayout(new BorderLayout());
        panelTwo.add(button);

        JTable table = createTable(selectedCategory);
        JScrollPane scrollPane = new JScrollPane(table);
        panelThree.add(scrollPane);

        frame.getContentPane().add(BorderLayout.EAST, panelTwo);
        frame.getContentPane().add(panelOne);
        frame.getContentPane().add(panelThree);
        frame.setSize(1200, 800);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static JTable createTable(String[] selectedCategory) {
        ArrayList<Product> products = WestminsterShoppingManager.getProducts();

        ArrayList<Product> electronics = new ArrayList<>();
        ArrayList<Product> clothing = new ArrayList<>();

        for (Product product : products) {
            if (product instanceof Electronics) {
                electronics.add((Electronics) product);
            } else if (product instanceof Clothing) {
                clothing.add((Clothing) product);
            }
        }

        ArrayList<Product> selectedProducts = new ArrayList<>();

        if (selectedCategory[0].equals("All")) {
            selectedProducts = products;
        } else if (selectedCategory[0].equals("Electronics")) {
            selectedProducts = electronics;
        } else if (selectedCategory[0].equals("Clothing")) {
            selectedProducts = clothing;
        }

        String[] columnNames = {"Product ID", "Product Name", "Product Price", "Product Category", "Product Info"};

        int dataLength = selectedProducts.size();
        String[][] data = new String[dataLength][5];

        String productInfo = "Error";
        for (int i = 0; i < dataLength; i++) {
            if (selectedProducts.get(i).getProductType().equals("Electronics")) {
                productInfo = ((Electronics) selectedProducts.get(i)).getBrand() + ", " + ((Electronics) selectedProducts.get(i)).getWarrantyPeriod() + " years warranty";
            } else {
                productInfo = ((Clothing) selectedProducts.get(i)).getSize() + ", " + ((Clothing) selectedProducts.get(i)).getColor();
            }
            data[i] = new String[]{selectedProducts.get(i).getProductId(), selectedProducts.get(i).getProductName(), String.valueOf(selectedProducts.get(i).getPrice()), selectedProducts.get(i).getProductType(), productInfo};
        }

        JTable table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(1000, 500));
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);

        table.setDefaultEditor(Object.class, null);

        //make the header bold
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Serif", Font.BOLD, 15));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

        //row height
        table.setRowHeight(30);

        return table;
    }
}
