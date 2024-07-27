import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Product> listProducts = new ArrayList<>();
        try{
        BufferedReader reader2 = new BufferedReader(new FileReader("src\\products.txt"));
        String line2 = reader2.readLine();
        while (line2 != null) {
            if (line2.startsWith("Electronics")) {
                String name = reader2.readLine().substring(6);
                double price = Double.parseDouble(reader2.readLine().substring(8));
                String description = reader2.readLine().substring(13);
                String brand = reader2.readLine().substring(7);
                String model = reader2.readLine().substring(7);
                String color = reader2.readLine().substring(7);
                Electronics electronics = new Electronics(name, price, description, brand, model, color);
                listProducts.add(electronics);
            } else if (line2.startsWith("Food")) {
                String name = reader2.readLine().substring(6);
                double price = Double.parseDouble(reader2.readLine().substring(8));
                String description = reader2.readLine().substring(13);
                String expirationDate = reader2.readLine().substring(17);
                Food food = new Food(name, price, description, expirationDate);
                listProducts.add(food);
            }
            line2 = reader2.readLine();
        }
        reader2.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for(Product item: listProducts)
            System.out.println(item.toString());
    }
}
