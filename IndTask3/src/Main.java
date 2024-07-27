import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args){
        Map<Product, Integer> products = new TreeMap<>();

        try {
            //Отримання даних з файлу
            BufferedReader reader = new BufferedReader(new FileReader("src\\products.txt"));
            String line = reader.readLine();
            while (line != null) {
                if (line.startsWith("Electronics")) {
                    String name = reader.readLine().substring(6);
                    double price = Double.parseDouble(reader.readLine().substring(8));
                    String description = reader.readLine().substring(13);
                    String brand = reader.readLine().substring(7);
                    String model = reader.readLine().substring(7);
                    String color = reader.readLine().substring(7);
                    int count = Integer.parseInt(reader.readLine().substring(7));
                    Electronics electronics = new Electronics(name, price, description, brand, model, color);
                    products.put(electronics, count);
                } else if (line.startsWith("Food")) {
                    String name = reader.readLine().substring(6);
                    double price = Double.parseDouble(reader.readLine().substring(8));
                    String description = reader.readLine().substring(13);
                    String expirationDate = reader.readLine().substring(17);
                    Food food = new Food(name, price, description, expirationDate);
                    int count = Integer.parseInt(reader.readLine().substring(7));
                    products.put(food, count);
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        // надрукувати вміст TreeMap
        System.out.println("List of products:");
        for (Product prod : products.keySet()) {
            System.out.println(prod + "Count: " + products.get(prod) + "\n");
        }

        // ввести з клавіатури ключ та отримати значення за цим ключем
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the product: ");
        String name = scanner.nextLine();
        Product key = null;
        for (Product prod : products.keySet()) {
            if (prod.getName().equals(name)) {
                key = prod;
                break;
            }
        }
        if (key != null) {
            System.out.println(key + "Count: " + products.get(key));
        } else {
            System.out.println("Product " + name + " not found");
        }

        // отримати множину ключів та надрукувати її
        System.out.println("\nProducts (keys):");
        for (Product prod : products.keySet()) {
            System.out.println(prod);
        }

        // видалити значення за ключом та надрукувати TreeMap та множину ключів
        Set<Product> keySet = products.keySet();
        System.out.print("\nEnter the name of the product to remove: ");
        name = scanner.nextLine();
        key = null;
        for (Product prod : products.keySet()) {
            if (prod.getName().equals(name)) {
                key = prod;
                break;
            }
        }
        if (key != null) {
            products.remove(key);
            System.out.println("List of products after removal: ");
            for (Product prod : keySet) {
                System.out.println(prod + "Count: " + products.get(prod));
            }
            System.out.println("Product names after removal: ");
            for (Product prod : keySet) {
                System.out.println(prod.getName());
            }
        } else {
            System.out.println("Product " + name + " not found");
        }

        //Виведення даних до файлу, наявність однакового ключа
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("src\\products.txt"));
            for (Product prod : products.keySet()) {
                writer.println(prod + "Count: " + products.get(prod) + "\n");
            }
            Electronics duplicate = new Electronics("Dell XPS 13", 700.50, "13.4-inch FHD+ Touch Laptop", "Dell", "XPS 13", "Platinum Silver");
            writer.println(duplicate + "Count: " + 15 + "\n");
            writer.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}