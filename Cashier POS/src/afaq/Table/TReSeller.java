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
public class TReSeller {
    
         private SimpleStringProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty amount;
    private SimpleStringProperty buyprices;
 
    private SimpleStringProperty total;
    private SimpleStringProperty databill;
 

 TReSeller(String id,String name, String amount, String buyprices, String total, String databill) {
            this.id = new SimpleStringProperty(id);
            this.amount = new SimpleStringProperty(amount);
            this.total = new SimpleStringProperty(total);
           
            this.buyprices = new SimpleStringProperty(buyprices);
            this.name = new SimpleStringProperty(name);
            this.databill = new SimpleStringProperty(databill);
            
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
     * @return the billNumber
     */
    public String getname() {
        return name.get();
    }

    /**
     * @param billNumber the billNumber to set
     */
    public void setBillNumber(String name) {
        this.name.set(name);
    }

    /**
     * @return the total
     */
    public String getTotal() {
        return total.get();
    }

    /**
     * @param total the total to set
     */
    public void setTotal(String total) {
        this.total.set(total);
    }

    /**
     * @return the buyprices
     */
    public String getBuyprices() {
        return buyprices.get();
    }

    /**
     * @param buyprices the buyprices to set
     */
    public void setBuyprices(String buyprices) {
        this.buyprices.set(buyprices);
    }

    /**
     * @return the reprices
     */


    /**
     * @return the databill
     */
    public String getDatabill() {
        return databill.get();
    }

    /**
     * @param databill the databill to set
     */
    public void setDatabill(String databill) {
        this.databill.set(databill);
    }
}
