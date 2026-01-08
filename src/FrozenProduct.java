public class FrozenProduct extends Product {
    private int storageTemp;

    public FrozenProduct(int id, String name, double price, int storageTemp) {
        super(id, name, price);
        this.storageTemp = storageTemp;
    }

    @Override
    public String work() {
        return "Frozen product must be kept below zero.";
    }

    @Override
    public String toString() {
        return super.toString() +
                ", Storage Temp: " + storageTemp + "°C" +
                ", Type: Frozen";
    }
}
