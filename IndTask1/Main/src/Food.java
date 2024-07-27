//Клас Їжі
public class Food extends Product {
    private String expirationDate;

    public Food(String name, double price, String description, String expirationDate) {
        super(name, price, description);
        this.expirationDate = expirationDate;
    }

    public String getExpirationDate() {//Отримання терміну придатності
        return expirationDate;
    }

    @Override
    public String toString() {
        return super.toString() +
                "Expiration Date: " + expirationDate + '\n';
    }
}
