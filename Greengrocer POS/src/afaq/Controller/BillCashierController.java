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
import afaq.Table.TBillCashier;
import afaq.Table.TFullCashier;
import afaq.Table.TuserPermission;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AmrSaid
 */
public class BillCashierController implements Initializable, ControlledScreen {

    ScreensController myController;
    TuserPermission current;
    DBConnection DB;
    ObservableList items;
    ObservableList items2;
    @FXML
    ComboBox<String> comboBox1;
    ArrayList<String> Cus_Name = new ArrayList<String>();
    @FXML
    ComboBox<String> comboBox2;
    ArrayList<String> type_Name = new ArrayList<String>();
@FXML
    TextField tfsearch;
    final ToggleGroup group = new ToggleGroup();
    @FXML
    RadioButton rb2;
    @FXML
    RadioButton rb1;
    @FXML
    Label Erromassage;

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
    DatePicker Datepicker3;
    @FXML
    DatePicker Datepicker4;

    ObservableList<TBillCashier> data = FXCollections.observableArrayList();
    String sql = "SELECT  * FROM  buy_bills WHERE";
    String sql1 = "5555";
    String sql2 = "555555";
    String sql3 = "55555";
    String sql4 = "5555";

    @FXML
    TextField allchange;
    @FXML
    TextField tfpayed;

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        billNumber.setCellValueFactory(new PropertyValueFactory<TBillCashier, String>("billNumber"));
        total.setCellValueFactory(new PropertyValueFactory<TBillCashier, String>("total"));
        buyprices.setCellValueFactory(new PropertyValueFactory<TBillCashier, String>("buyprices"));
        reprices.setCellValueFactory(new PropertyValueFactory<TBillCashier, String>("reprices"));
        databill.setCellValueFactory(new PropertyValueFactory<TBillCashier, String>("databill"));
        tableBill.setItems(data);
        Datepicker3.setValue(LocalDate.now());
        Datepicker4.setValue(LocalDate.now());
        try {
            DB = new DBConnection();
            menucustomer();
            menutype();
            rb1.setToggleGroup(group);
            rb2.setToggleGroup(group);
            rb1.setSelected(true);

            rb2.setId("rb2");
            rb1.setId("rb1");

            sql1 = "";
            group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
                @Override
                public void changed(ObservableValue<? extends Toggle> ov, Toggle t, Toggle t1) {
                    RadioButton chk = (RadioButton) t1.getToggleGroup().getSelectedToggle(); // Cast object to radio button
                    System.out.println("HERE ENTER RADIO BUTTon");
                    if (chk.getId().equals("rb2")) {

                        String date3 = Datepicker3.getValue().toString();
                        String date4 = Datepicker4.getValue().toString();

                        sql1 = "AND bill_date  BETWEEN   '" + date3 + "' AND   '" + date4 + "'";
                    } else {
                        sql1 = "";

                    }
                }
            });
        } catch (Exception ex) {
            Logger.getLogger(BillCashierController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setCurrent(TuserPermission current) {

        this.current = current;

    }

    public void menucustomer() throws SQLException {
        comboBox1.getItems().clear();
        DB.rs = DB.statemen.executeQuery("select * from company");
        while (DB.rs.next()) {
            Cus_Name.add(DB.rs.getString("com_name"));

        }
        items = FXCollections.observableArrayList(Cus_Name);
        comboBox1.getItems().addAll(items);
        comboBox1.setOnAction(e -> setDisplayUser(comboBox1.getValue()));
    }

    public void menutype() throws SQLException {
        comboBox2.getItems().clear();

        type_Name.add("دين");
        type_Name.add("نقدية");

        items2 = FXCollections.observableArrayList(type_Name);
        comboBox2.getItems().addAll(items2);
        comboBox2.setOnAction(e -> setDisplayComboBox(items2.indexOf(comboBox2.getValue())));
    }

    @FXML
    public void Show() throws SQLException {
        try {
            sql3 = " " + sql + " " + sql2 + "   " + sql4 + " " + sql1 + "";
            System.out.println(sql3);
            DB.rs = DB.statemen.executeQuery(sql3);
            data.clear();
            while (DB.rs.next()) {
                data.add(new TBillCashier(DB.rs.getString("id"), DB.rs.getString("bill_no"), DB.rs.getString("tot_price"), DB.rs.getString("paid"), DB.rs.getString("change"), DB.rs.getString("bill_date")));
            }
            SumAll();
            Erromassage.setText("");

        } catch (Exception ex) {
            Erromassage.setText("ادخل باقي المطلبات");
        }
    }

    public void setDisplayComboBox(int index) {
        if (index == 0) {
            sql2 = " `change`  > 0 ";
            tfpayed.setDisable(false);
        } else {
            sql2 = " `change`  = 0 ";
            tfpayed.setDisable(true);
        }

    }

    public void setDisplayUser(String index) {
        sql4 = "AND com_name = '" + index + "'";
        System.out.println(sql4);

    }

    public void SumAll() {
        double totall = 0;
        for (int i = 0; i < data.size(); i++) {

            double y = Double.parseDouble(data.get(i).getReprices());

            totall = totall + y;

        }

        allchange.setText("" + totall);

    }

    @FXML
    public void payed() throws SQLException {
      try {
        double pay = Double.parseDouble(tfpayed.getText());
        String sql;
        double change;
        double tot_pric;
        double paid;
        DB.rs = DB.statemen.executeQuery(sql3);

        if (pay > Double.parseDouble(allchange.getText())) {
            Erromassage.setText("يجب ان يكون المبلغ المدفوع اقل من المتبقي");
        } else {
            if (data.isEmpty()) {
                Erromassage.setText("لايوجد فزاتير");
            } else {
                for (int i = 0; i < data.size(); i++) {

                    change = Double.parseDouble(data.get(i).getReprices());
                    tot_pric = Double.parseDouble(data.get(i).getTotal());
                    paid = Double.parseDouble(data.get(i).getBuyprices());
                    System.out.println("change  " + change + "tot_pric " + tot_pric + "paid  " + paid);

                    if (pay >= 0) {

                        if (pay > change) {
                            pay = pay - change;
                            sql = "UPDATE `buy_bills` SET `paid`='" + tot_pric + "',`change`='0' WHERE id = '" + data.get(i).getId() + "'";
                            System.out.println("pay > change = " + sql);
                            DB.statemen.executeUpdate(sql);
                        } else if (pay == change) {
                            sql = "UPDATE `buy_bills` SET `paid`='" + (paid + pay) + "',`change`='" + (change - pay) + "' WHERE id = '" + data.get(i).getId() + "'";
                            DB.statemen.executeUpdate(sql);
                            pay = 0;
                            System.out.println("pay = change = " + sql);

                        } else {
                            sql = "UPDATE `buy_bills` SET `paid`='" + (paid + pay) + "',`change`='" + (change - pay) + "' WHERE id = '" + data.get(i).getId() + "'";

                            DB.statemen.executeUpdate(sql);

                            pay = 0;
                            System.out.println("pay < change = " + sql);

                        }

                    }

                }
            }

            Show();
            Erromassage.setText("");
            tfpayed.setText("");
        }} catch (Exception e) {
            Erromassage.setText("لا يوحد فواتير ");
        }

    }

    @FXML
    public void CloseScene() {

        Stage stage = (Stage) Erromassage.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void Clearall() {
        Erromassage.setText("");
        tfpayed.setText("");
        allchange.setText("");
        data.clear();;
    }
    
        @FXML
    public void Search() throws SQLException {

     
       String sql10 = "SELECT  * FROM  company WHERE com_name like  '%" + tfsearch.getText() + "%'";
            System.out.println(sql10);
          DB.rs = DB.statemen.executeQuery(sql10);
          if(DB.rs.next()){
              comboBox1.setValue(DB.rs.getString("com_name"));
          }
     Show();
    }
}
