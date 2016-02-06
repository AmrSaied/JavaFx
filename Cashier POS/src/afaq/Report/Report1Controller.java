/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afaq.Report;

import afaq.ControlledScreen;
import afaq.DBConnection;
import afaq.ScreensController;
import afaq.Table.TInsertData;

import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author AMR SAID
 */
public class Report1Controller implements Initializable, ControlledScreen {

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
    TableColumn<TableReport, String> TPrice_buy;
    @FXML
    TableColumn<TableReport, String> Ttotal;

    
    @FXML
    TextField TFbarcode;
    @FXML
    Label Ldate;

    @FXML
    Label sums;

    @FXML
    Label sumb;

    @FXML
    Label sumt;

    double SELL = 0, BUY = 0, TOTAL = 0;

    ObservableList<TableReport> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Tbarcode.setCellValueFactory(new PropertyValueFactory<TableReport, String>("parcode"));
        Tname.setCellValueFactory(new PropertyValueFactory<TableReport, String>("name"));
        Tamount.setCellValueFactory(new PropertyValueFactory<TableReport, String>("amount"));
        TPrice_Sell.setCellValueFactory(new PropertyValueFactory<TableReport, String>("pricesell"));
        TPrice_buy.setCellValueFactory(new PropertyValueFactory<TableReport, String>("pricebuy"));
        Ttotal.setCellValueFactory(new PropertyValueFactory<TableReport, String>("total"));
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
     
        DB.rs = DB.statemen.executeQuery("SELECT barcode, items, SUM( qty ) AS qty, SUM( sell_price ) AS sp, SUM( buy_price ) AS bp,  SUM( sell_price ) - SUM( buy_price ) AS ssp FROM sell_items GROUP BY items, barcode");

        while (DB.rs.next()) {

            data.add(new TableReport("", DB.rs.getString("barcode"), DB.rs.getString("items"), DB.rs.getString("qty"), DB.rs.getString("sp"), DB.rs.getString("ssp"), DB.rs.getString("bp")));



        }
       
    }
    public void SearchParcode() throws SQLException {
        data.clear();
     
        String X= TFbarcode.getText();
        String Sql ="SELECT barcode, items, SUM( qty ) AS qty, SUM( sell_price ) AS sp, SUM( buy_price ) AS bp,  SUM( sell_price ) - SUM( buy_price ) AS ssp FROM sell_items GROUP BY items, barcode HAVING barcode = '"+X+"'";

        System.out.println(Sql);
        DB.rs = DB.statemen.executeQuery(Sql);
        while (DB.rs.next()) {

            data.add(new TableReport("", DB.rs.getString("barcode"), DB.rs.getString("items"), DB.rs.getString("qty"), DB.rs.getString("sp"), DB.rs.getString("ssp"), DB.rs.getString("bp")));



        }
       
    }
    
    
    public void sum (){
           SELL = 0;
        BUY = 0;
        TOTAL = 0;
        for (int i = 0; i < data.size(); i++) {
               SELL = SELL + Double.parseDouble(data.get(i).getPricesell());
               BUY = BUY + Double.parseDouble(data.get(i).getPricebuy());
               TOTAL = TOTAL + Double.parseDouble(data.get(i).getTotal());
        }
     sumb.setText("" + BUY);
      sums.setText("" + SELL);
       
        sumt.setText("" + TOTAL);
    }

}
