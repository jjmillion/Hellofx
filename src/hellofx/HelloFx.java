/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hellofx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Student
 */
public class HelloFx extends Application {

    @Override
    public void start(Stage primaryStage) {

        TextField txtName = new TextField();
        txtName.setText("");

        Label lblName = new Label();

        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                if (txtName.getText().equals("")) {
                    
                    System.out.println("Hello World");
                
                } else {

                    System.out.println("Hello " + txtName.getText());

                }
            }
        });

        ////
        VBox root = new VBox();
        root.getChildren().add(txtName);
        root.getChildren().add(btn);
        root.getChildren().add(lblName);
        ////
        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
