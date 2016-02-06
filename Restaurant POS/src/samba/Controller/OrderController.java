/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package samba.Controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import samba.ControlledScreen;
import samba.DBConnection;
import samba.Samba;
import samba.ScreensController;
import samba.Table.Report;
import samba.Table.TDefineCustomer;
import samba.Table.TDelivery;

/**
 * FXML Controller class
 *
 * @author belal
 */
public class OrderController implements Initializable, ControlledScreen {

    ScreensController ControlledScreen;
    DBConnection db;

    @FXML
    TableView<TDelivery> tableDelivery;
    @FXML
    TableColumn<TDelivery, String> pon_id;
    @FXML
    TableColumn<TDelivery, String> Emp_name;
    @FXML
    TableColumn<TDelivery, String> cus_name;
    @FXML
    TableColumn<TDelivery, String> Address;
    @FXML
    TableColumn<TDelivery, String> tel;
    @FXML
    TableColumn<TDelivery, String> tot_price;

    ObservableList<TDelivery> data = FXCollections.observableArrayList();

    @FXML
    TableView<TDelivery> Ttable;
    @FXML
    TableColumn<TDelivery, String> Tponid;
    @FXML
    TableColumn<TDelivery, String> Ttablenumber;
    @FXML
    TableColumn<TDelivery, String> Ttotall;
    ObservableList<TDelivery> Tdata = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        pon_id.setCellValueFactory(new PropertyValueFactory<TDelivery, String>("pon_id"));
        Emp_name.setCellValueFactory(new PropertyValueFactory<TDelivery, String>("Emp_name"));
        cus_name.setCellValueFactory(new PropertyValueFactory<TDelivery, String>("cus_name"));
        Address.setCellValueFactory(new PropertyValueFactory<TDelivery, String>("Address"));
        tel.setCellValueFactory(new PropertyValueFactory<TDelivery, String>("tel"));
        tot_price.setCellValueFactory(new PropertyValueFactory<TDelivery, String>("tot_price"));
        tableDelivery.setItems(data);

        Tponid.setCellValueFactory(new PropertyValueFactory<TDelivery, String>("pon_id"));
        Ttablenumber.setCellValueFactory(new PropertyValueFactory<TDelivery, String>("Emp_name"));
        Ttotall.setCellValueFactory(new PropertyValueFactory<TDelivery, String>("tel"));
        Ttable.setItems(Tdata);

        try {
            db = new DBConnection();
            FillTable();
        } catch (Exception ex) {
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        ControlledScreen = screenPage;

    }

    void FillTable() throws SQLException {
        data.clear();
           Tdata.clear();
           
           
        String sql = "select * from sell_bills , customer where customer.cus_name = sell_bills.cus_name and del_S = '1' and Action = 'خدمة توصيل'";
        db.rs = db.statemen.executeQuery(sql);
        System.out.println(sql);
        while (db.rs.next()) {

            data.add(new TDelivery(db.rs.getString("id"), db.rs.getString("bill_no"), db.rs.getString("cus_name"), db.rs.getString("delivery"), db.rs.getString("address"), db.rs.getString("tel"), db.rs.getString("tot_price"), db.rs.getString("user_name")));

        }

        sql = "select * from sell_bills  where  T_S = '1' and Action = 'الطاولات'";
        db.rs = db.statemen.executeQuery(sql);
        System.out.println(sql);
        while (db.rs.next()) {
            Tdata.add(new TDelivery(db.rs.getString("id"), db.rs.getString("bill_no"), db.rs.getString("T_name"), db.rs.getString("T_S"), db.rs.getString("bill_no"), db.rs.getString("tot_price"), db.rs.getString("user_name"), "الطاولات"));

        }

    }

    @FXML
    void CliclTdeli() throws SQLException {
        TDelivery InsertData = (TDelivery) tableDelivery.getSelectionModel().getSelectedItem();
        FXMLLoader loder = Samba.FXDDeliverypage;
        DeliverypageController scenseController = loder.getController();
        System.out.println("HERE");

        scenseController.setCurrent(InsertData);
        ControlledScreen.setScreen(Samba.DeliverypageId);
    }

    @FXML
    void ClicTable() throws SQLException {
        TDelivery InsertData = (TDelivery) Ttable.getSelectionModel().getSelectedItem();
        FXMLLoader loder = Samba.FXTablePage;
        TablePageController scenseController = loder.getController();
        scenseController.setCurrent(InsertData);
        ControlledScreen.setScreen(Samba.TablePageId);
    }

    @FXML
    public void CloseScene() {

        ControlledScreen.setScreen(Samba.MainyId);

    }
}
