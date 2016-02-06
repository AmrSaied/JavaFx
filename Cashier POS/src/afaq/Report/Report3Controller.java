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
import java.util.Calendar;
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
public class Report3Controller implements Initializable , ControlledScreen {

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
           
        } catch (Exception ex) {
            System.out.println("ERRO");
        }

    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    public void FillTable() throws SQLException {
          final Calendar cal = Calendar.getInstance();
       
            int month = cal.get(Calendar.MONTH);
           
                
            int year = cal.get(Calendar.YEAR);
          
            int day = cal.get(Calendar.DAY_OF_MONTH);
            
            String dateString = year+"-"+month+"-"+day;
         
        
        
        data.clear();
     
        String sql = "SELECT Barcode ,Items,QTY,EXP_Date FROM Stock where EXP_Date < '"+dateString+"'";
        System.out.println(""+sql);
        DB.rs = DB.statemen.executeQuery(sql);

        while (DB.rs.next()) {

            data.add(new TableReport("", DB.rs.getString("barcode"), DB.rs.getString("items"), DB.rs.getString("QTY"), "", "", DB.rs.getString("EXP_Date")));



        }
       
    }
   
    
    
    

}
