/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afaq.Controller;

import afaq.ControlledScreen;
import afaq.DBConnection;
import afaq.ScreensController;
import afaq.Table.TBillCashier;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author HackerMan
 */
public class ReportController implements Initializable, ControlledScreen {

    ScreensController myController;
    @FXML
    TableView<TBillCashier> tableBuy;
    @FXML
    TableColumn<TBillCashier, String> billNumber_buy;
    @FXML
    TableColumn<TBillCashier, String> total_buy;
    ObservableList<TBillCashier> dataBuy = FXCollections.observableArrayList();

    @FXML
    TableView<TBillCashier> tableSell;
    @FXML
    TableColumn<TBillCashier, String> billNumber_Sell;
    @FXML
    TableColumn<TBillCashier, String> total_Sell;
    ObservableList<TBillCashier> dataSell = FXCollections.observableArrayList();

    @FXML
    TableView<TBillCashier> tableCheck;
    @FXML
    TableColumn<TBillCashier, String> billNumber_Check;
    @FXML
    TableColumn<TBillCashier, String> total_Chech;
    ObservableList<TBillCashier> dataCheck = FXCollections.observableArrayList();

    String Sql_buy;
    String Sql_Sell;
    String Sql_Check;
    DBConnection DB;

    @FXML
    Label sumbuy;
    @FXML
    Label sumsell;
    @FXML
    Label sumcheck;
    @FXML
    DatePicker date1;
    @FXML
    DatePicker date2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        date1.setValue(LocalDate.ofYearDay(1990, 1));
        date2.setValue(LocalDate.ofYearDay(2020, 1));
        billNumber_buy.setCellValueFactory(new PropertyValueFactory<TBillCashier, String>("billNumber"));
        total_buy.setCellValueFactory(new PropertyValueFactory<TBillCashier, String>("total"));
        tableBuy.setItems(dataBuy);

        billNumber_Sell.setCellValueFactory(new PropertyValueFactory<TBillCashier, String>("billNumber"));
        total_Sell.setCellValueFactory(new PropertyValueFactory<TBillCashier, String>("total"));
        tableSell.setItems(dataSell);

        billNumber_Check.setCellValueFactory(new PropertyValueFactory<TBillCashier, String>("billNumber"));
        total_Chech.setCellValueFactory(new PropertyValueFactory<TBillCashier, String>("total"));
        tableCheck.setItems(dataCheck);

        try {
            DB = new DBConnection();
            
           buyedmony() ;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    public void FillTableBuy() throws SQLException {
        dataBuy.clear();
        DB.rs = DB.statemen.executeQuery(Sql_buy);
        double sum = 0;

        while (DB.rs.next()) {
            dataBuy.add(new TBillCashier(DB.rs.getString("id"), DB.rs.getString("bill_no"), DB.rs.getString("paid")));
            sum = sum + Double.parseDouble(DB.rs.getString("paid"));
        }
        sumbuy.setText("" + sum);

    }

    public void FillTableSell() throws SQLException {
        dataSell.clear();
        DB.rs = DB.statemen.executeQuery(Sql_Sell);

        double sum = 0;
        while (DB.rs.next()) {
            dataSell.add(new TBillCashier(DB.rs.getString("id"), DB.rs.getString("bill_no"), DB.rs.getString("tot_price")));
            sum = sum + Double.parseDouble(DB.rs.getString("tot_price"));
        }
        sumsell.setText("" + sum);
    }

    public void FillTableCheck() throws SQLException {
        dataCheck.clear();
        DB.rs = DB.statemen.executeQuery(Sql_Check);

        double sum = 0;

        while (DB.rs.next()) {
            dataCheck.add(new TBillCashier(DB.rs.getString("id"), DB.rs.getString("user"), DB.rs.getString("price")));
            sum = sum + Double.parseDouble(DB.rs.getString("price"));
        }
        sumcheck.setText("" + sum);
    }

    public void buyedmony() throws SQLException {

        Sql_buy = "SELECT * FROM `buy_bills`  where `change` ='0'";
        Sql_Sell = "SELECT * FROM `sell_bill`  ";
        Sql_Check = "SELECT * FROM `check` where state = 'تم'";
        FillTableBuy();
        FillTableSell();
        FillTableCheck();

    }

    @FXML
    public void Changemony() throws SQLException {

        Sql_buy = "SELECT * FROM `buy_bills` where `change` != '0'";
        Sql_Sell = "SELECT * FROM `sell_bill` ";
        Sql_Check = "SELECT * FROM `check` where state != 'تم'";
        FillTableBuy();
        FillTableSell();
        FillTableCheck();
    }

    
      public void buyedata() throws SQLException {

        Sql_buy = "SELECT * FROM `buy_bills`  where `change` ='0' and  bill_date BETWEEN  '"+date1.getValue()+"'  AND '"+date2.getValue()+"' ";
        Sql_Sell = "SELECT * FROM `sell_bill` where   bill_date BETWEEN  '"+date1.getValue()+"'  AND '"+date2.getValue()+"' ";
        Sql_Check = "SELECT * FROM `check` where state = 'تم' and  Date_in BETWEEN  '"+date1.getValue()+"'  AND '"+date2.getValue()+"' ";
        FillTableBuy();
        FillTableSell();
        FillTableCheck();

    }
      public void Changedata() throws SQLException {

        Sql_buy = "SELECT * FROM `buy_bills`  where `change` !='0' and  bill_date BETWEEN  '"+date1.getValue()+"'  AND '"+date2.getValue()+"' ";
        Sql_Sell = "SELECT * FROM `sell_bill` bill_date BETWEEN  '"+date1.getValue()+"'  AND '"+date2.getValue()+"' ";
        Sql_Check = "SELECT * FROM `check` where state != 'تم' and  Date_in BETWEEN  '"+date1.getValue()+"'  AND '"+date2.getValue()+"' ";
        FillTableBuy();
        FillTableSell();
        FillTableCheck();

    }
}
