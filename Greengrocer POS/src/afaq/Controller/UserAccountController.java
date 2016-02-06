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

import afaq.Table.TuserPermission;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author AmrSaid
 */
public class UserAccountController implements Initializable, ControlledScreen {

    ScreensController myController;
    DBConnection DB;

    @FXML
    TableView<TuserPermission> tableUser;

    @FXML
    TableColumn<TuserPermission, String> TUserName;

    @FXML
    TableColumn<TuserPermission, String> Tpassword;

    @FXML
    CheckBox CB1;
    @FXML
    CheckBox CB2;
   
    @FXML
    TextField User_name;
    @FXML
    TextField pass;
    @FXML
    TextArea note;
    
    @FXML
    Label CustomerN;
    @FXML
    Label Erromassage;
    int selected = 0;

    ObservableList<TuserPermission> data = FXCollections.observableArrayList();
    int count = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

      
        try {
            DB = new DBConnection();
            TUserName.setCellValueFactory(new PropertyValueFactory<TuserPermission, String>("user_name"));
            Tpassword.setCellValueFactory(new PropertyValueFactory<TuserPermission, String>("pass"));
            tableUser.setItems(data);

            FillTable();


        } catch (Exception ex) {
            String message = ex.getMessage();
            System.out.println("Errrrrrrrrrrro" + message);

        }
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    public void FillTable() throws SQLException {
        System.out.println("FillTable");
        data.clear();
        count = 0;
        DB.rs = DB.statemen.executeQuery("SELECT * FROM users");

        while (DB.rs.next()) {
            count++;

            data.add(new TuserPermission(DB.rs.getInt("id"), DB.rs.getString("user_name"), DB.rs.getString("pass"), DB.rs.getString("note"), DB.rs.getBoolean("permission1"), DB.rs.getBoolean("permission2")));}
CustomerN.setText(""+count);
Erromassage.setText("");
    }

    @FXML
    public void Selecteditem() throws SQLException {

        TuserPermission InsertData = (TuserPermission) tableUser.getSelectionModel().getSelectedItem();
        selected =InsertData.getId();
        String Sql = " SELECT * FROM users WHERE id =  '" + InsertData.getId() + "'";
        DB.rs = DB.statemen.executeQuery(Sql);

        if (DB.rs.next()) {
           
            User_name.setText(DB.rs.getString("user_name"));
            pass.setText(DB.rs.getString("pass"));
            note.setText(DB.rs.getString("note"));
            CB1.setSelected(DB.rs.getBoolean("permission1"));
            CB2.setSelected(DB.rs.getBoolean("permission2"));
        
        }
        
    }

    @FXML
    public void InsertUser() throws SQLException {

        
           int selectedOption = JOptionPane.showConfirmDialog(null,
                "هل انت متاكد من هذة العملية  ؟",
                "Choose",
                JOptionPane.YES_NO_OPTION);
        if (selectedOption == JOptionPane.YES_OPTION) {
        boolean value1 = CB1.isSelected();
        boolean value2 = CB2.isSelected();

          DB.rs = DB.statemen.executeQuery("SELECT * FROM users WHERE user_name ='"+User_name.getText()+"'");
        if(User_name.getText().equals("")){
        Erromassage.setText("من فضلك ادخل البياناتالمطلوبة");
         User_name.requestFocus();
        }else if (DB.rs.next()){
          Erromassage.setText("هذا الاسم موجود مسبقا");
         User_name.requestFocus();
        }else{

        DB.statemen.executeUpdate("INSERT INTO users( user_name, pass, note, permission1, permission2) VALUES ('" + User_name.getText() + "','" + pass.getText() + "','" + note.getText() + "','" + value1 + "','" + value2 + "')");

        FillTable();
        clear();
        }}}

    @FXML
    public void clear() {
        User_name.requestFocus();
        User_name.setText(null);

        pass.setText(null);
Erromassage.setText("");
        note.setText(null);
        CB1.setSelected(false);
        CB2.setSelected(false);
   
    }

    @FXML
    public void UpdataUser() throws SQLException {
           int selectedOption = JOptionPane.showConfirmDialog(null,
                "هل انت متاكد من هذة العملية  ؟",
                "Choose",
                JOptionPane.YES_NO_OPTION);
        if (selectedOption == JOptionPane.YES_OPTION) {
          TuserPermission InsertData = (TuserPermission) tableUser.getSelectionModel().getSelectedItem();
        boolean value1 = CB1.isSelected();
        boolean value2 = CB2.isSelected();
       
        Erromassage.setText("");
        System.out.println(selected);
 if(User_name.getText().equals("")){
        Erromassage.setText("من فضلك ادخل البياناتالمطلوبة");
         User_name.requestFocus();
        }else{
        DB.statemen.executeUpdate("UPDATE users SET user_name='" + User_name.getText() + "',pass='" + pass.getText() + "',note='" + note.getText() + "',permission1='" + value1 + "',permission2='" + value2 + "'  WHERE id = "+selected+"");
        FillTable();
        clear();
 }}
    }
    
     @FXML
    public void DeleteUser() throws SQLException {
           int selectedOption = JOptionPane.showConfirmDialog(null,
                "هل انت متاكد من هذة العملية  ؟",
                "Choose",
                JOptionPane.YES_NO_OPTION);
        if (selectedOption == JOptionPane.YES_OPTION) {
    DB.statemen.executeUpdate("DELETE FROM users WHERE id = '"+selected+"'");
    Erromassage.setText("");
   FillTable();
        clear(); 
    }}


    @FXML
    public void CloseScene() {
        Stage stage = (Stage) Erromassage.getScene().getWindow();
        stage.close();
    }

    
}
