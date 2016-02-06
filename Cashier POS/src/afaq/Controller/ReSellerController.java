/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afaq.Controller;

import afaq.ControlledScreen;
import afaq.DBConnection;
import afaq.ScreensController;
import afaq.Table.TCashierService;
import afaq.Table.TFullCashier;
import afaq.Table.TInsertData;
import afaq.Table.TReButed;
import afaq.Table.TuserPermission;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AmrSaid
 */
public class ReSellerController implements Initializable, ControlledScreen {

  
    ScreensController myController;
    DBConnection DB;
    ObservableList items;
    @FXML
    ComboBox<String> comboBox1;
    ArrayList<String> Cus_Name = new ArrayList<String>();

    @FXML
    TextField amount;
    @FXML
    TextField barcodenumber;
    @FXML
    Label totalAmout;
    @FXML
    Label namebar;
    @FXML
    Label Erromassage;
    @FXML
    Label pricebuyed;
    @FXML
    Label username;
    @FXML
    DatePicker Date1;

    @FXML
    TableView<TReButed> tablebuyed;
    @FXML
    TableColumn<TReButed, String> Tbarcode;

    @FXML
    TableColumn<TReButed, String> TCustomer;
    @FXML
    TableColumn<TReButed, String> Titem;
    @FXML
    TableColumn<TReButed, String> Tamount;
    @FXML
    TableColumn<TReButed, String> TPrice_Sell;
    @FXML
    TableColumn<TReButed, String> Ttotal;
    @FXML
    TableColumn<TReButed, String> TDate;
    ObservableList<TReButed> data = FXCollections.observableArrayList();

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ClearAdd();
            DB = new DBConnection();
            menucustomer();
            fillTable();
            Tbarcode.setCellValueFactory(new PropertyValueFactory<TReButed, String>("parcode"));
            Titem.setCellValueFactory(new PropertyValueFactory<TReButed, String>("name"));
            TCustomer.setCellValueFactory(new PropertyValueFactory<TReButed, String>("Customer"));
            Tamount.setCellValueFactory(new PropertyValueFactory<TReButed, String>("amount"));
            TPrice_Sell.setCellValueFactory(new PropertyValueFactory<TReButed, String>("buyprices"));
            Ttotal.setCellValueFactory(new PropertyValueFactory<TReButed, String>("total"));
            TDate.setCellValueFactory(new PropertyValueFactory<TReButed, String>("databill"));
            tablebuyed.setItems(data);

        } catch (Exception ex) {
            Logger.getLogger(ReBuyedController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    public void SearchBarCode() throws SQLException {

        System.out.println("Enter");
        DB.rs = DB.statemen.executeQuery("SELECT * FROM stock WHERE Barcode = '" + barcodenumber.getText() + "'");
        if (DB.rs.next()) {

            double Sell_Price = DB.rs.getDouble("Sell_Price");
            pricebuyed.setText(DB.rs.getString("Sell_Price"));

            double CastAmount = Double.parseDouble(amount.getText()) * Sell_Price;

            totalAmout.setText("" + CastAmount);

            namebar.setText(DB.rs.getString("items"));
            Erromassage.setText("");

        } else {
            if (!barcodenumber.getText().equals("")) {
                Erromassage.setText("هذا المنتج غير موجود الرجاء تسجيلة");
                ClearAdd();
            }
        }
    }

    public void ClearAdd() {
        barcodenumber.clear();
        namebar.setText("");
        amount.setText("1");
        totalAmout.setText("");
        pricebuyed.setText("");
    }

    public void menucustomer() throws SQLException {
        comboBox1.getItems().clear();
        DB.rs = DB.statemen.executeQuery("select * from customer");
        while (DB.rs.next()) {
            Cus_Name.add(DB.rs.getString("cus_name"));
            comboBox1.setValue(DB.rs.getString("cus_name"));

        }
        items = FXCollections.observableArrayList(Cus_Name);
        comboBox1.getItems().addAll(items);
    }

    @FXML
    public void insertRebyed() throws SQLException {
SearchBarCode();
        try {
            final Calendar cal = Calendar.getInstance();

            int month = cal.get(Calendar.MONTH);
            month++;
            int year = cal.get(Calendar.YEAR);
            int day = cal.get(Calendar.DAY_OF_MONTH);

            String value1 = barcodenumber.getText();
            String value2 = amount.getText();
            String value3 = comboBox1.getValue();
            String value4 = pricebuyed.getText();
            String value5 = totalAmout.getText();
            String value7 = namebar.getText();
            String value8 = username.getText();

            System.out.println("" + value3);
            String value6 = (year + "-" + month + "-" + day);

            if (value1.equals("") && value2.equals("") && value3.equals("null") && value4.equals("") && value5.equals("") && value6.equals("") && value7.equals("")) {

                Erromassage.setText("ادخل المطلبات");
            } else {

                DB.statemen.executeUpdate("INSERT INTO sell_ret(barcode, items, cus_name, qty, sell_price, tot_price, ret_date, user_name, action) VALUES ('" + value1 + "','" + value7 + "','" + value3 + "','" + value2 + "','" + value4 + "','" + value5 + "','" + value6 + "','" + value8 + "','مرتجع')");
                UpdataStock();
                fillTable();
                ClearAdd();
            }

        } catch (Exception e) {
            Erromassage.setText("ادخل المطلبات");
        }

    }

    @FXML
    public void fillTable() throws SQLException {
        data.clear();
        DB.rs = DB.statemen.executeQuery("SELECT * FROM sell_ret");

        while (DB.rs.next()) {
            data.add(new TReButed(DB.rs.getString("id"), DB.rs.getString("barcode"), DB.rs.getString("items"), DB.rs.getString("cus_name"), DB.rs.getString("qty"), DB.rs.getString("sell_price"), DB.rs.getString("tot_price"), DB.rs.getString("ret_date")));

        }
    }

    @FXML
    public void fillTable(String value1) throws SQLException {
        data.clear();
        DB.rs = DB.statemen.executeQuery("SELECT * FROM sell_ret WHERE ret_date =  '" + value1 + "'");

        while (DB.rs.next()) {
            data.add(new TReButed(DB.rs.getString("id"), DB.rs.getString("barcode"), DB.rs.getString("items"), DB.rs.getString("cus_name"), DB.rs.getString("qty"), DB.rs.getString("sell_price"), DB.rs.getString("tot_price"), DB.rs.getString("ret_date")));

        }
    }

    @FXML
    public void Deleteitem() throws SQLException {
        SearchBarCode();
        try {
            TReButed InsertData = (TReButed) tablebuyed.getSelectionModel().getSelectedItem();

            String Sql = " DELETE FROM  sell_ret WHERE id = '" + InsertData.getId() + "'";
            DB.statemen.executeUpdate(Sql);
            deleteStock();
            data.remove(InsertData);
            Erromassage.setText(null);

        } catch (Exception ex) {
            Erromassage.setText("لم يتم اختيار الخدمة");
        }
        fillTable();
        ClearAdd();
    }

    @FXML
    public void updataitem() throws SQLException {
        try {
 TReButed InsertData = (TReButed) tablebuyed.getSelectionModel().getSelectedItem();

            String Sql = " DELETE FROM  sell_ret WHERE id = '" + InsertData.getId() + "'";
            DB.statemen.executeUpdate(Sql);
            deleteStock();
            data.remove(InsertData);
            
            insertRebyed();
         
        } catch (Exception e) {
            Erromassage.setText("ادخل المطلبات");
        }
    }

    @FXML
    public void Selecteditem() throws SQLException {

        System.out.println("Selecteditem");
        TReButed InsertData = (TReButed) tablebuyed.getSelectionModel().getSelectedItem();
        String Sql = " SELECT * FROM sell_ret WHERE id =  '" + InsertData.getId() + "'";
        DB.rs = DB.statemen.executeQuery(Sql);

        if (DB.rs.next()) {
            barcodenumber.setText(DB.rs.getString("barcode"));

            amount.setText(DB.rs.getString("qty"));
            namebar.setText(DB.rs.getString("items"));

            pricebuyed.setText(DB.rs.getString("sell_price"));
            totalAmout.setText(DB.rs.getString("tot_price"));

            comboBox1.setValue(DB.rs.getString("cus_name"));

        }

    }

    @FXML
    public void Search() throws SQLException {

        String value1 = Date1.getValue().toString();;

        fillTable(value1);
    }

    @FXML
    public void CloseScene() {
        ScreensController.p12 = false;
        Stage stage = (Stage) amount.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void UpdataStock() throws SQLException {

        for (int i = 0; i < data.size(); i++) {
            String sql = "SELECT * FROM  `stock`  WHERE Barcode = " + barcodenumber.getText() + "";
            DB.rs = DB.statemen.executeQuery(sql);
            DB.rs.next();
            int numberStock = DB.rs.getInt("QTY") + Integer.parseInt(amount.getText());
            sql = "UPDATE stock SET QTY='" + numberStock + "' WHERE  Barcode = " + barcodenumber.getText() + "";
            DB.statemen.executeUpdate(sql);

        }
    }

    @FXML
    public void deleteStock() throws SQLException {

        for (int i = 0; i < data.size(); i++) {
            String sql = "SELECT * FROM  `stock`  WHERE Barcode = " + barcodenumber.getText() + "";
            DB.rs = DB.statemen.executeQuery(sql);
            DB.rs.next();
            int numberStock = DB.rs.getInt("QTY") - Integer.parseInt(amount.getText());
            sql = "UPDATE stock SET QTY='" + numberStock + "' WHERE  Barcode = " + barcodenumber.getText() + "";
            DB.statemen.executeUpdate(sql);

        }
    }

    public void userPermission(TuserPermission tuserPermission) {

        username.setText(tuserPermission.getUser_name());
    }

}
