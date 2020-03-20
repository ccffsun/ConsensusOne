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
                p.setUserId(rs.getInt(2));
                p.setStatus(rs.getString(3));
                p.setStartDate(rs.getDate(4));
                p.setEndDate(rs.getDate(5));
                p.setProjectName(rs.getString(6));

                myList.add(p);
            }
        } catch (SQLException ex) {
            throw ex;
        }
        return myList;
    }

    public void create(Project p) throws SQLException {
        query = "INSERT INTO project (id,  userId, status, startDate, endDate, projectName) VALUES (?,?,?,?,?,?)";

        try (Connection con = Database.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, p.getId());
            pst.setInt(2,p.getUserId());
            pst.setString(3,p.getStatus());
            pst.setDate(4, (Date) p.getStartDate());
            pst.setDate(5, (Date) p.getEndDate());
            pst.setString(6,p.getProjectName());

            pst.execute();
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public void update(Project p) throws SQLException {
        query = "UPDATE project SET   userId = ?, status = ?, startDate = ? ,endDate = ?, projectName = ? WHERE id = ?";
        try (Connection con = Database.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1,p.getUserId());
            pst.setString(2,p.getStatus());
            pst.setDate(3, (Date) p.getStartDate());
            pst.setDate(4, (Date) p.getEndDate());
            pst.setString(5,p.getProjectName());
            pst.setInt(6, p.getId());
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
        Project p = null;
        try (Connection con = Database.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                p = new Project();
                p.setId(rs.getInt(1));
                p.setUserId(rs.getInt(2));
                p.setStatus(rs.getString(3));
                p.setStartDate(rs.getDate(4));
                p.setEndDate(rs.getDate(5));
                p.setProjectName(rs.getString(6));

            }
            return p;
        } catch (SQLException ex) {
            throw ex;
        }
    }
}