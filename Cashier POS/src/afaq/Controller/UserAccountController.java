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
    CheckBox CB3;
    @FXML
    CheckBox CB4;
    @FXML
    CheckBox CB5;
    @FXML
    CheckBox CB6;
    @FXML
    CheckBox CB7;
    @FXML
    CheckBox CB8;
    @FXML
    CheckBox CB9;
    @FXML
    CheckBox CB10;
    @FXML
    CheckBox CB11;
    @FXML
    CheckBox CB12;
    @FXML
    CheckBox CB13;
    @FXML
    CheckBox CB14;
    @FXML
    CheckBox CB15;
    @FXML
    CheckBox CB16;
    @FXML
    CheckBox CB17;
    @FXML
    CheckBox CB18;
    @FXML
    CheckBox CB19;
    @FXML
    CheckBox CB20;
    @FXML
    CheckBox CB21;
    @FXML
    CheckBox CB22;
    @FXML
    CheckBox CB23;
    @FXML
    CheckBox CB24;
    @FXML
    CheckBox CB25;
    @FXML
    CheckBox CB26;
    @FXML
    CheckBox CB27;
    @FXML
    CheckBox CB28;
    @FXML
    CheckBox CB29;
    @FXML
    CheckBox CB30;
    @FXML
    CheckBox CB31;
    @FXML
    CheckBox CB32;
    @FXML
    CheckBox CB33;
    @FXML
    CheckBox CB34;

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

            data.add(new TuserPermission(DB.rs.getInt("id"), DB.rs.getString("user_name"), DB.rs.getString("pass"), DB.rs.getString("note"), DB.rs.getBoolean("stock1"), DB.rs.getBoolean("stock2"), DB.rs.getBoolean("stock3"), DB.rs.getBoolean("stock4"), DB.rs.getBoolean("stock5"), DB.rs.getBoolean("stock6"), DB.rs.getBoolean("bills1"), DB.rs.getBoolean("bills2"), DB.rs.getBoolean("bills3"), DB.rs.getBoolean("bills4"), DB.rs.getBoolean("bills5"), DB.rs.getBoolean("bills6"), DB.rs.getBoolean("bills7"), DB.rs.getBoolean("bills8"), DB.rs.getBoolean("bills9"), DB.rs.getBoolean("bills10"), DB.rs.getBoolean("bills11"), DB.rs.getBoolean("bills12"), DB.rs.getBoolean("bills13"), DB.rs.getBoolean("bills14"), DB.rs.getBoolean("comcus1"), DB.rs.getBoolean("comcus2"), DB.rs.getBoolean("comcus3"), DB.rs.getBoolean("comcus4"), DB.rs.getBoolean("comcus5"), DB.rs.getBoolean("comcus6"), DB.rs.getBoolean("comcus7"), DB.rs.getBoolean("comcus8"), DB.rs.getBoolean("setting1"), DB.rs.getBoolean("setting2"), DB.rs.getBoolean("setting3"), DB.rs.getBoolean("setting4"), DB.rs.getBoolean("setting5"), DB.rs.getBoolean("setting6")));
        }
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
            CB1.setSelected(DB.rs.getBoolean("stock1"));
            CB2.setSelected(DB.rs.getBoolean("stock2"));
            CB3.setSelected(DB.rs.getBoolean("stock3"));
            CB4.setSelected(DB.rs.getBoolean("stock4"));
            CB5.setSelected(DB.rs.getBoolean("stock5"));
            CB6.setSelected(DB.rs.getBoolean("stock6"));
            CB7.setSelected(DB.rs.getBoolean("bills1"));
            CB8.setSelected(DB.rs.getBoolean("bills2"));
            CB9.setSelected(DB.rs.getBoolean("bills3"));
            CB10.setSelected(DB.rs.getBoolean("bills4"));
            CB11.setSelected(DB.rs.getBoolean("bills5"));
            CB12.setSelected(DB.rs.getBoolean("bills6"));
            CB13.setSelected(DB.rs.getBoolean("bills7"));
            CB14.setSelected(DB.rs.getBoolean("bills8"));
            CB15.setSelected(DB.rs.getBoolean("bills9"));
            CB16.setSelected(DB.rs.getBoolean("bills10"));
            CB17.setSelected(DB.rs.getBoolean("bills11"));
            CB18.setSelected(DB.rs.getBoolean("bills12"));
            CB19.setSelected(DB.rs.getBoolean("bills13"));
            CB20.setSelected(DB.rs.getBoolean("bills14"));
            CB21.setSelected(DB.rs.getBoolean("comcus1"));
            CB22.setSelected(DB.rs.getBoolean("comcus2"));
            CB23.setSelected(DB.rs.getBoolean("comcus3"));
            CB24.setSelected(DB.rs.getBoolean("comcus4"));
            CB25.setSelected(DB.rs.getBoolean("comcus5"));
            CB26.setSelected(DB.rs.getBoolean("comcus6"));
            CB27.setSelected(DB.rs.getBoolean("comcus7"));
            CB28.setSelected(DB.rs.getBoolean("comcus8"));
            CB29.setSelected(DB.rs.getBoolean("setting1"));
            CB30.setSelected(DB.rs.getBoolean("setting2"));
            CB31.setSelected(DB.rs.getBoolean("setting3"));
            CB32.setSelected(DB.rs.getBoolean("setting4"));
            CB33.setSelected(DB.rs.getBoolean("setting5"));
            CB34.setSelected(DB.rs.getBoolean("setting6"));

        }
        
    }

    @FXML
    public void InsertUser() throws SQLException {

        boolean value1 = CB1.isSelected();
        boolean value2 = CB2.isSelected();
        boolean value3 = CB3.isSelected();
        boolean value4 = CB4.isSelected();
        boolean value5 = CB5.isSelected();
        boolean value6 = CB6.isSelected();
        boolean value7 = CB7.isSelected();
        boolean value8 = CB8.isSelected();
        boolean value9 = CB9.isSelected();
        boolean value10 = CB10.isSelected();
        boolean value11 = CB11.isSelected();
        boolean value12 = CB12.isSelected();
        boolean value13 = CB13.isSelected();
        boolean value14 = CB14.isSelected();
        boolean value15 = CB15.isSelected();
        boolean value16 = CB16.isSelected();
        boolean value17 = CB17.isSelected();
        boolean value18 = CB18.isSelected();
        boolean value19 = CB19.isSelected();
        boolean value20 = CB20.isSelected();
        boolean value21 = CB21.isSelected();
        boolean value22 = CB22.isSelected();
        boolean value23 = CB23.isSelected();
        boolean value24 = CB24.isSelected();
        boolean value25 = CB25.isSelected();
        boolean value26 = CB26.isSelected();
        boolean value27 = CB27.isSelected();
        boolean value28 = CB28.isSelected();
        boolean value29 = CB29.isSelected();
        boolean value30 = CB30.isSelected();
        boolean value31 = CB31.isSelected();
        boolean value32 = CB32.isSelected();
        boolean value33 = CB33.isSelected();
        boolean value34 = CB34.isSelected();
        if(User_name.getText().equals("")){
        Erromassage.setText("من فضلك ادخل البياناتالمطلوبة");
        }else{

        DB.statemen.executeUpdate("INSERT INTO users( user_name, pass, note, stock1, stock2, stock3, stock4, stock5, stock6, bills1, bills2, bills3, bills4, bills5, bills6, bills7, bills8, bills9, bills10, bills11, bills12, bills13, bills14, comcus1, comcus2, comcus3, comcus4, comcus5, comcus6, comcus7, comcus8, setting1, setting2, setting3, setting4, setting5, setting6) VALUES ('" + User_name.getText() + "','" + pass.getText() + "','" + note.getText() + "','" + value1 + "','" + value2 + "','" + value3 + "','" + value4 + "','" + value5 + "','" + value6 + "','" + value7 + "','" + value8 + "','" + value9 + "','" + value10 + "','" + value11 + "','" + value12 + "','" + value13 + "','" + value14 + "','" + value15 + "','" + value16 + "','" + value17 + "','" + value18 + "','" + value19 + "','" + value20 + "','" + value21 + "','" + value22 + "','" + value23 + "','" + value24 + "','" + value25 + "','" + value26 + "','" + value27 + "','" + value28 + "','" + value29 + "','" + value30 + "','" + value31 + "','" + value32 + "','" + value33 + "','" + value34 + "')");

        FillTable();
        clear();
        }}

    @FXML
    public void clear() {
        User_name.setText(null);

        pass.setText(null);
Erromassage.setText("");
        note.setText(null);
        CB1.setSelected(false);
        CB2.setSelected(false);
        CB3.setSelected(false);
        CB4.setSelected(false);
        CB5.setSelected(false);
        CB6.setSelected(false);
        CB7.setSelected(false);
        CB8.setSelected(false);
        CB9.setSelected(false);
        CB10.setSelected(false);
        CB11.setSelected(false);
        CB12.setSelected(false);
        CB13.setSelected(false);
        CB14.setSelected(false);
        CB15.setSelected(false);
        CB16.setSelected(false);
        CB17.setSelected(false);
        CB18.setSelected(false);
        CB19.setSelected(false);
        CB20.setSelected(false);
        CB21.setSelected(false);
        CB22.setSelected(false);
        CB23.setSelected(false);
        CB24.setSelected(false);
        CB25.setSelected(false);
        CB26.setSelected(false);
        CB27.setSelected(false);
        CB28.setSelected(false);
        CB29.setSelected(false);
        CB30.setSelected(false);
        CB31.setSelected(false);
        CB32.setSelected(false);
        CB33.setSelected(false);
        CB34.setSelected(false);
    }

    @FXML
    public void UpdataUser() throws SQLException {
          TuserPermission InsertData = (TuserPermission) tableUser.getSelectionModel().getSelectedItem();
        boolean value1 = CB1.isSelected();
        boolean value2 = CB2.isSelected();
        boolean value3 = CB3.isSelected();
        boolean value4 = CB4.isSelected();
        boolean value5 = CB5.isSelected();
        boolean value6 = CB6.isSelected();
        boolean value7 = CB7.isSelected();
        boolean value8 = CB8.isSelected();
        boolean value9 = CB9.isSelected();
        boolean value10 = CB10.isSelected();
        boolean value11 = CB11.isSelected();
        boolean value12 = CB12.isSelected();
        boolean value13 = CB13.isSelected();
        boolean value14 = CB14.isSelected();
        boolean value15 = CB15.isSelected();
        boolean value16 = CB16.isSelected();
        boolean value17 = CB17.isSelected();
        boolean value18 = CB18.isSelected();
        boolean value19 = CB19.isSelected();
        boolean value20 = CB20.isSelected();
        boolean value21 = CB21.isSelected();
        boolean value22 = CB22.isSelected();
        boolean value23 = CB23.isSelected();
        boolean value24 = CB24.isSelected();
        boolean value25 = CB25.isSelected();
        boolean value26 = CB26.isSelected();
        boolean value27 = CB27.isSelected();
        boolean value28 = CB28.isSelected();
        boolean value29 = CB29.isSelected();
        boolean value30 = CB30.isSelected();
        boolean value31 = CB31.isSelected();
        boolean value32 = CB32.isSelected();
        boolean value33 = CB33.isSelected();
        boolean value34 = CB34.isSelected();
        Erromassage.setText("");
        System.out.println(selected);
 if(User_name.getText().equals("")){
        Erromassage.setText("من فضلك ادخل البياناتالمطلوبة");
        }else{
        DB.statemen.executeUpdate("UPDATE users SET user_name='" + User_name.getText() + "',pass='" + pass.getText() + "',note='" + note.getText() + "',stock1='" + value1 + "',stock2='" + value2 + "',stock3='" + value3 + "',stock4='" + value4 + "',stock5='" + value5 + "',stock6='" + value6 + "',bills1='" + value7 + "',bills2='" + value8 + "',bills3='" + value9 + "',bills4='" + value10 + "',bills5='" + value11 + "',bills6='" + value12 + "',bills7='" + value13 + "',bills8='" + value14 + "',bills9='" + value15 + "',bills10='" + value16 + "',bills11='" + value17 + "',bills12='" + value18 + "',bills13='" + value19 + "',bills14='" + value20 + "',comcus1='" + value21 + "',comcus2='" + value22 + "',comcus3='" + value23 + "',comcus4='" + value24 + "',comcus5='" + value25 + "',comcus6='" + value26 + "',comcus7='" + value27 + "',comcus8='" + value28 + "',setting1='" + value29 + "',setting2='" + value30 + "',setting3='" + value31 + "',setting4='" + value32 + "',setting5='" + value33 + "',setting6='" + value34 + "'  WHERE id = "+selected+"");
        FillTable();
        clear();
 }
    }
    
     @FXML
    public void DeleteUser() throws SQLException {
    DB.statemen.executeUpdate("DELETE FROM users WHERE id = '"+selected+"'");
    Erromassage.setText("");
   FillTable();
        clear(); 
    }


    @FXML
    public void CloseScene() {
        ScreensController.p13 = false;
        Stage stage = (Stage) Erromassage.getScene().getWindow();
        stage.close();
    }

    
}
