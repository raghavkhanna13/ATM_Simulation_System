package atm.simulator.system;

import java.sql.*;

// this class is created for the connection with the mysql database
// as this class is used as the object in other classes for the connection
public class Conn {

    Connection c;
    Statement s;
    public Conn() {
        try {
            c = DriverManager.getConnection("jdbc:mysql:///bankmanagementsystem", "root", "raghav");
            s = c.createStatement();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
