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
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import samba.ControlledScreen;
import samba.Samba;
import samba.ScreensController;
import samba.Table.TuserPermission;

/**
 * FXML Controller class
 *
 * @author AMR SAID
 */
public class MainController implements Initializable, ControlledScreen {

    TuserPermission permission;
    ScreensController ControlledScreen;
    @FXML
    AnchorPane sp;
    @FXML
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
//        sp.setPrefWidth(width);
//        sp.setPrefHeight(height);
//        btn1.setPrefHeight(height / 3.1);
//        btn1.setPrefWidth(width / 3.1);
//        btn2.setPrefHeight(height / 3.1);
//        btn2.setPrefWidth(width / 3.1);
//        btn3.setPrefHeight(height / 3.1);
//        btn3.setPrefWidth(width / 3.1);
//        btn4.setPrefHeight(height / 3.1);
//        btn4.setPrefWidth(width / 3.1);
//        btn5.setPrefHeight(height / 3.1);
//        btn5.setPrefWidth(width / 3.1);
//        btn6.setPrefHeight(height / 3.1);
//        btn6.setPrefWidth(width / 3.1);
//        btn7.setPrefHeight(height / 3.1);
//        btn7.setPrefWidth(width / 3.1);
//        btn8.setPrefHeight(height / 3.1);
//        btn8.setPrefWidth(width / 3.1);
//        btn9.setPrefHeight(height / 3.1);
//        btn9.setPrefWidth(width / 3.1);


        
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        ControlledScreen = screenPage;
    }

    @FXML
    public void Delivery() {

        FXMLLoader loder = Samba.FXDelivery;
        DeliveryController scenseController = loder.getController();
        scenseController.setCurrent(permission);
        ControlledScreen.setScreen(Samba.DeliveryId);

    }

    @FXML
    public void TakeAway() throws SQLException {
         ControlledScreen.setScreen(Samba.TakeAwayId);
        FXMLLoader loder = Samba.FXTakeAway;
        TakeAwayController scenseController = loder.getController();
       scenseController.setCurrent(permission);
       
    }
    @FXML
    public void Order() {
         ControlledScreen.setScreen(Samba.OrderId);
       
       
    }
    @FXML
    public void Table() {
        FXMLLoader loder = Samba.FXTable;
        TableController scenseController = loder.getController();
        scenseController.setCurrent(permission);
        ControlledScreen.setScreen(Samba.TableId);
       
       
    }

    public void setCurrent(TuserPermission permission) {
        this.permission = permission;
        
        
        
        

    }
    
    

}
