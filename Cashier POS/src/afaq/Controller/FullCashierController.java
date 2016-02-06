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
import afaq.Table.TFullCashier;
import afaq.Table.TuserPermission;
import java.net.URL;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class FullCashierController implements Initializable, ControlledScreen {

    ScreensController myController;
    
    TCashierService cashierService;
    DBConnection DB;

    @FXML
    TextField barcodenumber;
    @FXML
    TextField amount;
    @FXML
    Label FullTotal;
    @FXML
    Label pricebuyed;
    @FXML
    Label namebar;
    @FXML
    Label raminlabel;
    @FXML
    Label totalAmout;
    @FXML
    Label numberBill;
    @FXML
    TextField payedEditText;
    @FXML
    Label QTY;
    @FXML
    public Label username;
    @FXML
    Label Erromassage;
    @FXML
    TextField searchEditText;

    @FXML
    TableView<TFullCashier> tablebuyed;
    @FXML
    TableColumn<TFullCashier, String> Tbarcode;

    @FXML
    TableColumn<TFullCashier, String> Tname;
    @FXML
    TableColumn<TFullCashier, String> Tamount;
    @FXML
    TableColumn<TFullCashier, String> TPrice_Sell;
    @FXML
    TableColumn<TFullCashier, String> Ttotal;

    String X;

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            DB = new DBConnection();
            amount.setText("1");

            Tbarcode.setCellValueFactory(new PropertyValueFactory<TFullCashier, String>("parcode"));
            Tname.setCellValueFactory(new PropertyValueFactory<TFullCashier, String>("name"));
            Tamount.setCellValueFactory(new PropertyValueFactory<TFullCashier, String>("amount"));
            TPrice_Sell.setCellValueFactory(new PropertyValueFactory<TFullCashier, String>("pricebuy"));
            Ttotal.setCellValueFactory(new PropertyValueFactory<TFullCashier, String>("total"));
            tablebuyed.setItems(DB.data);

            numberBillDB();

        } catch (Exception ex) {
            System.out.println("There Erro Is " + ex.getMessage());
        }
    }

    @FXML
    public void NewBill() throws SQLException {

        final Calendar cal = Calendar.getInstance();

        int month = cal.get(Calendar.MONTH);
        month++;
        int year = cal.get(Calendar.YEAR);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        X = (year + "-" + month + "-" + day);

        if (DB.data.isEmpty()) {
            Erromassage.setText("لايوجد مبيعات");
        } else if (payedEditText.getText().equals("") || payedEditText.getText().equals(null)) {

            Erromassage.setText("من  فضلك ادخل المدفوع");
        } else {

            String Q1 = "INSERT INTO sell_bills ( bill_no, tot_price, paid, bill_date, user_name) VALUES "
                    + "('" + numberBill.getText() + "','" + FullTotal.getText() + "','" + payedEditText.getText() + "','" + X + "','" + username.getText() + "')";
            System.out.println(Q1);
            DB.statemen.executeUpdate(Q1);

            for (int i = 0; i < DB.data.size(); i++) {
                int y = i + 1;
                DB.statemen.executeUpdate("INSERT INTO sell_items(bill_no, barcode, items, qty, sell_price, tot_price, buy_price, sell_date, action, id2) VALUES"
                        + " ('" + numberBill.getText() + "','" + DB.data.get(i).getParcode() + "','" + DB.data.get(i).getName() + "','" + DB.data.get(i).getAmount() + "','" + DB.data.get(i).getPricesell() + "','" + DB.data.get(i).getTotal() + "', '" + DB.data.get(i).getPricebuy() + "','" + X + "','بيع','" + y + "')");

                UpdataStock();
            }

            barcodenumber.clear();
            namebar.setText("");
            amount.setText("1");
            pricebuyed.setText("");
            totalAmout.setText("");
            FullTotal.setText("");
            payedEditText.clear();
            raminlabel.setText("");
            QTY.setText("");
            DB.data.clear();
            numberBillDB();
            Erromassage.setText("");

        }

    }

    @FXML
    public void Updata() throws SQLException {
        String Q1 = "DELETE FROM sell_items WHERE  bill_no = " + searchEditText.getText() + "";
        System.out.println(Q1);
        DB.statemen.executeUpdate(Q1);
        System.out.println("Deleted");
        for (int i = 0; i < DB.data.size(); i++) {
            int y = i + 1;
            DB.statemen.executeUpdate("INSERT INTO sell_items(bill_no, barcode, items, qty, sell_price, tot_price, buy_price, sell_date, action, id2) VALUES"
                    + " ('" + searchEditText.getText() + "','" + DB.data.get(i).getParcode() + "','" + DB.data.get(i).getName() + "','" + DB.data.get(i).getAmount() + "','" + DB.data.get(i).getPricesell() + "','" + DB.data.get(i).getTotal() + "', '" + DB.data.get(i).getPricebuy() + "','" + X + "','بيع','" + y + "')");
            UpdataStock();
        }

        barcodenumber.clear();
        namebar.setText("");
        amount.setText("1");
        pricebuyed.setText("");
        totalAmout.setText("");
        FullTotal.setText("");
        payedEditText.clear();
        raminlabel.setText("");
        QTY.setText("");
        DB.data.clear();
        numberBillDB();
        searchEditText.setText("");
    }

    public void Print() {
         Stage stage = (Stage) tablebuyed.getScene().getWindow();
          PrinterJob printerJob = PrinterJob.createPrinterJob();
   if(printerJob.showPrintDialog(stage.getOwner()) && printerJob.printPage(tablebuyed))
       printerJob.endJob();
    }

    @FXML
    public void RemoveBill() {
        
        barcodenumber.clear();
        namebar.setText("");
        amount.setText("1");
        pricebuyed.setText("");
        totalAmout.setText("");
        FullTotal.setText("");
        payedEditText.clear();
        raminlabel.setText("");
        QTY.setText("");
        DB.data.clear();
        searchEditText.setText(null);

    }

    @FXML
    public void SearchBill() throws SQLException {
        DB.rs = DB.statemen.executeQuery("SELECT * FROM sell_items WHERE bill_NO = '" + searchEditText.getText() + "'");
        if (DB.rs.next()) {

            DB.data.clear();
            do {
                DB.data.add(new TFullCashier(DB.rs.getString("id"), DB.rs.getString("barcode"), DB.rs.getString("items"), DB.rs.getString("qty"), DB.rs.getString("buy_price"), DB.rs.getString("tot_price"), DB.rs.getString("sell_price")));

                SumTotal();
                ClearAdd();
            } while (DB.rs.next());
            Erromassage.setText(null);
        } else {
            if (!searchEditText.getText().equals("") && !searchEditText.getText().equals(null)) {
                DB.data.clear();
                Erromassage.setText("لايوجد فاتورة بهذا الرقم");
            } else {
                DB.data.clear();
                Erromassage.setText(null);
            }
        }
    }

    @FXML
    public void CloseScene() {
        ScreensController.p10 = false;
        Stage stage = (Stage) totalAmout.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void AddtoTable() throws SQLException {
        SearchBarCode();
        DB.rs = DB.statemen.executeQuery("SELECT * FROM stock WHERE Barcode = '" + barcodenumber.getText() + "'");
        DB.rs.next();

        DB.data.add(new TFullCashier(DB.rs.getString("ID"), barcodenumber.getText(), DB.rs.getString("items"), amount.getText(), DB.rs.getString("Sell_Price"), totalAmout.getText(), DB.rs.getString("Buy_Price")));
        System.out.println("ADDed To Table");
        SumTotal();
        ClearAdd();
        SearchBarCode();
    }

    @FXML
    public void AddtoTableEnter(final KeyEvent keyEvent) throws SQLException {
        SearchBarCode();
        if (keyEvent.getCode() == KeyCode.ENTER) {
            DB.rs = DB.statemen.executeQuery("SELECT * FROM stock WHERE Barcode = '" + barcodenumber.getText() + "'");
            DB.rs.next();

            DB.data.add(new TFullCashier(DB.rs.getString("ID"), barcodenumber.getText(), DB.rs.getString("items"), amount.getText(), DB.rs.getString("Sell_Price"), totalAmout.getText(), DB.rs.getString("Buy_Price")));
            System.out.println("ADDed To Table");
            SumTotal();
            ClearAdd();
        }
    }

    @FXML
    public void SearchBarCode() throws SQLException {

        System.out.println("Enter");
        DB.rs = DB.statemen.executeQuery("SELECT * FROM stock WHERE Barcode = '" + barcodenumber.getText() + "'");
        if (DB.rs.next()) {

            double Sell_Price = DB.rs.getDouble("Sell_Price");
            pricebuyed.setText(DB.rs.getString("Sell_Price"));

            double CastAmount = Double.parseDouble(amount.getText()) * Sell_Price;

            totalAmout.setText("" + CastAmount);

            QTY.setText(DB.rs.getString("QTY"));
            namebar.setText(DB.rs.getString("items"));
            Erromassage.setText("");

        } else {
            if (!barcodenumber.getText().equals("")) {
                Erromassage.setText("هذا المنتج غير موجود الرجاء تسجيلة");
                ClearAdd();
            }
        }

    }

    public void SumTotal() {
        double totall = 0;
        for (int i = 0; i < DB.data.size(); i++) {

            System.out.println("" + DB.data.get(i).getTotal());
            totall = totall + Double.parseDouble(DB.data.get(i).getTotal());

        }

        FullTotal.setText("" + totall);
    }

    @FXML
    public void remain(final KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            double remainD;
            remainD = Double.parseDouble(payedEditText.getText()) - Double.parseDouble(FullTotal.getText());

            raminlabel.setText("" + remainD);

        }

    }

    public void ClearAll() {
        barcodenumber.clear();
        namebar.setText("");
        amount.setText("1");
        pricebuyed.setText("");
        totalAmout.setText("");
        FullTotal.setText("");
        payedEditText.clear();
        raminlabel.setText("");
        QTY.setText("");
        DB.data.clear();
    }

    public void ClearAdd() {
        barcodenumber.clear();
        namebar.setText("");
        amount.setText("1");
        pricebuyed.setText("");
        totalAmout.setText("");

        payedEditText.clear();
        raminlabel.setText("");
        QTY.setText("");

    }

    public void numberBillDB() throws SQLException {
        DB.rs = DB.statemen.executeQuery("SELECT * FROM sell_bills");
        DB.rs.last();
        int x = DB.rs.getInt(2) + 1;
        numberBill.setText("" + x);
    }

    @FXML
    public void RemoveRowByDelete(final KeyEvent keyEvent) {

        if (keyEvent.getCode() == KeyCode.DELETE) {
            TFullCashier person = (TFullCashier) tablebuyed.getSelectionModel().getSelectedItem();
            DB.data.remove(person);
            SumTotal();
        }
    }

    @FXML
    public void RemoveRow() {
        TFullCashier person = (TFullCashier) tablebuyed.getSelectionModel().getSelectedItem();
        DB.data.remove(person);
        SumTotal();
    }

    @FXML
    public void AddService() {
        myController.NewloadScreen(afaq.Afag.CashierServiceID, afaq.Afag.CashierServiceFile);
    }

    public void userPermission(TuserPermission tuserPermission) {
    
        username.setText(tuserPermission.getUser_name());
    }

    @FXML
    public void EffectTable() {
        SumTotal();
    }

    @FXML
    public void UpdataStock() throws SQLException {

        for (int i = 0; i < DB.data.size(); i++) {
            if (!DB.data.get(i).getParcode().equals("")) {
                String sql = "SELECT * FROM  `stock`  WHERE Barcode = " + DB.data.get(i).getParcode() + "";
                DB.rs = DB.statemen.executeQuery(sql);
                DB.rs.next();
                int numberStock = DB.rs.getInt("QTY") - Integer.parseInt(DB.data.get(i).getAmount());
                sql = "UPDATE stock SET QTY='" + numberStock + "' WHERE  Barcode = " + DB.data.get(i).getParcode() + "";
                DB.statemen.executeUpdate(sql);
            }
        }
    }
}
