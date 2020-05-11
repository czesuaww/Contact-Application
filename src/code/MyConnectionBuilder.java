package code;

import java.sql.Connection;
import java.sql.DriverManager;

public class MyConnectionBuilder {

    public static Connection getConnection() {


        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost/contactapplication","root","");

        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
        }

        return  con;

    }
}
