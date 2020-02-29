package BackEnd.Other;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Security {
    Connection conn = null;
    public static Connection conDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gymhacker","root","");
            return con;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Something");
            return null;

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } return null;
    }
}
