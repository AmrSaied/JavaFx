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
import javafx.scene.control.DatePicker;
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
public class InsertDataController implements Initializable, ControlledScreen {

    ScreensController myController;
    @FXML
    TextField barcode;
    @FXML
    TextField item;
    @FXML
    TextField Sell_price;
    @FXML
    TextField QTY;
    @FXML
    TextField pricebuy;
    @FXML
    DatePicker EXP_Data;
    @FXML
    TextField MIN_QTY;
    @FXML
    TextField Search;
    @FXML
    Label Erro_Massage;

    @FXML
    Label totalstock;

    int count = 0;
    DBConnection DB;

    @FXML
    TableView<TInsertData> tableStock;
    @FXML
    TableColumn<TInsertData, String> Tparcode;

    @FXML
    TableColumn<TInsertData, String> Tname;
    @FXML
    TableColumn<TInsertData, String> Tpricesell;
    @FXML
    TableColumn<TInsertData, String> Tpricebuy;
    @FXML
    TableColumn<TInsertData, String> TQTY;

    ObservableList<TInsertData> data = FXCollections.observableArrayList();

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        EXP_Data.setValue(LocalDate.now());

        try {
            DB = new DBConnection();
            FillTable();

        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        Tparcode.setCellValueFactory(new PropertyValueFactory<TInsertData, String>("parcode"));
        Tname.setCellValueFactory(new PropertyValueFactory<TInsertData, String>("name"));
        Tpricesell.setCellValueFactory(new PropertyValueFactory<TInsertData, String>("pricebuy"));
        Tpricebuy.setCellValueFactory(new PropertyValueFactory<TInsertData, String>("pricesell"));
        TQTY.setCellValueFactory(new PropertyValueFactory<TInsertData, String>("QTY"));
        tableStock.setItems(data);

    }

    public void FillTable() throws SQLException {
        data.clear();
        count = 0;
        DB.rs = DB.statemen.executeQuery("SELECT * FROM stock");

        while (DB.rs.next()) {
            count++;
            data.add(new TInsertData(DB.rs.getString("ID"), DB.rs.getString("Barcode"), DB.rs.getString("Items"), DB.rs.getString("Sell_Price"), DB.rs.getString("Buy_Price"), DB.rs.getString("EXP_Date"), DB.rs.getString("QTY"), DB.rs.getString("MIN_QTY")));
        }

        totalstock.setText("" + count);
        Claerall();
    }

    @FXML
    public void insertData() throws SQLException {
        try{

        if (barcode.getText().equals("") && MIN_QTY.getText().equals("") && item.getText().equals("") && QTY.getText().equals("") && pricebuy.getText().equals("") && Sell_price.getText().equals("")) {
            Erro_Massage.setText("ادخل الحفول المطلوبة");
        } else if (barcode.getText().equals(null) && MIN_QTY.getText().equals(null) && item.getText().equals(null) && QTY.getText().equals(null) && pricebuy.getText().equals(null) && Sell_price.getText().equals(null)) {

            Erro_Massage.setText("ادخل الحفول المطلوبة");
        } else {
            Erro_Massage.setText(null);
   DB.rs = DB.statemen.executeQuery("SELECT * FROM stock WHERE Barcode = '" + barcode.getText() + "'");
        if (DB.rs.next()) {
              Erro_Massage.setText(" المنتج موحود بالفعل ");
        }else{
            String Sql = "INSERT INTO stock(Barcode,Items,Sell_Price,Buy_Price,QTY,EXP_Date,MIN_QTY) VALUES ('" + barcode.getText() + "','" + item.getText() + "','" + Sell_price.getText() + "','" + pricebuy.getText() + "','" + QTY.getText() + "','" + EXP_Data.getValue().toString() + "','" + MIN_QTY.getText() + "')  ";

            DB.statemen.executeUpdate(Sql);
            Claerall();
            FillTable();
       
        }
        
        
        }}catch(Exception ex){
         Erro_Massage.setText("ادخل الحفول المطلوبة");
        }

    }

    @FXML
    public void SearchBarCode() throws SQLException {

        data.clear();
        DB.rs = DB.statemen.executeQuery("SELECT * FROM stock WHERE Barcode = '" + Search.getText() + "'");
        if (DB.rs.next()) {

            do {
                data.add(new TInsertData(DB.rs.getString("ID"), DB.rs.getString("Barcode"), DB.rs.getString("Items"), DB.rs.getString("Sell_Price"), DB.rs.getString("Buy_Price"), DB.rs.getString("EXP_Date"), DB.rs.getString("QTY"), DB.rs.getString("MIN_QTY")));
            } while (DB.rs.next());
            Erro_Massage.setText(null);
        } else {
            if (Search.getText().equals("")) {
                FillTable();
                Erro_Massage.setText(null);
            } else {
                Erro_Massage.setText("هذا المنتج غير موجود الرجاء تسجيلة");
            }
        }
        Claerall();

    }

    @FXML
    public void Deleteitem() throws SQLException {
        try {
            TInsertData InsertData = (TInsertData) tableStock.getSelectionModel().getSelectedItem();
            String Sql = " DELETE FROM stock WHERE ID = " + InsertData.getId();
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
    public void Selecteditem() throws SQLException {

        TInsertData InsertData = (TInsertData) tableStock.getSelectionModel().getSelectedItem();
        String Sql = " SELECT * FROM stock WHERE ID =  '" + InsertData.getId() + "'";
        DB.rs = DB.statemen.executeQuery(Sql);

        if (DB.rs.next()) {
            barcode.setText(DB.rs.getString("Barcode"));

            item.setText(DB.rs.getString("Items"));

            Sell_price.setText(DB.rs.getString("Sell_Price"));
            QTY.setText(DB.rs.getString("QTY"));

            pricebuy.setText(DB.rs.getString("Buy_Price"));

            
            String value =DB.rs.getString("EXP_Date");
            String x=value.substring(0, 4);
            String x1=value.substring(5, 7);
            String x2=value.substring(8, 10);
            
            int year = Integer.parseInt(x);
            int month = Integer.parseInt(x1);
            int day = Integer.parseInt(x2);
            EXP_Data.setValue(LocalDate.of(year, Month.of(month), day));

            MIN_QTY.setText(DB.rs.getString("MIN_QTY"));
        }
    }

    @FXML
    public void updataitem() throws SQLException {
 try{
        String Sql = "UPDATE stock SET  Barcode= '" + barcode.getText() + "', Items = '" + item.getText() + "', Sell_Price ='" + Sell_price.getText() + "',Buy_Price ='" + pricebuy.getText() + "',QTY ='" + QTY.getText() + "',EXP_Date ='" + EXP_Data.getValue().toString() + "',MIN_QTY ='" + MIN_QTY.getText() + "' WHERE Barcode ='" + barcode.getText() + "'";
        System.out.println(Sql);
        DB.statemen.executeUpdate(Sql);
        Claerall();
        FillTable();}catch(Exception ex){
         Erro_Massage.setText("ادخل الحفول المطلوبة");
        }

    }

    public void Claerall() {
        barcode.setText(null);
        item.setText(null);
        Sell_price.setText(null);
        QTY.setText(null);
        pricebuy.setText(null);
        EXP_Data.setValue(LocalDate.now());

        MIN_QTY.setText(null);
        Erro_Massage.setText(null);

    }

    @FXML
    public void Close() {
        ScreensController.p1 = false;
        Stage stage = (Stage) Erro_Massage.getScene().getWindow();

        stage.close();
    }

}
