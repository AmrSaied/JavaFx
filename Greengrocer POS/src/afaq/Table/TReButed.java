/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afaq.Table;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author AmrSaid
 */
public class TReButed {
         private SimpleStringProperty id;
         private SimpleStringProperty parcode;
    private SimpleStringProperty name;
    private SimpleStringProperty amount;
    private SimpleStringProperty buyprices;
 
    private SimpleStringProperty Customer;
 
    private SimpleStringProperty total;
    private SimpleStringProperty databill;
 

 public TReButed(String id,String parcode,String name, String Customer,String amount, String buyprices, String total, String databill) {
            this.id = new SimpleStringProperty(id);
            this.amount = new SimpleStringProperty(amount);
            this.total = new SimpleStringProperty(total);
           
            this.buyprices = new SimpleStringProperty(buyprices);
            this.name = new SimpleStringProperty(name);
            this.databill = new SimpleStringProperty(databill);
            this.parcode = new SimpleStringProperty(parcode);
            this.Customer = new SimpleStringProperty(Customer);
            
        }

    public String getId() {
        return id.get();
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getParcode() {
        return parcode.get();
    }

    public void setParcode(String parcode) {
        this.parcode.set(parcode);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getAmount() {
        return amount.get();
    }

    public void setAmount(String amount) {
        this.amount.set(amount);
    }

    public String getBuyprices() {
        return buyprices.get();
    }

    public void setBuyprices(String buyprices) {
        this.buyprices.set(buyprices);
    }

    public String getCustomer() {
        return Customer.get();
    }

    public void setCustomer(String Customer) {
        this.Customer.set(Customer);
    }

    public String getTotal() {
        return total.get();
    }

    public void setTotal(String total) {
        this.total.set(total);
    }

    public String getDatabill() {
        return databill.get();
    }

    public void setDatabill(String databill) {
        this.databill.set(databill);
    }

 
}
