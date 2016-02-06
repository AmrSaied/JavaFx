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
public class Report4Controller implements Initializable, ControlledScreen {

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
    TextField TFbarcode;
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

    double TOTAL = 0;

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

        DB.rs = DB.statemen.executeQuery("SELECT bill_no,items,com_name,qty,buy_price,tot_price,buy_date FROM buy_items");

        while (DB.rs.next()) {

            data.add(new TableReport(DB.rs.getString("buy_date"), DB.rs.getString("bill_no"), DB.rs.getString("items"), DB.rs.getString("com_name"), DB.rs.getString("qty"), DB.rs.getString("tot_price"), DB.rs.getString("buy_price")));

        }

    }

    public void SearchParcode() throws SQLException {
        data.clear();
        String date = Datepicker.getValue().toString();
        String date2 = Datepicker2.getValue().toString();
        String Sql = "SELECT bill_no,items,com_name,qty,buy_price,tot_price,buy_date FROM buy_items WHERE buy_date between '" + date + "' and '" + date2 + "'";
        if (!TFbarcode.getText().equals("")) {
            Sql = "SELECT bill_no,items,com_name,qty,buy_price,tot_price,buy_date FROM buy_items WHERE barcode = '" + TFbarcode.getText() + "' and  buy_date between '" + date + "' and '" + date2 + "'";
        }
       
        if (!comboBox1.getSelectionModel().isEmpty()) {
            Sql = "SELECT bill_no,items,com_name,qty,buy_price,tot_price,buy_date FROM buy_items WHERE com_name = '" + comboBox1.getValue() + "' and  buy_date between '" + date + "' and '" + date2 + "'";

        }
         if (!TFbarcode.getText().equals("") && !comboBox1.getSelectionModel().isEmpty()) {
            Sql = "SELECT bill_no,items,com_name,qty,buy_price,tot_price,buy_date FROM buy_items WHERE barcode = '" + TFbarcode.getText() + "' and com_name = '" + comboBox1.getValue() + "' and  buy_date between '" + date + "' and '" + date2 + "'";

        }
        System.out.println(Sql);
        DB.rs = DB.statemen.executeQuery(Sql);
        while (DB.rs.next()) {

            data.add(new TableReport(DB.rs.getString("buy_date"), DB.rs.getString("bill_no"), DB.rs.getString("items"), DB.rs.getString("com_name"), DB.rs.getString("qty"), DB.rs.getString("tot_price"), DB.rs.getString("buy_price")));

        }

        sum();

    }

    public void sum() {

        TOTAL = 0;
        for (int i = 0; i < data.size(); i++) {

            TOTAL = TOTAL + Double.parseDouble(data.get(i).getTotal());
        }

        sumt.setText("" + TOTAL);
    }

    public void menucustomer() throws SQLException {
        comboBox1.getItems().clear();
        DB.rs = DB.statemen.executeQuery("select * from company");
        while (DB.rs.next()) {
            Cus_Name.add(DB.rs.getString("com_name"));

        }
        items = FXCollections.observableArrayList(Cus_Name);
        comboBox1.getItems().addAll(items);

    }

}
