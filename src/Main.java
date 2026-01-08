import java.util.ArrayList;
import java.util.Scanner;

public class Main {

  private static ArrayList<Product> products = new ArrayList<>();
  private static ArrayList<Customer> customers = new ArrayList<>();
  private static ArrayList<Sale> sales = new ArrayList<>();
  private static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {

    products.add(new FreshProduct(1, "Apples", 450, 5));
    products.add(new PackagedProduct(2, "Rice", 1200, "Basmati"));
    products.add(new FrozenProduct(3, "Ice Cream", 900, -18));

    customers.add(new Customer(101, "Aruzhan", 8500));
    customers.add(new Customer(102, "Dias", 12000));

    boolean running = true;

    while (running) {
      showMenu();
      int choice = scanner.nextInt();
      scanner.nextLine();

      switch (choice) {
        case 1 -> viewProducts();
        case 2 -> addProduct();
        case 3 -> viewCustomers();
        case 4 -> addCustomer();
        case 5 -> createSale();
        case 6 -> viewSales();
        case 0 -> {
          running = false;
          System.out.println("Grocery Store System closed ");
        }
        default -> System.out.println("Invalid choice ");
      }
    }
    scanner.close();
  }


  private static void showMenu() {
    System.out.println("\n=== GROCERY STORE SYSTEM ===");
    System.out.println("1. View Products");
    System.out.println("2. Add Product");
    System.out.println("3. View Customers");
    System.out.println("4. Add Customer");
    System.out.println("5. Create Sale");
    System.out.println("6. View Sales");
    System.out.println("0. Exit");
    System.out.print("Choose option: ");
  }


  private static void viewProducts() {
    if (products.isEmpty()) {
      System.out.println("No products available.");
      return;
    }

    for (Product p : products) {
      System.out.println(p);
      System.out.println("Work(): " + p.work());


      if (p instanceof FreshProduct) {
        System.out.println("Category: Fresh Product");
      } else if (p instanceof PackagedProduct) {
        System.out.println("Category: Packaged Product");
      } else if (p instanceof FrozenProduct) {
        System.out.println("Category: Frozen Product");
      }

      System.out.println("--------------------------");
    }
  }

  private static void addProduct() {
    System.out.println("Select Product Type:");
    System.out.println("1. Fresh");
    System.out.println("2. Packaged");
    System.out.println("3. Frozen");
    System.out.print("Choice: ");
    int type = scanner.nextInt();
    scanner.nextLine();

    System.out.print("Enter ID: ");
    int id = scanner.nextInt();
    scanner.nextLine();

    System.out.print("Enter Name: ");
    String name = scanner.nextLine();

    System.out.print("Enter Price: ");
    double price = scanner.nextDouble();
    scanner.nextLine();

    switch (type) {
      case 1 -> {
        System.out.print("Enter Shelf Life (days): ");
        int days = scanner.nextInt();
        products.add(new FreshProduct(id, name, price, days));
      }
      case 2 -> {
        System.out.print("Enter Brand: ");
        String brand = scanner.nextLine();
        products.add(new PackagedProduct(id, name, price, brand));
      }
      case 3 -> {
        System.out.print("Enter Storage Temperature: ");
        int temp = scanner.nextInt();
        products.add(new FrozenProduct(id, name, price, temp));
      }
      default -> {
        System.out.println("Invalid product type ❌");
        return;
      }
    }

    System.out.println("Product added successfully ✅");
  }


  private static void viewCustomers() {
    if (customers.isEmpty()) {
      System.out.println("No customers found.");
      return;
    }

    for (Customer c : customers) {
      System.out.println(c);
    }
  }

  private static void addCustomer() {
    System.out.print("Enter Customer ID: ");
    int id = scanner.nextInt();
    scanner.nextLine();

    System.out.print("Enter Customer Name: ");
    String name = scanner.nextLine();

    customers.add(new Customer(id, name, 0));
    System.out.println("Customer added ✅");
  }

  private static void createSale() {
    if (customers.isEmpty() || products.isEmpty()) {
      System.out.println("Need customers and products to create a sale.");
      return;
    }

    System.out.print("Enter Sale ID: ");
    int saleId = scanner.nextInt();
    scanner.nextLine();

    System.out.println("Select Customer:");
    for (int i = 0; i < customers.size(); i++) {
      System.out.println((i + 1) + ". " + customers.get(i).getName());
    }

    int customerIndex = scanner.nextInt() - 1;
    scanner.nextLine();

    if (customerIndex < 0 || customerIndex >= customers.size()) {
      System.out.println("Invalid customer ❌");
      return;
    }

    Sale sale = new Sale(saleId, customers.get(customerIndex));

    boolean adding = true;
    while (adding) {
      viewProducts();
      System.out.print("Enter product number to add (0 to finish): ");
      int choice = scanner.nextInt();

      if (choice == 0) {
        adding = false;
      } else if (choice > 0 && choice <= products.size()) {
        sale.addProduct(products.get(choice - 1));
        System.out.println("Product added to sale ✅");
      } else {
        System.out.println("Invalid product ❌");
      }
    }

    sales.add(sale);
    System.out.println("Sale created successfully 🧾");
  }

  private static void viewSales() {
    if (sales.isEmpty()) {
      System.out.println("No sales recorded.");
      return;
    }

    for (Sale s : sales) {
      System.out.println(s);
    }
  }
}
