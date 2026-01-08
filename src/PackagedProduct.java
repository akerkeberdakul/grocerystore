public class PackagedProduct extends Product {
    private String brand;

    public PackagedProduct(int id, String name, double price, String brand) {
        super(id, name, price);
        this.brand = brand;
    }

    @Override
    public String work() {
        return "Packaged product has long shelf stability.";
    }

    @Override
    public String toString() {
        return super.toString() +
                ", Brand: " + brand +
                ", Type: Packaged";
    }
}
