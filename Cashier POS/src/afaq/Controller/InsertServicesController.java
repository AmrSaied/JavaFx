/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afaq.Controller;

import afaq.ControlledScreen;
import afaq.DBConnection;
import afaq.ScreensController;
import afaq.Table.TCashierService;
import afaq.Table.TInsertData;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AmrSaid
 */
public class InsertServicesController implements Initializable, ControlledScreen {

    ScreensController myController;
    DBConnection DB;
    @FXML
    TableView<TCashierService> tableService;
    @FXML
    TableColumn<TCashierService, String> TServiceName;

    @FXML
    TableColumn<TCashierService, String> Tprice;

    @FXML
    TextField ServiceName;
    @FXML
    TextField price;
    @FXML
        TextArea Note;
    @FXML
    TextField searchEditText;

    @FXML
    Label ServiceN;

    @FXML
    Label Erro_Massage;

    int count = 0;

    ObservableList<TCashierService> data = FXCollections.observableArrayList();

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            DB = new DBConnection();
            TServiceName.setCellValueFactory(new PropertyValueFactory<TCashierService, String>("name"));
            Tprice.setCellValueFactory(new PropertyValueFactory<TCashierService, String>("price"));
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

        count=0;
        while (DB.rs.next()) {
            data.add(new TCashierService(DB.rs.getString("id"), DB.rs.getString("s_name"), DB.rs.getString("price"), DB.rs.getString("note"), "1"));
            count++;
        }
        ServiceN.setText("" + count);
    }

    @FXML
    public void Close() throws SQLException {

        ScreensController.p2 = false;
        Stage stage = (Stage) searchEditText.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void insertDataDB() throws SQLException {

        try {
            if (ServiceName.getText().equals("") && price.getText().equals("") && Note.getText().equals("")) {
            Erro_Massage.setText("ادخل الحفول المطلوبة");
        } else if (ServiceName.getText().equals("") && price.getText().equals("") && Note.getText().equals("")) {

            Erro_Massage.setText("ادخل الحفول المطلوبة");
        } else {
            Erro_Massage.setText(null);

            
                String Sql = " SELECT * FROM service WHERE id =  '" + ServiceName.getText() + "'";
        DB.rs = DB.statemen.executeQuery(Sql);

        if (DB.rs.next()) {
           Erro_Massage.setText("موجود بالفعل");
        
        }else{
            
            
             Sql = "INSERT INTO service(s_name, price, note) VALUES ('"+ServiceName.getText()+"','"+ price.getText()+"','"+Note.getText()+"')";
            DB.statemen.executeUpdate(Sql);
            Claerall();
            FillTable();}
        }
        } catch (Exception e) {
            Erro_Massage.setText("ادخل الحفول المطلوبة");
        }
        

    }

    public void Claerall() {
        ServiceName.setText("");
        price.setText("");
        Note.setText("");
        Erro_Massage.setText("");
    }

    @FXML
    public void Deleteitem() throws SQLException {
        try {
            TCashierService InsertData = (TCashierService) tableService.getSelectionModel().getSelectedItem();
            String Sql = " DELETE FROM service WHERE id = '" + InsertData.getId()+"'";
            DB.statemen.executeUpdate(Sql);
            data.remove(InsertData);
            Erro_Massage.setText(null);
        } catch (Exception ex) {
            Erro_Massage.setText("لم يتم اختيار الخدمة");
        }
        FillTable();
        Claerall();
    }

    @FXML
    public void updataitem() throws SQLException {
  try {
                TCashierService InsertData = (TCashierService) tableService.getSelectionModel().getSelectedItem();
        String Sql = "UPDATE service SET s_name='"+ServiceName.getText()+"',price='"+price.getText()+"',note='"+Note.getText()+"' WHERE ID = " + InsertData.getId();
        System.out.println(Sql);
        DB.statemen.executeUpdate(Sql);
        Claerall();
        FillTable();
        } catch (Exception e) {
            Erro_Massage.setText("ادخل الحفول المطلوبة");
        }
        

    }
    
     @FXML
    public void Selecteditem() throws SQLException {
  try {
             TCashierService InsertData = (TCashierService) tableService.getSelectionModel().getSelectedItem();
        String Sql = " SELECT * FROM service WHERE id =  '" + InsertData.getId() + "'";
        DB.rs = DB.statemen.executeQuery(Sql);

        if (DB.rs.next()) {
            ServiceName.setText(DB.rs.getString("s_name"));

            price.setText(DB.rs.getString("price"));

            Note.setText(DB.rs.getString("note"));
           
        }
        } catch (Exception e) {
            Erro_Massage.setText("ادخل الحفول المطلوبة");
        }
       
    }
}
