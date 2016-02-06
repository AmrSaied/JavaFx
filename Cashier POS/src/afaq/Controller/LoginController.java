/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afaq.Controller;

import afaq.Afag;
import afaq.ControlledScreen;
import afaq.DBConnection;
import afaq.ScreensController;
import afaq.Table.TCashierService;
import afaq.Table.TFullCashier;
import afaq.Table.TuserPermission;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AmrSaid
 */
public class LoginController implements Initializable, ControlledScreen {

    ScreensController myController;
    DBConnection DB;
    @FXML
    TextField username;
    @FXML
    TextField password;
    @FXML
    Label erro;

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            DB = new DBConnection();
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void Login(final KeyEvent keyEvent) throws Exception {
        if (keyEvent.getCode() == KeyCode.ENTER) {
 

            DB.rs = DB.statemen.executeQuery("SELECT * FROM users WHERE user_name ='" + username.getText() + "' and pass = '" + password.getText() + "' ");
            if (DB.rs.next()) {
      

                TuserPermission permission = new TuserPermission(DB.rs.getInt("id"), DB.rs.getString("user_name"), DB.rs.getString("pass"), DB.rs.getString("note"), DB.rs.getBoolean("stock1"), DB.rs.getBoolean("stock2"), DB.rs.getBoolean("stock3"), DB.rs.getBoolean("stock4"), DB.rs.getBoolean("stock5"), DB.rs.getBoolean("stock6"), DB.rs.getBoolean("bills1"), DB.rs.getBoolean("bills2"), DB.rs.getBoolean("bills3"), DB.rs.getBoolean("bills4"), DB.rs.getBoolean("bills5"), DB.rs.getBoolean("bills6"), DB.rs.getBoolean("bills7"), DB.rs.getBoolean("bills8"), DB.rs.getBoolean("bills9"), DB.rs.getBoolean("bills10"), DB.rs.getBoolean("bills11"), DB.rs.getBoolean("bills12"), DB.rs.getBoolean("bills13"), DB.rs.getBoolean("bills14"), DB.rs.getBoolean("comcus1"), DB.rs.getBoolean("comcus2"), DB.rs.getBoolean("comcus3"), DB.rs.getBoolean("comcus4"), DB.rs.getBoolean("comcus5"), DB.rs.getBoolean("comcus6"), DB.rs.getBoolean("comcus7"), DB.rs.getBoolean("comcus8"), DB.rs.getBoolean("setting1"), DB.rs.getBoolean("setting2"), DB.rs.getBoolean("setting3"), DB.rs.getBoolean("setting4"), DB.rs.getBoolean("setting5"), DB.rs.getBoolean("setting6"));
                
                FXMLLoader loder = myController.pageWithParamter(Afag.MainFrameFile);
                MainFrameController scenseController = loder.getController();
                scenseController.setCurrent(permission);

                Stage stage = (Stage) username.getScene().getWindow();
                stage.close();

            } else {
                erro.setText("اسم المستخدم او كلمة المرور غير صحيحة");

            }
        }

    }

}
