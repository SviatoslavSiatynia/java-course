import java.util.List;


public class Admin extends User {
    public Admin(String name, String email, String password) {
        super(name, email, password);
    }

    public void addProduct(List<Product> products, Product newProduct){
        products.add(newProduct);
    }

    public boolean changePrice (Product product, double newPrice){
        if(newPrice >= 0) {
            product.price = newPrice;
            return true;
        }
        else return false;
    }

    public void removeProduct(List<Product> products,Product product){
        products.remove(product);
    }

    @Override
    public String toString() {
        return "Store Admin: \n" +
                "Name: " + getName() + "\n" +
                "Email: " + getEmail() + "\n";
    }
}
