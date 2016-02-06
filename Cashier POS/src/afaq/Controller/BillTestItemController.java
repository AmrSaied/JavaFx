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
import afaq.Table.TInsertData;
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
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
public class BillTestItemController implements Initializable, ControlledScreen {

    ScreensController myController;

    DBConnection DB;
    @FXML
    ComboBox<String> combo;
    ArrayList<String> Cus_Name = new ArrayList<String>();
    ObservableList items;
    @FXML
    TextField barcodenumber;
    @FXML
    TextField pricebuyed;
    @FXML
    TextField amount;
    @FXML
    TextField totalAmout;
    @FXML
    Label QTY;
    @FXML
    TextField namebar;
    @FXML
    TextField FullTotal;
    @FXML
    Label Erromassage;
    @FXML
    DatePicker Datepicker2;

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
        amount.setText("1");
        Datepicker2.setValue(LocalDate.now());
        Tbarcode.setCellValueFactory(new PropertyValueFactory<TFullCashier, String>("parcode"));
        Tname.setCellValueFactory(new PropertyValueFactory<TFullCashier, String>("name"));
        Tamount.setCellValueFactory(new PropertyValueFactory<TFullCashier, String>("amount"));
        TPrice_Sell.setCellValueFactory(new PropertyValueFactory<TFullCashier, String>("pricebuy"));
        Ttotal.setCellValueFactory(new PropertyValueFactory<TFullCashier, String>("total"));
        tablebuyed.setItems(DB.data);
        try {

            DB = new DBConnection();
            menucustomer();

        } catch (Exception ex) {
            Logger.getLogger(BillTestItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void menucustomer() throws SQLException {
        System.out.println("menucustomer");
        combo.getItems().clear();
        DB.rs = DB.statemen.executeQuery("select * from customer");
        while (DB.rs.next()) {
            Cus_Name.add(DB.rs.getString("cus_name"));

        }
        items = FXCollections.observableArrayList(Cus_Name);
        combo.getItems().addAll(items);
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
                ClearAll();
            }
        }
    }

    @FXML
    public void ClearAll() {
        barcodenumber.clear();
        namebar.setText("");
        amount.setText("1");
        pricebuyed.setText("");
        totalAmout.setText("");
        FullTotal.setText("");
        QTY.setText("");
        DB.data.clear();
    }

    @FXML
    public void AddtoTable() throws SQLException {
        SumTotal();
        SearchBarCode();
        DB.rs = DB.statemen.executeQuery("SELECT * FROM stock WHERE Barcode = '" + barcodenumber.getText() + "'");
        DB.rs.next();
        DB.data.add(new TFullCashier(DB.rs.getString("ID"), barcodenumber.getText(), DB.rs.getString("items"), amount.getText(), DB.rs.getString("Sell_Price"), totalAmout.getText(), DB.rs.getString("Buy_Price")));
        System.out.println("ADDed To Table");
        ClearAdd();
        SearchBarCode();
        SumTotal();
    }

    public void ClearAdd() {
        barcodenumber.clear();
        namebar.setText("");
        amount.setText("1");
        pricebuyed.setText("");
        totalAmout.setText("");
        FullTotal.setText("");
        QTY.setText("");

    }

    public void SumTotal() {
        double totall = 0;
        for (int i = 0; i < DB.data.size(); i++) {

            totall = totall + Double.parseDouble(DB.data.get(i).getTotal());

            System.out.println("totall " + totall);
        }

        FullTotal.setText("" + totall);
    }

    public void NewBill() {
        barcodenumber.clear();
        namebar.setText("");
        amount.setText("1");
        pricebuyed.setText("");
        totalAmout.setText("");
        DB.data.clear();
        QTY.setText("");

    }

    @FXML
    public void CloseScene() {
        ScreensController.p7 = false;
        Stage stage = (Stage) barcodenumber.getScene().getWindow();
        stage.close();
        DB.data.clear();
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
    
    @FXML
    public void print() throws SQLException {
        if (DB.data.isEmpty()) {
            JOptionPane.showMessageDialog(null, "لايوجد مبيعات");
        } else {

            PrinterJob pj = PrinterJob.getPrinterJob();

            PageFormat pf = pj.defaultPage();
            Paper paper = new Paper();

            double margin = 0; // half inch
            int hegith = DB.data.size() * 15;
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
           
            System.out.println("Printed");
        }
        ClearAll ();

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
                header = DB.rs.getString("com_name");
            } catch (Exception ex) {
                Logger.getLogger(MainFrameController.class.getName()).log(Level.SEVERE, null, ex);
            }
          
//            String DateN = "التاريخ : " + date.getText();
//            String TimeN = "الوقت  " + time.getText();

            Graphics2D g2 = (Graphics2D) g;

            g2.finalize();
            g2.setFont(new Font("Serif", Font.BOLD, 13));
            g2.setPaint(Color.black);

            g2.drawString(header, 40, 17);
            g2.drawString("فواتير اسعار", 130, 17);

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

            g2.drawString("سعر البيع", 60, 83);
            g2.drawString("المجموع", 12, 83);

            int y = 101;
            for (int i = 0; i < DB.data.size(); i++) {

                g2.drawString(DB.data.get(i).getName(), 120, y);

                g2.drawString(DB.data.get(i).getAmount(), 180, y);

                g2.drawString(DB.data.get(i).getPricebuy(), 65, y);
                g2.drawString("" + DB.data.get(i).getTotal(), 15, y);

                g2.drawString("|", 110, y);
                g2.drawString("|", 50, y);
                y = y + 17;

            }

            g2.drawString("------------------------------------------------------------------------------------", 0, y);
            y = y + 17;
            g2.drawString("الاجمالي  :" + FullTotal.getText(), 120, y);
            

            Rectangle2D outline = new Rectangle2D.Double(pf.getImageableX(), pf.getImageableY(), pf
                    .getImageableWidth(), pf.getImageableHeight());
            g2.draw(outline);
            return PAGE_EXISTS;
        }
    }
}
