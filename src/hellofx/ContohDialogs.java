package hellofx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ContohDialogs extends Application {
    CheckBox modal;

    @Override
    public void start(Stage stage) throws Exception {
        List<String> labels = Arrays.asList(
                "Information Dialog", "Information Dialog Without Header",
                "Warning Dialog", "Error Dialog", "Exception Dialog",
                "Confirmation Dialog", "Confirmation Dialog With Custom Action",
                "Text Input Dialog", "Choice Dialog", "Login Dialog"
        );
        Map<String, Button> btns = new HashMap<>();

        labels.forEach(label -> btns.put(label, new Button(label)));
        btns.get("Information Dialog").setOnAction(makeDialog(Alert.AlertType.INFORMATION,
                "Information Dialog",
                "This is a information dialog",
                "Hello this is an information"));

        btns.get("Information Dialog Without Header").setOnAction(makeNoHeaderDialog(Alert.AlertType.INFORMATION,
                "Information Dialog no header", "This is an information"));

        btns.get("Warning Dialog").setOnAction(makeNoHeaderDialog(Alert.AlertType.WARNING,
                "Warning Dialog", "This is a warning"));

        btns.get("Error Dialog").setOnAction(makeNoHeaderDialog(Alert.AlertType.ERROR,
                "Error Dialog", "This is an error"));

        btns.get("Exception Dialog").setOnAction(this::exceptionDialog);

        btns.get("Confirmation Dialog").setOnAction(this::OKnotOKDialog);

        btns.get("Confirmation Dialog With Custom Action").setOnAction(this::customConfirmation);

        btns.get("Text Input Dialog").setOnAction(this::textInputDialog);

        btns.get("Choice Dialog").setOnAction(this::choiceDialog);

        btns.get("Login Dialog").setOnAction(this::loginDialog);

        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10, 10, 10, 10));

        labels.forEach(lbl -> vbox.getChildren().add(btns.get(lbl)));

        modal = new CheckBox("Modal");
        modal.setIndeterminate(false);

        vbox.getChildren().add(modal);

        Scene scene = new Scene(vbox);
        stage.setScene(scene);
        stage.setTitle("Contoh dialog");
        stage.show();
    }

    private Modality isModal() {
        return modal.isSelected() ? Modality.APPLICATION_MODAL : Modality.NONE;
    }

    private EventHandler<ActionEvent> makeDialog(Alert.AlertType type, String title,
                                                 String header, String content) {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Alert alert = new Alert(type);
                alert.setTitle(title);
                alert.setHeaderText(header);
                alert.setContentText(content);
                alert.initModality(isModal());
                alert.showAndWait();
            }
        };
    }

    private EventHandler<ActionEvent> makeNoHeaderDialog(Alert.AlertType type, String title,
                                                         String content) {
        return makeDialog(type, title, null, content);
    }

    private void OKnotOKDialog(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation dialog");
        alert.setHeaderText(null);
        alert.setContentText("Are you ok");
        alert.initModality(isModal());

        Optional<ButtonType> confirmation = alert.showAndWait();

        if (confirmation.orElse(ButtonType.CANCEL) == ButtonType.OK) {
            System.out.println("OK");
        } else {
            System.out.println("Not OK");
        }
    }

    private void textInputDialog(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog("budi");

        dialog.setTitle("Text Input Dialog");
        dialog.setHeaderText("Hallo please tell me your name");
        dialog.setContentText("Name:");
        dialog.initModality(isModal());

        Optional<String> name = dialog.showAndWait();
        System.out.println("Hello " + name.orElse("world"));
    }

    private void choiceDialog(ActionEvent event) {
        List<String> choices = Arrays.asList("Pizza", "Burger", "Noodle");
        ChoiceDialog<String> choiceDialog = new ChoiceDialog<>("Pizza", choices);
        choiceDialog.setTitle("Choice dialog");
        choiceDialog.setHeaderText(null);
        choiceDialog.setContentText("Favourite food: ");
        choiceDialog.initModality(isModal());

        System.out.println(choiceDialog.showAndWait().orElse("None"));
    }

    private void customConfirmation(ActionEvent event) {
        ButtonType ABORT = new ButtonType("abort", ButtonBar.ButtonData.CANCEL_CLOSE);
        ButtonType CONTINUE = new ButtonType("continue");
        ButtonType RETRY = new ButtonType("retry");

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Progress halted");
        alert.setHeaderText(null);
        alert.setContentText("What to do?");
        alert.getButtonTypes().setAll(Arrays.asList(RETRY, CONTINUE, ABORT));
        alert.initModality(isModal());

        System.out.println(alert.showAndWait().orElse(ButtonType.CANCEL).getText());
    }

    private void exceptionDialog(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Exception Dialog");
        alert.setHeaderText("Look, an Exception Dialog");
        alert.setContentText("Could not find file blabla.txt!");
        alert.initModality(isModal());

        Exception ex = new FileNotFoundException("Could not find file blabla.txt");

        // Create expandable Exception.
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label label = new Label("The exception stacktrace was:");

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

        // Set expandable Exception into the dialog pane.
        alert.getDialogPane().setExpandableContent(expContent);

        alert.showAndWait();
    }

    private void loginDialog(ActionEvent event) {
        // Create the custom dialog.
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Login Dialog");
        dialog.setHeaderText("Look, a Custom Login Dialog");

        // Set the button types.
        ButtonType loginButtonType = new ButtonType("Login", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        // Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField username = new TextField();
        username.setPromptText("Username");
        PasswordField password = new PasswordField();
        password.setPromptText("Password");

        grid.add(new Label("Username:"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("Password:"), 0, 1);
        grid.add(password, 1, 1);

        // Enable/Disable login button depending on whether a username was entered.
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

        // Do some validation (using the Java 8 lambda syntax).
        username.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

        // Request focus on the username field by default.
        Platform.runLater(username::requestFocus);

        // Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(username.getText(), password.getText());
            }
            return null;
        });

        dialog.initModality(isModal());
        Optional<Pair<String, String>> result = dialog.showAndWait();

        result.ifPresent(usernamePassword -> {
            System.out.println("Username=" + usernamePassword.getKey() + ", Password=" + usernamePassword.getValue());
        });
    }

    public static void main(String[] args) {
        launch();
    }
}
