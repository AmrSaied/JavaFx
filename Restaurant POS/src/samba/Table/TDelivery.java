/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package samba.Table;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author AMR SAID
 */
public class TDelivery {

    private SimpleStringProperty id;
    private SimpleStringProperty pon_id;
    private SimpleStringProperty Emp_name;
    private SimpleStringProperty cus_name;
    private SimpleStringProperty Address;
    private SimpleStringProperty tel;
    private SimpleStringProperty tot_price;
    private SimpleStringProperty user_name;

    public TDelivery(String id, String pon_id, String Emp_name, String cus_name, String Address, String tel, String tot_price,String user_name) {
        this.id = new SimpleStringProperty(id);
        this.pon_id = new SimpleStringProperty(pon_id);
        this.Emp_name = new SimpleStringProperty(Emp_name);
        this.cus_name = new SimpleStringProperty(cus_name);
        this.Address = new SimpleStringProperty(Address);
        this.tel = new SimpleStringProperty(tel);
        this.tot_price = new SimpleStringProperty(tot_price);
        this.user_name = new SimpleStringProperty(user_name);
    }

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
     * @return the pon_id
     */
    public String getPon_id() {
        return pon_id.get();
    }

    /**
     * @param pon_id the pon_id to set
     */
    public void setPon_id(String pon_id) {
        this.pon_id.set(pon_id);
    }

    /**
     * @return the Emp_name
     */
    public String getEmp_name() {
        return Emp_name.get();
    }

    /**
     * @param Emp_name the Emp_name to set
     */
    public void setEmp_name(String Emp_name) {
        this.Emp_name.set(Emp_name);
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
     * @return the Address
     */
    public String getAddress() {
        return Address.get();
    }

    /**
     * @param Address the Address to set
     */
    public void setAddress(String Address) {
        this.Address.set(Address);
    }

    /**
     * @return the tel
     */
    public String getTel() {
        return tel.get();
    }

    /**
     * @param tel the tel to set
     */
    public void setTel(String tel) {
        this.tel.set(tel);
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
