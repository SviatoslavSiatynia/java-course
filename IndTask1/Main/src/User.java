import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private String name;
    private String email;
    private String password;
    private List<Product> cart;
    private List<String> orderHistory;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.cart = new ArrayList<>();
        this.orderHistory = new ArrayList<>();
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
    }

    public void removeFromCart(Product product) {
        cart.remove(product);
    }

    public void freeCart(){
        cart.clear();
    }

    public List<String> getOrderHistory(){
        return orderHistory;
    }

    public void addOrderHistory(String order){
        orderHistory.add(order);
    }

    @Override
    public String toString() {
        return "Store User: \n" +
                "Name: " + name + "\n" +
                "Email: " + email + "\n";
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

