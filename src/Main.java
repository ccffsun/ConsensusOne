import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        String url = "jdbc:mysql://localhost:3306/ConsensusOne?serverTimezone=UTC";
        String user = "root";
        String password = "ccffsun86";
        String query = "SELECT * from user";

        try (Connection con = DriverManager.getConnection(url, user, password);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            while (rs.next()) {
                System.out.println(rs.getInt(1)+ " "+rs.getString(2));
            }

            //query = "INSERT INTO `ConsensusOne`.`user` (`id`, `name`, `email`, `tel`) VALUES ('4','Miao', 'miao@gmail.com', '425-444-8888');";
            //query = "update user set name = 'Gaga' where name = 'Miao'";
            query = "DELETE FROM user where id = 4";
            st.execute(query);

        } catch (SQLException ex) {
            System.out.println(ex);
            ex.printStackTrace();
        }
    }
}