import db.Database;
import db.OrderDAO;
import db.ProductDAO;
import db.UserDAO;
import entity.Order;
import entity.Product;
import entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        OrderDAO db = new OrderDAO();
        Order o = new Order();
        List<Order> res = db.readAll();
        for(Order order : res) {
            System.out.println(order.getId() + order.getProjectId());
        }
    }
}