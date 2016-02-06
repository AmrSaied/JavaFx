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
public class TBillCashier {
       private SimpleStringProperty id;
    private SimpleStringProperty billNumber;
    private SimpleStringProperty total;
    private SimpleStringProperty buyprices;
    private SimpleStringProperty reprices;
    private SimpleStringProperty databill;
 

public  TBillCashier(String id,String billNumber, String total, String buyprices, String reprices, String databill) {
            this.id = new SimpleStringProperty(id);
            this.billNumber = new SimpleStringProperty(billNumber);
            this.total = new SimpleStringProperty(total);
            this.reprices = new SimpleStringProperty(reprices);
            this.buyprices = new SimpleStringProperty(buyprices);
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
    public String getBillNumber() {
        return billNumber.get();
    }

    /**
     * @param billNumber the billNumber to set
     */
    public void setBillNumber(String billNumber) {
        this.billNumber.set(billNumber);
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
    public String getReprices() {
        return reprices.get();
    }

    /**
     * @param reprices the reprices to set
     */
    public void setReprices(String reprices) {
        this.reprices.set(reprices);
    }

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
