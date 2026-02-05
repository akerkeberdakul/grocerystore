package database;

import database.ProductDAO;
import model.FreshProduct;

public class TestInsert {
    public static void main(String[] args) throws Exception {

        ProductDAO dao = new ProductDAO();

        dao.insertFreshProduct(
                new FreshProduct(0, "Milk", 450, 5)
        );
    }
}
