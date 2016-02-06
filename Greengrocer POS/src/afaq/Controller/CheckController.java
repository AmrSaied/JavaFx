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
import afaq.Table.TDefineCustomer;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author HackerMan
 */
public class CheckController implements Initializable, ControlledScreen {

    ScreensController myController;

    String id_Check = "";
    @FXML
    TableView<TBillCashier> tableBill;
    @FXML
    TableColumn<TBillCashier, String> billNumber;
    @FXML
    TableColumn<TBillCashier, String> total;
    @FXML
    TableColumn<TBillCashier, String> buyprices;
    @FXML
    TableColumn<TBillCashier, String> reprices;
    @FXML
    TableColumn<TBillCashier, String> databill;
    @FXML
    TableColumn<TBillCashier, String> state;

    @FXML
    Label Erromassage;
    @FXML
    Label checknumber;
    @FXML
    ComboBox<String> comboBox1;
    @FXML
    ComboBox<String> comboBox2;
    ArrayList<String> Cus_Name = new ArrayList<String>();
    ArrayList<String> Cus_Name2 = new ArrayList<String>();
    ObservableList items;
    ObservableList items2;
    @FXML
    TextField TFnameCheck;
    @FXML
    TextField tfsearchCustomer;
    @FXML
    TextField tfsearchcompany;
    @FXML
    TextField price;
    @FXML
    DatePicker Date_out;
    @FXML
    DatePicker Date_in;
    String comboValue;

    DBConnection DB;

    ObservableList<TBillCashier> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        billNumber.setCellValueFactory(new PropertyValueFactory<TBillCashier, String>("billNumber"));
        total.setCellValueFactory(new PropertyValueFactory<TBillCashier, String>("total"));
        buyprices.setCellValueFactory(new PropertyValueFactory<TBillCashier, String>("buyprices"));
        reprices.setCellValueFactory(new PropertyValueFactory<TBillCashier, String>("reprices"));
        databill.setCellValueFactory(new PropertyValueFactory<TBillCashier, String>("databill"));
        state.setCellValueFactory(new PropertyValueFactory<TBillCashier, String>("state"));
        tableBill.setItems(data);
        // TODO

        try {
            DB = new DBConnection();

            menucustomer();
            menuCompany();
            FillTable();
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    @FXML
    public void newcheck() throws SQLException {

        if (TFnameCheck.getText().equals("") && comboBox1.getValue().equals("") && price.getText().equals("") && Date_out.getValue().equals("")) {
            Erromassage.setText("ادخل الحفول المطلوبة");
        } else {
            Erromassage.setText(null);

            String Sql = "INSERT INTO `Check` ( `user`, `name_check`, `Date_out`, `Date_in`, `state`,`price`) VALUES ('" + comboValue + "','" + TFnameCheck.getText() + "','" + Date_out.getValue() + "','" + Date_in.getValue() + "','لم يستلم','" + price.getText() + "')";
            DB.statemen.executeUpdate(Sql);
            System.out.println(Sql);
            Claerall();
            FillTable();
        }

    }

    @FXML
    public void savecheck() {
        try {
            TBillCashier InsertData = (TBillCashier) tableBill.getSelectionModel().getSelectedItem();
            String sql = "UPDATE `Check` SET user='" + comboBox1.getValue() + "',name_check='" + TFnameCheck.getText() + "',Date_out='" + Date_out.getValue() + "',Date_in='" + Date_in.getValue() + "',price='" + price.getText() + "' WHERE id = '" + id_Check + "'";
            DB.statemen.executeUpdate(sql);

            Erromassage.setText(null);
        } catch (Exception ex) {
            Erromassage.setText("لم يتم اختيار الخدمة");
        }
    }

    @FXML
    public void PayedFinish() throws SQLException {
        try {

            String sql = "UPDATE `Check` SET state='تم' WHERE id = '" + id_Check + "'";
            System.out.println(sql);

            DB.statemen.executeUpdate(sql);
            Erromassage.setText(null);
        } catch (Exception ex) {
            Erromassage.setText("لم يتم اختيار الخدمة");
        }
        FillTable();
    }

    @FXML
    public void deletCheck() throws SQLException {
        int selectedOption = JOptionPane.showConfirmDialog(null,
                "هل انت متاكد من هذة العملية  ؟",
                "Choose",
                JOptionPane.YES_NO_OPTION);
        if (selectedOption == JOptionPane.YES_OPTION) {
            try {
                TBillCashier InsertData = (TBillCashier) tableBill.getSelectionModel().getSelectedItem();
                String Sql = " DELETE FROM `check` WHERE id = '" + id_Check + "'";
                DB.statemen.executeUpdate(Sql);
                data.remove(InsertData);
                Erromassage.setText(null);
            } catch (Exception ex) {
                Erromassage.setText("لم يتم اختيار الخدمة");
            }
            FillTable();
            Claerall();
        }
    }

    @FXML
    public void Close() {
        Stage stage = (Stage) Erromassage.getScene().getWindow();
        stage.close();
    }

    public void menucustomer() throws SQLException {
        comboBox1.getItems().clear();
        DB.rs = DB.statemen.executeQuery("select * from company");
        while (DB.rs.next()) {
            Cus_Name.add(DB.rs.getString("com_name"));

        }
        items = FXCollections.observableArrayList(Cus_Name);
        comboBox1.getItems().addAll(items);
        comboBox1.setOnAction(e -> {
            setDisplayUser(comboBox1.getValue());
        });

    }

    public void menuCompany() throws SQLException {
        comboBox2.getItems().clear();
        DB.rs = DB.statemen.executeQuery("select * from  customer");
        while (DB.rs.next()) {
            Cus_Name2.add(DB.rs.getString("cus_name"));

        }
        items2 = FXCollections.observableArrayList(Cus_Name2);
        comboBox2.getItems().addAll(items2);
        comboBox2.setOnAction(e -> {
            setDisplayCompany(comboBox2.getValue());
        });
    }

    @FXML
    public void FillTable() throws SQLException {
        data.clear();
        DB.rs = DB.statemen.executeQuery("SELECT * FROM `check`");

        System.out.println("here");
        int count = 0;
        while (DB.rs.next()) {
            data.add(new TBillCashier(DB.rs.getString("id"), DB.rs.getString("user"), DB.rs.getString("name_check"), DB.rs.getString("Date_out"), DB.rs.getString("Date_in"), DB.rs.getString("price"), DB.rs.getString("state")));
            count++;
        }
        checknumber.setText("" + count);

    }

    public void Claerall() {
        TFnameCheck.setText("");
        Date_out.setValue(null);
        Date_in.setValue(null);
        comboBox1.setValue(null);
        comboBox2.setValue(null);
    }

    @FXML
    public void Selecteditem() throws SQLException {
        TBillCashier InsertData = (TBillCashier) tableBill.getSelectionModel().getSelectedItem();
        id_Check = InsertData.getId();

        String Sql = " SELECT * FROM `check` WHERE id =  '" + id_Check + "'";
        DB.rs = DB.statemen.executeQuery(Sql);

        if (DB.rs.next()) {
            TFnameCheck.setText(DB.rs.getString("name_check"));

            comboBox1.setValue(DB.rs.getString("user"));

            price.setText(DB.rs.getString("price"));

            String value = DB.rs.getString("Date_out");

            String x = value.substring(0, 4);
            String x1 = value.substring(5, 7);
            String x2 = value.substring(8, 10);
            int year = Integer.parseInt(x);
            int month = Integer.parseInt(x1);
            int day = Integer.parseInt(x2);
            Date_out.setValue(LocalDate.of(year, Month.of(month), day));

            value = DB.rs.getString("Date_in");

            x = value.substring(0, 4);
            x1 = value.substring(5, 7);
            x2 = value.substring(8, 10);
            year = Integer.parseInt(x);
            month = Integer.parseInt(x1);
            day = Integer.parseInt(x2);
            Date_in.setValue(LocalDate.of(year, Month.of(month), day));

        }
    }

    @FXML
    public void SearchCustomer() throws SQLException {

        String Sql = " SELECT * FROM customer WHERE cus_name like  '%" + tfsearchCustomer.getText() + "%'";
        DB.rs = DB.statemen.executeQuery(Sql);

        if (DB.rs.next()) {
            comboBox2.setValue(DB.rs.getString("cus_name"));
            setDisplayUser(comboBox2.getValue());

        }
    }

    @FXML
    public void SearchCompany() throws SQLException {

        String Sql = " SELECT * FROM company WHERE com_name like  '%" + tfsearchcompany.getText() + "%'";
        DB.rs = DB.statemen.executeQuery(Sql);

        if (DB.rs.next()) {
            comboBox1.setValue(DB.rs.getString("com_name"));
            setDisplayCompany(comboBox1.getValue());

        }

    }

    private void setDisplayUser(String value) {
       
        comboValue = comboBox2.getValue();
        comboBox1.setValue("");
    }

    private void setDisplayCompany(String value) {
      
        comboValue = comboBox1.getValue();
        comboBox2.setValue("");
    }
}
