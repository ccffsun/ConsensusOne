package db;

import entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    String query;

    public List<Product> readAll() throws SQLException {
        List<Product> myList = new ArrayList<>();
        query = "SELECT * FROM product";

        try(Connection con = Database.getConnection();
            PreparedStatement pst = con.prepareStatement(query)) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId (rs.getInt(1));
                p.setName(rs.getString(2));
                p.setUnit(rs.getString(3));
                p.setPrice(rs.getDouble(4));
                p.setFirstMonthPrice(rs.getDouble(5));
                p.setInventory(rs.getInt(6));
                myList.add(p);
            }
        }catch (SQLException ex) {
            throw ex;
        }
        return myList;
    }

    public void create(Product p) throws SQLException {
        query = "INSERT INTO product (id, name , unit, price, firstMonthPrice, inventory) VALUES (?,?,?,?,?,?)";

        try(Connection con =  Database.getConnection();
        PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1,p.getId());
            pst.setString(2,p.getName());
            pst.setString(3,p.getUnit());
            pst.setDouble(4,p.getPrice());
            pst.setDouble(5,p.getFirstMonthPrice());
            pst.setInt(6,p.getInventory());
            pst.execute();
        }catch (SQLException ex) {
            throw ex;
        }
    }

    public void update(Product p) throws  SQLException {
        query = "UPDATE product SET name = ?, unit = ?, price = ?, firstMonthPrice = ? ,inventory = ? WHERE id = ?";
        try(Connection con = Database.getConnection();
        PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1,p.getName());
            pst.setString(2,p.getUnit());
            pst.setDouble(3,p.getPrice());
            pst.setDouble(4,p.getFirstMonthPrice());
            pst.setInt(5,p.getInventory());
            pst.setInt(6, p.getId());
            pst.execute();
        }catch (SQLException ex) {
            throw ex;
        }
    }

    public  void delete(Product p) throws SQLException {
        query = "DELETE FROM product where id = ?";

        try(Connection con = Database.getConnection();
        PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1,p.getId());
            pst.execute();
        }catch (SQLException ex) {
            throw ex;
        }
    }


    public Product getProduct(int id) throws SQLException {
        query = "SELECT * FROM product where id = ?";
        Product p = null;
        try (Connection con =  Database.getConnection();
        PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1,id);
            ResultSet rs = pst.executeQuery();
            if(rs.next()) {
                p = new Product();
                p.setId(rs.getInt(1));
                p.setName(rs.getString(2));
                p.setUnit(rs.getString(3));
                p.setPrice(rs.getDouble(4));
                p.setFirstMonthPrice(rs.getDouble(5));
                p.setInventory(rs.getInt(6));
            }
            return p;
        }catch (SQLException ex) {
            throw ex;
        }
    }
}
