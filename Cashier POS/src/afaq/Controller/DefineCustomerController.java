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
import afaq.Table.TDefineCustomer;
import afaq.Table.TFullCashier;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AmrSaid
 */
public class DefineCustomerController implements Initializable, ControlledScreen {

    ScreensController myController;
    DBConnection DB;
    @FXML
    TextField TFCustomername;
    @FXML
    TextField TFAdrees;
    @FXML
    TextField TFPhone;
    @FXML
    TextArea TFnote;
    @FXML
    TextField tfsearch;

    @FXML
    Label customerN;
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
    int count = 0;

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TCustomername.setCellValueFactory(new PropertyValueFactory<TDefineCustomer, String>("name"));
        TAdrees.setCellValueFactory(new PropertyValueFactory<TDefineCustomer, String>("Adress"));
        TPhone.setCellValueFactory(new PropertyValueFactory<TDefineCustomer, String>("phone"));
        Tnote.setCellValueFactory(new PropertyValueFactory<TDefineCustomer, String>("note"));
        tableCustomer.setItems(data);
        try {
            DB = new DBConnection();
            FillTable();
        } catch (Exception ex) {
            Logger.getLogger(DefineCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    public void FillTable() throws SQLException {
        data.clear();
        DB.rs = DB.statemen.executeQuery("SELECT * FROM customer");

        count = 0;
        while (DB.rs.next()) {
            data.add(new TDefineCustomer(DB.rs.getString("id"), DB.rs.getString("cus_name"), DB.rs.getString("address"), DB.rs.getString("tel"), DB.rs.getString("note")));
            count++;
        }
        customerN.setText("" + count);
         Claerall();
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

    public void Claerall() {
        TFCustomername.setText("");
        TFAdrees.setText("");
        TFPhone.setText("");
        TFnote.setText("");
        Erro_Massage.setText("");
    }
    
    @FXML
    public void Deleteitem() throws SQLException {
        try {
            TDefineCustomer InsertData = (TDefineCustomer) tableCustomer.getSelectionModel().getSelectedItem();
            String Sql = " DELETE FROM customer WHERE id = '" + InsertData.getId()+"'";
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
    public void updataitem() throws SQLException {

            TDefineCustomer InsertData = (TDefineCustomer) tableCustomer.getSelectionModel().getSelectedItem();
        String Sql = "UPDATE customer SET cus_name='"+TFCustomername.getText()+"',address='"+TFAdrees.getText()+"',tel='"+TFPhone.getText()+"',note='"+Tnote.getText()+"' WHERE id = '"+InsertData.getId()+"'";
        System.out.println(Sql);
        DB.statemen.executeUpdate(Sql);
        Claerall();
        FillTable();

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
    public void CloseScene() {
        ScreensController.p15 = false;
        Stage stage = (Stage) TFAdrees.getScene().getWindow();
        stage.close();
    }
    
         @FXML
    public void Search() throws SQLException {

 
        String Sql = " SELECT * FROM customer WHERE cus_name like  '%" + tfsearch.getText()+ "%'";
        DB.rs = DB.statemen.executeQuery(Sql);

        if (DB.rs.next()) {
            TFCustomername.setText(DB.rs.getString("cus_name"));

            TFAdrees.setText(DB.rs.getString("address"));

            TFPhone.setText(DB.rs.getString("tel"));
            TFnote.setText(DB.rs.getString("note"));
           
        }
    }
}


