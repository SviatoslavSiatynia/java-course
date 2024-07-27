import java.util.List;


public class Admin extends User {
    public Admin(String name, String email, String password) {
        super(name, email, password);
    }

    public boolean addProduct(List<Product> listProducts, Product newProduct){
        return listProducts.add(newProduct);
    }

    public boolean changePrice (Product product, double newPrice){
        if(newPrice >= 0) {
            product.setPrice(newPrice);

            return true;
        }
        else return false;
    }

    public boolean removeProduct(List<Product> listProducts,Product product) {
        return listProducts.remove(product);

    }

    @Override
    public String toString() {
        return "Admin: \n" +
                "Name: " + getName() + "\n" +
                "Email: " + getEmail() + "\n" +
                "Password: " + getPassword() + "\n";
    }
}
