import db.Database;
import db.ProductDAO;
import db.UserDAO;
import entity.Product;
import entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ProductDAO db = new ProductDAO();
        Product p = new Product();
        p.setId(1);
        p.setName("");





    }
}