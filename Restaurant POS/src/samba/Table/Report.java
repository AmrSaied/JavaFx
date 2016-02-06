/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package samba.Table;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author AmrSaid
 */
public class Report {
    private SimpleIntegerProperty id;
    private SimpleStringProperty type;
    private SimpleDoubleProperty price;
    private SimpleStringProperty amout;
    private SimpleDoubleProperty total;

 public Report(int id,String type, double price, String amout,double total) {
            this.type = new SimpleStringProperty(type);
            this.price = new SimpleDoubleProperty(price);
            this.amout = new SimpleStringProperty(amout);
            this.total = new SimpleDoubleProperty(total);
            this.id = new SimpleIntegerProperty(id);
            
        }


 
    public String getType() {
        return type.get();
    }


    public void setType(String type) {
         this.type.set(type);
    }


    public double getPrice() {
        return price.get();
    }


    public void setPrice(double price) {
        this.price.set(price);
    }

 
    public String getAmout() {
      
        return amout.get();
    }

    public void setAmout(String amout) {
        this.amout.set( amout);
    }

    public double getTotal() {
        return total.get();
    }

 
    public void setTotal(double total) {
        this.total.set(total);
    }

    /**
     * @return the id
     */
    public int getId() {
        return id.get();
    }

  
}
