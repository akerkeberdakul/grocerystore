package database;

import model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    public boolean insertFreshProduct(FreshProduct product) {
        String sql = "INSERT INTO product (name, price, category, shelf_life_days) " +
                "VALUES (?, ?, 'FRESH', ?)";
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return false;

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPrice());
            stmt.setInt(3, product.getShelfLifeDays());

            int rows = stmt.executeUpdate();
            stmt.close();

            if (rows > 0) {
                System.out.println(" Fresh product added: " + product.getName());
                return true;
            }

        } catch (SQLException e) {
            System.out.println(" Insert Fresh Product failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(conn);
        }

        return false;
    }

    public boolean insertPackagedProduct(PackagedProduct product) {
        String sql = "INSERT INTO product (name, price, category, brand) " +
                "VALUES (?, ?, 'PACKAGED', ?)";
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return false;

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPrice());
            stmt.setString(3, product.getBrand());

            int rows = stmt.executeUpdate();
            stmt.close();

            if (rows > 0) {
                System.out.println(" Packaged product added: " + product.getName());
                return true;
            }

        } catch (SQLException e) {
            System.out.println(" Insert Packaged Product failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(conn);
        }

        return false;
    }

    public boolean insertFrozenProduct(FrozenProduct product) {
        String sql = "INSERT INTO product (name, price, category, storage_temp) " +
                "VALUES (?, ?, 'FROZEN', ?)";
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return false;

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPrice());
            stmt.setInt(3, product.getStorageTemp());

            int rows = stmt.executeUpdate();
            stmt.close();

            if (rows > 0) {
                System.out.println(" Frozen product added: " + product.getName());
                return true;
            }

        } catch (SQLException e) {
            System.out.println(" Insert Frozen Product failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(conn);
        }

        return false;
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product ORDER BY id";
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return products;

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Product p = extractProductFromResultSet(rs);
                if (p != null) products.add(p);
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            System.out.println(" Fetch all products failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(conn);
        }

        return products;
    }

    public Product getProductById(int id) {
        String sql = "SELECT * FROM product WHERE id = ?";
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return null;

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Product p = extractProductFromResultSet(rs);
                rs.close();
                stmt.close();
                if (p != null) System.out.println(" Found product: " + p.getName());
                return p;
            }

            rs.close();
            stmt.close();
            System.out.println(" No product found with ID: " + id);

        } catch (SQLException e) {
            System.out.println(" Fetch product by ID failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(conn);
        }

        return null;
    }


    public boolean updateFreshProduct(FreshProduct product) {
        String sql = "UPDATE product SET name = ?, price = ?, shelf_life_days = ? " +
                "WHERE id = ? AND category = 'FRESH'";
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return false;

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPrice());
            stmt.setInt(3, product.getShelfLifeDays());
            stmt.setInt(4, product.getId());

            int rows = stmt.executeUpdate();
            stmt.close();

            if (rows > 0) {
                System.out.println(" Fresh product updated: " + product.getName());
                return true;
            } else {
                System.out.println(" No Fresh product found with ID: " + product.getId());
            }

        } catch (SQLException e) {
            System.out.println(" Update Fresh Product failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(conn);
        }

        return false;
    }

    public boolean updatePackagedProduct(PackagedProduct product) {
        String sql = "UPDATE product SET name = ?, price = ?, brand = ? " +
                "WHERE id = ? AND category = 'PACKAGED'";
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return false;

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPrice());
            stmt.setString(3, product.getBrand());
            stmt.setInt(4, product.getId());

            int rows = stmt.executeUpdate();
            stmt.close();

            if (rows > 0) {
                System.out.println(" Packaged product updated: " + product.getName());
                return true;
            } else {
                System.out.println("⚠ No Packaged product found with ID: " + product.getId());
            }

        } catch (SQLException e) {
            System.out.println(" Update Packaged Product failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(conn);
        }

        return false;
    }

    public boolean updateFrozenProduct(FrozenProduct product) {
        String sql = "UPDATE product SET name = ?, price = ?, storage_temp = ? " +
                "WHERE id = ? AND category = 'FROZEN'";
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return false;

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPrice());
            stmt.setInt(3, product.getStorageTemp());
            stmt.setInt(4, product.getId());

            int rows = stmt.executeUpdate();
            stmt.close();

            if (rows > 0) {
                System.out.println(" Frozen product updated: " + product.getName());
                return true;
            } else {
                System.out.println(" No Frozen product found with ID: " + product.getId());
            }

        } catch (SQLException e) {
            System.out.println(" Update Frozen Product failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(conn);
        }

        return false;
    }

    public boolean deleteProduct(int id) {
        String sql = "DELETE FROM product WHERE id = ?";
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return false;

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            int rows = stmt.executeUpdate();
            stmt.close();

            if (rows > 0) {
                System.out.println(" Product deleted (ID: " + id + ")");
                return true;
            } else {
                System.out.println(" No product found with ID: " + id);
            }

        } catch (SQLException e) {
            System.out.println(" Delete Product failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(conn);
        }

        return false;
    }


    public List<Product> searchByName(String name) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE name ILIKE ? ORDER BY name";
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return products;

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + name + "%");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Product p = extractProductFromResultSet(rs);
                if (p != null) products.add(p);
            }

            rs.close();
            stmt.close();

            System.out.println(" Found " + products.size() + " product(s) matching: '" + name + "'");

        } catch (SQLException e) {
            System.out.println(" Search by name failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(conn);
        }

        return products;
    }

    public List<Product> searchByPriceRange(double min, double max) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE price BETWEEN ? AND ? ORDER BY price";
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return products;

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, min);
            stmt.setDouble(2, max);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Product p = extractProductFromResultSet(rs);
                if (p != null) products.add(p);
            }

            rs.close();
            stmt.close();

            System.out.println(" Found " + products.size() + " product(s) in price range " + min + " - " + max);

        } catch (SQLException e) {
            System.out.println(" Search by price range failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(conn);
        }

        return products;
    }

    public List<Product> searchByMinPrice(double min) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE price >= ? ORDER BY price";
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return products;

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, min);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Product p = extractProductFromResultSet(rs);
                if (p != null) products.add(p);
            }

            rs.close();
            stmt.close();

            System.out.println(" Found " + products.size() + " product(s) with price >= " + min);

        } catch (SQLException e) {
            System.out.println(" Search by min price failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(conn);
        }

        return products;
    }

    public void demonstratePolymorphism() {
        List<Product> products = getAllProducts();
        System.out.println("\n========================================");
        System.out.println("  POLYMORPHISM: Products from Database");
        System.out.println("========================================");

        if (products.isEmpty()) {
            System.out.println("No products to demonstrate.");
        } else {
            for (Product p : products) p.work();
        }

        System.out.println("========================================\n");
    }

    private Product extractProductFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        double price = rs.getDouble("price");
        String category = rs.getString("category");

        return switch (category) {
            case "FRESH" -> new FreshProduct(id, name, price, rs.getInt("shelf_life_days"));
            case "PACKAGED" -> new PackagedProduct(id, name, price, rs.getString("brand"));
            case "FROZEN" -> new FrozenProduct(id, name, price, rs.getInt("storage_temp"));
            default -> null;
        };
    }
}
