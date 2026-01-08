public abstract class Product {
    private int id;
    private String name;
    private double price;

    public Product(int id, String name, double price) {
        this.id = id;
        setName(name);
        setPrice(price);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }


    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        } else {
            this.name = "Unknown Product";
        }
    }

    public void setPrice(double price) {
        if (price >= 0) {
            this.price = price;
        } else {
            this.price = 0;
        }
    }


    public abstract String work();

    @Override
    public String toString() {
        return "ID: " + id +
                ", Name: " + name +
                ", Price: " + price;
    }
}
