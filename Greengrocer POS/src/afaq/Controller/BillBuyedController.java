/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afaq.Controller;

import afaq.ControlledScreen;

import afaq.DBConnection;
import afaq.ScreensController;

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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import static javafx.scene.input.KeyCode.ENTER;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author AmrSaid
 */
public class BillBuyedController implements Initializable, ControlledScreen {
    
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
    TextField tfsearch;
    @FXML
    TextField payed;
    @FXML
    TextField change;
    @FXML
    TextField BillNumber;
    
    @FXML
    TextField Prodnames;
    
    @FXML
    TextField totalbill;
    @FXML
    TextField SearchBillN;
    
    @FXML
    Label Erromassage;
    
    @FXML
    DatePicker Datepicker2;
    @FXML
    TextArea TAComment;
    
    String paid;
    String changeSQl;
    
    double cahmged;
    
    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            DB = new DBConnection();
            
            menucustomer();
            Datepicker2.setValue(LocalDate.now());
            BillNo();
        } catch (Exception ex) {
            Logger.getLogger(BillBuyedController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Prodnames.setText("");
        rb1.setSelected(true);
        rb2.setId("rb2");
        rb1.setId("rb1");
        total.setDisable(true);
        payed.setDisable(true);
        change.setDisable(true);
        rb1.setToggleGroup(group);
        rb2.setToggleGroup(group);
        
        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> ov, Toggle t, Toggle t1) {
                RadioButton chk = (RadioButton) t1.getToggleGroup().getSelectedToggle(); // Cast object to radio button
                if (chk.getId().endsWith("rb2")) {
                    total.setDisable(true);
                    payed.setDisable(false);
                    change.setDisable(true);
                    paid = payed.getText();
                    
                    changeSQl = total.getText();
                } else {
                    total.setDisable(true);
                    payed.setDisable(true);
                    change.setDisable(true);
                    paid = "0";
                    changeSQl = total.getText();
                }
            }
        });
    }
    
    public void menucustomer() throws SQLException {
        comboBox1.getItems().clear();
        DB.rs = DB.statemen.executeQuery("select * from company");
        while (DB.rs.next()) {
            Cus_Name.add(DB.rs.getString("com_name"));
            
        }
        items = FXCollections.observableArrayList(Cus_Name);
        comboBox1.getItems().addAll(items);
    }
    
    public void BillNo() throws SQLException {
        DB.rs = DB.statemen.executeQuery("SELECT * FROM buy_bills where bill_no  ");
        DB.rs.last();
        int x = Integer.parseInt(DB.rs.getString("bill_no"));
        x++;
        BillNumber.setText("" + x);
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

//        double cahmged = Double.parseDouble(total.getText()) - Double.parseDouble(payed.getText());
//        change.setText("" + cahmged);
        if (!total.getText().equals("")) {
            cahmged = Double.parseDouble(total.getText()) - Double.parseDouble(payed.getText());
            if (cahmged <= 0) {
                Erromassage.setText("يجب المدفوع اقل من الاجمالي");
            }
            change.setText("" + cahmged);
        }
    }
    
    @FXML
    public void InsertBill() throws SQLException {
        try {
            total.setText(totalbill.getText());
            String Value1 = BillNumber.getText();
            String Value2 = comboBox1.getValue();
            
            String Value6 = Datepicker2.getValue().toString();
            
            String Value3 = total.getText();
            String Value7 = Prodnames.getText();
            String Value8 = TAComment.getText();
            
            if (!Value2.equals("")) {
                
                if (rb2.isSelected()) {
                    CahngedMethod();
                    if (cahmged <= 0) {
                        Erromassage.setText("يجب المدفوع اقل من الاجمالي");
                    } else {
                        String Value4 = payed.getText();
                        
                        String Value5 = change.getText();
                        
                        String Sql = "INSERT INTO `buy_bills` ( `bill_no`, `com_name`, `tot_price`, `paid`, `change`, `bill_date`, `item`,`Comment`) VALUES ('" + Value1 + "', '" + Value2 + "', '" + Value3 + "', '" + Value4 + "', '" + Value5 + "', '" + Value6 + "', '" + Value7 + "', '" + Value8 + "');";
                        System.out.println(Sql);
                        
                        DB.statemen.executeUpdate(Sql);
                        
                        ClearAll();
                    }
                } else {
                    
                    String Sql = "INSERT INTO `buy_bills` ( `bill_no`, `com_name`, `tot_price`, `paid`, `change`, `bill_date`, `item`,`Comment`) VALUES ('" + Value1 + "', '" + Value2 + "', '" + Value3 + "', '" + Value3 + "', '0', '" + Value6 + "', '" + Value7 + "', '" + Value8 + "');";
                    DB.statemen.executeUpdate(Sql);
                    System.out.println(Sql);
                    
                    ClearAll();
                    
                }
                
            } else {
                Erromassage.setText("ادخل اسم المورد");
            }
        } catch (Exception ex) {
            Erromassage.setText("ادخل البيانات المطلوبة");
            
        }
        
    }
    
    @FXML
    public void CloseScene() {
        
        Stage stage = (Stage) payed.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    public void SearchBill(final KeyEvent keyEvent) throws SQLException {
        
        if (keyEvent.getCode() == KeyCode.ENTER) {
            String x = "";
            if (!SearchBillN.getText().equals("")) {
                x = SearchBillN.getText();
            } else {
                x = BillNumber.getText();
            }
            try {
                String Sql = "SELECT * FROM buy_bills Where bill_no ='" + SearchBillN.getText() + "'";
                
                System.out.println(Sql);
                DB.data.clear();
                DB.rs = DB.statemen.executeQuery(Sql);
                DB.rs.next();
                
                BillNumber.setText(DB.rs.getString("bill_no"));
                
                comboBox1.setValue(DB.rs.getString("com_name"));
                Prodnames.setText(DB.rs.getString("item"));
                TAComment.setText(DB.rs.getString("Comment"));
                
                String Data = DB.rs.getString("bill_date");
                totalbill.setText(DB.rs.getString("tot_price"));
                
                Datepicker2.setValue(LocalDate.of(1991, Month.of(1), 1));
                
                total.setText(DB.rs.getString("tot_price"));
                payed.setText(DB.rs.getString("paid"));
                change.setText(DB.rs.getString("change"));
                if (DB.rs.getDouble("change") > 0) {
                    System.out.println("change");
                    total.setDisable(true);
                    payed.setDisable(false);
                    change.setDisable(true);
                    paid = payed.getText();
                    double cahmged = Double.parseDouble(paid) - Double.parseDouble(payed.getText());
                    changeSQl = total.getText();
                    rb2.setSelected(true);
                } else {
                    total.setDisable(true);
                    payed.setDisable(true);
                    change.setDisable(true);
                    paid = "0";
                    changeSQl = total.getText();
                    rb1.setSelected(true);
                }
                
                Erromassage.setText("");
            } catch (Exception e) {
                ClearAll();
                Erromassage.setText("الفاتورة غير موجودة");
                
            }
        }
    }
    
    public void ClearAll() throws SQLException {
        
        Erromassage.setText("");
        comboBox1.setValue("");
        
        total.setText("");
        
        payed.setText("");
        
        change.setText("");
        SearchBillN.setText("");
        totalbill.setText("");
        Prodnames.setText("");
        BillNo();
        
    }
    
    @FXML
    public void RemoveBill() throws SQLException {
        try {
            String Sql = "delete FROM `buy_bills` WHERE `bill_no` = '" + BillNumber.getText() + "'";
            
            DB.statemen.executeUpdate(Sql);
            
            ClearAll();
        } catch (Exception e) {
            Erromassage.setText("الفاتورة غير موجودة");
        }
        
    }

//    public void print() throws SQLException {
//        if (DB.data.isEmpty()) {
//            JOptionPane.showMessageDialog(null, "لايوجد مبيعات");
//        } else {
//
//            PrinterJob pj = PrinterJob.getPrinterJob();
//
//            PageFormat pf = pj.defaultPage();
//            Paper paper = new Paper();
//
//            double margin = 0; // half inch
//            int hegith = DB.data.size() * 15;
//            hegith += 165;
//            paper.setImageableArea(margin, margin, 200, hegith);
//            pf.setPaper(paper);
//
//            pj.setPrintable(new MyPrintable(), pf);
//            if (pj.printDialog()) {
//                try {
//                    pj.print();
//                } catch (PrinterException e) {
//                    System.out.println(e);
//                }
//            }
//            InsertBill();
//            System.out.println("Printed");
//        }
//
//    }
//
//    class MyPrintable implements Printable {
//
//        public int print(Graphics g, PageFormat pf, int pageIndex) {
//            if (pageIndex != 0) {
//                return NO_SUCH_PAGE;
//
//            }
//
//            String header = "";
//            try {
//                DB = new DBConnection();
//
//                DB.rs = DB.statemen.executeQuery("select * from info ");
//
//                DB.rs.last();
//                header = DB.rs.getString("com_name");
//            } catch (Exception ex) {
//       
//            }
//            String ponN = "رقم البون : " + BillNumber.getText() + "";
////            String DateN = "التاريخ : " + date.getText();
////            String TimeN = "الوقت  " + time.getText();
//
//            Graphics2D g2 = (Graphics2D) g;
//
//            g2.finalize();
//            g2.setFont(new Font("Serif", Font.BOLD, 13));
//            g2.setPaint(Color.black);
//
//            g2.drawString(header, 40, 17);
//            g2.drawString("فواتير  العملاء", 130, 17);
//            g2.drawString(ponN, 125, 46);
////           g2.drawString(DateN, 55, 63);
////            g2.drawString(TimeN, 7, 46);
//            g2.drawString("----------------------------------------------------------------------------------------", 0, 28);
//            g2.drawString("-----------------------------------------------------------------------------------------", 0, 32);
//            g2.drawString("----------------------------------------------------------------------------------------", 0, 72);
//            g2.drawString("-----------------------------------------------------------------------------------------", 0, 92);
//
//            g2.drawString("|", 110, 77);
//
//            g2.drawString("|", 110, 87);
//            g2.drawString("|", 50, 77);
//
//            g2.drawString("|", 50, 87);
//
//            g2.drawString("الصنف", 145, 83);
//
//            g2.drawString("سعر الشراء", 60, 83);
//            g2.drawString("المجموع", 12, 83);
//
//            int y = 101;
//            for (int i = 0; i < DB.data.size(); i++) {
//
//                g2.drawString(DB.data.get(i).getName(), 120, y);
//
//                g2.drawString(DB.data.get(i).getAmount(), 180, y);
//
//                g2.drawString(DB.data.get(i).getPricebuy(), 65, y);
//                g2.drawString("" + DB.data.get(i).getTotal(), 15, y);
//
//                g2.drawString("|", 110, y);
//                g2.drawString("|", 50, y);
//                y = y + 17;
//
//            }
//
//            g2.drawString("------------------------------------------------------------------------------------", 0, y);
//            y = y + 17;
//            g2.drawString("الاجمالي  :" + total.getText(), 120, y);
//            g2.drawString("المبلغ المدفوع   :" + payed.getText(), 12, y);
//            y = y + 17;
//            g2.drawString("المتبقي  :" + change.getText(), 12, y);
//
//            Rectangle2D outline = new Rectangle2D.Double(pf.getImageableX(), pf.getImageableY(), pf
//                    .getImageableWidth(), pf.getImageableHeight());
//            g2.draw(outline);
//            return PAGE_EXISTS;
//        }
//    }
//
//
// 
//
//
//
//    public void p3(final KeyEvent keyEvent) throws SQLException {
//        if (keyEvent.getCode() == KeyCode.ENTER) {
//            amount.requestFocus();
//        
//
//        }
//
//    }
//
//
//    public void p4(final KeyEvent keyEvent) throws SQLException {
//        if (keyEvent.getCode() == KeyCode.ENTER) {
//            amount.requestFocus();
//         
//        
//
//        }
//
//    }
    @FXML
    public void SetTotall() {
        total.setText(totalbill.getText());
    }
    
    @FXML
    public void SetChange() {
        CahngedMethod();
    }
    
    @FXML
    public void Search() throws SQLException {
        
        String sql10 = "SELECT  * FROM  company WHERE com_name like  '%" + tfsearch.getText() + "%'";
        DB.rs = DB.statemen.executeQuery(sql10);
        if (DB.rs.next()) {
            comboBox1.setValue(DB.rs.getString("com_name"));
        }
        
    }
}
