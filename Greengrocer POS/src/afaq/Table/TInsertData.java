/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afaq.Table;

import java.util.Collection;
import java.util.Vector;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author AmrSaid
 */
public class TInsertData {
    
    private SimpleStringProperty id;
    private SimpleStringProperty parcode;
    private SimpleStringProperty name;

    private SimpleStringProperty pricesell;
    private SimpleStringProperty pricebuy;
    private SimpleStringProperty EXP_Data;
    private SimpleStringProperty QTY;
    private SimpleStringProperty MIN_QTY;
    
 

 public TInsertData(String id,String parcode, String name, String pricesell, String pricebuy,String EXP_Data,String QTY,String MIN_QTY) {
            this.id = new SimpleStringProperty(id);
            this.parcode = new SimpleStringProperty(parcode);
            this.name = new SimpleStringProperty(name);
        
            this.pricesell = new SimpleStringProperty(pricesell);
            this.pricebuy = new SimpleStringProperty(pricebuy);
            this.EXP_Data = new SimpleStringProperty(EXP_Data);
            this.QTY = new SimpleStringProperty(QTY);
            this.MIN_QTY = new SimpleStringProperty(MIN_QTY);
            
        }

    public String getEXP_Data() {
        return EXP_Data.get();
    }

    public void setEXP_Data(String EXP_Data) {
        this.EXP_Data.set(EXP_Data);
    }

    public String getQTY() {
        return QTY.get();
    }

    public void setQTY(String QTY) {
        this.QTY.set(QTY);
    }

    public String getMIN_QTY() {
        return MIN_QTY.get();
    }

    public void setMIN_QTY(String MIN_QTY) {
        this.MIN_QTY.set(MIN_QTY);
    }

 
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
