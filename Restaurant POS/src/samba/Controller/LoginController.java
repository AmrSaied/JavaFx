/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package samba.Controller;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import samba.ControlledScreen;
import samba.DBConnection;
import samba.Samba;
import samba.ScreensController;
import samba.Table.TuserPermission;

/**
 * FXML Controller class
 *
 * @author belal
 */
public class LoginController implements Initializable, ControlledScreen {

    ScreensController ControlledScreen;
    DBConnection DB;
    @FXML
    TextField username;
    @FXML
    TextField password;
    @FXML
    Label labelError1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            DB = new DBConnection();
        } catch (Exception ex) {

        }
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        ControlledScreen = screenPage;

    }

    @FXML
    public void Login() throws Exception {

        if (password.getText().equals("") && username.getText().equals("")) {

            System.out.println("HERE");
            labelError1.setVisible(true);

        } else {

            DB.rs = DB.statemen.executeQuery("SELECT * FROM users WHERE user_name ='" + username.getText() + "' and pass = '" + password.getText() + "' ");
            if (DB.rs.next()) {

                TuserPermission permission = new TuserPermission(DB.rs.getInt("id"), DB.rs.getString("user_name"), DB.rs.getString("pass"), DB.rs.getString("note"), DB.rs.getBoolean("stock1"), DB.rs.getBoolean("stock2"), DB.rs.getBoolean("stock3"), DB.rs.getBoolean("stock4"), DB.rs.getBoolean("stock5"), DB.rs.getBoolean("stock6"), DB.rs.getBoolean("bills1"), DB.rs.getBoolean("bills2"), DB.rs.getBoolean("bills3"), DB.rs.getBoolean("bills4"), DB.rs.getBoolean("bills5"), DB.rs.getBoolean("bills6"), DB.rs.getBoolean("bills7"), DB.rs.getBoolean("bills8"), DB.rs.getBoolean("bills9"), DB.rs.getBoolean("bills10"), DB.rs.getBoolean("bills11"), DB.rs.getBoolean("bills12"), DB.rs.getBoolean("bills13"), DB.rs.getBoolean("bills14"), DB.rs.getBoolean("comcus1"), DB.rs.getBoolean("comcus2"), DB.rs.getBoolean("comcus3"), DB.rs.getBoolean("comcus4"), DB.rs.getBoolean("comcus5"), DB.rs.getBoolean("comcus6"), DB.rs.getBoolean("comcus7"), DB.rs.getBoolean("comcus8"), DB.rs.getBoolean("setting1"), DB.rs.getBoolean("setting2"), DB.rs.getBoolean("setting3"), DB.rs.getBoolean("setting4"), DB.rs.getBoolean("setting5"), DB.rs.getBoolean("setting6"));

                ControlledScreen.setScreen(Samba.MainyId);

                FXMLLoader loder = Samba.FXMain;
                MainController scenseController = loder.getController();
                scenseController.setCurrent(permission);

//                Stage stage = (Stage) labelError1.getScene().getWindow();
//                stage.setFullScreen(true);

                labelError1.setVisible(false);

            } else {
                labelError1.setVisible(true);

            }
        }

    }

    @FXML
    public void LoginEnter(final KeyEvent keyEvent) throws Exception {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            Login();
        }

    }

    public void ExitSoft() {

        System.exit(0);
    }

}
