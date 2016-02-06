package samba;

import javafx.application.Application;
import static javafx.application.Application.launch;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;

import javafx.stage.Stage;


public class Samba extends Application {

    public static String LoginId = "Login";
    public static String LoginFile = "FXML/Login.fxml";

    public static String TakeAwayId = "TakeAway";
    public static String TakeAwayFile = "FXML/TakeAway.fxml";

    public static String MainyId = "Main";
    public static String MainFile = "FXML/Main.fxml";

    public static String DeliveryId = "Delivery";
    public static String DeliveryFile = "FXML/Delivery.fxml";
    
    
    public static String OrderId = "Order";
    public static String OrderFile = "FXML/Order.fxml";
    
    
    public static String DeliverypageId = "Deliverypage";
    public static String DeliverypageFile = "FXML/Deliverypage.fxml";
    
    
    public static String TableId = "Table";
    public static String TableFile = "FXML/Table.fxml";
    
    
    
    
    public static String TablePageId = "TablePage";
    public static String TablePageFile = "FXML/TablePage.fxml";
    
    
    

    public static FXMLLoader FXMain;
    public static FXMLLoader FXLogin;
    public static FXMLLoader FXTakeAway;
    public static FXMLLoader FXDelivery;
    public static FXMLLoader FXDOrder;
    public static FXMLLoader FXDDeliverypage;
    public static FXMLLoader FXTable;
    public static FXMLLoader FXTablePage;

    @Override
    public void start(Stage stage) throws Exception {
        ScreensController mainContainer = new ScreensController();
        FXMain = mainContainer.loadScreen(Samba.MainyId, Samba.MainFile);
        FXLogin = mainContainer.loadScreen(Samba.LoginId, Samba.LoginFile);
        FXTakeAway = mainContainer.loadScreen(Samba.TakeAwayId, Samba.TakeAwayFile);
        FXDelivery = mainContainer.loadScreen(Samba.DeliveryId, Samba.DeliveryFile);
        FXDOrder = mainContainer.loadScreen(Samba.OrderId, Samba.OrderFile);
        FXDDeliverypage = mainContainer.loadScreen(Samba.DeliverypageId, Samba.DeliverypageFile);
        FXTable = mainContainer.loadScreen(Samba.TableId, Samba.TableFile);
        FXTablePage = mainContainer.loadScreen(Samba.TablePageId, Samba.TablePageFile);
        

        mainContainer.setScreen(Samba.LoginId);

        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
        stage.setScene(scene);

//     stage.initStyle(StageStyle.TRANSPARENT);
//       scene.setFill(Color.TRANSPARENT);
        
      

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
