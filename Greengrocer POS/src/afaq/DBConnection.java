/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afaq;

import afaq.Controller.*;
import afaq.Table.TFullCashier;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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

  
   

    public DBConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Frist Class Done");

        connection = DriverManager.getConnection("jdbc:mysql://localhost/afaq?useUnicode=yes&characterEncoding=UTF-8", "root", "root");

        statemen = connection.createStatement();

    }

    
      public static ObservableList<TFullCashier> data = FXCollections.observableArrayList();
      public static ObservableList<TFullCashier> data2 = FXCollections.observableArrayList();

  


}
