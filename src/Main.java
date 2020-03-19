import db.Database;
import db.OrderDAO;
import db.ProductDAO;
import db.UserDAO;
import entity.Order;
import entity.Product;
import entity.User;
import ui.Constant;
import ui.Page;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args){
        Page p = new Page();
        p.run();
    }
}