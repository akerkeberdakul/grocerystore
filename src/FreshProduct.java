public class FreshProduct extends Product {
    private int shelfLifeDays;

    public FreshProduct(int id, String name, double price, int shelfLifeDays) {
        super(id, name, price);
        this.shelfLifeDays = shelfLifeDays;
    }

    @Override
    public String work() {
        return "Fresh product must be sold quickly.";
    }

    @Override
    public String toString() {
        return super.toString() +
                ", Shelf Life: " + shelfLifeDays + " days" +
                ", Type: Fresh";
    }
}
