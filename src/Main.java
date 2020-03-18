import db.Database;
import entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Database db =  new Database();
        User u = db.getUser(2);
        //System.out.println("name:"+u.getName()+"\nemail:"+u.getEmail());
       // u.setName("Julie");
        //db.update(u);
        //db.read();
        //db.close();
        //u=db.getUser(2);
        //db.delete(u);
        //db.read();
        //User v = new User("Judy ", 2, "judy@gmail.com","425-666-8888");
        //db.create(v);
        List<User> myList = db.readAll();
        for(User w : myList) System.out.println(w.getName());



        //db.create(6, "Mina", "mina@gmail.com","425-777-8888");
        //db.read();
        //db.delete(" id = 0 ");
        //db.create(4, "Mina", "mina@gmail.com","425-777-8888");
        //db.read();
        //db.delete(" id = 4 ");
        //db.update( "name = 'Mina'", "' name = 'Luna'" );
        //db.read();
    }
}