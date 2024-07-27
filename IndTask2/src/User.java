import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private String name;
    private String email;
    private String password;
    private List<Product> cart = new ArrayList<>();
    private List<String> orderHistory = new ArrayList<>();


    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        if(!(this instanceof Admin)) {
            try {
                File cartFile = new File("src\\" + name + "_cart.txt");
                File orderFile = new File("src\\" + name + "_orderHistory.txt");
                boolean newFile = cartFile.createNewFile();
                boolean newFil = orderFile.createNewFile();


                BufferedReader reader = new BufferedReader(new FileReader("src\\" + name + "_cart.txt"));
                String line = reader.readLine();
                while (line != null) {
                    if (line.startsWith("Electronics")) {
                        String nameP = reader.readLine().substring(6);
                        double price = Double.parseDouble(reader.readLine().substring(8));
                        String description = reader.readLine().substring(13);
                        String brand = reader.readLine().substring(7);
                        String model = reader.readLine().substring(7);
                        String color = reader.readLine().substring(7);
                        Electronics electronics = new Electronics(nameP, price, description, brand, model, color);
                        cart.add(electronics);
                    } else if (line.startsWith("Food")) {
                        String nameP = reader.readLine().substring(6);
                        double price = Double.parseDouble(reader.readLine().substring(8));
                        String description = reader.readLine().substring(13);
                        String expirationDate = reader.readLine().substring(17);
                        Food food = new Food(nameP, price, description, expirationDate);
                        cart.add(food);
                    }
                    line = reader.readLine();
                }
                reader.close();

                BufferedReader reader2 = new BufferedReader(new FileReader("src\\" + name + "_orderHistory.txt"));
                String line2 = reader2.readLine();
                String order = "";
                while (line2 != null) {
                    if (line2.trim().isEmpty()) {
                        orderHistory.add(order);
                        order = "";
                    } else {
                        order += line2 + "\n";
                    }
                    line2 = reader2.readLine();
                }
                if (!order.isEmpty()) {
                    orderHistory.add(order);
                }
                reader2.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public List<Product> getCart() {
        return cart;
    }

    public void addToCart(Product product) {
        cart.add(product);
        try {
            FileWriter writer = new FileWriter("src\\" + name + "_cart.txt");
            for (Product prod : cart) {
                writer.write(prod.toString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeFromCart(Product product) {
        cart.remove(product);
        try {
            FileWriter writer = new FileWriter("src\\" + name + "_cart.txt");
            for (Product prod : cart) {
                writer.write(prod.toString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void freeCart(){
        cart.clear();
        try {
            FileWriter writer = new FileWriter("src\\" + name + "_cart.txt");
            writer.write("");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getOrderHistory(){
        return orderHistory;
    }

    public void addOrderHistory(String order){
        orderHistory.add(order);
        try {
            FileWriter writer = new FileWriter("src\\" + name + "_orderHistory.txt");
            for (String orders : orderHistory) {
                writer.write(orders.toString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "User: \n" +
                "Name: " + name + "\n" +
                "Email: " + email + "\n" +
                "Password: " + password + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, password);
    }
}

