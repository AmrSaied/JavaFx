/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afaq.Controller;

import afaq.ControlledScreen;
import afaq.DBConnection;
import afaq.ScreensController;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AmrSaid
 */
public class CompanyInformationController implements Initializable, ControlledScreen {

  ScreensController myController;
     DBConnection DB;
  @FXML
  TextField TFnameCompany;
  @FXML
  TextField TFAdrees;
  @FXML
  TextField TFPhone;
  @FXML
  TextArea TFother;
  @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      try {
          DB = new DBConnection();
          FillFiled();
                      Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    TFnameCompany.requestFocus();
                }
            });

      } catch (Exception ex) {
          Logger.getLogger(CompanyInformationController.class.getName()).log(Level.SEVERE, null, ex);
      } 
    }
    
    
    @FXML
     public void FillFiled() throws SQLException{
      DB.rs = DB.statemen.executeQuery("SELECT * FROM info");
      DB.rs.next();
     TFnameCompany.setText(DB.rs.getString("com_name"));
     TFAdrees.setText(DB.rs.getString("address"));
     TFPhone.setText(DB.rs.getString("tel"));
     TFother.setText(DB.rs.getString("note"));
     }
    @FXML
     public void UpdateFiled() throws SQLException{
     DB.statemen.executeUpdate("UPDATE info SET com_name='"+TFnameCompany.getText()+"',address='"+TFAdrees.getText()+"',tel='"+TFPhone.getText()+"',note='"+TFother.getText()+"' ");
      
  
     }
     
     
     @FXML
    public void CloseScene() {

        Stage stage = (Stage) TFAdrees.getScene().getWindow();
        stage.close();
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
            TFnameCompany.requestFocus();
            UpdateFiled();

        }
    }

 
    
}
