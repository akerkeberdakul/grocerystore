public class Sale {
    private int saleId;
    private Customer customer;
    private double totalAmount;

    public Sale(int saleId, Customer customer) {
        this.saleId = saleId;
        this.customer = customer;
        this.totalAmount = 0;
    }

    public void addProduct(Product product) {
        if (product != null) {
            totalAmount += product.getPrice();
            customer.addPurchase(product.getPrice());
        }
    }

    @Override
    public String toString() {
        return "Sale ID: " + saleId +
                ", Customer: " + customer.getName() +
                ", Total: " + totalAmount;
    }
}
