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
public class TFullCashier {
    
    public SimpleStringProperty id;
    public SimpleStringProperty parcode;
    public SimpleStringProperty name;
    public SimpleStringProperty amount;

    public SimpleStringProperty pricebuy;
    public SimpleStringProperty total;
    private SimpleStringProperty pricesell;
 

 public TFullCashier(String id,String parcode, String name, String amount, String pricebuy, String total,String pricesell) {
            this.id = new SimpleStringProperty(id);
            this.parcode = new SimpleStringProperty(parcode);
            this.name = new SimpleStringProperty(name);
            this.amount = new SimpleStringProperty(amount);
           
            this.pricebuy = new SimpleStringProperty(pricebuy);
             this.total = new SimpleStringProperty(total);
             this.pricesell = new SimpleStringProperty(pricesell);
            
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
    public String getTotal() {
        return total.get();
    }


    public void setTotal(String total) {
        this.total.set(total);
    }

    /**
     * @return the pricebuy
     */
    public String getPricebuy() {
        return pricebuy.get();
    }

 
    public void setPricebuy(String pricebuy) {
        this.pricebuy.set(pricebuy);
    }

    /**
     * @return the pricesell
     */
    public String getPricesell() {
        return pricesell.get();
    }

    /**
     * @param pricesell the pricesell to set
     */
    public void setPricesell(String pricesell) {
        this.pricesell.set(pricesell); 
    }


 

}
