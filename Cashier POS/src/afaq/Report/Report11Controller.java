/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afaq.Report;

import afaq.ControlledScreen;
import afaq.DBConnection;
import afaq.ScreensController;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author AMR SAID
 */
public class Report11Controller implements Initializable , ControlledScreen {

    ScreensController myController;
    DBConnection DB;
    @FXML
    TableView<TableReport> tablebuyed;
    @FXML
    TableColumn<TableReport, String> Tbarcode;

    @FXML
    TableColumn<TableReport, String> Tname;
    @FXML
    TableColumn<TableReport, String> Tamount;
    @FXML
    TableColumn<TableReport, String> TPrice_Sell;
  

    
  
    @FXML
    Label Ldate;

   

    @FXML
    Label sumb;

  
    double SELL = 0, BUY = 0;

    ObservableList<TableReport> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Tbarcode.setCellValueFactory(new PropertyValueFactory<TableReport, String>("parcode"));
        Tname.setCellValueFactory(new PropertyValueFactory<TableReport, String>("name"));
        Tamount.setCellValueFactory(new PropertyValueFactory<TableReport, String>("amount"));
        TPrice_Sell.setCellValueFactory(new PropertyValueFactory<TableReport, String>("pricesell"));
      
        tablebuyed.setItems(data);

        try {
            DB = new DBConnection();
            FillTable();
            Ldate.setText(new Date().toString());
            sum();
        } catch (Exception ex) {
            System.out.println("ERRO");
        }

    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    public void FillTable() throws SQLException {
        data.clear();
     
        DB.rs = DB.statemen.executeQuery("SELECT * FROM  `customer`");

        while (DB.rs.next()) {

            data.add(new TableReport("", DB.rs.getString("cus_name"), DB.rs.getString("address"), DB.rs.getString("tel"), DB.rs.getString("note"), DB.rs.getString("note"), DB.rs.getString("note")));



        }
       
    }
   
    
    
    public void sum (){
           SELL = 0;
        BUY = 0;
 
        
     sumb.setText("" + data.size());
    
       
        
    }

}
