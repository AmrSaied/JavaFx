/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afaq.Controller;

import afaq.ControlledScreen;
import afaq.DBConnection;
import afaq.ScreensController;
import afaq.Table.TBillCashier;
import afaq.Table.TuserPermission;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
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
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author AmrSaid
 */
public class BillCustomerController implements Initializable, ControlledScreen {

    ScreensController myController;
    TuserPermission current;
    DBConnection DB;
    ObservableList items;

    @FXML
    ComboBox<String> comboBox1;
    ArrayList<String> Cus_Name = new ArrayList<String>();

    final ToggleGroup group = new ToggleGroup();
    @FXML
    RadioButton rb2;
    @FXML
    RadioButton rb1;

    @FXML
    TextField tfsearch;
    @FXML
    TableView<TBillCashier> tableBill;

    @FXML
    TableColumn<TBillCashier, String> total;
    @FXML
    TableColumn<TBillCashier, String> buyprices;
    @FXML
    TableColumn<TBillCashier, String> reprices;
    @FXML
    TableColumn<TBillCashier, String> databill;
    @FXML
    DatePicker Datepicker3;
    @FXML
    DatePicker Datepicker4;
    @FXML
    Label Erromassage;

    ObservableList<TBillCashier> data = FXCollections.observableArrayList();
    String sql = "SELECT  * FROM  sell_bill WHERE";
    String sql1 = "5";
    String sql2 = "5";
    String sql3 = "5";
    String sql4 = "5";

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        total.setCellValueFactory(new PropertyValueFactory<TBillCashier, String>("total"));
        buyprices.setCellValueFactory(new PropertyValueFactory<TBillCashier, String>("buyprices"));
        reprices.setCellValueFactory(new PropertyValueFactory<TBillCashier, String>("reprices"));
        databill.setCellValueFactory(new PropertyValueFactory<TBillCashier, String>("databill"));
        tableBill.setItems(data);
        Datepicker3.setValue(LocalDate.now());
        Datepicker4.setValue(LocalDate.now());
        try {
            DB = new DBConnection();
            menucustomer();

            rb1.setToggleGroup(group);
            rb2.setToggleGroup(group);
            rb1.setSelected(true);

            rb2.setId("rb2");
            rb1.setId("rb1");

            sql1 = "";
            group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
                @Override
                public void changed(ObservableValue<? extends Toggle> ov, Toggle t, Toggle t1) {
                    RadioButton chk = (RadioButton) t1.getToggleGroup().getSelectedToggle(); // Cast object to radio button

                    if (chk.getId().equals("rb2")) {

                        String date3 = Datepicker3.getValue().toString();
                        String date4 = Datepicker4.getValue().toString();

                        sql1 = "AND bill_date  BETWEEN   '" + date3 + "' AND   '" + date4 + "'";
                    } else {
                        sql1 = "";

                    }
                }
            });
        } catch (Exception ex) {
            Logger.getLogger(BillCashierController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setCurrent(TuserPermission current) {

        this.current = current;

    }

    public void menucustomer() throws SQLException {
        comboBox1.getItems().clear();
        DB.rs = DB.statemen.executeQuery("select * from customer");
        while (DB.rs.next()) {
            Cus_Name.add(DB.rs.getString("cus_name"));

        }
        items = FXCollections.observableArrayList(Cus_Name);
        comboBox1.getItems().addAll(items);
        comboBox1.setOnAction(e -> setDisplayUser(comboBox1.getValue()));
    }

    @FXML
    public void Show() throws SQLException {
        try {
            sql3 = " " + sql + " " + sql2 + "   " + sql4 + " " + sql1 + "";
            System.out.println(sql3);
            DB.rs = DB.statemen.executeQuery(sql3);
            data.clear();
            double tottal = 0;
            while (DB.rs.next()) {
                tottal = tottal + Double.parseDouble(DB.rs.getString("tot_price"));
                data.add(new TBillCashier(DB.rs.getString("id"), DB.rs.getString("bill_no"), "" + tottal, DB.rs.getString("tot_price"), DB.rs.getString("item"), DB.rs.getString("bill_date")));
            }

            Erromassage.setText("");

        } catch (Exception ex) {
            Erromassage.setText("ادخل باقي المطلبات");
        }
    }

    public void setDisplayUser(String index) {
        sql4 = "AND cus_name = '" + index + "'";
        System.out.println(sql4);

    }

    @FXML
    public void CloseScene() {

        Stage stage = (Stage) comboBox1.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void Search() throws SQLException {

        String sql10 = "SELECT  * FROM  customer WHERE cus_name like  '%" + tfsearch.getText() + "%'";
        DB.rs = DB.statemen.executeQuery(sql10);
        if (DB.rs.next()) {
            comboBox1.setValue(DB.rs.getString("cus_name"));
        }
        Show();
    }

    public void Clearall() {

        data.clear();;
    }

    public void print() throws SQLException {
        if (data.isEmpty()) {
            JOptionPane.showMessageDialog(null, "لايوجد مبيعات");
        } else {

            PrinterJob pj = PrinterJob.getPrinterJob();

            PageFormat pf = pj.defaultPage();
            Paper paper = new Paper();

            double margin = 0; // half inch
            int hegith = data.size() * 15;
            hegith += 165;
            paper.setImageableArea(margin, margin, 2480, hegith);
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

            }
            String ponN = "اسم العميل : " + comboBox1.getValue() + "";
//            String DateN = "التاريخ : " + date.getText();
//            String TimeN = "الوقت  " + time.getText();

            Graphics2D g2 = (Graphics2D) g;

            g2.finalize();
            g2.setFont(new Font("Serif", Font.BOLD, 13));
            g2.setPaint(Color.black);

            g2.drawString(header, 40, 17);
            g2.drawString("فواتير  العملاء", 250, 17);
              g2.drawString("محلات عبد الهادي غطاشة", 10, 17);
            g2.drawString(ponN, 500, 46);
//           g2.drawString(DateN, 55, 63);
//            g2.drawString(TimeN, 7, 46);
            g2.drawString("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------", 0, 28);
            g2.drawString("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------", 0, 32);
            g2.drawString("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------", 0, 72);
            g2.drawString("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------", 0, 92);

            g2.drawString("|", 450, 77);

            g2.drawString("|", 450, 87);
            g2.drawString("|", 300, 77);

            g2.drawString("|", 300, 87);
            g2.drawString("|", 110, 77);

            g2.drawString("|", 110, 87);

            g2.drawString("اسماء المنتجات", 500, 83);
            g2.drawString("الرصيد", 10, 83);
            g2.drawString("اجمالي الفاتورة ", 150, 83);
            g2.drawString("تاريخ الفاتورة", 350, 83);

            int y = 101;
            for (int i = 0; i < data.size(); i++) {
                g2.drawString(data.get(i).getReprices(), 500, y);
                g2.drawString(data.get(i).getDatabill(), 350, y);
                g2.drawString(data.get(i).getTotal(), 10, y);

                g2.drawString(data.get(i).getBuyprices(), 150, y);

                g2.drawString("|", 450, y);
                g2.drawString("|", 300, y);
                g2.drawString("|", 110, y);

                y = y + 17;

            }

            g2.drawString("--------------------------------------------------------------------------------------------------------------------------------------------------------------------", 0, y);
            y = y + 17;
            g2.drawString("الاجمالي  :" + data.get(data.size() - 1).getTotal(), 500, y);
            g2.drawString(" الادارة : فيصل عبد الهادي ", 480, y+17);
            g2.drawString(" رقم الهاتف : 0599479310", 10, y+17);
//            g2.drawString("المبلغ المدفوع   :" + payed.getText(), 12, y);
//            y = y + 17;
//            g2.drawString("المتبقي  :" + change.getText(), 12, y);

            Rectangle2D outline = new Rectangle2D.Double(pf.getImageableX(), pf.getImageableY(), pf
                    .getImageableWidth(), pf.getImageableHeight());
            g2.draw(outline);
            return PAGE_EXISTS;
        }
    }
}
