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
public class TDefineCustomer {
     private SimpleStringProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty Adress;
    private SimpleStringProperty phone;
    private SimpleStringProperty note;
    
 public TDefineCustomer(String id,String name, String Adress,String phone,String note) {
            this.id = new SimpleStringProperty(id);
            this.name = new SimpleStringProperty(name);
            this.Adress = new SimpleStringProperty(Adress);
            this.phone = new SimpleStringProperty(phone);
            this.note = new SimpleStringProperty(note);
          
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
    public void setId( String id) {
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
    public void setName( String name) {
        this.name.set(name);
    }

    /**
     * @return the Adress
     */
    public String getAdress() {
        return Adress.get();
    }

    /**
     * @param Adress the Adress to set
     */
    public void setAdress( String Adress) {
        this.Adress.set(Adress);
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone.get();
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone( String phone) {
        this.phone.set(phone);
    }

    /**
     * @return the note
     */
    public String getNote() {
        return note.get();
    }

    /**
     * @param note the note to set
     */
    public void setNote( String note) {
        this.note.set(note);
    }
}
