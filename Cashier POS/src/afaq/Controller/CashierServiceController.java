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
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AmrSaid
 */
public class CashierServiceController implements Initializable, ControlledScreen {

    ScreensController myController;
    DBConnection DB;
    @FXML
    TableView<TCashierService> tableService;
    @FXML
    TableColumn<TCashierService, String> ServiceName;

    @FXML
    TableColumn<TCashierService, String> price;

    @FXML
    TextField amount;
    @FXML
    TextField searchEditText;
    @FXML
    Label Erromassage;
    ObservableList<TCashierService> data = FXCollections.observableArrayList();

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            DB = new DBConnection();
            ServiceName.setCellValueFactory(new PropertyValueFactory<TCashierService, String>("name"));
            price.setCellValueFactory(new PropertyValueFactory<TCashierService, String>("price"));
            tableService.setItems(data);

            FillTable();
        } catch (Exception ex) {
            Logger.getLogger(CashierServiceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void SearchServices() throws SQLException {
        DB.rs = DB.statemen.executeQuery("SELECT * FROM service WHERE s_name LIKE  '%" + searchEditText.getText() + "%'");
        if (DB.rs.next()) {

            data.clear();
            do {
                data.add(new TCashierService(DB.rs.getString("id"), DB.rs.getString("s_name"), DB.rs.getString("price"), DB.rs.getString("note"), "1"));
            } while (DB.rs.next());

        } else {
            if (!searchEditText.getText().equals("") && !searchEditText.getText().equals(null)) {
                data.clear();
                FillTable();
            } else {
                data.clear();
                FillTable();
            }
        }
    }

       @FXML
    public void FillTable() throws SQLException {
        data.clear();
        DB.rs = DB.statemen.executeQuery("SELECT * FROM service");
        System.out.println("service");
        while (DB.rs.next()) {
            data.add(new TCashierService(DB.rs.getString("id"), DB.rs.getString("s_name"), DB.rs.getString("price"), DB.rs.getString("note"), "1"));
        }
    }

    @FXML
    public void AddService() {

       try{
        TCashierService cashierService = (TCashierService) tableService.getSelectionModel().getSelectedItem();
         

        cashierService.setAmount(amount.getText());
        double total = Double.parseDouble(cashierService.getAmount()) * Double.parseDouble(cashierService.getPrice());
        cashierService.setNote("" + total);

        DBConnection.data.add(new TFullCashier(cashierService.getId(), "", cashierService.getName(), cashierService.getAmount(), cashierService.getPrice(), cashierService.getNote(), cashierService.getPrice()));
              Stage stage = (Stage) Erromassage.getScene().getWindow();
        stage.close();
       
       }catch (Exception ex) {
            Erromassage.setText("لم يتم اختيار الخدمة");
        }
       
       }

    @FXML
    public void Close() throws SQLException {

        Stage stage = (Stage) Erromassage.getScene().getWindow();
        stage.close();
    }
}
