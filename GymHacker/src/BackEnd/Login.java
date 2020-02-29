package BackEnd;

import BackEnd.Other.Security;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Login extends Application implements Initializable {
    private Stage window;

    @FXML
    private Label lblErrors;

    @FXML
    private AnchorPane defaultPane;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Button logInButton;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button closingButton;


    private Connection con = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public static void main(String args[]) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        DataManager dataManager = new DataManager();
        window = primaryStage;
        window.setTitle("GymHacker");
        Parent root = FXMLLoader.load(getClass().getResource("/FrontEnd/Login.fxml"));
        root.getStylesheets().add(getClass().getResource("/FrontEnd/style.css").toExternalForm());
        Scene scene = new Scene(root, 675, 400);
        window.setScene(scene);
        window.show();


    }

    @FXML
    void loadMainMenu(ActionEvent event) throws IOException {
        if (logIn().equals("Success")) {
            try {
                AnchorPane pane = FXMLLoader.load(getClass().getResource("/FrontEnd/MainMenu.fxml"));
                rootPane.getChildren().setAll(pane);

            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }

    }

    @FXML
    void closeApplication(ActionEvent event) throws IOException {
        Stage windowQuit = new Stage();
        windowQuit.initModality(Modality.APPLICATION_MODAL);
        AnchorPane alert = FXMLLoader.load(getClass().getResource("/FrontEnd/QuitApplication.fxml"));
        windowQuit.setScene(new Scene(alert));
        windowQuit.show();

    }




    private String logIn() {
        String status = "Success";
        String email = usernameField.getText();
        String password = passwordField.getText();
        if(email.isEmpty() || password.isEmpty()) {
            status = "Error";
        } else {
            //query
            String sql = "SELECT * FROM login Where email = ? and password = ?";
            try {
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);
                resultSet = preparedStatement.executeQuery();
                if (!resultSet.next()) {
                    setLblError(Color.TOMATO, "Enter Correct Email/Password");
                    status = "Error";
                } else {
                    setLblError(Color.GREEN, "Login Successful..Redirecting..");
                }

            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                status = "Exception";
            }
        }

        return status;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (con == null) {
            lblErrors.setTextFill(Color.TOMATO);
            lblErrors.setText("Server Error : Check");
        } else {
            lblErrors.setTextFill(Color.GREEN);
            lblErrors.setText("Server is up : Good to go");
        }
    }

    public Login() {
        con = Security.conDB();
    }

    private void setLblError(Color color, String text) {
        lblErrors.setTextFill(color);
        lblErrors.setText(text);
        System.out.println(text);
    }
}


