import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        WestminsterShoppingManager westminsterShoppingManager = new WestminsterShoppingManager();

        // Loads the contents of the file into the ArrayList
        westminsterShoppingManager.loadFile();

        Scanner scanner = new Scanner(System.in);

        // The main menu
        while (true) {
            System.out.println("\nWelcome to Westminster Shopping Manager!");
            System.out.println("1. Add a new product");
            System.out.println("2. Delete a product");
            System.out.println("3. Print the list of products");
            System.out.println("4. Save the list of products");
            System.out.println("5. Open customer menu");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input!");
                scanner.nextLine();
                continue;
            }
            switch (choice) {
                case 1:
                    addProduct(westminsterShoppingManager, scanner);
                    break;
                case 2:
                    removeProduct(westminsterShoppingManager, scanner);
                    break;
                case 3:
                    westminsterShoppingManager.printProductList();
                    break;
                case 4:
                    westminsterShoppingManager.saveFile();
                    break;
                case 5:
                    customerMenu();
                    break;
                case 6:
                    System.out.println("Thank you for using Westminster Shopping Manager!");
                    System.exit(0);
                default:
                    System.out.println("Invalid input!");
            }
        }

    }

    private static void customerMenu() {
        // retrieve the users from the class
        ArrayList<User> users = User.getUsers();
        System.out.println("\nAre you a registered customer?(Y/N)");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.next();
        if (choice.equalsIgnoreCase("Y")) {
            System.out.println("Enter your username: ");
            String username = scanner.next();
            System.out.println("Enter your password: ");
            String password = scanner.next();

            // check if the user exists
            if (users != null) {
                for (User user1 : users) {
                    if (user1.getUserName().equals(username) && user1.getPassword().equals(password)) {
                        System.out.println("Login successful!");
                        ClientPortal.runGui(user1);
                        return;
                    }
                }
            }


            System.out.println("Login failed!");
        } else if (choice.equalsIgnoreCase("N")) {
            System.out.println("Enter your username: ");
            String username = scanner.next();
            System.out.println("Enter your password: ");
            String password = scanner.next();

            // creates a new user and adds it to the ArrayList
            User user = new User(username, password, new ArrayList<>());
            if (users == null) {
                users = new ArrayList<>();
            }
            users.add(user);
            System.out.println("Registration successful!");
            User.setUsers(users);
            ClientPortal.runGui(user);
        }
    }

    private static void removeProduct(WestminsterShoppingManager westminsterShoppingManager, Scanner scanner) {
        System.out.println("\nEnter product ID: ");
        String productId = scanner.next();
        westminsterShoppingManager.removeProduct(productId);
    }

    private static void addProduct(WestminsterShoppingManager westminsterShoppingManager, Scanner scanner) {
        while (true) {
            System.out.println("\nSelect a product type:");
            System.out.println("1. Electronics");
            System.out.println("2. Clothing");
            System.out.println("3. Exit");
            int choice;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input!");
                scanner.nextLine();
                continue;
            }
            switch (choice) {
                case 1:
                    addElectronicsProduct(westminsterShoppingManager, scanner);
                    break;
                case 2:
                    addClothingProduct(westminsterShoppingManager, scanner);
                    break;
                case 3:
                    return;
            }
            break;
        }


    }

    private static void addClothingProduct(WestminsterShoppingManager westminsterShoppingManager, Scanner scanner) {
        System.out.println("\nEnter product ID: ");
        String productId = scanner.next();
        System.out.println("Enter product name: ");
        String productName = scanner.next();
        int noOfAvailableItems;
        double price;
        String size;
        while (true) {
            System.out.println("Enter number of available items: ");
            try {
                noOfAvailableItems = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input!");
                scanner.nextLine();
            }
        }
        while (true) {
            System.out.println("Enter price: ");
            try {
                price = scanner.nextDouble();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input!");
                scanner.nextLine();
            }
        }
        System.out.println("Enter color: ");
        String color = scanner.next();

        while (true) {
            System.out.println("Select size: ");
            System.out.println("Select size: ");
            System.out.println("1. Small");
            System.out.println("2. Medium");
            System.out.println("3. Large");
            int choice;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input!");
                scanner.nextLine();
                continue;
            }
            switch (choice) {
                case 1:
                    size = "Small";
                    break;
                case 2:
                    size = "Medium";
                    break;
                case 3:
                    size = "Large";
                    break;
                default:
                    System.out.println("Invalid input!");
                    continue;
            }
            westminsterShoppingManager.addProduct(new Clothing(productId, productName, noOfAvailableItems, price, color, size));
            break;
        }
    }

    private static void addElectronicsProduct(WestminsterShoppingManager westminsterShoppingManager, Scanner scanner) {
        System.out.println("\nEnter product ID: ");
        String productId = scanner.next();
        System.out.println("Enter product name: ");
        String productName = scanner.next();
        int noOfAvailableItems;
        double price;
        String brand;
        int warrantyPeriod;
        while (true) {
            System.out.println("Enter number of available items: ");
            try {
                noOfAvailableItems = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input!");
                scanner.nextLine();
            }
        }
        while (true) {
            System.out.println("Enter price: ");
            try {
                price = scanner.nextDouble();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input!");
                scanner.nextLine();
            }
        }
        System.out.println("Enter brand: ");
        brand = scanner.next();

        while (true) {
            System.out.println("Enter warranty period: ");
            try {
                warrantyPeriod = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input!");
                scanner.nextLine();
                continue;
            }
            westminsterShoppingManager.addProduct(new Electronics(productId, productName, noOfAvailableItems, price, brand, warrantyPeriod));
            break;
        }
    }
}