package hellofx;

import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ContohFXML extends Application implements Initializable {
    @FXML
    private TextField txtName;

    @FXML
    private Button btnClick;

    @FXML
    private Label lblHello;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ContohFXML.fxml"));


        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Contoh FXML");
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // event handler by reference to method
        btnClick.setOnAction(this::btnClickClicked);
    }

    @FXML
    private void btnClickClicked(Event event) {
        String name = txtName.getText();
        if (name.isEmpty()) name = "World";
        this.lblHello.setText(String.format("Hello %s", name));
        txtName.clear();
        txtName.requestFocus();
    }

    public static void main(String []args) {
        launch();
    }
}
