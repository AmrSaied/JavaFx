/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afaq.Controller;

import afaq.ControlledScreen;
import afaq.ScreensController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AmrSaid
 */
public class NewBarCodeController implements Initializable, ControlledScreen {

    ScreensController myController;
    @FXML
    TextField Barcode;
  @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
      @FXML
    public void CloseScene() {
       ScreensController.p3 = false;
        Stage stage = (Stage) Barcode.getScene().getWindow();
        stage.close();
    }

    
}
