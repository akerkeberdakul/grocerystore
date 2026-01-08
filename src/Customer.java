public class Customer {
    private int customerId;
    private String name;
    private double totalSpent;

    public Customer(int customerId, String name, double totalSpent) {
        this.customerId = customerId;
        setName(name);
        setTotalSpent(totalSpent);
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public double getTotalSpent() {
        return totalSpent;
    }

    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        } else {
            this.name = "Unknown Customer";
        }
    }

    public void setTotalSpent(double totalSpent) {
        if (totalSpent >= 0) {
            this.totalSpent = totalSpent;
        } else {
            this.totalSpent = 0;
        }
    }

    public void addPurchase(double amount) {
        if (amount > 0) {
            totalSpent += amount;
        }
    }

    public boolean isVIP() {
        return totalSpent >= 10000;
    }

    @Override
    public String toString() {
        return "Customer ID: " + customerId +
                ", Name: " + name +
                ", Total Spent: " + totalSpent +
                ", VIP: " + (isVIP() ? "Yes" : "No");
    }
}
