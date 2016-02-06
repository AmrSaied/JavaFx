/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afaq.Controller;

import afaq.ControlledScreen;
import afaq.DBConnection;
import afaq.ScreensController;
import afaq.Table.TFullCashier;
import afaq.Table.TuserPermission;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author AmrSaid
 */
public class BillsellerController implements Initializable, ControlledScreen {

    ScreensController myController;
    TuserPermission current;
    final ToggleGroup group = new ToggleGroup();

    @FXML
    RadioButton rb2;
    @FXML
    RadioButton rb1;

    DBConnection DB;
    ObservableList items;
    @FXML
    ComboBox<String> comboBox1;
    ArrayList<String> Cus_Name = new ArrayList<String>();

    @FXML
    TextField total;
    @FXML
    TextField payed;
    @FXML
    TextField change;
    @FXML
    TextField BillNumber;
    @FXML
    TextField barcodenumber;
    @FXML
    TextField namebar;

    @FXML
    Label totalAmout;

    @FXML
    Label Selllbuyed;

    @FXML
    TextField amount;
    @FXML
    TextField SearchBillN;
    @FXML
    Label QTY;
    @FXML
    Label Erromassage;
    @FXML
    Label username;
    @FXML
    DatePicker Datepicker2;

    String paid;
    String changeSQl;

    double cahmged;
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
  

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Tbarcode.setCellValueFactory(new PropertyValueFactory<TFullCashier, String>("parcode"));
        Tname.setCellValueFactory(new PropertyValueFactory<TFullCashier, String>("name"));
        Tamount.setCellValueFactory(new PropertyValueFactory<TFullCashier, String>("amount"));
        TPrice_Sell.setCellValueFactory(new PropertyValueFactory<TFullCashier, String>("pricebuy"));
        Ttotal.setCellValueFactory(new PropertyValueFactory<TFullCashier, String>("total"));
        tablebuyed.setItems( DB.data);
        try {
            DB = new DBConnection();

            menucustomer();
            Datepicker2.setValue(LocalDate.now());
            BillNo();
        } catch (Exception ex) {
            Logger.getLogger(BillBuyedController.class.getName()).log(Level.SEVERE, null, ex);
        }
        amount.setText("1");
        rb1.setSelected(true);
        rb2.setId("rb2");
        rb1.setId("rb1");
        total.setEditable(false);
        payed.setEditable(false);
        change.setEditable(false);
        rb1.setToggleGroup(group);
        rb2.setToggleGroup(group);
        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> ov, Toggle t, Toggle t1) {
                RadioButton chk = (RadioButton) t1.getToggleGroup().getSelectedToggle(); // Cast object to radio button
                if (chk.getId().endsWith("rb2")) {
                    total.setEditable(false);
                    payed.setEditable(true);
                    change.setEditable(false);
                    paid = payed.getText();

                    changeSQl = total.getText();
                } else {
                    total.setEditable(false);
                    payed.setEditable(false);
                    change.setEditable(false);
                    paid = "0";
                    changeSQl = total.getText();
                }
            }
        });
    }

    public void menucustomer() throws SQLException {
        comboBox1.getItems().clear();
        DB.rs = DB.statemen.executeQuery("select * from customer");
        while (DB.rs.next()) {
            Cus_Name.add(DB.rs.getString("cus_name"));

        }
        items = FXCollections.observableArrayList(Cus_Name);
        comboBox1.getItems().addAll(items);
    }

    public void BillNo() throws SQLException {
        DB.rs = DB.statemen.executeQuery("SELECT * FROM sell_bills where bill_no > 1 ");
        DB.rs.last();
        int x = Integer.parseInt(DB.rs.getString("bill_no"));
        x++;
        BillNumber.setText("" + x);
    }

    @FXML
    public void SearchBarCode() throws SQLException {

        DB.rs = DB.statemen.executeQuery("SELECT * FROM stock WHERE Barcode = '" + barcodenumber.getText() + "'");
        if (DB.rs.next()) {

            double Sell_Price = DB.rs.getDouble("Sell_Price");

            double CastAmount = Double.parseDouble(amount.getText()) * Sell_Price;
            totalAmout.setText("" + CastAmount);
            QTY.setText(DB.rs.getString("QTY"));
            namebar.setText(DB.rs.getString("items"));
            Erromassage.setText("");
            Selllbuyed.setText(DB.rs.getString("Sell_Price"));

        } else {
            if (!barcodenumber.getText().equals("")) {
                Erromassage.setText("هذا المنتج غير موجود الرجاء تسجيلة");

                totalAmout.setText("");
                QTY.setText("");
                namebar.setText("");

                Selllbuyed.setText("");

            }
        }
    }

    @FXML
    public void AddtoTable() throws SQLException {
        try {
            SumTotal();

            SearchBarCode();
            DB.rs = DB.statemen.executeQuery("SELECT * FROM stock WHERE Barcode = '" + barcodenumber.getText() + "'");
            DB.rs.next();
             DB.data.add(new TFullCashier(DB.rs.getString("ID"), barcodenumber.getText(), DB.rs.getString("items"), amount.getText(), DB.rs.getString("buy_Price"), totalAmout.getText(), DB.rs.getString("Sell_Price")));
            ClearAdd();
            SearchBarCode();
            SumTotal();
        } catch (Exception EX) {
            Erromassage.setText("ادخل الحقول المطلوبة");
        }
    }

    public void ClearAdd() {
        barcodenumber.clear();
        namebar.setText("");
        amount.setText("1");

        totalAmout.setText("");
        QTY.setText("");
        Selllbuyed.setText("");
    }

    public void CahngedMethod(final KeyEvent keyEvent) {

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (!total.getText().equals("")) {
                cahmged = Double.parseDouble(total.getText()) - Double.parseDouble(payed.getText());
                if (cahmged <= 0) {
                    Erromassage.setText("يجب المدفوع اقل من الاجمالي");
                }
                change.setText("" + cahmged);
            }
        }
    }

    public void CahngedMethod() {

        double cahmged = Double.parseDouble(total.getText()) - Double.parseDouble(payed.getText());
        change.setText("" + cahmged);
    }

    public void SumTotal() {
        double totall = 0;
        for (int i = 0; i <  DB.data.size(); i++) {
            totall = totall + Double.parseDouble( DB.data.get(i).getTotal());

        }
        total.setText("" + totall);
    }

    public void setCurrent(TuserPermission current) {
        this.current = current;
    }

    @FXML
    public void InsertBill() throws SQLException {
        try{
            String Value1 = BillNumber.getText();
            String Value2 = comboBox1.getValue();

            String Value6 = Datepicker2.getValue().toString();

            String Value3 = total.getText();
            String Value7 = username.getText();
            String Sql5 = "SELECT * FROM sell_bills Where bill_no ='" + SearchBillN.getText() + "'";
            DB.rs = DB.statemen.executeQuery(Sql5);
            if (DB.rs.next()) {
                String Sql = "delete FROM `sell_bills` WHERE `bill_no` = '" + BillNumber.getText() + "'";
                String Sql2 = "delete FROM `sell_items` WHERE `bill_no` = '" + BillNumber.getText() + "'";

                DB.statemen.executeUpdate(Sql);
                DB.statemen.executeUpdate(Sql2);
            }

            if (!Value2.equals("")) {

                if (rb2.isSelected()) {
                    CahngedMethod();
                    if (cahmged <= 0) {
                        Erromassage.setText("يجب المدفوع اقل من الاجمالي");
                    } else {
                        String Value4 = payed.getText();

                        String Value5 = change.getText();

                        String Sql = "INSERT INTO `sell_bills` ( `bill_no`, `cus_name`, `tot_price`, `paid`, `change`, `bill_date`, `user_name`) VALUES ('" + Value1 + "', '" + Value2 + "', '" + Value3 + "', '" + Value4 + "', '" + Value5 + "', '" + Value6 + "', '" + Value7 + "');";
                        System.out.println(Sql);
                        
                      
                        DB.statemen.executeUpdate(Sql);
                       
                           for (int i = 0; i <  DB.data.size(); i++) {
                       
                        System.out.println("AFTER INSERT");
                        int y = i + 1;
                        DB.statemen.executeUpdate("INSERT INTO sell_items( bill_no, barcode, items, qty, buy_price,sell_price, tot_price, sell_date, cus_name, action) VALUES ('" + Value1 + "','" +  DB.data.get(i).getParcode() + "','" +  DB.data.get(i).getName() + "','" +  DB.data.get(i).getAmount() + "','" +  DB.data.get(i).getPricebuy() + "','"+  DB.data.get(i).getPricesell() + "','" +  DB.data.get(i).getTotal() + "','" + Value6 + "','" + Value2 + "','بيع')");
                    }
                    UpdataStock();
                    ClearAll();
                    }
                } else {

                    String Sql = "INSERT INTO `sell_bills` ( `bill_no`, `cus_name`, `tot_price`, `paid`, `change`, `bill_date`, `user_name`) VALUES ('" + Value1 + "', '" + Value2 + "', '" + Value3 + "', '" + Value3 + "', '0', '" + Value6 + "', '" + Value7 + "');";
                    DB.statemen.executeUpdate(Sql);
                    System.out.println(Sql);
                    for (int i = 0; i <  DB.data.size(); i++) {
                       
                        System.out.println("AFTER INSERT");
                        int y = i + 1;
                        DB.statemen.executeUpdate("INSERT INTO sell_items( bill_no, barcode, items, qty, buy_price,sell_price, tot_price, sell_date, cus_name, action) VALUES ('" + Value1 + "','" +  DB.data.get(i).getParcode() + "','" +  DB.data.get(i).getName() + "','" +  DB.data.get(i).getAmount() + "','" +  DB.data.get(i).getPricebuy() + "','"+  DB.data.get(i).getPricesell() + "','" +  DB.data.get(i).getTotal() + "','" + Value6 + "','" + Value2 + "','بيع')");
                    }
                    UpdataStock();
                    ClearAll();

                }

            } else {
                Erromassage.setText("ادخل اسم المورد");
            }}catch(Exception ex){
             Erromassage.setText("ادخل البيانات المطلوبة");
            
            }
        
    }

    @FXML
    public void CloseScene() {
        ScreensController.p8 = false;
        DB.data.clear();
        Stage stage = (Stage) payed.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void SearchBill() throws SQLException {
        try {
            String Sql = "SELECT * FROM sell_bills Where bill_no ='" + SearchBillN.getText() + "'";
            String Sql2 = "SELECT * FROM sell_items Where bill_no = '" + SearchBillN.getText() + "'";
            System.out.println(Sql);
             DB.data.clear();
            DB.rs = DB.statemen.executeQuery(Sql);
            DB.rs.next();

            BillNumber.setText(DB.rs.getString("bill_no"));

            comboBox1.setValue(DB.rs.getString("cus_name"));

            String Data = DB.rs.getString("bill_date");

            Datepicker2.setValue(LocalDate.of(1991, Month.of(1), 1));

            total.setText(DB.rs.getString("tot_price"));
            payed.setText(DB.rs.getString("paid"));
            change.setText(DB.rs.getString("change"));
            if (DB.rs.getDouble("change") > 0) {
                System.out.println("change");
                total.setEditable(false);
                payed.setEditable(true);
                change.setEditable(false);
                paid = payed.getText();
                double cahmged = Double.parseDouble(paid) - Double.parseDouble(payed.getText());
                changeSQl = total.getText();
                rb2.setSelected(true);
            } else {
                total.setEditable(false);
                payed.setEditable(false);
                change.setEditable(false);
                paid = "0";
                changeSQl = total.getText();
                rb1.setSelected(true);
            }

            DB.rs = DB.statemen.executeQuery(Sql2);
            while (DB.rs.next()) {
                 DB.data.add(new TFullCashier(DB.rs.getString("id"), DB.rs.getString("barcode"), DB.rs.getString("items"), DB.rs.getString("qty"), DB.rs.getString("Sell_Price"), DB.rs.getString("tot_price"), DB.rs.getString("Sell_Price")));

            }
            Erromassage.setText("");
        } catch (Exception e) {
            ClearAll();
            Erromassage.setText("الفاتورة غير موجودة");

        }

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

    public void ClearAll() throws SQLException {
         DB.data.clear();
        ClearAdd();
        BillNumber.setText("");
        Erromassage.setText("");
        comboBox1.setValue("");

        total.setText("");

        payed.setText("");

        change.setText("");
//        SearchBillN.setText("");
        BillNo();
    }

    @FXML
    public void RemoveBill() throws SQLException {
        try {
            String Sql = "delete FROM `sell_bills` WHERE `bill_no` = '" + BillNumber.getText() + "'";
            String Sql2 = "delete FROM `sell_items` WHERE `bill_no` = '" + BillNumber.getText() + "'";

            DB.statemen.executeUpdate(Sql);
            DB.statemen.executeUpdate(Sql2);
            ClearAll();
        } catch (Exception e) {
            Erromassage.setText("الفاتورة غير موجودة");
        }

    }

    public void userPermission(TuserPermission current) {

        username.setText(current.getUser_name());
    }

    @FXML
    public void print() throws SQLException {
        if ( DB.data.isEmpty()) {
            JOptionPane.showMessageDialog(null, "لايوجد مبيعات");
        } else {

            PrinterJob pj = PrinterJob.getPrinterJob();

            PageFormat pf = pj.defaultPage();
            Paper paper = new Paper();

            double margin = 0; // half inch
            int hegith =  DB.data.size() * 15;
            hegith += 165;
            paper.setImageableArea(margin, margin, 200, hegith);
            pf.setPaper(paper);

            pj.setPrintable(new MyPrintable(), pf);
            if (pj.printDialog()) {
                try {
                    pj.print();
                } catch (PrinterException e) {
                    System.out.println(e);
                }
            }
            InsertBill();
            System.out.println("Printed");
        }

    }

    class MyPrintable implements Printable {

        public int print(Graphics g, PageFormat pf, int pageIndex) {
            if (pageIndex != 0) {
                return NO_SUCH_PAGE;

            }

            String header = "";
            try {
                DB = new DBConnection();

                DB.rs = DB.statemen.executeQuery("select * from info ");

                DB.rs.last();
                header = DB.rs.getString("cus_name");
            } catch (Exception ex) {
                Logger.getLogger(MainFrameController.class.getName()).log(Level.SEVERE, null, ex);
            }
            String ponN = "رقم البون : " + BillNumber.getText() + "";
//            String DateN = "التاريخ : " + date.getText();
//            String TimeN = "الوقت  " + time.getText();

            Graphics2D g2 = (Graphics2D) g;

            g2.finalize();
            g2.setFont(new Font("Serif", Font.BOLD, 13));
            g2.setPaint(Color.black);

            g2.drawString(header, 40, 17);
            g2.drawString("فواتير  العملاء", 130, 17);
            g2.drawString(ponN, 125, 46);
//           g2.drawString(DateN, 55, 63);
//            g2.drawString(TimeN, 7, 46);
            g2.drawString("----------------------------------------------------------------------------------------", 0, 28);
            g2.drawString("-----------------------------------------------------------------------------------------", 0, 32);
            g2.drawString("----------------------------------------------------------------------------------------", 0, 72);
            g2.drawString("-----------------------------------------------------------------------------------------", 0, 92);

            g2.drawString("|", 110, 77);

            g2.drawString("|", 110, 87);
            g2.drawString("|", 50, 77);

            g2.drawString("|", 50, 87);

            g2.drawString("الصنف", 145, 83);

            g2.drawString("سعر الشراء", 60, 83);
            g2.drawString("المجموع", 12, 83);

            int y = 101;
            for (int i = 0; i < DB.data.size(); i++) {

                g2.drawString(DB.data.get(i).getName(), 120, y);

                g2.drawString( DB.data.get(i).getAmount(), 180, y);

                g2.drawString( DB.data.get(i).getPricebuy(), 65, y);
                g2.drawString("" +  DB.data.get(i).getTotal(), 15, y);

                g2.drawString("|", 110, y);
                g2.drawString("|", 50, y);
                y = y + 17;

            }

            g2.drawString("------------------------------------------------------------------------------------", 0, y);
            y = y + 17;
            g2.drawString("الاجمالي  :" + total.getText(), 120, y);
            g2.drawString("المبلغ المدفوع   :" + payed.getText(), 12, y);
            y = y + 17;
            g2.drawString("المتبقي  :" + change.getText(), 12, y);

            Rectangle2D outline = new Rectangle2D.Double(pf.getImageableX(), pf.getImageableY(), pf
                    .getImageableWidth(), pf.getImageableHeight());
            g2.draw(outline);
            return PAGE_EXISTS;
        }
    }

    @FXML
    public void UpdataStock() throws SQLException {

        
        for (int i = 0; i <  DB.data.size(); i++) {
            if (!DB.data.get(i).getParcode().equals("")){
            String sql = "SELECT * FROM  `stock`  WHERE Barcode = " +  DB.data.get(i).getParcode() + "";
            DB.rs = DB.statemen.executeQuery(sql);
            DB.rs.next();
            int numberStock = DB.rs.getInt("QTY") - Integer.parseInt( DB.data.get(i).getAmount());
            sql = "UPDATE stock SET QTY='" + numberStock + "' WHERE  Barcode = " +  DB.data.get(i).getParcode() + "";
            DB.statemen.executeUpdate(sql);
            }
        }
    }
    
     @FXML
    public void AddService() {
        myController.NewloadScreen(afaq.Afag.CashierServiceID, afaq.Afag.CashierServiceFile);
    }
}
