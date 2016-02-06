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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AmrSaid
 */
public class DeleteAllController implements Initializable, ControlledScreen {

 ScreensController myController;
  @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
      
    public void CloseScene() {
        ScreensController.p9 = false;
//        Stage stage = (Stage) TFAdrees.getScene().getWindow();
//        stage.close();
    }
    
}
