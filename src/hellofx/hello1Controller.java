/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hellofx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

/**
 *
 * @author Student
 */

public class hello1Controller implements Initializable {

    @FXML
    private TextField txtNama;
    @FXML
    private Button btn;
    @FXML
    private Label txtLabel;
    
    @Override
    public void initialize (URL url, ResourceBundle rb) {
    }
    
    @FXML
    private void btnOnAction(ActionEvent event){
    String nama = txtNama.getText();
    if (nama.equals("")) nama = "World";
    txtLabel.setText("Hello " + nama);
    
    
    }

}
