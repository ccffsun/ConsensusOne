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
                ProjectBookkeeping p = resultSetToProjectBookkeeping(rs);
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
        ProjectBookkeeping p = null;
        try (Connection con =  Database.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1,id);
            ResultSet rs = pst.executeQuery();
            if(rs.next()) {
                p = resultSetToProjectBookkeeping(rs);
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
                p = resultSetToProjectBookkeeping(rs);
            }
            return p;
        }catch (SQLException ex) {
            throw ex;
        }
    }

    public List<ProjectBookkeeping> getProjectBookkeepings(int projectId) throws SQLException {
        query = "SELECT * FROM projectBookkeeping where projectId = ?";
        List<ProjectBookkeeping> bookkeepings = new ArrayList<>();
        try (Connection con =  Database.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, projectId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                ProjectBookkeeping pb = resultSetToProjectBookkeeping(rs);
                bookkeepings.add(pb);
            }
            return bookkeepings;
        }catch (SQLException ex){
            throw ex;
        }
    }

    private ProjectBookkeeping resultSetToProjectBookkeeping(ResultSet rs) throws SQLException {
        ProjectBookkeeping pb = new ProjectBookkeeping();
        pb.setId(rs.getInt(1));
        pb.setProjectId(rs.getInt(2));
        pb.setProductId(rs.getInt(3));
        pb.setQuantity(rs.getInt(4));
        return pb;
    }
}
