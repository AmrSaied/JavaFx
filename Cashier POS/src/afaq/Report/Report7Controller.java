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
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author AMR SAID
 */
public class Report7Controller implements Initializable , ControlledScreen {

    ScreensController myController;
    DBConnection DB;
    @FXML
    TableView<TableReport> tablebuyed;
    @FXML
    TableColumn<TableReport, String> Tbarcode;

    @FXML
    TableColumn<TableReport, String> Tname;

  
    @FXML
    TableColumn<TableReport, String> TPrice_buy;
    @FXML
    TableColumn<TableReport, String> total;
  
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

    double TOTAL = 0,  change  = 0 , only = 0 ;

    ObservableList<TableReport> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Tbarcode.setCellValueFactory(new PropertyValueFactory<TableReport, String>("parcode"));
        Tname.setCellValueFactory(new PropertyValueFactory<TableReport, String>("name"));

       
        TPrice_buy.setCellValueFactory(new PropertyValueFactory<TableReport, String>("pricebuy"));
     
        datebuy.setCellValueFactory(new PropertyValueFactory<TableReport, String>("id"));
        total.setCellValueFactory(new PropertyValueFactory<TableReport, String>("total"));

        tablebuyed.setItems(data);

        Datepicker.setValue(LocalDate.now());
        Datepicker2.setValue(LocalDate.now());
        try {
            DB = new DBConnection();
            FillTable();
            Ldate.setText(new Date().toString());
            sum();
            menucustomer();
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

        DB.rs = DB.statemen.executeQuery("SELECT * from sell_bills");

        while (DB.rs.next()) {

            data.add(new TableReport(DB.rs.getString("bill_date"), DB.rs.getString("bill_no"), DB.rs.getString("cus_name"), DB.rs.getString("user_name"), DB.rs.getString("tot_price"), DB.rs.getString("change")));

        }

    }

    public void SearchParcode() throws SQLException {
        data.clear();
        String date = Datepicker.getValue().toString();
        String date2 = Datepicker2.getValue().toString();
        String Sql = "SELECT * from sell_bills WHERE  bill_date between '" + date + "' and '" + date2 + "'";

        if (!comboBox1.getSelectionModel().isEmpty()) {
            Sql = "SELECT * from sell_bills  where   user_name = '" + comboBox1.getValue() + "' and  bill_date between '" + date + "' and '" + date2 + "'";

        }
        System.out.println(Sql);
        DB.rs = DB.statemen.executeQuery(Sql);
        while (DB.rs.next()) {
         data.add(new TableReport(DB.rs.getString("bill_date"), DB.rs.getString("bill_no"), DB.rs.getString("cus_name"), DB.rs.getString("user_name"), DB.rs.getString("tot_price"), DB.rs.getString("change")));

        }

        sum();

    }

    public void sum() {

        TOTAL = 0;
       change= 0;
       only = 0; 
        for (int i = 0; i < data.size(); i++) {
//
            TOTAL = TOTAL + Double.parseDouble(data.get(i).getTotal());
         change = change + Double.parseDouble(data.get(i).getPricesell());
//
       }
//
        
        System.out.println("HEre");
       only = TOTAL - change;
        sumt.setText("" + TOTAL);
      sumt1.setText("" + change);
        sumt2.setText("" + only);
System.out.println(""+data.get(10).getPricesell());
    }

    public void menucustomer() throws SQLException {
        comboBox1.getItems().clear();
        DB.rs = DB.statemen.executeQuery("select * from users");
        while (DB.rs.next()) {
            Cus_Name.add(DB.rs.getString("user_name"));

        }
        items = FXCollections.observableArrayList(Cus_Name);
        comboBox1.getItems().addAll(items);

    }

}
