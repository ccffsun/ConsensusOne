package db;

import entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    String query;

    public List<User> readAll() throws SQLException {
        List<User> myList = new ArrayList<User>();
        query = "SELECT * from user";

        try (Connection con = Database.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt(1));
                u.setName(rs.getString(2));
                u.setEmail(rs.getString(3));
                u.setTel(rs.getString(4));
                myList.add(u);
            }
        } catch (SQLException ex) {
            throw ex;
        }

        return myList;
    }

    public void create(User u) throws SQLException {
        query = "INSERT INTO user (id, name, email, tel) VALUES (?,?,?,?)";
/*
        Connection con;
        PreparedStatement pst;
        try {
            con = Database.getConnection();
            pst = con.prepareStatement(query);
            pst.setInt(1, u.getId());
            pst.setString(2, u.getName());
            pst.setString(3, u.getEmail());
            pst.setString(4, u.getTel());
            pst.execute();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            pst.close();
            con.close();
        }
 */

        try (Connection con = Database.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, u.getId());
            pst.setString(2, u.getName());
            pst.setString(3, u.getEmail());
            pst.setString(4, u.getTel());
            pst.execute();
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public void delete(User u) throws SQLException {
        query = "DELETE FROM user where id = ?";
        try(Connection con = Database.getConnection();
        PreparedStatement pst =  con.prepareStatement(query)) {
            pst.setInt(1, u.getId());
            pst.execute();
        }catch (SQLException ex) {
            throw ex;
        }
    }

    public void update(User u) throws SQLException {
        query = "update user set name=?, email=?, tel=? where id=?";
        try(Connection con = Database.getConnection();
        PreparedStatement pst =  con.prepareStatement(query)){
            pst.setString(1, u.getName());
            pst.setString(2, u.getEmail());
            pst.setString(3, u.getTel());
            pst.setInt(4, u.getId());
            pst.execute();
        }catch (SQLException ex) {
            throw ex;
        }
    }

    public User getUser(int id) throws SQLException {
        query = "select * from user where id = ?";
        User u = new User();
        try(Connection con = Database.getConnection();
            PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                u.setId(rs.getInt(1));
                u.setName(rs.getString(2));
                u.setEmail(rs.getString(3));
                u.setTel(rs.getString(4));
            }

            return u;
        }catch (SQLException ex) {
            throw ex;
        }
    }
}
