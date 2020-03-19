package db;

import entity.Order;
import entity.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    String query;

    public List<Order> readAll() throws SQLException {
            List<Order> myList = new ArrayList<>();
            query = "SELECT * FROM `order`";

            try(Connection con = Database.getConnection();
                PreparedStatement pst = con.prepareStatement(query)) {
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    Order o = new Order();
                    o.setId (rs.getInt(1));
                    o.setProductId(rs.getInt(2));
                    o.setDate(rs.getDate(3));
                    o.setType(rs.getInt(4));
                    o.setQuantity(rs.getInt(5));
                    o.setProjectId(rs.getInt(6));
                    myList.add(o);
                }
            }catch (SQLException ex) {
                throw ex;
            }
            return myList;
        }

        public void create(Order o) throws SQLException {
            query = "INSERT INTO order (id, productId , orderDate, type, quantity, projectId) VALUES (?,?,?,?,?,?)";

            try(Connection con =  Database.getConnection();
                PreparedStatement pst = con.prepareStatement(query)) {
                pst.setInt(1,o.getId());
                pst.setInt(2,o.getProductId());
                pst.setDate(3, (Date) o.getDate());
                pst.setInt(4,o.getType());
                pst.setInt(5,o.getQuantity());
                pst.setInt(6,o.getProjectId());
                pst.execute();
            }catch (SQLException ex) {
                throw ex;
            }
        }

        public void update(Order o) throws  SQLException {
            query = "UPDATE order SET  productId= ?, orderDate = ?, type = ?, quantity= ? ,projectId=?";
            try(Connection con = Database.getConnection();
                PreparedStatement pst = con.prepareStatement(query)) {
                pst.setInt(2,o.getProductId());
                pst.setDate(3, (Date) o.getDate());
                pst.setInt(4,o.getType());
                pst.setInt(5,o.getQuantity());
                pst.setInt(6,o.getProjectId());
                pst.execute();
            }catch (SQLException ex) {
                throw ex;
            }
        }

        public  void delete(Order o) throws SQLException {
            query = "DELETE FROM order where id = ?";

            try(Connection con = Database.getConnection();
                PreparedStatement pst = con.prepareStatement(query)) {
                pst.setInt(1,o.getId());
                pst.execute();
            }catch (SQLException ex) {
                throw ex;
            }
        }


        public Order getOrder(int id) throws SQLException {
            query = "SELECT * FROM order where id = ?";
            Order o = new Order();
            try (Connection con =  Database.getConnection();
                 PreparedStatement pst = con.prepareStatement(query)) {
                pst.setInt(1,id);
                ResultSet rs = pst.executeQuery();
                if(rs.next()) {
                    o.setId(rs.getInt(1));
                    o.setProductId(rs.getInt(2));
                    o.setDate(rs.getDate(3));
                    o.setType(rs.getInt(4));
                    o.setQuantity(rs.getInt(5));
                    o.setProjectId(rs.getInt(6));
                }
                return o;
            }catch (SQLException ex) {
                throw ex;
            }
    }
}

    
    
    

