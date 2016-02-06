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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
public class Report12Controller implements Initializable, ControlledScreen {

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
    TableColumn<TableReport, String> datebuy;

    
    @FXML
    Label Ldate;
    @FXML
    DatePicker Datepicker;
    @FXML
    DatePicker Datepicker2;

    @FXML
    ComboBox<String> comboBox1;
    ArrayList<String> Cus_Name = new ArrayList<String>();
    ObservableList items;
    @FXML
    Label sumt;
    @FXML
    Label sumt1;
    @FXML
    Label sumt2;

  

    ObservableList<TableReport> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Tbarcode.setCellValueFactory(new PropertyValueFactory<TableReport, String>("parcode"));
        Tname.setCellValueFactory(new PropertyValueFactory<TableReport, String>("name"));
        Tamount.setCellValueFactory(new PropertyValueFactory<TableReport, String>("amount"));
        TPrice_Sell.setCellValueFactory(new PropertyValueFactory<TableReport, String>("pricesell"));
        TPrice_buy.setCellValueFactory(new PropertyValueFactory<TableReport, String>("pricebuy"));
        Ttotal.setCellValueFactory(new PropertyValueFactory<TableReport, String>("total"));
        datebuy.setCellValueFactory(new PropertyValueFactory<TableReport, String>("id"));
        tablebuyed.setItems(data);

        Datepicker.setValue(LocalDate.now());
        Datepicker2.setValue(LocalDate.now());
        try {
            DB = new DBConnection();
            FillTable();
            Ldate.setText(new Date().toString());

            menucustomer();
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

        DB.rs = DB.statemen.executeQuery("SELECT *  FROM  sell_bills");

        while (DB.rs.next()) {

            data.add(new TableReport(DB.rs.getString("user_name"), DB.rs.getString("bill_no"), DB.rs.getString("cus_name"), DB.rs.getString("tot_price"), DB.rs.getString("paid"), DB.rs.getString("bill_date"), DB.rs.getString("change")));

        }
        

    }

    public void SearchParcode() throws SQLException {
        data.clear();
        String date = Datepicker.getValue().toString();
        String date2 = Datepicker2.getValue().toString();
        String Sql = "SELECT *  FROM  sell_bills WHERE bill_date between '" + date + "' and '" + date2 + "'";
      
       
        if (!comboBox1.getSelectionModel().isEmpty()) {
            Sql = "SELECT *  FROM  sell_bills  WHERE com_name = '" + comboBox1.getValue() + "' and  bill_date between '" + date + "' and '" + date2 + "'";

        }
        
        System.out.println(Sql);
        DB.rs = DB.statemen.executeQuery(Sql);
        while (DB.rs.next()) {

           data.add(new TableReport(DB.rs.getString("user_name"), DB.rs.getString("bill_no"), DB.rs.getString("cus_name"), DB.rs.getString("tot_price"), DB.rs.getString("paid"), DB.rs.getString("bill_date"), DB.rs.getString("change")));
      }

        sum();

    }

    public void sum() {
  double TOTAL = 0 , change = 0 ,paid = 0;
  
        for (int i = 0; i < data.size(); i++) {

            TOTAL = TOTAL + Double.parseDouble(data.get(i).getAmount());
            change = change + Double.parseDouble(data.get(i).getPricesell());
            paid = paid + Double.parseDouble(data.get(i).getPricebuy());
        }

        sumt.setText("" + TOTAL);
        sumt1.setText("" + change);
        sumt2.setText("" + paid);
    }

    public void menucustomer() throws SQLException {
        comboBox1.getItems().clear();
        DB.rs = DB.statemen.executeQuery("select * from customer");
        System.out.println("HERE");
        while (DB.rs.next()) {
            Cus_Name.add(DB.rs.getString("cus_name"));

        }
        items = FXCollections.observableArrayList(Cus_Name);
        comboBox1.getItems().addAll(items);

    }

}
