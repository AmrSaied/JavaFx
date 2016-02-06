/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package samba.Controller;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import samba.ControlledScreen;
import samba.DBConnection;
import samba.Samba;
import samba.ScreensController;
import samba.Table.TDefineCustomer;
import samba.Table.TDelivery;
import samba.Table.TuserPermission;


/**
 * FXML Controller class
 *
 * @author belal
 */
public class DeliveryController implements Initializable, ControlledScreen {

    ScreensController ControlledScreen;
    TuserPermission permission;
    @FXML
    AnchorPane AP1, AP2, AP3;

    DBConnection DB;
    @FXML
    TextField TFCustomername;
    @FXML
    TextField TFAdrees;
    @FXML
    TextField TFPhone;
    @FXML
    TextField TFnote;
    @FXML
    TextField tfsearch;

    @FXML
    Label Erro_Massage;

    @FXML
    TableView<TDefineCustomer> tableCustomer;
    @FXML
    TableColumn<TDefineCustomer, String> TCustomername;
    @FXML
    TableColumn<TDefineCustomer, String> TAdrees;
    @FXML
    TableColumn<TDefineCustomer, String> TPhone;
    @FXML
    TableColumn<TDefineCustomer, String> Tnote;
    ObservableList<TDefineCustomer> data = FXCollections.observableArrayList();

    @FXML
    TextField TFCustomernameD;
    @FXML
    TextField TFAdreesD;
    @FXML
    TextField TFPhoneD;
    @FXML
    TextField TFnoteD;
    @FXML
    TextField tfsearchD;

    @FXML
    Label Erro_MassageD;
    
    @FXML
    Label username;

  
    @FXML
    TableView<TDefineCustomer> tableCustomerD;
    @FXML
    TableColumn<TDefineCustomer, String> TCustomernameD;
    @FXML
    TableColumn<TDefineCustomer, String> TAdreesD;
    @FXML
    TableColumn<TDefineCustomer, String> TPhoneD;
    @FXML
    TableColumn<TDefineCustomer, String> TnoteD;
    ObservableList<TDefineCustomer> dataD = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
                  Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            double width = screenSize.getWidth();
            double height = screenSize.getHeight();
//           AP3.setPrefHeight(height);
//            AP3.setPrefWidth(width);
//            AP1.setPrefWidth(width/2.1);
//            AP1.setPrefHeight(height-100);
//            AP2.setPrefWidth(width/2.1);

        TCustomername.setCellValueFactory(new PropertyValueFactory<TDefineCustomer, String>("name"));
        TAdrees.setCellValueFactory(new PropertyValueFactory<TDefineCustomer, String>("Adress"));
        TPhone.setCellValueFactory(new PropertyValueFactory<TDefineCustomer, String>("phone"));
        Tnote.setCellValueFactory(new PropertyValueFactory<TDefineCustomer, String>("note"));
        tableCustomer.setItems(data);
        TCustomernameD.setCellValueFactory(new PropertyValueFactory<TDefineCustomer, String>("name"));
        TAdreesD.setCellValueFactory(new PropertyValueFactory<TDefineCustomer, String>("Adress"));
        TPhoneD.setCellValueFactory(new PropertyValueFactory<TDefineCustomer, String>("phone"));
        TnoteD.setCellValueFactory(new PropertyValueFactory<TDefineCustomer, String>("note"));
        tableCustomerD.setItems(dataD);
        try {
            DB = new DBConnection();
            FillTable();
            FillTableD();
    
        } catch (Exception ex) {

            System.out.println("ERROR");
        }

    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        ControlledScreen = screenPage;
    }

    @FXML
    public void FillTable() throws SQLException {
        data.clear();
        DB.rs = DB.statemen.executeQuery("SELECT * FROM customer");

        while (DB.rs.next()) {
            data.add(new TDefineCustomer(DB.rs.getString("id"), DB.rs.getString("cus_name"), DB.rs.getString("address"), DB.rs.getString("tel"), DB.rs.getString("note")));

        }

        Claerall();
    }

    @FXML
    public void FillTableD() throws SQLException {
        dataD.clear();
        DB.rs = DB.statemen.executeQuery("SELECT * FROM delivery");

        while (DB.rs.next()) {
            dataD.add(new TDefineCustomer(DB.rs.getString("id"), DB.rs.getString("del_name"), DB.rs.getString("address"), DB.rs.getString("tel"), DB.rs.getString("note")));

        }

        ClaerallD();
    }

    @FXML
    public void insertDataDB() throws SQLException {

        if (TFCustomername.getText().equals("") && TFAdrees.getText().equals("") && TFPhone.getText().equals("")) {
            Erro_Massage.setText("ادخل الحفول المطلوبة");
        } else if (TFCustomername.getText().equals("") && TFAdrees.getText().equals("") && TFPhone.getText().equals("")) {

            Erro_Massage.setText("ادخل الحفول المطلوبة");
        } else {
            Erro_Massage.setText(null);

            String Sql = "INSERT INTO customer(cus_name, address, tel, note) VALUES ('" + TFCustomername.getText() + "','" + TFAdrees.getText() + "','" + TFPhone.getText() + "','" + TFnote.getText() + "')";
            DB.statemen.executeUpdate(Sql);
            Claerall();
            FillTable();
        }

    }

    @FXML
    public void insertDataDBD() throws SQLException {

        if (TFCustomernameD.getText().equals("") && TFAdreesD.getText().equals("") && TFPhoneD.getText().equals("")) {
            Erro_MassageD.setText("ادخل الحفول المطلوبة");
        } else if (TFCustomernameD.getText().equals("") && TFAdreesD.getText().equals("") && TFPhoneD.getText().equals("")) {

            Erro_MassageD.setText("ادخل الحفول المطلوبة");
        } else {
            Erro_MassageD.setText(null);

            String Sql = "INSERT INTO delivery(del_name, address, tel, note,status,sell_id) VALUES ('" + TFCustomernameD.getText() + "','" + TFAdreesD.getText() + "','" + TFPhoneD.getText() + "','" + TFnoteD.getText() + "',0,0)";
            System.out.println(Sql);
            DB.statemen.executeUpdate(Sql);

            ClaerallD();
            FillTableD();
        }

    }

    public void Claerall() {
        TFCustomername.setText("");
        TFAdrees.setText("");
        TFPhone.setText("");
        TFnote.setText("");
        Erro_Massage.setText("");
    }

    public void ClaerallD() {
        TFCustomernameD.setText("");
        TFAdreesD.setText("");
        TFPhoneD.setText("");
        TFnoteD.setText("");
        Erro_MassageD.setText("");
    }

    @FXML
    public void Deleteitem() throws SQLException {
        try {
            TDefineCustomer InsertData = (TDefineCustomer) tableCustomer.getSelectionModel().getSelectedItem();
            String Sql = " DELETE FROM customer WHERE id = '" + InsertData.getId() + "'";
            DB.statemen.executeUpdate(Sql);
            data.remove(InsertData);
            Erro_Massage.setText(null);
        } catch (Exception ex) {
            Erro_Massage.setText("لم يتم اختيار الخدمة");
        }
        FillTable();
        Claerall();
    }

    @FXML
    public void DeleteitemD() throws SQLException {
        try {
            TDefineCustomer InsertData = (TDefineCustomer) tableCustomerD.getSelectionModel().getSelectedItem();
            String Sql = " DELETE FROM delivery WHERE id = '" + InsertData.getId() + "'";
            DB.statemen.executeUpdate(Sql);
            dataD.remove(InsertData);
            Erro_MassageD.setText(null);
        } catch (Exception ex) {
            Erro_MassageD.setText("لم يتم اختيار الخدمة");
        }
        FillTableD();
        ClaerallD();
    }

    @FXML
    public void updataitem() throws SQLException {

        TDefineCustomer InsertData = (TDefineCustomer) tableCustomer.getSelectionModel().getSelectedItem();
        String Sql = "UPDATE customer SET cus_name='" + TFCustomername.getText() + "',address='" + TFAdrees.getText() + "',tel='" + TFPhone.getText() + "',note='" + TFnote.getText() + "' WHERE id = '" + InsertData.getId() + "'";
        System.out.println(Sql);
        DB.statemen.executeUpdate(Sql);
        Claerall();
        FillTable();

    }

    @FXML
    public void updataitemD() throws SQLException {

        TDefineCustomer InsertData = (TDefineCustomer) tableCustomerD.getSelectionModel().getSelectedItem();
        String Sql = "UPDATE delivery SET del_name='" + TFCustomernameD.getText() + "',address='" + TFAdreesD.getText() + "',tel='" + TFPhoneD.getText() + "',note='" + TFnoteD.getText() + "' WHERE id = '" + InsertData.getId() + "'";
        System.out.println(Sql);
        DB.statemen.executeUpdate(Sql);
        ClaerallD();
        FillTableD();

    }

    @FXML
    public void Selecteditem() throws SQLException {

        TDefineCustomer InsertData = (TDefineCustomer) tableCustomer.getSelectionModel().getSelectedItem();
        String Sql = " SELECT * FROM customer WHERE id =  '" + InsertData.getId() + "'";
        DB.rs = DB.statemen.executeQuery(Sql);

        if (DB.rs.next()) {
            TFCustomername.setText(DB.rs.getString("cus_name"));

            TFAdrees.setText(DB.rs.getString("address"));

            TFPhone.setText(DB.rs.getString("tel"));
            TFnote.setText(DB.rs.getString("note"));

        }
    }

    @FXML
    public void SelecteditemD() throws SQLException {

        TDefineCustomer InsertData = (TDefineCustomer) tableCustomerD.getSelectionModel().getSelectedItem();
        String Sql = " SELECT * FROM delivery WHERE id =  '" + InsertData.getId() + "'";
        DB.rs = DB.statemen.executeQuery(Sql);

        if (DB.rs.next()) {
            TFCustomernameD.setText(DB.rs.getString("del_name"));

            TFAdreesD.setText(DB.rs.getString("address"));

            TFPhoneD.setText(DB.rs.getString("tel"));
            TFnoteD.setText(DB.rs.getString("note"));

        }
    }

    @FXML
    public void Search() throws SQLException {

        String Sql = " SELECT * FROM customer WHERE cus_name like  '%" + tfsearch.getText() + "%'";
        DB.rs = DB.statemen.executeQuery(Sql);

        if (DB.rs.next()) {
            TFCustomername.setText(DB.rs.getString("cus_name"));

            TFAdrees.setText(DB.rs.getString("address"));

            TFPhone.setText(DB.rs.getString("tel"));
            TFnote.setText(DB.rs.getString("note"));

        }
    }

    @FXML
    public void SearchD() throws SQLException {

        String Sql = " SELECT * FROM delivery WHERE del_name like  '%" + tfsearchD.getText() + "%'";
        DB.rs = DB.statemen.executeQuery(Sql);

        if (DB.rs.next()) {
            TFCustomername.setText(DB.rs.getString("cus_name"));

            TFAdrees.setText(DB.rs.getString("address"));

            TFPhone.setText(DB.rs.getString("tel"));
            TFnote.setText(DB.rs.getString("note"));

        }
    }


    @FXML
    public void CloseScene() {
        FXMLLoader loder = Samba.FXMain;
          this.permission = permission;
        MainController scenseController = loder.getController();
        scenseController.setCurrent(permission);
            ControlledScreen.setScreen(Samba.MainyId);
   
    }
    @FXML
    public void SendDelivery() throws SQLException {
        TDefineCustomer InsertData = (TDefineCustomer) tableCustomer.getSelectionModel().getSelectedItem();
        TDefineCustomer InsertDataD = (TDefineCustomer) tableCustomerD.getSelectionModel().getSelectedItem();


        String cus_name = InsertData.getName();
              
        String Delivery = InsertDataD.getName();
        
        String user_name=username.getText();

                    
              FXMLLoader loder = Samba.FXDDeliverypage;
              DeliverypageController scenseController = loder.getController();
              scenseController.setCurrent(cus_name,Delivery,permission);

                ControlledScreen.setScreen(Samba.DeliverypageId);
    }
      
       
 

    void setCurrent(TuserPermission permission) {
        this.permission=permission;
        username.setText(permission.getUser_name());
    }

 
}
