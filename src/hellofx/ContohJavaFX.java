/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hellofx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author david
 */
public class ContohJavaFX extends Application {
    private TextField txtName;
    private Label lblHello;
    private Button btnHello;

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
	    launch();
	}

    @Override
    public void start(Stage stage) throws Exception {
	    txtName = new TextField();
	    lblHello = new Label();
	    btnHello = new Button("Hello");

	    // set event handler
	    btnHello.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                sayHello();
            }
        });

	    // set event handler using java 8 lambda
        txtName.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                sayHello();
            }
        });

	    // container
        VBox box = new VBox();
        box.setSpacing(10.0);
        box.setPadding(new Insets(10, 10, 10, 10));
        box.getChildren().add(txtName);
        box.getChildren().add(btnHello);
        box.getChildren().add(lblHello);

	    Scene scene = new Scene(box);

	    stage.setScene(scene);
	    stage.setTitle("Hello!");
	    stage.show();
    }

    private void sayHello() {
	    String name = this.txtName.getText();
	    if (name.isEmpty()) name = "world";
	    lblHello.setText(String.format("Hello %s", name));
	    txtName.setText("");
	    txtName.requestFocus();
    }
}
