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
import javafx.event.EventHandler;
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
import javafx.scene.control.cell.TextFieldTableCell;
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
public class BillsellerController implements Initializable, ControlledScreen {

    ScreensController myController;
    TuserPermission current;
    final ToggleGroup group = new ToggleGroup();
    DBConnection DB;
    ObservableList items;
    @FXML
    ComboBox<String> CBNameCuastomer;
    ArrayList<String> Cus_Name = new ArrayList<String>();
    @FXML
    TextField Prodnames;

    @FXML
    TextField totalbill;

    @FXML
    TextField tfsearch;
    @FXML
    TextField allchange;
    @FXML
    TextField tfpayed;

    @FXML
    Label Erromassage;

    @FXML
    DatePicker Datepicker2;
    @FXML
    TextArea TAComment;

    String paid;
    String changeSQl;
    String BillNumber;
    String BillNumberTable;

    double cahmged;
    double totall ;
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
String sql3 ;
      ObservableList<TBillCashier> data = FXCollections.observableArrayList();
    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            DB = new DBConnection();
           
        total.setCellValueFactory(new PropertyValueFactory<TBillCashier, String>("total"));
        buyprices.setCellValueFactory(new PropertyValueFactory<TBillCashier, String>("buyprices"));
        reprices.setCellValueFactory(new PropertyValueFactory<TBillCashier, String>("reprices"));
        databill.setCellValueFactory(new PropertyValueFactory<TBillCashier, String>("databill"));
        tableBill.setItems(data);
        tableBill.setEditable(true);
           buyprices.setCellFactory(TextFieldTableCell.forTableColumn());
buyprices.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<TBillCashier, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<TBillCashier, String> t) {
                        ((TBillCashier) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setBuyprices(t.getNewValue());

                        String x = ((TBillCashier) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).getId();

                        try {
                            String sql = "UPDATE sell_bill SET tot_price = '" + t.getNewValue() + "' where id = " + x + " ";
                            System.out.println(sql);
                           DB.statemen.executeUpdate(sql);
                            ClearAll();
                        } catch (Exception ex) {
                            Logger.getLogger(BillsellerController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
        );
           reprices.setCellFactory(TextFieldTableCell.forTableColumn());
reprices.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<TBillCashier, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<TBillCashier, String> t) {
                        ((TBillCashier) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setReprices(t.getNewValue());

                        String x = ((TBillCashier) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).getId();

                        try {
                            String sql = "UPDATE sell_bill SET item = '" + t.getNewValue() + "' where id = " + x + " ";
                            System.out.println(sql);
                           DB.statemen.executeUpdate(sql);
                            ClearAll();
                        } catch (Exception ex) {
                            Logger.getLogger(BillsellerController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
        );
            menucustomer();
            Datepicker2.setValue(LocalDate.now());
            BillNo();

        } catch (Exception ex) {
            Logger.getLogger(BillBuyedController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Prodnames.setText("");

    }

    public void menucustomer() throws Exception {
        CBNameCuastomer.getItems().clear();
        DB.rs = DB.statemen.executeQuery("select * from customer");
        while (DB.rs.next()) {
            Cus_Name.add(DB.rs.getString("cus_name"));
        }
        items = FXCollections.observableArrayList(Cus_Name);
        CBNameCuastomer.getItems().addAll(items);
        
           CBNameCuastomer.setOnAction(e -> {
            try {
                setDisplayUser(CBNameCuastomer.getValue());
            } catch (SQLException ex) {
                Logger.getLogger(BillsellerController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public void BillNo() throws SQLException {
        DB.rs = DB.statemen.executeQuery("SELECT * FROM buy_bills where bill_no  ");
        DB.rs.last();
        int x = Integer.parseInt(DB.rs.getString("bill_no"));
        x++;
        BillNumber = "" + x;
    }

    public void CahngedMethod(final KeyEvent keyEvent) {

        if (keyEvent.getCode() == KeyCode.ENTER) {
//            if (!total.getText().equals("")) {
//                cahmged = Double.parseDouble(total.getText()) - Double.parseDouble(payed.getText());
//                if (cahmged <= 0) {
//                    Erromassage.setText("يجب المدفوع اقل من الاجمالي");
//                }
//                change.setText("" + cahmged);
//            }
        }
    }

    public void CahngedMethod() {

//        double cahmged = Double.parseDouble(total.getText()) - Double.parseDouble(payed.getText());
//        change.setText("" + cahmged);
//        if (!total.getText().equals("")) {
//            cahmged = Double.parseDouble(total.getText()) - Double.parseDouble(payed.getText());
//            if (cahmged <= 0) {
//                Erromassage.setText("يجب المدفوع اقل من الاجمالي");
//            }
//            change.setText("" + cahmged);
//        }
    }

    @FXML
    public void InsertBill() throws SQLException {

        String Value1 = BillNumber;
        String Value2 = CBNameCuastomer.getValue();

        String Value6 = Datepicker2.getValue().toString();

        String Value3 = totalbill.getText();
        String Value7 = Prodnames.getText();
        String Value8 = TAComment.getText();
     

        
        String Sql = "INSERT INTO `sell_bill` ( `bill_no`, `cus_name`, `tot_price`, `bill_date`, `item`,`Comment`) VALUES ('" + Value1 + "', '" + Value2 + "', '" + Value3 + "', '" + Value6 + "', '" + Value7 + "', '" + Value8 + "');";
        System.out.println(Sql);

        DB.statemen.executeUpdate(Sql);

        ClearAll();
    }
    
    @FXML
    public void CloseScene() {
        
        Stage stage = (Stage) Datepicker2.getScene().getWindow();
        stage.close();
    }
        @FXML
    public void Search() throws SQLException {

     
       String sql10 = "SELECT  * FROM  customer WHERE cus_name like  '%" + tfsearch.getText() + "%'";
          DB.rs = DB.statemen.executeQuery(sql10);
          if(DB.rs.next()){
              CBNameCuastomer.setValue(DB.rs.getString("cus_name"));
          }

    }

    public void ClearAll() throws SQLException {
        Erromassage.setText("");


        totalbill.setText("");

        totalbill.setText("");

        Prodnames.setText("");
        TAComment.setText("");
        BillNo();
        
        setDisplayUser(CBNameCuastomer.getValue());
    }

    
    @FXML
    public void RemoveBill() throws SQLException {
           TBillCashier productCp = (TBillCashier) tableBill.getSelectionModel().getSelectedItem();
            data.remove(productCp);
            DB.statemen.executeUpdate("DELETE FROM sell_bill WHERE id = " + productCp.getId());
               ClearAll();

        
    }
//
////    public void print() throws SQLException {
////        if (DB.data.isEmpty()) {
////            JOptionPane.showMessageDialog(null, "لايوجد مبيعات");
////        } else {
////
////            PrinterJob pj = PrinterJob.getPrinterJob();
////
////            PageFormat pf = pj.defaultPage();
////            Paper paper = new Paper();
////
////            double margin = 0; // half inch
////            int hegith = DB.data.size() * 15;
////            hegith += 165;
////            paper.setImageableArea(margin, margin, 200, hegith);
////            pf.setPaper(paper);
////
////            pj.setPrintable(new MyPrintable(), pf);
////            if (pj.printDialog()) {
////                try {
////                    pj.print();
////                } catch (PrinterException e) {
////                    System.out.println(e);
////                }
////            }
////            InsertBill();
////            System.out.println("Printed");
////        }
////
////    }
////
////    class MyPrintable implements Printable {
////
////        public int print(Graphics g, PageFormat pf, int pageIndex) {
////            if (pageIndex != 0) {
////                return NO_SUCH_PAGE;
////
////            }
////
////            String header = "";
////            try {
////                DB = new DBConnection();
////
////                DB.rs = DB.statemen.executeQuery("select * from info ");
////
////                DB.rs.last();
////                header = DB.rs.getString("cus_name");
////            } catch (Exception ex) {
////       
////            }
////            String ponN = "رقم البون : " + BillNumber.getText() + "";
//////            String DateN = "التاريخ : " + date.getText();
//////            String TimeN = "الوقت  " + time.getText();
////
////            Graphics2D g2 = (Graphics2D) g;
////
////            g2.finalize();
////            g2.setFont(new Font("Serif", Font.BOLD, 13));
////            g2.setPaint(Color.black);
////
////            g2.drawString(header, 40, 17);
////            g2.drawString("فواتير  العملاء", 130, 17);
////            g2.drawString(ponN, 125, 46);
//////           g2.drawString(DateN, 55, 63);
//////            g2.drawString(TimeN, 7, 46);
////            g2.drawString("----------------------------------------------------------------------------------------", 0, 28);
////            g2.drawString("-----------------------------------------------------------------------------------------", 0, 32);
////            g2.drawString("----------------------------------------------------------------------------------------", 0, 72);
////            g2.drawString("-----------------------------------------------------------------------------------------", 0, 92);
////
////            g2.drawString("|", 110, 77);
////
////            g2.drawString("|", 110, 87);
////            g2.drawString("|", 50, 77);
////
////            g2.drawString("|", 50, 87);
////
////            g2.drawString("الصنف", 145, 83);
////
////            g2.drawString("سعر الشراء", 60, 83);
////            g2.drawString("المجموع", 12, 83);
////
////            int y = 101;
////            for (int i = 0; i < DB.data.size(); i++) {
////
////                g2.drawString(DB.data.get(i).getName(), 120, y);
////
////                g2.drawString(DB.data.get(i).getAmount(), 180, y);
////
////                g2.drawString(DB.data.get(i).getPricebuy(), 65, y);
////                g2.drawString("" + DB.data.get(i).getTotal(), 15, y);
////
////                g2.drawString("|", 110, y);
////                g2.drawString("|", 50, y);
////                y = y + 17;
////
////            }
////
////            g2.drawString("------------------------------------------------------------------------------------", 0, y);
////            y = y + 17;
////            g2.drawString("الاجمالي  :" + total.getText(), 120, y);
////            g2.drawString("المبلغ المدفوع   :" + payed.getText(), 12, y);
////            y = y + 17;
////            g2.drawString("المتبقي  :" + change.getText(), 12, y);
////
////            Rectangle2D outline = new Rectangle2D.Double(pf.getImageableX(), pf.getImageableY(), pf
////                    .getImageableWidth(), pf.getImageableHeight());
////            g2.draw(outline);
////            return PAGE_EXISTS;
////        }
////    }

    private void setDisplayUser(String value) throws SQLException {
          sql3 = "SELECT  * FROM  sell_bill WHERE cus_name = '"+value+"' ";
         System.out.println(sql3);
           DB.rs = DB.statemen.executeQuery(sql3);
            data.clear();
            double tottal = 0 ;
            while (DB.rs.next()) {
                tottal = tottal+ Double.parseDouble(DB.rs.getString("tot_price"));
              data.add(new TBillCashier(DB.rs.getString("id"), DB.rs.getString("bill_no"),""+tottal, DB.rs.getString("tot_price"), DB.rs.getString("item"), DB.rs.getString("bill_date")));
            }
     SumAll();
    }

   
      public void SumAll() {
         totall = 0;
        for (int i = 0; i < data.size(); i++) {

            double y = Double.parseDouble(data.get(i).getTotal());

            totall = y;

        }

        allchange.setText("" + totall);

    }
      
      @FXML
      public void PayedMoney(){
          
          double x2= Double.parseDouble(totalbill.getText());
          allchange.setText(""+(totall+x2));
      }
      
         @FXML
    public void payed() throws SQLException {
                String Value1 = BillNumber;
        String Value2 = CBNameCuastomer.getValue();

        String Value6 = Datepicker2.getValue().toString();

        double Value3 = (Double.parseDouble(tfpayed.getText()) * -1);
        String Value7 = "تم دفع ";
        String Value8 = TAComment.getText();
        
       
 
        
        String Sql = "INSERT INTO `sell_bill` ( `bill_no`, `cus_name`, `tot_price``, `bill_date`, `item`,`Comment`) VALUES ('" + Value1 + "', '" + Value2 + "', '" + Value3 + "', '" + Value6 + "', '" + Value7 + "', '" + Value8 + "');";
        System.out.println(Sql);

        DB.statemen.executeUpdate(Sql);

        ClearAll();
    }
    
    
    @FXML
    public void RemoveBillTable (KeyEvent keyEvent) throws SQLException{
        if (keyEvent.getCode() == KeyCode.DELETE) {

            TBillCashier productCp = (TBillCashier) tableBill.getSelectionModel().getSelectedItem();
            data.remove(productCp);
            DB.statemen.executeUpdate("DELETE FROM sell_bill WHERE id = " + productCp.getId());
ClearAll();
        }
          
    }
  
    
    
}
