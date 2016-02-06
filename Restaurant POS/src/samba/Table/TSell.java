/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package samba.Table;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TSell {

    private SimpleStringProperty id;
    private SimpleStringProperty delivery;
    private SimpleStringProperty del_S;
    private SimpleStringProperty bill_no;
    private SimpleStringProperty cus_name;
    private SimpleStringProperty T_name;
    private SimpleStringProperty T_S;
    private SimpleStringProperty tot_price;
    private SimpleStringProperty bill_date;
    private SimpleStringProperty bill_Time;
    private SimpleStringProperty user_name;

    /**
     * @return the id
     */
    public String getId() {
        return id.get();
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id.set(id);
    }

    /**
     * @return the delivery
     */
    public String getDelivery() {
        return delivery.get();
    }

    /**
     * @param delivery the delivery to set
     */
    public void setDelivery(String delivery) {
        this.delivery.set(delivery);
    }

    /**
     * @return the del_S
     */
    public String getDel_S() {
        return del_S.get();
    }

    /**
     * @param del_S the del_S to set
     */
    public void setDel_S(String del_S) {
        this.del_S.set(del_S);
    }

    /**
     * @return the bill_no
     */
    public String getBill_no() {
        return bill_no.get();
    }

    /**
     * @param bill_no the bill_no to set
     */
    public void setBill_no(String bill_no) {
        this.bill_no.set(bill_no);
    }

    /**
     * @return the cus_name
     */
    public String getCus_name() {
        return cus_name.get();
    }

    /**
     * @param cus_name the cus_name to set
     */
    public void setCus_name(String cus_name) {
        this.cus_name.set(cus_name);
    }

    /**
     * @return the T_name
     */
    public String getT_name() {
        return T_name.get();
    }

    /**
     * @param T_name the T_name to set
     */
    public void setT_name(String T_name) {
        this.T_name.set(T_name);
    }

    /**
     * @return the T_S
     */
    public String getT_S() {
        return T_S.get();
    }

    /**
     * @param T_S the T_S to set
     */
    public void setT_S(String T_S) {
        this.T_S.set(T_S);
    }

    /**
     * @return the tot_price
     */
    public String getTot_price() {
        return tot_price.get();
    }

    /**
     * @param tot_price the tot_price to set
     */
    public void setTot_price(String tot_price) {
        this.tot_price.set(tot_price);
    }

    /**
     * @return the bill_date
     */
    public String getBill_date() {
        return bill_date.get();
    }

    /**
     * @param bill_date the bill_date to set
     */
    public void setBill_date(String bill_date) {
        this.bill_date.set(bill_date);
    }

    /**
     * @return the bill_Time
     */
    public String getBill_Time() {
        return bill_Time.get();
    }

    /**
     * @param bill_Time the bill_Time to set
     */
    public void setBill_Time(String bill_Time) {
        this.bill_Time.set(bill_Time);
    }

    /**
     * @return the user_name
     */
    public String getUser_name() {
        return user_name.get();
    }

    /**
     * @param user_name the user_name to set
     */
    public void setUser_name(String user_name) {
        this.user_name.set(user_name);
    }

  
    
}
