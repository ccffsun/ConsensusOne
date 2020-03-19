package db;

import entity.Project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectDAO {
    String query;

    public List<Project> readAll() throws SQLException {
        List<Project> myList = new ArrayList<>();
        query = "SELECT * FROM project";

        try (Connection con = Database.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Project p = new Project();
                p.setId(rs.getInt(1));
                p.setProductId(rs.getInt(2));
                p.setUserId(rs.getInt(3));
                p.setStatus(rs.getString(4));
                p.setStartDate(rs.getDate(5));

                myList.add(p);
            }
        } catch (SQLException ex) {
            throw ex;
        }
        return myList;
    }

    public void creat(Project p) throws SQLException {
        query = "INSERT INTO project (id, productId , userId, status, startDate) VALUES (?,?,?,?,?)";

        try (Connection con = Database.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, p.getId());
            pst.setInt(2, p.getProductId());
            pst.setInt(3,p.getUserId());
            pst.setString(4,p.getStatus());
            pst.setDate(5, (Date) p.getStartDate());

            pst.execute();
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public void update(Project p) throws SQLException {
        query = "UPDATE project SET  productId= ?, userId = ?, status = ?, startDate = ? ";
        try (Connection con = Database.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(2, p.getProductId());
            pst.setInt(3,p.getUserId());
            pst.setString(4,p.getStatus());
            pst.setDate(5, (Date) p.getStartDate());
            pst.execute();
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public void delete(Project p) throws SQLException {
        query = "DELETE FROM project where id = ?";

        try (Connection con = Database.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, p.getId());
            pst.execute();
        } catch (SQLException ex) {
            throw ex;
        }
    }


    public Project getProject(int id) throws SQLException {
        query = "SELECT * FROM project where id = ?";
        Project p = new Project();
        try (Connection con = Database.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                p.setId(rs.getInt(1));
                p.setProductId(rs.getInt(2));
                p.setUserId(rs.getInt(3));
                p.setStatus(rs.getString(4));
                p.setStartDate(rs.getDate(5));
            }
            return p;
        } catch (SQLException ex) {
            throw ex;
        }
    }
}