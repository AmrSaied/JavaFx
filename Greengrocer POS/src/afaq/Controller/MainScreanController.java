package afaq.Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import afaq.Afag;
import afaq.ControlledScreen;
import afaq.ScreensController;
import afaq.Table.TuserPermission;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HackerMan
 */
public class MainScreanController implements Initializable, ControlledScreen {

    ScreensController myController;
    @FXML
    AnchorPane A1;
    @FXML
    AnchorPane A2;
    @FXML
    AnchorPane A3;
    @FXML
    AnchorPane A4;
    @FXML
    Button btn;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
  
    }    

    @Override
    public void setScreenParent(ScreensController screenPage) {
          myController = screenPage;
    }
    
    @FXML
    public void DefineCustomer(){
            myController.NewloadScreen(afaq.Afag.DefineCustomerID, afaq.Afag.DefineCustomerFile);
     
    }
    @FXML
    public void BillCustomer(){
            myController.NewloadScreen(afaq.Afag.BillCustomerID, afaq.Afag.BillCustomerFile);

    }
    @FXML
    public void Billseller(){
                  myController.NewloadScreen(afaq.Afag.BillsellerID, afaq.Afag.BillsellerFile);

    }
    @FXML
    public void DefineSeller(){
                  myController.NewloadScreen(afaq.Afag.DefineSellerID, afaq.Afag.DefineSellerFile);

    }
    @FXML
    public void BillCashier(){
                  myController.NewloadScreen(afaq.Afag.BillCashierID, afaq.Afag.BillCashierFile);

    }
    @FXML
    public void BillBuyed(){
                  myController.NewloadScreen(afaq.Afag.BillBuyedID, afaq.Afag.BillBuyedFile);

    }
    @FXML
    public void UserAccount(){
                  myController.NewloadScreen(afaq.Afag.UserAccountID, afaq.Afag.UserAccountFile);

    }
    @FXML
    public void CompanyInformation(){
                  myController.NewloadScreen(afaq.Afag.CompanyInformationID, afaq.Afag.CompanyInformationFile);

    }
    @FXML
    public void check(){
                  myController.NewloadScreen(afaq.Afag.checkID, afaq.Afag.checkFile);

    }
    @FXML
    public void Report(){
                  myController.NewloadScreen(afaq.Afag.ReportID, afaq.Afag.ReportFile);

    }
    @FXML
    public void btnCustomer(){
               A1.setVisible(true);
               A2.setVisible(false);
               A3.setVisible(false);
               A4.setVisible(false);
    }
    @FXML
    public void btnSeller(){
               A1.setVisible(false);
               A2.setVisible(true);
               A3.setVisible(false);
               A4.setVisible(false);

    }
    @FXML
    public void btnCheck(){
               A1.setVisible(false);
               A2.setVisible(false);
               A3.setVisible(false);
               A4.setVisible(true);

    }
    @FXML
    public void btnSetting(){
               A1.setVisible(false);
               A2.setVisible(false);
               A3.setVisible(true);
               A4.setVisible(false);

    }
    @FXML
    public void logout(){
             FXMLLoader loder = myController.pageWithParamter(Afag.LoginFile);          
                Stage stage = (Stage) btn.getScene().getWindow();
                stage.close();

    }
    @FXML
    public void close(){
             System.exit(0);

    }
 


}
