package db;

import entity.ProjectBookkeeping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectBookkeepingDAO {

    String query;

    public List<ProjectBookkeeping> readAll() throws SQLException {
        List<ProjectBookkeeping> myList = new ArrayList<>();
        query = "SELECT * FROM projectBookkeeping";

        try(Connection con = Database.getConnection();
            PreparedStatement pst = con.prepareStatement(query)) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                ProjectBookkeeping p = new ProjectBookkeeping();
                p.setId (rs.getInt(1));
                p.setProjectId(rs.getInt(2));
                p.setProductId(rs.getInt(3));
                p.setQuantity(rs.getInt(4));
                myList.add(p);
            }
        }catch (SQLException ex) {
            throw ex;
        }
        return myList;
    }

    public void create(ProjectBookkeeping p) throws SQLException {
        query = "INSERT INTO projectBookkeeping (projectId , productId, quantity) VALUES (?,?,?)";

        try(Connection con =  Database.getConnection();
            PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1,p.getProjectId());
            pst.setInt(2,p.getProductId());
            pst.setInt(3,p.getQuantity());
            pst.execute();
        }catch (SQLException ex) {
            throw ex;
        }
    }

    public void update(ProjectBookkeeping p) throws  SQLException {
        query = "UPDATE projectBookkeeping SET projectId = ?, productId = ?, quantity = ? WHERE id = ?";
        try(Connection con = Database.getConnection();
            PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1,p.getProjectId());
            pst.setInt(2,p.getProductId());
            pst.setInt(3,p.getQuantity());
            pst.setInt(4, p.getId());
            pst.execute();
        }catch (SQLException ex) {
            throw ex;
        }
    }

    public  void delete(ProjectBookkeeping p) throws SQLException {
        query = "DELETE FROM projectBookkeeping where id = ?";

        try(Connection con = Database.getConnection();
            PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1,p.getId());
            pst.execute();
        }catch (SQLException ex) {
            throw ex;
        }
    }


    public ProjectBookkeeping getProjectBookkeeping(int id) throws SQLException {
        query = "SELECT * FROM projectBookkeeping where id = ?";
        ProjectBookkeeping p = new ProjectBookkeeping();
        try (Connection con =  Database.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1,id);
            ResultSet rs = pst.executeQuery();
            if(rs.next()) {
                p.setId(rs.getInt(1));
                p.setProjectId(rs.getInt(2));
                p.setProductId(rs.getInt(3));
                p.setQuantity(rs.getInt(4));
            }
            return p;
        }catch (SQLException ex) {
            throw ex;
        }
    }

    public ProjectBookkeeping getProjectBookkeeping(int projectId, int productId) throws SQLException {
        query = "SELECT * FROM projectBookkeeping where projectId = ? AND productId = ?";
        ProjectBookkeeping p = null;
        try (Connection con =  Database.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, projectId);
            pst.setInt(2, productId);
            ResultSet rs = pst.executeQuery();
            if(rs.next()) {
                p = new ProjectBookkeeping();
                p.setId(rs.getInt(1));
                p.setProjectId(rs.getInt(2));
                p.setProductId(rs.getInt(3));
                p.setQuantity(rs.getInt(4));
            }
            return p;
        }catch (SQLException ex) {
            throw ex;
        }
    }
}
