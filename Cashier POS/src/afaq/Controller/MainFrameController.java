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
import afaq.Table.TuserPermission;
import java.awt.Color;
import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;



/**
 * FXML Controller class
 *
 * @author AmrSaid
 */
public class MainFrameController implements Initializable, ControlledScreen {

    ScreensController myController;
    TuserPermission current;
    NewBarCodeController newBarCodeController;

    @FXML
    Label datenow;
    @FXML
    Label username;
    @FXML
    MenuItem mi1, mi2, mi3, mi4, mi5, mi6, mi7, mi8, mi9, mi10, mi11, mi12, mi13, mi14, mi15, mi16, mi17, mi18, mi19, mi20, mi21, mi22, mi23, mi24, mi25, mi26, mi27, mi28, mi30, mi32;

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
    }

    public void setCurrent(TuserPermission current) {

        this.current = current;
        username.setText(current.getUser_name());
        mi1.setVisible(current.isStock1());
        mi2.setVisible(current.isStock2());
        mi3.setVisible(current.isStock3());
        mi4.setVisible(current.isStock4());
        mi5.setVisible(current.isStock5());
        mi6.setVisible(current.isStock6());
        mi7.setVisible(current.isBills1());
        mi8.setVisible(current.isBills2());
        mi9.setVisible(current.isBills3());
        mi10.setVisible(current.isBills4());
        mi11.setVisible(current.isBills5());
        mi12.setVisible(current.isBills6());
        
        mi13.setVisible(current.isBills7());
        mi14.setVisible(current.isBills8());
        mi15.setVisible(current.isBills9());
        mi16.setVisible(current.isBills10());
        
        mi17.setVisible(current.isBills11());
        mi18.setVisible(current.isBills12());

        mi20.setVisible(current.isBills14());
        mi21.setVisible(current.isComcus1());
        mi22.setVisible(current.isComcus2());
        
        mi23.setVisible(current.isComcus3());
        mi24.setVisible(current.isComcus4());
        
        
        mi25.setVisible(current.isComcus5());
        mi26.setVisible(current.isComcus6());
        mi27.setVisible(current.isComcus7());
        mi28.setVisible(current.isComcus8());
        mi30.setVisible(current.isSetting2());
        mi32.setVisible(current.isSetting4());


    }

    @FXML
    public void CloseScene() {
    
 
        System.exit(0);
    }

    @FXML
    public void InserData() {
        if(ScreensController.p1 == false){
             myController.NewloadScreen(afaq.Afag.InsertDataID, afaq.Afag.InsertDataFile);
            ScreensController.p1 = true;
        }
    }

    @FXML
    public void InserService() {
         if(ScreensController.p2 == false){
               myController.NewloadScreen(afaq.Afag.InsertServicesID, afaq.Afag.InsertServicesFile);
            ScreensController.p2 = true;
        }
      

    }

    @FXML
    public void printBarcode() {
         if(ScreensController.p3 == false){
          myController.NewloadScreen(afaq.Afag.NewBarCodeID, afaq.Afag.NewBarCodeFile);
            ScreensController.p3 = true;
        }
       

    }

    @FXML
    public void BillBuyed() {
         if(ScreensController.p4 == false){
             FXMLLoader loder = myController.pageWithParamter(Afag.BillBuyedFile);
                BillBuyedController scenseController = loder.getController();
                scenseController.userPermission(current);
            ScreensController.p4 = true;
        }
   
          

    }

    @FXML
    public void BillCashier() {
         if(ScreensController.p5 == false){
      myController.NewloadScreen(afaq.Afag.BillCashierID, afaq.Afag.BillCashierFile);

            ScreensController.p5 = true;
        }
       
    }

    @FXML
    public void BillCustomer() {
         if(ScreensController.p6 == false){
              myController.NewloadScreen(afaq.Afag.BillCustomerID, afaq.Afag.BillCustomerFile);
            ScreensController.p6 = true;
        }
      

    }

    @FXML
    public void BillTestItem() {
         if(ScreensController.p7 == false){
           myController.NewloadScreen(afaq.Afag.BillTestItemID, afaq.Afag.BillTestItemFile);

            ScreensController.p7 = true;
        }
     
    }

    @FXML
    public void Billseller() {
       
         if(ScreensController.p8 == false){
              FXMLLoader loder = myController.pageWithParamter(Afag.BillsellerFile);
                BillsellerController scenseController = loder.getController();
                scenseController.userPermission(current);
            ScreensController.p8 = true;
        }
        
         

    }

    @FXML
    public void DeleteAll() {
         if(ScreensController.p9 == false){
                 myController.NewloadScreen(afaq.Afag.DeleteAllID, afaq.Afag.DeleteAllFile);
            ScreensController.p9 = true;
        }
   

    }

    @FXML
    public void FullCashier() {
         if(ScreensController.p10 == false){
            FXMLLoader loder = myController.pageWithParamter(Afag.FullCashierFile);
                FullCashierController scenseController = loder.getController();
                scenseController.userPermission(current);
            ScreensController.p10 = true;
        }
        
          

    }

    @FXML
    public void Login() {
        myController.NewloadScreen(afaq.Afag.LoginID, afaq.Afag.LoginFile);

    }

    @FXML
    public void ReBuyed() {
         if(ScreensController.p11 == false){
            FXMLLoader loder = myController.pageWithParamter(Afag.ReButedFile);
                ReBuyedController scenseController = loder.getController();
                 
            ScreensController.p11 = true;
             System.out.println("DONE pageWithParamter");
        }
     

         
    }

    @FXML
    public void ReSeller() {
         if(ScreensController.p12 == false){
            
           FXMLLoader loder = myController.pageWithParamter(Afag.ReSellerFile);
             System.out.println("DONE pageWithParamter");
                ReSellerController scenseController = loder.getController();
                 System.out.println("DONE pageWithParamter");
                scenseController.userPermission(current);
               
                System.out.println("DONE pageWithParamter");
                scenseController.userPermission(current);
            ScreensController.p12 = true;
              System.out.println("DONE pageWithParamter");
        }
        

    }

    @FXML
    public void UserAccount() {
         if(ScreensController.p13 == false){
               myController.NewloadScreen(afaq.Afag.UserAccountID, afaq.Afag.UserAccountFile);
            ScreensController.p13 = true;
        }
    

    }

    @FXML
    public void CompanyInformation() {
         if(ScreensController.p14 == false){
              myController.NewloadScreen(afaq.Afag.CompanyInformationID, afaq.Afag.CompanyInformationFile);
            ScreensController.p14 = true;
        }
      

    }

    @FXML
    public void DefineCustomer() {
         if(ScreensController.p15 == false){
              myController.NewloadScreen(afaq.Afag.DefineCustomerID, afaq.Afag.DefineCustomerFile);
            ScreensController.p15 = true;
        }
    

    }

    @FXML
    public void DefineSeller() {
         if(ScreensController.p16 == false){
          myController.NewloadScreen(afaq.Afag.DefineSellerID, afaq.Afag.DefineSellerFile);
            ScreensController.p16 = true;
        }
      

    }

    @FXML
    public void calc() {

    }

    @FXML
    public void aboutus() {

    }

    @FXML
    public void Report1() throws Exception {

         myController.NewloadScreen(afaq.Afag.Report1, afaq.Afag.Report1File);
             

    }

    @FXML
    public void Report2() {
        myController.NewloadScreen(afaq.Afag.Report2, afaq.Afag.Report2File);

    }

    @FXML
    public void Report3() {
       myController.NewloadScreen(afaq.Afag.Report3, afaq.Afag.Report3File);

    }

    @FXML
    public void Report4() {
         myController.NewloadScreen(afaq.Afag.Report4, afaq.Afag.Report4File);

    }

    @FXML
    public void Report5() {
      myController.NewloadScreen(afaq.Afag.Report5, afaq.Afag.Report5File);

    }

    @FXML
    public void Report6() {
        myController.NewloadScreen(afaq.Afag.Report6, afaq.Afag.Report6File);

    }

    @FXML
    public void Report7() {
        myController.NewloadScreen(afaq.Afag.Report7, afaq.Afag.Report7File);

    }

    @FXML
    public void Report8() {
       myController.NewloadScreen(afaq.Afag.Report8, afaq.Afag.Report8File);

    }

    @FXML
    public void Report9() {
       myController.NewloadScreen(afaq.Afag.Report9, afaq.Afag.Report9File);

    }

 

    @FXML
    public void Report11() {
      myController.NewloadScreen(afaq.Afag.Report10, afaq.Afag.Report10File);

    }

    @FXML
    public void Report12() {
       myController.NewloadScreen(afaq.Afag.Report11, afaq.Afag.Report11File);

    }

    @FXML
    public void Report13() {
       myController.NewloadScreen(afaq.Afag.Report12, afaq.Afag.Report12File);

    }

    @FXML
    public void Report14() {
        myController.NewloadScreen(afaq.Afag.Report13, afaq.Afag.Report13File);

    }

    @FXML
    public void logout() {
        FXMLLoader loder = myController.pageWithParamter(Afag.LoginFile);
        Stage stage = (Stage) username.getScene().getWindow();
        stage.close();
    }

}
