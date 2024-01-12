import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

//reference: https://www.youtube.com/watch?v=Kmgo00avvEw

public class ClientPortal {
    public static void runGui(User user) {
//    public static void main(String[] args) {
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

            //reference: https://stackoverflow.com/questions/10128064/jtable-selected-row-click-event
            table.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    int row = table.rowAtPoint(evt.getPoint());

                    String productId = table.getValueAt(row, 0).toString();

                    displayProductDetails(productId);
                }
            });
        });

        JButton button = new JButton("Shopping Cart");
        button.setLayout(new FlowLayout(FlowLayout.TRAILING));
        panelTwo.setLayout(new BorderLayout());
        panelTwo.add(button);

        button.addActionListener(e -> {
            ArrayList<Product> products = ShoppingCart.getProducts();
            ShoppingCartFrame(user);
        });

        JTable table = createTable(selectedCategory);
        JScrollPane scrollPane = new JScrollPane(table);
        panelThree.add(scrollPane);

        //reference: https://stackoverflow.com/questions/10128064/jtable-selected-row-click-event
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = table.rowAtPoint(evt.getPoint());

                String productId = table.getValueAt(row, 0).toString();

                displayProductDetails(productId);
            }
        });

        frame.getContentPane().add(panelOne);
        frame.getContentPane().add(BorderLayout.EAST, panelTwo);
        frame.getContentPane().add(panelThree);
        frame.setSize(1200, 800);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private static void ShoppingCartFrame(User user) {
        JFrame frame = new JFrame();
        frame.setTitle("Shopping Cart");
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        ArrayList<Product> products = ShoppingCart.getProducts();

        if (products == null || products.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Shopping cart is empty!");
            return;
        }

        String[] columnNames = {"Product", "Quantity", "Price"};

        String[][] data = new String[products.size()][3];

        String productInfo = "Error";
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductType().equals("Electronics")) {
                productInfo = ((Electronics) products.get(i)).getBrand() + ", " + ((Electronics) products.get(i)).getWarrantyPeriod() + " years warranty";
            } else {
                productInfo = ((Clothing) products.get(i)).getSize() + ", " + ((Clothing) products.get(i)).getColor();
            }
            data[i] = new String[]{products.get(i).getProductId() + "\n" + products.get(i).getProductName() + "\n" + productInfo, String.valueOf(products.get(i).getNoOfAvailableItems()), String.valueOf(products.get(i).getPrice())};
        }

        JTable shoppingCartTable = new JTable(data, columnNames);
        shoppingCartTable.setPreferredScrollableViewportSize(new Dimension(1000, 500));
        shoppingCartTable.setFillsViewportHeight(true);
        shoppingCartTable.setAutoCreateRowSorter(true);

        shoppingCartTable.setDefaultEditor(Object.class, null);

        //make the header bold
        JTableHeader header = shoppingCartTable.getTableHeader();
        header.setFont(new Font("Serif", Font.BOLD, 15));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        shoppingCartTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        shoppingCartTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        shoppingCartTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

        //row height
        shoppingCartTable.setRowHeight(30);

        JPanel panelOne = new JPanel();
        panelOne.setLayout(new BoxLayout(panelOne, BoxLayout.Y_AXIS));

        double totalPrice = ShoppingCart.calculateTotalPrice();
        JLabel label = new JLabel("Total Price: " + totalPrice);
        panelOne.add(label);

        JPanel panelTwo = new JPanel();
        panelTwo.setLayout(new BoxLayout(panelTwo, BoxLayout.Y_AXIS));

        double firstPurchaseDiscount = 0;

        if (user.getPurchaseHistory().isEmpty()) {
            firstPurchaseDiscount = totalPrice * 0.1;
        } else {
            firstPurchaseDiscount = 0;
        }

        JLabel label2 = new JLabel("First Purchase Discount (10%): " + firstPurchaseDiscount);
        panelTwo.add(label2);

        JPanel panelThree = new JPanel();
        panelThree.setLayout(new BoxLayout(panelThree, BoxLayout.Y_AXIS));

        double additionalDiscount = 0;

        int electronicsCount = 0;
        int clothingCount = 0;

        for (Product product : products) {
            if (product.getProductType().equals("Electronics")) {
                electronicsCount++;
            } else if (product.getProductType().equals("Clothing")) {
                clothingCount++;
            }
        }

        if (electronicsCount >= 3 || clothingCount >= 3) {
            additionalDiscount = totalPrice * 0.2;
        } else {
            additionalDiscount = 0;
        }

        JLabel label3 = new JLabel("Additional Discount (20%): " + additionalDiscount);
        panelThree.add(label3);

        JPanel panelFour = new JPanel();
        panelFour.setLayout(new BoxLayout(panelFour, BoxLayout.Y_AXIS));

        JLabel label4 = new JLabel("Final Total: " + (totalPrice - firstPurchaseDiscount - additionalDiscount));
        panelFour.add(label4);

        JPanel panelFive = new JPanel();
        panelFive.setLayout(new BoxLayout(panelFive, BoxLayout.Y_AXIS));

        JButton button = new JButton("Checkout");
        panelFive.add(button);

        button.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Checkout successful!");
            ArrayList<Product> purchaseHistory = user.getPurchaseHistory();
            purchaseHistory.addAll(products);
            ShoppingCart.getProducts().clear();
            frame.dispose();
        });

        JPanel panelSix = new JPanel();
        panelSix.setLayout(new BoxLayout(panelSix, BoxLayout.Y_AXIS));

        JButton button2 = new JButton("Cancel");
        panelSix.add(button2);

        button2.addActionListener(e -> {
            frame.dispose();
        });

        frame.getContentPane().add(panelOne);
        frame.getContentPane().add(panelTwo);
        frame.getContentPane().add(panelThree);
        frame.getContentPane().add(panelFour);
        frame.getContentPane().add(panelFive);
        frame.getContentPane().add(panelSix);
        frame.getContentPane().add(shoppingCartTable);
        frame.setSize(1200, 800);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

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

        table.setRowHeight(30);

        return table;
    }

    private static void displayProductDetails(String productId) {
        ArrayList<Product> products = WestminsterShoppingManager.getProducts();

        JPanel containerPanel = new JPanel();

        for (Product product : products) {
            if (product.getProductId().equals(productId)) {

                JPanel panelOne = new JPanel();
                panelOne.setLayout(new BoxLayout(panelOne, BoxLayout.Y_AXIS));

                JLabel label = new JLabel("Selected Product - Details");
                panelOne.add(label);

                JPanel panelTwo = new JPanel();
                panelTwo.setLayout(new BoxLayout(panelTwo, BoxLayout.Y_AXIS));

                JLabel productIdLabel = new JLabel("\n\nProduct ID: " + product.getProductId());
                panelTwo.add(productIdLabel);

                JLabel productCategoryLabel = new JLabel("\nCategory: " + product.getProductType());
                panelTwo.add(productCategoryLabel);

                JLabel productNameLabel = new JLabel("\nProduct Name: " + product.getProductName());
                panelTwo.add(productNameLabel);

                if (product.getProductType().equals("Electronics")) {
                    JLabel productInfoLabel = new JLabel("\nBrand: " + ((Electronics) product).getBrand());
                    panelTwo.add(productInfoLabel);

                    JLabel productInfoLabel2 = new JLabel("\nWarranty Period: " + ((Electronics) product).getWarrantyPeriod() + " years");
                    panelTwo.add(productInfoLabel2);
                } else {
                    JLabel productInfoLabel = new JLabel("\nSize: " + ((Clothing) product).getSize());
                    panelTwo.add(productInfoLabel);

                    JLabel productInfoLabel2 = new JLabel("\nColor: " + ((Clothing) product).getColor());
                    panelTwo.add(productInfoLabel2);
                }

                JLabel productInfoLabel = new JLabel("\nItems Available: " + product.getNoOfAvailableItems());
                panelTwo.add(productInfoLabel);

                JPanel panelThree = new JPanel();
                panelThree.setLayout(new BoxLayout(panelThree, BoxLayout.Y_AXIS));

                JButton button = new JButton("Add to Shopping Cart");
                panelThree.add(button);

                button.addActionListener(e -> {
                    ShoppingCart.addProduct(product);
                    JOptionPane.showMessageDialog(null, "Product added to the shopping cart!");
                });

                containerPanel.setLayout(new GridLayout(3, 1));

                JFrame frame = new JFrame();
                frame.setTitle("Westminster Shopping Manager");
                frame.setLayout(new FlowLayout());

                containerPanel.add(panelOne);
                containerPanel.add(panelTwo);
                containerPanel.add(panelThree);

                frame.getContentPane().add(containerPanel);
                frame.setSize(500, 500);
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                break;
            }
        }
    }
}
