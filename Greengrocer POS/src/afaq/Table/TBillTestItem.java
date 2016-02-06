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
public class TBillTestItem {
       private SimpleStringProperty id;
    private SimpleStringProperty parcode;
    private SimpleStringProperty name;
    private SimpleStringProperty amount;
    
    private SimpleStringProperty pricebuy;
    private SimpleStringProperty totall;
 

 TBillTestItem(String id,String parcode, String name, String amount, String pricebuy,String totall) {
            this.id = new SimpleStringProperty(id);
            this.parcode = new SimpleStringProperty(parcode);
            this.name = new SimpleStringProperty(name);
            this.amount = new SimpleStringProperty(amount);
            this.pricebuy = new SimpleStringProperty(pricebuy);
            this.totall = new SimpleStringProperty(totall);
            
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
     * @return the parcode
     */
    public String getParcode() {
        return parcode.get();
    }

    /**
     * @param parcode the parcode to set
     */
    public void setParcode(String parcode) {
        this.parcode.set(parcode);
    }

    /**
     * @return the name
     */
    public String getName() {
        return name.get();
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name.set(name);
    }

    /**
     * @return the amount
     */
    public String getAmount() {
        return amount.get();
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(String amount) {
        this.amount.set(amount);
    }

    /**
     * @return the pricesell
     */
    public String gettotall() {
        return totall.get();
    }

    /**
     * @param pricesell the pricesell to set
     */
    public void settotall(String totall) {
        this.totall.set(totall);
    }

    /**
     * @return the pricebuy
     */
    public String getPricebuy() {
        return pricebuy.get();
    }

    /**
     * @param pricebuy the pricebuy to set
     */
    public void setPricebuy(String pricebuy) {
        this.pricebuy.set(pricebuy);
    }


 

}
