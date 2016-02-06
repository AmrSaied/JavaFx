/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afaq.Controller;

import afaq.ControlledScreen;
import afaq.DBConnection;
import afaq.ScreensController;

import afaq.Table.TDefineSeller;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author AmrSaid
 */
public class DefineSellerController implements Initializable, ControlledScreen {

    ScreensController myController;
    DBConnection DB;
    @FXML
    TextField TFCopanyname;
    @FXML
    TextField TFAdrees;
    @FXML
    TextField TFPhone;
    @FXML
    TextArea TFnote;

    @FXML
    Label customerN;
    @FXML
    Label Erro_Massage;
    
     @FXML
    TextField tfsearch;

    @FXML
    TableView<TDefineSeller> tableCopanyname;
    @FXML
    TableColumn<TDefineSeller, String> TCopanyname;
    @FXML
    TableColumn<TDefineSeller, String> TAdrees;
    @FXML
    TableColumn<TDefineSeller, String> TPhone;
    @FXML
    TableColumn<TDefineSeller, String> Tnote;

    ObservableList<TDefineSeller> data = FXCollections.observableArrayList();
    int count = 0;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       TCopanyname.setCellValueFactory(new PropertyValueFactory<TDefineSeller, String>("name"));
        TAdrees.setCellValueFactory(new PropertyValueFactory<TDefineSeller, String>("Adress"));
        TPhone.setCellValueFactory(new PropertyValueFactory<TDefineSeller, String>("phone"));
        Tnote.setCellValueFactory(new PropertyValueFactory<TDefineSeller, String>("note"));
        tableCopanyname.setItems(data);
        try {
            DB = new DBConnection();
            FillTable();
                Platform.runLater(new Runnable() {
            @Override
            public void run() {
                TFCopanyname.requestFocus();
            }
        });
        } catch (Exception ex) {
            Logger.getLogger(DefineCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }    
    
  @FXML
    public void FillTable() throws SQLException {
        data.clear();
        DB.rs = DB.statemen.executeQuery("SELECT * FROM company");

        count = 0;
        while (DB.rs.next()) {
            data.add(new TDefineSeller(DB.rs.getString("id"), DB.rs.getString("com_name"), DB.rs.getString("address"), DB.rs.getString("tel"), DB.rs.getString("note")));
            count++;
        }
        customerN.setText("" + count);
         Claerall();
    }

    @FXML
    public void insertDataDB() throws SQLException {


        if (TFCopanyname.getText().equals("") && TFAdrees.getText().equals("") && TFPhone.getText().equals("")) {
            Erro_Massage.setText("ادخل الحفول المطلوبة");
        } else if (TFCopanyname.getText().equals("") && TFAdrees.getText().equals("") && TFPhone.getText().equals("")) {

            Erro_Massage.setText("ادخل الحفول المطلوبة");
        } else {
            Erro_Massage.setText(null);

            String Sql = "INSERT INTO company(com_name, address, tel, note) VALUES ('" + TFCopanyname.getText() + "','" + TFAdrees.getText() + "','" + TFPhone.getText() + "','" + TFnote.getText() + "')";
            DB.statemen.executeUpdate(Sql);
            Claerall();
            FillTable();
        }

    }

    public void Claerall() {
        TFCopanyname.setText("");
        TFAdrees.setText("");
        TFPhone.setText("");
        TFnote.setText("");
        Erro_Massage.setText("");
    }
    
    @FXML
    public void Deleteitem() throws SQLException {
           int selectedOption = JOptionPane.showConfirmDialog(null,
                "هل انت متاكد من هذة العملية  ؟",
                "Choose",
                JOptionPane.YES_NO_OPTION);
        if (selectedOption == JOptionPane.YES_OPTION) {
        try {
            TDefineSeller InsertData = (TDefineSeller) tableCopanyname.getSelectionModel().getSelectedItem();
            String Sql = " DELETE FROM company WHERE id = '" + InsertData.getId()+"'";
            DB.statemen.executeUpdate(Sql);
            data.remove(InsertData);
            Erro_Massage.setText(null);
        } catch (Exception ex) {
            Erro_Massage.setText("لم يتم اختيار الخدمة");
        }
        FillTable();
        Claerall();
    }}

    @FXML
    public void updataitem() throws SQLException {
   int selectedOption = JOptionPane.showConfirmDialog(null,
                "هل انت متاكد من هذة العملية  ؟",
                "Choose",
                JOptionPane.YES_NO_OPTION);
        if (selectedOption == JOptionPane.YES_OPTION) {
            TDefineSeller InsertData = (TDefineSeller) tableCopanyname.getSelectionModel().getSelectedItem();
        String Sql = "UPDATE company SET com_name='"+TFCopanyname.getText()+"',address='"+TFAdrees.getText()+"',tel='"+TFPhone.getText()+"',note='"+TFnote.getText()+"' WHERE id = '"+InsertData.getId()+"'";
        System.out.println(Sql);
        DB.statemen.executeUpdate(Sql);
        System.out.println("Updated");
        Claerall();
        FillTable();

    }}
    
     @FXML
    public void Selecteditem() throws SQLException {

   TDefineSeller InsertData = (TDefineSeller) tableCopanyname.getSelectionModel().getSelectedItem();
        String Sql = " SELECT * FROM company WHERE id =  '" + InsertData.getId() + "'";
        DB.rs = DB.statemen.executeQuery(Sql);

        if (DB.rs.next()) {
            TFCopanyname.setText(DB.rs.getString("com_name"));

            TFAdrees.setText(DB.rs.getString("address"));

            TFPhone.setText(DB.rs.getString("tel"));
            TFnote.setText(DB.rs.getString("note"));
           
        }
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
      myController=screenPage;
    }
    
         @FXML
    public void CloseScene() {

        Stage stage = (Stage) TFAdrees.getScene().getWindow();
        stage.close();
    }
    
         @FXML
    public void Search() throws SQLException {

 
        String Sql = " SELECT * FROM company WHERE com_name like  '%" + tfsearch.getText()+ "%'";
        DB.rs = DB.statemen.executeQuery(Sql);

        if (DB.rs.next()) {
            TFCopanyname.setText(DB.rs.getString("com_name"));

            TFAdrees.setText(DB.rs.getString("address"));

            TFPhone.setText(DB.rs.getString("tel"));
            TFnote.setText(DB.rs.getString("note"));
           
        }
    }
    
         @FXML
    public void p1(final KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            TFAdrees.requestFocus();

        }
    }
     @FXML
    public void p2(final KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            TFPhone.requestFocus();

        }
    }
     @FXML
    public void p3(final KeyEvent keyEvent) throws SQLException {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            TFCopanyname.requestFocus();
            insertDataDB();

        }
    }
    
}


