package samba;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author AmrSaid
 */
public class DBConnection {

    public static String username;
    public PreparedStatement preparedStatement;
    public Statement statemen;

    public ResultSet rs;

    public Connection connection;
    double width;
    double height;

    public DBConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Frist Class Done");

        connection = DriverManager.getConnection("jdbc:mysql://localhost/samba?useUnicode=yes&characterEncoding=UTF-8", "root", "root");

        statemen = connection.createStatement();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
         width = screenSize.getWidth();
         height = screenSize.getHeight();
    }

}
