/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afaq;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;



public class Afag extends Application {
    

       public static String MainScreanID = "MainScrean";
    public static String MainScreanFile = "FXML/MainScrean.fxml";
   
    public static String BillBuyedID = "BillBuyed";
    public static String BillBuyedFile = "FXML/BillBuyed.fxml";
    public static String BillCashierID = "BillCashier";
    public static String BillCashierFile = "FXML/BillCashier.fxml";
    public static String BillCustomerID = "BillCustomer";
    public static String BillCustomerFile = "FXML/BillCustomer.fxml";
   
    public static String BillsellerID = "Billseller";
    public static String BillsellerFile = "FXML/Billseller.fxml";

    public static String CompanyInformationID = "CompanyInformation";
    public static String CompanyInformationFile = "FXML/CompanyInformation.fxml";
    public static String DefineCustomerID = "DefineCustomer";
    public static String DefineCustomerFile = "FXML/DefineCustomer.fxml";
    public static String DefineSellerID = "DefineSeller";
    public static String DefineSellerFile = "FXML/DefineSeller.fxml";
  
  
 
    public static String LoginID = "Login";
    public static String LoginFile = "FXML/Login.fxml";
 
    public static String ReportID = "Report";
    public static String ReportFile = "FXML/Report.fxml";
  
  
  
    public static String UserAccountID = "UserAccount";
    public static String UserAccountFile = "FXML/UserAccount.fxml";
    public static String checkID = "check";
    public static String checkFile = "FXML/check.fxml";
   
    @Override
    public void start(Stage stage) throws Exception {
         
        ScreensController mainContainer = new ScreensController();

       mainContainer.loadScreen(Afag.BillBuyedID, Afag.BillBuyedFile);
        mainContainer.loadScreen(Afag.BillCashierID, Afag.BillCashierFile);
      mainContainer.loadScreen(Afag.BillCustomerID, Afag.BillCustomerFile);
//     
        mainContainer.loadScreen(Afag.BillsellerID, Afag.BillsellerFile);
//       
       mainContainer.loadScreen(Afag.DefineSellerID, Afag.DefineSellerFile);
//        mainContainer.loadScreen(Afag.CompanyInformationID, Afag.CompanyInformationFile);
        mainContainer.loadScreen(Afag.DefineCustomerID, Afag.DefineCustomerFile);
//
        mainContainer.loadScreen(Afag.LoginID, Afag.LoginFile);
//    
//
//
//        mainContainer.loadScreen(Afag.UserAccountID, Afag.UserAccountFile);
     
        mainContainer.loadScreen(Afag.MainScreanID, Afag.MainScreanFile);
        mainContainer.loadScreen(Afag.ReportID, Afag.ReportFile);

        mainContainer.setScreen(Afag.LoginID);

        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
        stage.setScene(scene);
//        stage.initStyle(StageStyle.UNDECORATED);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("printlogo2.png")));
        stage.show();
       
    }
    public static void main(String[] args) {
        launch(args);
    }

}
