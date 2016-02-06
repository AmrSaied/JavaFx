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
public class TCashierService {
        private SimpleStringProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty price;
    private SimpleStringProperty note;
    private SimpleStringProperty amount;
    
 public TCashierService(String id,String name, String price,String note,String amount) {
            this.id = new SimpleStringProperty(id);
            this.name = new SimpleStringProperty(name);
            this.price = new SimpleStringProperty(price);
            this.amount = new SimpleStringProperty(amount);
            this.note = new SimpleStringProperty(note);
          
        }

    public String getNote() {
        return note.get();
    }

    public void setNote(String note) {
        this.note.set(note);
    }

    public String getAmount() {
        return amount.get();
    }

    public void setAmount(String amount) {
        this.amount.set(amount);
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
     * @return the price
     */
    public String getPrice() {
        return price.get();
    }

    /**
     * @param price the price to set
     */
    public void setPrice(String price) {
        this.price.set(price);
    }
 
}
