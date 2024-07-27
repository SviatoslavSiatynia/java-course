import java.util.Objects;

//Клас Товару
public abstract class Product {
    private String name;
    private double price;
    private String description;

    public Product(String name, double price, String description) {
        this.name = name;
        if(price <= 0) {
            price = 1;
        }
        else {
            this.price = price;
        }
        this.description = description;
    }

    public String getName() {//Отримання назви товару
        return name;
    }

    public double getPrice() {//Отримання ціни
        return price;
    }

    public String getDescription() {//Отримання опису
        return description;
    }

    public void setPrice(double newPrice){
        this.price = newPrice;
    }

    @Override
    public String toString() {
        return "Name: " + name + '\n' +
                "Price: $" + price + "\n" +
                "Description: " + description + '\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.price, price) == 0 && Objects.equals(name, product.name) && Objects.equals(description, product.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, description);
    }
}
