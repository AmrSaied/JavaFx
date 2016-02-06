/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package samba.Controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
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
import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import samba.ControlledScreen;
import samba.DBConnection;
import samba.Samba;
import samba.ScreensController;
import samba.Table.Report;
import samba.Table.TDelivery;
import samba.Table.TuserPermission;

/**
 * FXML Controller class
 *
 * @author AMR SAID
 */
public class TablePageController implements Initializable, ControlledScreen {

    TuserPermission permission;
    ScreensController myController;

    @FXML
    AnchorPane APM;
    @FXML
    FlowPane Category;
    @FXML
    FlowPane Product;
    @FXML
    Label time;
    @FXML
    Label date;
    @FXML
    Label ponid;

    @FXML
    TableView<Report> tableproducat;
    @FXML
    TableColumn<Report, String> type;
    @FXML
    TableColumn<Report, Double> price;
    @FXML
    TableColumn<Report, String> amout;
    @FXML
    TableColumn<Report, Double> total;
    @FXML
    Label totalls;
    @FXML
    Label totalls1;

    @FXML
    TextField payedmoney;
    @FXML
    Button finish;
    @FXML
    TextField TFamout;

    Button btncat;
    Button btnprod;
    
    TDelivery tDelivery;

    @FXML
    Button n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12;
    double totall = 0;

    DBConnection db;
    ObservableList<Report> data = FXCollections.observableArrayList();

    String user_name = "";
    int pagefrom = 0;
    String row_id, table_id;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Connect Colum To Class
        type.setCellValueFactory(new PropertyValueFactory<Report, String>("type"));
        price.setCellValueFactory(new PropertyValueFactory<Report, Double>("price"));
        amout.setCellValueFactory(new PropertyValueFactory<Report, String>("amout"));
        total.setCellValueFactory(new PropertyValueFactory<Report, Double>("total"));
        tableproducat.setItems(data);

        tableproducat.setEditable(true);
        amout.setCellFactory(TextFieldTableCell.forTableColumn());
        amout.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Report, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Report, String> t) {

                        ((Report) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setAmout(t.getNewValue());
                        double prices = ((Report) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).getPrice();

                        double value = Double.parseDouble(t.getNewValue());

                        double newtotall = value * prices;
                        ((Report) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setTotal(newtotall);
                        total.setVisible(false);
                        total.setVisible(true);

                        setTotal();

                    }

                }
        );
        try {

            db = new DBConnection();

            cat();
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            double width = screenSize.getWidth();
            double height = screenSize.getHeight();
//            APM.setPrefHeight(height);
//            APM.setPrefWidth(width);
            CurrentDateAndTime();
            countReport();
        } catch (Exception ex) {
            System.out.println("Error");
        }
    }

    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    public void CurrentDateAndTime() {
        Calendar cal = new GregorianCalendar();
        final Label clock = new Label();
        final DateFormat format = DateFormat.getInstance();
        final Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                final Calendar cal = Calendar.getInstance();

                int month = cal.get(Calendar.MONTH);
                month++;
                int year = cal.get(Calendar.YEAR);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                date.setText(year + "-" + month + "-" + day);

                int Hour = cal.get(Calendar.HOUR_OF_DAY);
                int Minute = cal.get(Calendar.MINUTE);
                int second = cal.get(Calendar.SECOND);

                time.setText(Hour + ":" + Minute);
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }

    public void newOrder() throws SQLException {

        data.clear();
        totall = 0;
        totalls.setText("0");
        payedmoney.clear();
        totalls1.setText("0");

        FXMLLoader loder = Samba.FXTable;
        TableController scenseController = loder.getController();
        scenseController.FillTable();
        
        
        FXMLLoader loders = Samba.FXDOrder;
        OrderController scenseControllers = loders.getController();
        scenseControllers.FillTable();

    }

    void countReport() throws SQLException {
        db.rs = db.statemen.executeQuery("select *   from sell_bills");
        db.rs.last();
        int x = db.rs.getInt("bill_no");
        x++;
        ponid.setText("" + x);
    }

    @FXML
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
        }

    }

    @FXML
    public void Exit() {
        myController.setScreen(Samba.LoginId
        );
    }

    class MyPrintable implements Printable {

        public int print(Graphics g, PageFormat pf, int pageIndex) {
            if (pageIndex != 0) {
                return NO_SUCH_PAGE;
            }
            Image img1 = Toolkit.getDefaultToolkit().getImage("srchasblyimagesprintlogo.jpg");
            String header = "";
            try {
                db.rs = db.statemen.executeQuery("select * from info ");
                db.rs.last();
                header = db.rs.getString(1);
            } catch (Exception ex) {
                System.out.println("ERRor");
            }
            String ponN = "رقم البون : " + ponid.getText();
            String DateN = "التاريخ : " + date.getText();
            String TimeN = "الوقت  " + time.getText();

            Graphics2D g2 = (Graphics2D) g;
            g2.drawImage(img1, 144, 0, 25, 25, null);

            g2.finalize();
            g2.setFont(new Font("Serif", Font.BOLD, 13));
            g2.setPaint(Color.black);

            g2.drawString(header, 40, 17);
            g2.drawString(ponN, 125, 46);
            g2.drawString(DateN, 55, 63);
            g2.drawString(TimeN, 7, 46);
            g2.drawString("----------------------------------------------------------------------------------------", 0, 28);
            g2.drawString("-----------------------------------------------------------------------------------------", 0, 32);
            g2.drawString("----------------------------------------------------------------------------------------", 0, 72);
            g2.drawString("-----------------------------------------------------------------------------------------", 0, 92);
            g2.drawString("|", 85, 77);
            g2.drawString("|", 85, 87);
            g2.drawString("|", 50, 77);
            g2.drawString("|", 50, 87);
            g2.drawString("الصنف", 145, 83);
            g2.drawString("الكمية", 60, 83);
            g2.drawString("المجموع", 12, 83);
            int y = 101;
            for (int i = 0; i < data.size(); i++) {
                g2.drawString(data.get(i).getType(), 100, y);
                g2.drawString(data.get(i).getAmout(), 65, y);
                g2.drawString("" + data.get(i).getTotal(), 15, y);
                g2.drawString("|", 85, y);
                g2.drawString("|", 50, y);
                y = y + 17;
            }
            g2.drawString("------------------------------------------------------------------------------------", 0, y);
            y = y + 17;
            g2.drawString("الاجمالي  :" + totalls.getText(), 120, y);
            g2.drawString("المدفوع   :" + payedmoney.getText(), 12, y);
            y = y + 17;
            g2.drawString("الباقي  :" + totalls1.getText(), 12, y);

            Rectangle2D outline = new Rectangle2D.Double(pf.getImageableX(), pf.getImageableY(), pf
                    .getImageableWidth(), pf.getImageableHeight());
            g2.draw(outline);
            return PAGE_EXISTS;
        }
    }

    @FXML
    public void removeallItem() {
        data.clear();
        setTotal();
    }

    @FXML
    public void removeItem() {
        Report person = (Report) tableproducat.getSelectionModel().getSelectedItem();
        data.remove(person);
        setTotal();
    }

    @FXML
    public void removeItemByDelet(final KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.DELETE) {
            Report person = (Report) tableproducat.getSelectionModel().getSelectedItem();
            data.remove(person);
        }
    }

    @FXML
    public void payed(final KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            double fristvalue = Double.parseDouble(payedmoney.getText());
            double Secondvalue = Double.parseDouble(totalls.getText());
            double result = fristvalue - Secondvalue;
            totalls1.setText("" + result);
        }
    }

    void setTotal() {
        totall = 0;
        for (int i = 0; i < data.size(); i++) {
            totall = totall + data.get(i).getTotal();
            totalls.setText("" + totall);
        }
    }

    void cat() throws SQLException {

        db.rs = db.statemen.executeQuery("select * from category ");

        while (db.rs.next()) {

            btncat = new Button(db.rs.getString("cat_name"));

            btncat.setPrefWidth(183);
            btncat.setPrefHeight(60);
            btncat.setOnAction(new catproduce());

            btncat.setId(db.rs.getString("id"));

            Category.getChildren().add(btncat);

        }

    }

    class catproduce implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            Product.getChildren().remove(btnprod);
            try {
                String x = event.getSource().toString().substring(10, 13).replaceAll(",", "");
                Product.getChildren().clear();
                prod(x);
            } catch (Exception ex) {
                System.out.println("ERROR catproduce");
            }

        }

    }

    void prod(String cat_id) throws SQLException {
        db.rs = db.statemen.executeQuery("select * from menu where cat_id  = " + cat_id + "");
        while (db.rs.next()) {
            btnprod = new Button(db.rs.getString("m_name"));
            btnprod.setPrefHeight(80);
            btnprod.setPrefWidth(80);
            btnprod.setOnAction(new prodbuy());
            btnprod.setId(db.rs.getString("id"));
            Product.getChildren().add(btnprod);
        }
    }

    class prodbuy implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            String x = event.getSource().toString().substring(10, 13).replaceAll(",", "");
            try {
                updateTable(x);
                setTotal();
            } catch (Exception ex) {
                System.out.println("ERORR prodbuy");
            }

        }

    }

    public void updateTable(String id) throws SQLException {
        db.rs = db.statemen.executeQuery("select * from menu where id = " + id);
        db.rs.next();

        if (!TFamout.getText().equals("") && Double.parseDouble(TFamout.getText()) > 0) {
            data.add(new Report(db.rs.getInt(1), db.rs.getString(2), db.rs.getDouble(3), TFamout.getText(), db.rs.getDouble(3)));
            setTotal();
        } else {
            data.add(new Report(db.rs.getInt(1), db.rs.getString(2), db.rs.getDouble(3), "1", db.rs.getDouble(3)));
            setTotal();
        }
    }

    public void calc(ActionEvent event) {
        String x = event.getSource().toString().substring(41).replaceAll("'", "");
        TFamout.appendText(x);

    }

    public void removeTFamout() {
        TFamout.clear();
    }

    public void setCurrent(String id, TuserPermission permission) throws SQLException {
        data.clear();
        String sql = "SELECT * FROM `table` where id = '" + id + "'";
        db.rs = db.statemen.executeQuery(sql);
        db.rs.next();
        table_id = id;
        this.user_name = permission.getUser_name();
        pagefrom = 1;
        finish.setVisible(false);
        countReport();

    }

    void setCurrent(TDelivery InsertData) throws SQLException {
        data.clear();

        tDelivery= InsertData;
        this.user_name = InsertData.getUser_name();

        pagefrom = 3;
        ponid.setText(InsertData.getPon_id());

        finish.setVisible(true);
        String sql = "SELECT * FROM `sell_items` where bill_id = '" + InsertData.getId() + "'";
        db.rs = db.statemen.executeQuery(sql);
        System.out.println(sql);
        data.clear();
        while (db.rs.next()) {
            data.add(new Report(db.rs.getInt("id"), db.rs.getString("items"), db.rs.getDouble("sell_price"), db.rs.getString("qty"), db.rs.getDouble("tot_price")));
        }
        row_id = InsertData.getId();

        setTotal();

    }

    public void insertBuyed() throws SQLException {
        String sql = "";
        String sql1 = "";

        if (pagefrom == 1) {
            sql = "INSERT INTO  `sell_bills` (`kitchen_S` ,`delivery` ,`del_S` ,`bill_no` ,`cus_name` ,`T_name` ,`T_S` ,`tot_price` ,`bill_date` ,`bill_Time` ,`user_name` ,`Action`) VALUES ("
                    + "'1',  '',  '',  '" + ponid.getText() + "',  '',  '" + table_id + "',  '1', '" + totalls.getText() + "',  '" + date.getText() + "',  '" + time.getText() + "',  '" + user_name + "','الطاولات ');";
            db.statemen.executeUpdate("UPDATE `table` SET `status` =  '1' where id = '" + table_id + "'");
        }

        if (pagefrom == 3) {
//            db.statemen.executeUpdate("delete  FROM `sell_bills` WHERE id = '" + tDelivery.getId() + "'");
            db.statemen.executeUpdate("delete  FROM `sell_items` WHERE bill_id = '" + tDelivery.getId() + "'");
           sql = "UPDATE sell_bills SET `tot_price` =  '" + totalls.getText() + "' where id = '" + tDelivery.getId() + "'";
        }
        if (data.isEmpty()) {
            JOptionPane.showMessageDialog(null, "لايوجد مبيعات");
        } else {
            System.out.println(sql);
            db.statemen.executeUpdate(sql);
            System.out.println(sql);
            if (pagefrom == 1) {

                db.rs = db.statemen.executeQuery("SELECT LAST_INSERT_ID( id ) AS id FROM sell_bills");
                db.rs.last();
                row_id = db.rs.getString("id");
            }

            System.out.println("" + row_id);

            for (int i = 0; i < data.size(); i++) {
                sql1 = "INSERT INTO `sell_items` (`bill_no`,`bill_id`, `items`, `qty`, `sell_price`, `tot_price`, `buy_price`) VALUES "
                        + "( '" + ponid.getText() + "', '" + row_id + "', '" + data.get(i).getType() + "', '" + data.get(i).getAmout() + "', '" + data.get(i).getPrice() + "', '" + data.get(i).getTotal() + "', '" + data.get(i).getPrice() + "');";
                System.out.println(sql1);

                db.statemen.executeUpdate(sql1);

            }
            countReport();
            newOrder();

            myController.setScreen(Samba.MainyId);

        }
    }

    @FXML
    public void CloseScene() {

        myController.setScreen(Samba.MainyId);

    }

    @FXML
    public void finishorder() throws SQLException {

        String sql = "update sell_bills set  T_S = '0'  where id = '" + row_id + "'";
        System.out.println(sql);
        db.statemen.executeUpdate(sql);
         sql = "update `table`  set  status = '0'  where id = '" +  tDelivery.getEmp_name()+ "'";
         System.out.println(sql);
        db.statemen.executeUpdate(sql);
        countReport();
        newOrder();
        myController.setScreen(Samba.MainyId);
    }
}
