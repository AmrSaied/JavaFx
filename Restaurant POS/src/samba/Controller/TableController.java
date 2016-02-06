/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package samba.Controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import samba.ControlledScreen;
import samba.DBConnection;
import samba.Samba;
import samba.ScreensController;
import samba.Table.TDelivery;
import samba.Table.TuserPermission;

/**
 * FXML Controller class
 *
 * @author AMR SAID
 */
public class TableController implements Initializable, ControlledScreen {

    TuserPermission permission = null;
    public ScreensController ControlledScreen;
    DBConnection db;

    @FXML
    AnchorPane AP;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        btn.getStyleClass().add("twoP");
//        btn.setRotate(180);
//        btn.setStyle("-fx-background-color: slateblue; -fx-text-fill: white;");
        try {
            db = new DBConnection();
            FillTable();
        } catch (Exception ex) {
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        ControlledScreen = screenPage;
    }

    void FillTable() throws SQLException {

        String sql = "SELECT * FROM `table`";
        db.rs = db.statemen.executeQuery(sql);
        System.out.println(sql);
        while (db.rs.next()) {
            Button btn = new Button(db.rs.getString("T_name"));

            btn.setId(db.rs.getString("id"));
            if (db.rs.getDouble("TYPE") == 1) {
                btn.getStyleClass().add("twoP");
                btn.setPrefSize(150, 70);

            } else if (db.rs.getDouble("TYPE") == 2) {
                btn.getStyleClass().add("fourP");
                btn.setPrefSize(200, 200);

            } else {
                btn.getStyleClass().add("eightP");
                btn.setPrefSize(400, 200);

            }

            btn.setRotate(db.rs.getDouble("Rotation"));

            if (db.rs.getDouble("status") == 1) {
                btn.setStyle(" -fx-background-color: #ffffff;");

            } else {
                btn.setStyle("-fx-background-color:rgb(255, 127, 80);");

            }

            btn.setOnAction(new TableController.TableClick());

            AP.getChildren().add(btn);

            if (db.rs.getDouble("PT") != 0) {
                AP.setTopAnchor(btn, db.rs.getDouble("PT"));
            }
            if (db.rs.getDouble("PD") != 0) {
                AP.setBottomAnchor(btn, db.rs.getDouble("PD"));
            }
            if (db.rs.getDouble("PL") != 0) {
                AP.setLeftAnchor(btn, db.rs.getDouble("PL"));
            }
            if (db.rs.getDouble("PR") != 0) {
                AP.setRightAnchor(btn, db.rs.getDouble("PR"));
            }
        }

    }

    void setCurrent(TuserPermission permission) {
        this.permission = permission;
    }

    private class TableClick implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            String x = event.getSource().toString().substring(10, 13).replaceAll(",", "");
            System.out.println("" + x);
            FXMLLoader loder = Samba.FXTablePage;
            TablePageController scenseController = loder.getController();
            try {
                 String sql = "select * from sell_bills  where T_name = '"+x+"'and T_S = '1' and Action = 'الطاولات'";
                 System.out.println(sql);
                db.rs = db.statemen.executeQuery(sql);
                if(db.rs.next()){
                  TDelivery delivery = new TDelivery(db.rs.getString("id"),db.rs.getString("bill_no"), db.rs.getString("T_name"), db.rs.getString("T_S"), db.rs.getString("bill_no"), db.rs.getString("tot_price"), db.rs.getString("user_name"), "الطاولات");
                scenseController.setCurrent(delivery);
     
            
           
                }else{
                    scenseController.setCurrent(x, permission);
                }
              
            } catch (SQLException ex) {
                Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
            }
            ControlledScreen.setScreen(Samba.TablePageId);
        }
    }
}
