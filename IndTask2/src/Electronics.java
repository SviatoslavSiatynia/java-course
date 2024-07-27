//Клас Электронного товару
public class Electronics extends Product {
    private String brand;
    private String model;
    private String color;

    public Electronics(String name, double price, String description, String brand, String model, String color) {
        super(name, price, description);
        this.brand = brand;
        this.model = model;
        this.color = color;
    }

    public String getBrand() {//Отримання бренду
        return brand;
    }

    public String getModel() {//Отримання моделі
        return model;
    }

    public String getColor() {//Отримання кольору
        return color;
    }

    @Override
    public String toString() {
        return  "Electronics: " + '\n'+
                "Name: " + getName() + '\n' +
                "Price: $" + getPrice() + "\n" +
                "Description: " + getDescription() + '\n' +
                "Brand: " + brand + '\n' +
                "Model: " + model + '\n' +
                "Color: " + color + '\n';
    }
}
