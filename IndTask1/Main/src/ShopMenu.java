import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ShopMenu {
    private List<User> listUsers = new ArrayList<User>();
    private List<Product> listProducts = new ArrayList<Product>();

    public ShopMenu() {
        listUsers.add(new User("Ivan", "ivanovych@gmail.com", "password123"));
        listUsers.add(new User("Marina", "igorivna@gmail.com", "qwerty123"));
        listUsers.add(new User("Oleksandr", "oleksandr123@gmail.com", "mypassword"));
        listUsers.add(new User("Olha", "olha.serg@gmail.com", "secret123"));
        listUsers.add(new Admin("Volodymyr", "volodymyr.oleksiiovych@gmail.com", "12345678a"));

        listProducts.add(new Electronics("Dell XPS 13", 1099.99, "13.4-inch FHD+ Touch Laptop", "Dell", "XPS 13", "Platinum Silver"));
        listProducts.add(new Electronics("Samsung Galaxy S21", 799.50, "5G Smartphone", "Samsung", "Galaxy S21", "Phantom Gray"));
        listProducts.add(new Electronics("LG CX Series 65 inch 4K Smart OLED TV", 2099.49, "4K OLED TV with AI ThinQ", "LG", "CX Series", "Black"));
        listProducts.add(new Electronics("Sonos One SL", 179.0, "Wireless Speaker", "Sonos", "One SL", "Black"));
        listProducts.add(new Electronics("Sony Alpha a7 III Mirrorless Camera", 1999.0, "24MP Full Frame Mirrorless Camera", "Sony", "Alpha a7 III", "Black"));
        listProducts.add(new Electronics("Apple Watch Series 7", 350.78, "Smartwatch with Always-On Retina Display", "Apple", "Watch Series 7", "Midnight"));
        listProducts.add(new Food("Banana", 0.49, "Fresh and ripe banana", "15/06/2023"));
        listProducts.add(new Food("Apple", 0.69, "Juicy and sweet apple", "31/8/2023"));
        listProducts.add(new Food("Whole Milk", 2.99, "1 gallon of fresh whole milk", "01/04/2023"));
        listProducts.add(new Food("Sourdough Bread", 4.35, "Freshly baked sourdough bread", "01/04/2023"));
        listProducts.add(new Food("Cheddar Cheese", 3.69, "1 pound of sharp cheddar cheese", "15/05/2023"));
        listProducts.add( new Food("Boneless Chicken Breast", 6.70, "1 pound of boneless, skinless chicken breast", "06/05/2023"));
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome!");
        boolean isUserFound = false;

        while (!isUserFound) {
            System.out.println("Do you have an account? (Y/N)");
            String answer = scanner.nextLine();

            if (answer.equalsIgnoreCase("Y")) {
                System.out.println("You must be logged in to continue!");
                System.out.println("Enter your email:");
                String email = scanner.nextLine();
                System.out.println("Enter your password:");
                String password = scanner.nextLine();

                for (User user : listUsers) {
                    if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                        isUserFound = true;
                        if (user instanceof Admin) {
                            adminMenu((Admin) user);
                        }
                        else {
                            userMenu(user);
                        }
                        break;
                    }
                }

                if (!isUserFound) {
                    System.out.println("Incorrect email or password. Please try again.");
                }
            } else if (answer.equalsIgnoreCase("N")) {
                System.out.println("You must be registered to continue!");
                System.out.println("Enter your first name:");
                String firstName = scanner.nextLine();
                System.out.println("Enter your email:");
                String email = scanner.nextLine();
                System.out.println("Enter your password:");
                String password = scanner.nextLine();

                User newUser = new User(firstName, email, password);
                listUsers.add(newUser);
                System.out.println("Registration successful! Please login to continue.");
            } else {
                System.out.println("Invalid answer. Please try again.");
            }
        }

    }
    private void userMenu(User currentUser) {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        System.out.println("Welcome to our shop, " + currentUser.getName() + "!");
        while (choice != 0) {
            System.out.println("1. View catalog");
            System.out.println("2. View Product Details");
            System.out.println("3. View cart");
            System.out.println("4. Add product to cart");
            System.out.println("5. Remove product from cart");
            System.out.println("6. Get order history");
            System.out.println("7. Order");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    Random random = new Random();
                    int chance = random.nextInt(5) + 1;
                    if (chance == 1) {
                        System.out.println("\nThe store needs employees!\nFor more detailed information, call: +380-ХХХ-ХХ-ХХ");
                        Scanner scanner2 = new Scanner(System.in);
                        System.out.println("Press any key to continue...");
                        String enter = scanner2.nextLine();
                    }
                    System.out.println(" ");
                    viewCatalog();
                    break;
                case 2:
                    System.out.println(" ");
                    viewProductDetails();
                    break;
                case 3:
                    System.out.println(" ");
                    viewCart(currentUser);
                    break;
                case 4:
                    System.out.println(" ");
                    addItemToCart(currentUser);
                    break;
                case 5:
                    System.out.println(" ");
                    removeItemFromCart(currentUser);
                    break;
                case 6:
                    System.out.println(" ");
                    if(currentUser.getOrderHistory().isEmpty()){
                        System.out.println("You have not made any order yet");
                    }
                    else{
                        for(String order : currentUser.getOrderHistory()){
                            System.out.println("\n" + order + "\n");
                        }
                    }
                    break;
                case 7:
                    System.out.println(" ");
                    if (!makeOrder(currentUser)){
                        System.out.println("Order not completed");
                    }
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
        }
    }

    private void viewCatalog() {
        System.out.println("Catalog:");
        for (Product item : listProducts) {
            System.out.println(item.getName() + " - $" + item.getPrice());
        }
        System.out.println(" ");
    }

    private void viewProductDetails() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter product name: ");
        String productName = scanner.nextLine();

        boolean found = false;
        for (Product product : listProducts) {
            if (product.getName().equals(productName)) {
                found = true;
                if (product instanceof Electronics) {
                    Electronics electronics = (Electronics) product;
                    System.out.println(electronics);
                } else if (product instanceof Food) {
                    Food food = (Food) product;
                    System.out.println(food);
                }
                System.out.println(" ");
                break;
            }
        }

        if (!found) {
            System.out.println("Product not found.");
            System.out.println(" ");
        }
    }

    private void viewCart(User currentUser) {
        System.out.println("Cart:");
        if (currentUser.getCart().isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            for (Product item : currentUser.getCart()) {
                System.out.println(item.getName() + " - $" + item.getPrice());
            }
        }
        System.out.println(" ");
    }

    private void addItemToCart(User currentUser) {
        viewCatalog();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the product you want to add to your cart: ");
        String itemName = scanner.nextLine();
        for (Product item : listProducts) {
            if (item.getName().equals(itemName)) {
                currentUser.addToCart(item);
                System.out.println(itemName + " has been added to your cart.");
                System.out.println(" ");
                return;
            }
        }
        System.out.println("Invalid product name. Try again.");
        System.out.println(" ");
    }

    private void removeItemFromCart(User currentUser) {
        if (currentUser.getCart().isEmpty()) {
            System.out.println("Your cart is empty. Cannot remove item.");
            System.out.println(" ");
            return;
        }

        viewCart(currentUser);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the product you want to remove from your cart: ");
        String itemName = scanner.nextLine();
        for (Product item : currentUser.getCart()) {
            if (item.getName().equals(itemName)) {
                currentUser.removeFromCart(item);
                System.out.println(itemName + " has been removed from your cart.");
                System.out.println(" ");
                return;
            }
        }
        System.out.println("Invalid product name. Try again.");
        System.out.println(" ");
    }


    private boolean makeOrder(User currentUser) {
        viewCart(currentUser);
        if (currentUser.getCart().isEmpty()) {
            System.out.println("Your cart is empty. Cannot checkout.");
            System.out.println(" ");
            return false;
        } else {
            double deliveryFee;
            double total = 0;
            for (Product item : currentUser.getCart()) {
                total += item.getPrice();
            }
            if (total <= 10){
                deliveryFee = 4;
            }else  deliveryFee = 8;
            total += deliveryFee;
            System.out.println("Total (including delivery fee of $" + deliveryFee + "): $" + total);


            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter delivery address: ");
            String address = scanner.nextLine();

            System.out.print("Enter payment method (1 for online payment, 2 for payment on delivery): ");
            int paymentMethod = scanner.nextInt();
            scanner.nextLine();

            String paymentMethodString;
            if (paymentMethod == 1) {
                paymentMethodString = "online payment";
            } else if (paymentMethod == 2) {
                paymentMethodString = "payment on delivery";
            } else {
                System.out.println("Invalid payment method.");
                paymentMethodString = "payment on delivery";
            }

            System.out.println("\nThank you for your purchase, " + currentUser.getName() + "!");
            System.out.println("Your items will be delivered to the following address: " + address);
            System.out.println("You have selected " + paymentMethodString + " as your payment method.");
            String order = "";
            for (Product item : currentUser.getCart()) {
                order += item.getName() + " - $" + item.getPrice() + "\n";
            }
            order += "Total: $" + total + "\n" +
                    "Address: " + address + "\n" +
                    "Payment method: " + paymentMethodString + "\n";
            currentUser.addOrderHistory(order);
            currentUser.freeCart();
            System.out.println(" ");
            return true;
        }
    }




    private void adminMenu(Admin admin) {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        System.out.println("Welcome back, " + admin.getName() + "!");
        while (choice != 0) {
            System.out.println("Admin Menu:");
            System.out.println("1. View Catalog");
            System.out.println("2. View Product Details");
            System.out.println("3. Add Product");
            System.out.println("4. Change Product Price");
            System.out.println("5. Remove Product");
            System.out.println("0. Exit");

            System.out.print("Enter choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println(" ");
                    viewCatalog();
                    break;
                case 2:
                    System.out.println(" ");
                    viewProductDetails();
                    break;
                case 3:
                    System.out.println(" ");
                    addP(admin);
                    break;
                case 4:
                    System.out.println(" ");
                    changePrice(admin);
                    break;
                case 5:
                    System.out.println(" ");
                    removeP(admin);
                    break;
                case 0:
                    System.out.println(" ");
                    System.out.println("Exiting Admin Menu...");
                    break;
                default:
                    System.out.println(" ");
                    System.out.println("Invalid choice!");
                    break;
            }
        }

    }
    private void addP(Admin admin) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();

        System.out.print("Enter product price: ");
        double price = scanner.nextDouble();
        if(price <= 0){
            price = 1;
        }
        scanner.nextLine();

        System.out.print("Enter product description: ");
        String description = scanner.nextLine();

        System.out.print("Enter product type (1 for Electronics, 2 for Food): ");
        int itemType = scanner.nextInt();
        scanner.nextLine();

        Product newItem;
        if (itemType == 1) {
            System.out.print("Enter product brand: ");
            String brand = scanner.nextLine();

            System.out.print("Enter product model: ");
            String model = scanner.nextLine();

            System.out.print("Enter product color: ");
            String color = scanner.nextLine();

            newItem = new Electronics(name, price, description, brand, model, color);
        } else if (itemType == 2) {
            System.out.print("Enter product expiration date: ");
            String expirationDate = scanner.nextLine();

            newItem = new Food(name, price, description, expirationDate);
        } else {
            System.out.println("Invalid product type.");
            return;
        }

        admin.addProduct(listProducts, newItem);

        System.out.println("Product added to catalog.");
        System.out.println(" ");
    }

    private void changePrice(Admin admin) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the product you want to change the price for: ");
        String itemName = scanner.nextLine();

        for (Product item : listProducts) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                System.out.print("Enter the new price for " + itemName + ": ");
                double newPrice = scanner.nextDouble();
                admin.changePrice(item, newPrice);
                return;
            }
        }
        System.out.println("Product not found in catalog.");
    }

    private void removeP(Admin admin) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter product name to remove: ");
        String name = scanner.nextLine();

        Product itemToRemove = null;

        for (Product item : listProducts) {
            if (item.getName().equals(name)) {
                itemToRemove = item;
                break;
            }
        }

        if (itemToRemove != null) {
            admin.removeProduct(listProducts, itemToRemove);
            System.out.println("Product removed from catalog.");
        } else {
            System.out.println("Product not found.");
        }
        System.out.println(" ");
    }

}

