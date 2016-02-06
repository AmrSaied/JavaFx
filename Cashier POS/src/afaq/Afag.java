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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;



public class Afag extends Application {
    

    public static String ReSellerID = "ReSeller";
    public static String ReSellerFile = "FXML/ReSeller.fxml";
    public static String BillBuyedID = "BillBuyed";
    public static String BillBuyedFile = "FXML/BillBuyed.fxml";
    public static String BillCashierID = "BillCashier";
    public static String BillCashierFile = "FXML/BillCashier.fxml";
    public static String BillCustomerID = "BillCustomer";
    public static String BillCustomerFile = "FXML/BillCustomer.fxml";
    public static String BillTestItemID = "BillTestItem";
    public static String BillTestItemFile = "FXML/BillTestItem.fxml";
    public static String BillsellerID = "Billseller";
    public static String BillsellerFile = "FXML/Billseller.fxml";
    public static String CashierServiceID = "CashierService";
    public static String CashierServiceFile = "FXML/CashierService.fxml";
    public static String CompanyInformationID = "CompanyInformation";
    public static String CompanyInformationFile = "FXML/CompanyInformation.fxml";
    public static String DefineCustomerID = "DefineCustomer";
    public static String DefineCustomerFile = "FXML/DefineCustomer.fxml";
    public static String DefineSellerID = "DefineSeller";
    public static String DefineSellerFile = "FXML/DefineSeller.fxml";
    public static String DeleteAllID = "DeleteAll";
    public static String DeleteAllFile = "FXML/DeleteAll.fxml";
    public static String FullCashierID = "FullCashier";
    public static String FullCashierFile = "FXML/FullCashier.fxml";
    public static String InsertDataID = "InsertData";
    public static String InsertDataFile = "FXML/InsertData.fxml";
    public static String InsertServicesID = "InsertServices";
    public static String InsertServicesFile = "FXML/InsertServices.fxml";
    public static String LoginID = "Login";
    public static String LoginFile = "FXML/Login.fxml";
    public static String MainFrameID = "MainFrame";
    public static String MainFrameFile = "FXML/MainFrame.fxml";
    public static String NewBarCodeID = "NewBarCode";
    public static String NewBarCodeFile = "FXML/NewBarCode.fxml";
    public static String ReButedID = "ReButed";
    public static String ReButedFile = "FXML/ReButed.fxml";
    public static String UserAccountID = "UserAccount";
    public static String UserAccountFile = "FXML/UserAccount.fxml";
    public static String Report1 = "Report";
    public static String Report1File = "Report/Report1.fxml";
    public static String Report2 = "Report2";
    public static String Report2File = "Report/Report2.fxml";
    public static String Report3 = "Report3";
    public static String Report3File = "Report/Report3.fxml";
    public static String Report4 = "Report4";
    public static String Report4File = "Report/Report4.fxml";
    public static String Report5 = "Report5";
    public static String Report5File = "Report/Report5.fxml";
    public static String Report6 = "Report6";
    public static String Report6File = "Report/Report6.fxml";
    public static String Report7 = "Report7";
    public static String Report7File = "Report/Report7.fxml";
    public static String Report8 = "Report8";
    public static String Report8File = "Report/Report8.fxml";
    public static String Report9 = "Report9";
    public static String Report9File = "Report/Report9.fxml";
    public static String Report10 = "Report10";
    public static String Report10File = "Report/Report10.fxml";
    public static String Report11 = "Report11";
    public static String Report11File = "Report/Report11.fxml";
    public static String Report12 = "Report12";
    public static String Report12File = "Report/Report12.fxml";
    public static String Report13 = "Report13";
    public static String Report13File = "Report/Report13.fxml";

    @Override
    public void start(Stage stage) throws Exception {
         
        ScreensController mainContainer = new ScreensController();
        mainContainer.loadScreen(Afag.ReSellerID, Afag.ReSellerFile);
        mainContainer.loadScreen(Afag.BillBuyedID, Afag.BillBuyedFile);
        mainContainer.loadScreen(Afag.BillCashierID, Afag.BillCashierFile);
        mainContainer.loadScreen(Afag.BillCustomerID, Afag.BillCustomerFile);
        mainContainer.loadScreen(Afag.BillTestItemID, Afag.BillTestItemFile);
        mainContainer.loadScreen(Afag.BillsellerID, Afag.BillsellerFile);
        mainContainer.loadScreen(Afag.CashierServiceID, Afag.CashierServiceFile);
        mainContainer.loadScreen(Afag.DefineSellerID, Afag.DefineSellerFile);
        mainContainer.loadScreen(Afag.CompanyInformationID, Afag.CompanyInformationFile);
        mainContainer.loadScreen(Afag.DefineCustomerID, Afag.DefineCustomerFile);
        mainContainer.loadScreen(Afag.DeleteAllID, Afag.DeleteAllFile);
        mainContainer.loadScreen(Afag.FullCashierID, Afag.FullCashierFile);
        mainContainer.loadScreen(Afag.InsertDataID, Afag.InsertDataFile);
        mainContainer.loadScreen(Afag.InsertServicesID, Afag.InsertServicesFile);
        mainContainer.loadScreen(Afag.LoginID, Afag.LoginFile);
        mainContainer.loadScreen(Afag.MainFrameID, Afag.MainFrameFile);
        mainContainer.loadScreen(Afag.NewBarCodeID, Afag.NewBarCodeFile);
        mainContainer.loadScreen(Afag.ReButedID, Afag.ReButedFile);
        mainContainer.loadScreen(Afag.UserAccountID, Afag.UserAccountFile);
        mainContainer.loadScreen(Afag.Report1, Afag.Report1File);
        mainContainer.loadScreen(Afag.Report2, Afag.Report2File);
        mainContainer.loadScreen(Afag.Report3, Afag.Report3File);
        mainContainer.loadScreen(Afag.Report4, Afag.Report4File);
        mainContainer.loadScreen(Afag.Report5, Afag.Report5File);
        mainContainer.loadScreen(Afag.Report6, Afag.Report6File);
        mainContainer.loadScreen(Afag.Report7, Afag.Report7File);
        mainContainer.loadScreen(Afag.Report8, Afag.Report8File);
        mainContainer.loadScreen(Afag.Report9, Afag.Report9File);
        mainContainer.loadScreen(Afag.Report10, Afag.Report10File);
        mainContainer.loadScreen(Afag.Report11, Afag.Report11File);
        mainContainer.loadScreen(Afag.Report12, Afag.Report12File);
        mainContainer.loadScreen(Afag.Report13, Afag.Report13File);

        mainContainer.setScreen(Afag.LoginID);

        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
       
       
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
