package menu;

import model.*;
import java.util.ArrayList;
import java.util.Scanner;
import exception.InvalidInputException;

public class GroceryMenu implements Menu{

    private ArrayList<Product> products;
    private ArrayList<Customer> customers;
    private ArrayList<Sale> sales;
    private Scanner scanner;

    public GroceryMenu(){
        this.products = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.sales = new ArrayList<>();
        this.scanner = new Scanner(System.in);

        products.add(new FreshProduct(1,"Apples",450, 5));
        products.add(new PackagedProduct(2,"Rice",1200,"Basmati"));
        products.add(new FrozenProduct(3,"Ice Cream",900,-18));

        customers.add(new Customer(101,"Aruzhan",8500));
        customers.add(new Customer(102,"Dias",12000));
    }
    @Override
    public void displayMenu(){
        System.out.println("\n======================================");
        System.out.println("           GROCERY STORE SYSTEM");
        System.out.println("========================================");
        System.out.println("1. Add products");
        System.out.println("2. View Products");
        System.out.println("3. Add model.Customer");
        System.out.println("4. View Customers");
        System.out.println("5. Create model.Sale");
        System.out.println("6. View Sales");
        System.out.println("7. Demonstrate Polymorphism");
        System.out.println("0. Exit");
        System.out.println("========================================");
    }
    @Override
    public void run(){
        boolean running = true;

        while(running){
            displayMenu();
            System.out.println("Enter choice");

            try{
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch(choice){
                    case 1:
                        addProduct();
                        break;
                    case 2:
                        viewProducts();
                        break;
                    case 3:
                        addCustomer();
                        break;
                    case 4:
                        viewCustomers();
                        break;
                    case 5:
                        createSale();
                        break;
                    case 6:
                        viewSales();
                        break;
                    case 7:
                        demonstratePolymorphism();
                        break;
                    case 0:
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice!");
                }
            } catch(Exception e){
                System.out.println("Error: "+e.getMessage());
                scanner.nextLine();
            }
        }
        scanner.close();
    }
    private void addProduct(){
        try{
            System.out.println("1.Fresh 2.Packaged 3.Frozen");
            System.out.print("Type: ");
            int type = scanner.nextInt();
            scanner.nextLine();

            System.out.print("ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Price: ");
            double price = scanner.nextDouble();
            scanner.nextLine();

            Product product = null;

            switch (type){
                case 1:
                System.out.println("Shelf life days: ");
                int days = scanner.nextInt();
                product = new FreshProduct(id, name, price, days);
                    break;
                case 2:
                    System.out.print("Brand: ");
                    String brand = scanner.nextLine();
                    product = new PackagedProduct(id, name, price, brand);
                    break;
                case 3:
                    System.out.print("Storage temperature: ");
                    int temp = scanner.nextInt();
                    product = new FrozenProduct(id, name, price, temp);
                    break;
                default:
                    System.out.println("Invalid product type");
                    return;
            }

            products.add(product);
            System.out.println("model.Product added");
        } catch (IllegalArgumentException e) {
            System.out.println("Not acceptable" + e.getMessage());
        }
    }

    private void viewProducts(){
        for(Product p: products){
            p.displayInfo();
            System.out.println("Category:"+p.getCategory());
            System.out.println("Work():"+p.work());
            System.out.println("-------------------------");
        }
    }
    private void addCustomer() {
        try {
            System.out.print("ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Name: ");
            String name = scanner.nextLine();

            customers.add(new Customer(id, name, 0));
            System.out.println("model.Customer added ");

        } catch (IllegalArgumentException e) {
            System.out.println("Not acceptable " + e.getMessage());
        }
    }
    private void viewCustomers() {
        for (Customer c : customers) {
            System.out.println(c);
        }
    }
    private void createSale() {
        System.out.print("model.Sale ID: ");
        int saleId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Select customer:");
        for (int i = 0; i < customers.size(); i++) {
            System.out.println((i + 1) + ". " + customers.get(i).getName());
        }

        int index = scanner.nextInt() - 1;
        scanner.nextLine();
        Sale sale = new Sale(saleId, customers.get(index));

        boolean adding = true;
        while (adding) {
            viewProducts();
            System.out.print("model.Product number (0 to stop): ");
            int choice = scanner.nextInt();

            if (choice == 0) {
                adding = false;
            } else {
                sale.addProduct(products.get(choice - 1));
            }
        }

        sales.add(sale);
        System.out.println("model.Sale created 🧾");
    }
    private void viewSales() {
        for (Sale s : sales) {
            System.out.println(s);
        }
    }
    private void demonstratePolymorphism() {
        System.out.println("\n--- POLYMORPHISM DEMO ---");
        for (Product p : products) {
            System.out.println(p.getName() + " → " + p.work());
        }
    }
}

