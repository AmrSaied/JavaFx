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
public class TInsertServices {
           private SimpleStringProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty price;
    
 TInsertServices(String id,String name, String price) {
            this.id = new SimpleStringProperty(id);
            this.name = new SimpleStringProperty(name);
            this.price = new SimpleStringProperty(price);
          
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
