package menu;

import model.*;
import database.*;
import java.util.List;
import java.util.Scanner;

public class GroceryMenu {
    private Scanner scanner;
    private ProductDAO productDAO;

    public GroceryMenu() {
        this.scanner = new Scanner(System.in);
        this.productDAO = new ProductDAO();
    }

    public void displayMenu() {
        System.out.println(" \n-----------GROCERY MENU--------------");
        System.out.println(" 1. Add Fresh Product                   ");
        System.out.println(" 2. Add Frozen Product                  ");
        System.out.println(" 3. Add Packaged Product                ");
        System.out.println(" 4. View All Products                   ");
        System.out.println(" 5. Update Product                      ");
        System.out.println(" 6. Delete Product                      ");
        System.out.println(" 7. Search by Name                      ");
        System.out.println(" 8. Search by Price Range               ");
        System.out.println(" 9. High-Priced Products (>= X)         ");
        System.out.println("10. Polymorphism Demo                   ");
        System.out.println("0. Exit                                 ");
    }

    public void run() {
        boolean running = true;

        while (running) {
            displayMenu();
            System.out.print("\n Enter your choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> addFreshProduct();
                    case 2 -> addFrozenProduct();
                    case 3 -> addPackagedProduct();
                    case 4 -> viewAllProducts();
                    case 5 -> updateProduct();
                    case 6 -> deleteProduct();
                    case 7 -> searchByName();
                    case 8 -> searchByPriceRange();
                    case 9 -> searchHighPricedProducts();
                    case 10 -> demonstratePolymorphism();
                    case 0 -> {
                        running = false;
                    }
                    default -> System.out.println(" Invalid choice! Please select 0-10.");
                }

                if (choice != 0) pressEnterToContinue();

            } catch (java.util.InputMismatchException e) {
                System.out.println(" Error: Please enter a valid number!");
                scanner.nextLine();
                pressEnterToContinue();
            } catch (Exception e) {
                System.out.println(" Error: " + e.getMessage());
                scanner.nextLine();
                pressEnterToContinue();
            }
        }

        scanner.close();
    }

    private void addFreshProduct() {
        try {
            System.out.println("\n--ADD FRESH PRODUCT -----------------------");
            System.out.print("│ Enter Product ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("│ Enter Name: ");
            String name = scanner.nextLine();

            System.out.print("│ Enter Price: ");
            double price = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("│ Enter Shelf Life (days): ");
            int shelfLife = scanner.nextInt();
            scanner.nextLine();

            System.out.println("----------------------------------------------");

            FreshProduct product = new FreshProduct(id, name, price, shelfLife);
            productDAO.insertFreshProduct(product);

        } catch (java.util.InputMismatchException e) {
            System.out.println(" Invalid input type!");
            scanner.nextLine();
        }
    }

    private void addFrozenProduct() {
        try {
            System.out.println("\n-- ADD FROZEN PRODUCT ----------------------");
            System.out.print(" Enter Product ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("│ Enter Name: ");
            String name = scanner.nextLine();

            System.out.print(" Enter Price: ");
            double price = scanner.nextDouble();
            scanner.nextLine();

            System.out.print(" Enter Storage Temperature (°C): ");
            int temp = scanner.nextInt();
            scanner.nextLine();

            System.out.println("-------------------------------------------");

            FrozenProduct product = new FrozenProduct(id, name, price, temp);
            productDAO.insertFrozenProduct(product);

        } catch (java.util.InputMismatchException e) {
            System.out.println(" Invalid input type!");
            scanner.nextLine();
        }
    }

    private void addPackagedProduct() {
        try {
            System.out.println("\n-- ADD PACKAGED PRODUCT ----------------------");
            System.out.print("│ Enter Product ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("│ Enter Name: ");
            String name = scanner.nextLine();

            System.out.print("│ Enter Price: ");
            double price = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("│ Enter Brand: ");
            String brand = scanner.nextLine();

            System.out.println("-----------------------------------------------");

            PackagedProduct product = new PackagedProduct(id, name, price, brand);
            productDAO.insertPackagedProduct(product);

        } catch (java.util.InputMismatchException e) {
            System.out.println(" Invalid input type!");
            scanner.nextLine();
        }
    }


    private void viewAllProducts() {
        List<Product> products = productDAO.getAllProducts();

        System.out.println("\n------------------------------------------");
        System.out.println("         ALL PRODUCTS                    ");
        System.out.println("-------------------------------------------");

        if (products.isEmpty()) {
            System.out.println(" No products in database.");
        } else {
            for (Product p : products) {
                System.out.println(p.toString());
            }
            System.out.println("Total Products: " + products.size());
        }
    }

    private void updateProduct() {
        try {
            System.out.println("\n-- UPDATE PRODUCT ------------------------");
            System.out.print("│ Enter Product ID to update: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            Product existing = productDAO.getProductById(id);
            if (existing == null) {
                System.out.println(" No product found with ID: " + id);
                return;
            }

            System.out.println(" Current Info:");
            System.out.println(" " + existing.toString());
            System.out.println("-----------------------------------------");

            System.out.println("\n--ENTER NEW VALUES --------------------");
            System.out.println("│ (Press Enter to keep current value)");

            System.out.print("│ New Name [" + existing.getName() + "]: ");
            String newName = scanner.nextLine();
            if (newName.isEmpty()) newName = existing.getName();

            System.out.print("│ New Price [" + existing.getPrice() + "]: ");
            String priceInput = scanner.nextLine();
            double newPrice = priceInput.isEmpty() ? existing.getPrice() : Double.parseDouble(priceInput);

            if (existing instanceof FreshProduct fp) {
                System.out.print("│ New Shelf Life [" + fp.getShelfLifeDays() + "]: ");
                String slInput = scanner.nextLine();
                int newShelfLife = slInput.isEmpty() ? fp.getShelfLifeDays() : Integer.parseInt(slInput);
                FreshProduct updated = new FreshProduct(id, newName, newPrice, newShelfLife);
                productDAO.updateFreshProduct(updated);

            } else if (existing instanceof FrozenProduct fr) {
                System.out.print("│ New Storage Temp [" + fr.getStorageTemp() + "]: ");
                String tInput = scanner.nextLine();
                int newTemp = tInput.isEmpty() ? fr.getStorageTemp() : Integer.parseInt(tInput);
                FrozenProduct updated = new FrozenProduct(id, newName, newPrice, newTemp);
                productDAO.updateFrozenProduct(updated);

            } else if (existing instanceof PackagedProduct pp) {
                System.out.print("│ New Brand [" + pp.getBrand() + "]: ");
                String bInput = scanner.nextLine();
                String newBrand = bInput.isEmpty() ? pp.getBrand() : bInput;
                PackagedProduct updated = new PackagedProduct(id, newName, newPrice, newBrand);
                productDAO.updatePackagedProduct(updated);
            }

            System.out.println("---------------------------------------");

        } catch (NumberFormatException e) {
            System.out.println("Invalid number format!");
        }
    }

    private void deleteProduct() {
        try {
            System.out.println("\n-- DELETE PRODUCT -----------------------");
            System.out.print("│ Enter Product ID to delete: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            Product existing = productDAO.getProductById(id);
            if (existing == null) {
                System.out.println(" No product found with ID: " + id);
                return;
            }

            System.out.println("│ Product to delete:");
            System.out.println("│ " + existing.toString());
            System.out.println("└────────────────────────────────────────┘");

            System.out.print("  Are you sure? (yes/no): ");
            String confirm = scanner.nextLine();
            if (confirm.equalsIgnoreCase("yes")) {
                productDAO.deleteProduct(id);
            } else {
                System.out.println(" Deletion cancelled.");
            }

        } catch (java.util.InputMismatchException e) {
            System.out.println(" Invalid input!");
            scanner.nextLine();
        }
    }


    private void searchByName() {
        System.out.print("Enter product name to search: ");
        String name = scanner.nextLine();
        List<Product> results = productDAO.searchByName(name);
        displaySearchResults(results, "Name contains '" + name + "'");
    }

    private void searchByPriceRange() {
        try {
            System.out.print("Enter min price: ");
            double min = scanner.nextDouble();
            System.out.print("Enter max price: ");
            double max = scanner.nextDouble();
            scanner.nextLine();

            List<Product> results = productDAO.searchByPriceRange(min, max);
            displaySearchResults(results, "Price: " + min + " - " + max);

        } catch (java.util.InputMismatchException e) {
            System.out.println(" Invalid number!");
            scanner.nextLine();
        }
    }

    private void searchHighPricedProducts() {
        try {
            System.out.print("Enter minimum price: ");
            double min = scanner.nextDouble();
            scanner.nextLine();

            List<Product> results = productDAO.searchByMinPrice(min);
            displaySearchResults(results, "Price >= " + min);

        } catch (java.util.InputMismatchException e) {
            System.out.println(" Invalid number!");
            scanner.nextLine();
        }
    }

    private void displaySearchResults(List<Product> results, String criteria) {
        System.out.println("\n---------------------------------------");
        System.out.println("         SEARCH RESULTS                ");
        System.out.println("------------------------------------------");
        System.out.println("Criteria: " + criteria);
        System.out.println("-------------------------------------------");

        if (results.isEmpty()) {
            System.out.println(" No products found.");
        } else {
            for (Product p : results) {
                System.out.println(p.toString());
            }
            System.out.println("---------------------------------------");
            System.out.println("Total Results: " + results.size());
        }
    }

    private void demonstratePolymorphism() {
        productDAO.demonstratePolymorphism();
    }

    private void pressEnterToContinue() {
        System.out.println("\n[Press Enter to continue...]");
        scanner.nextLine();
    }
}
