package BackEnd;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenu{

    private static MainMenu instance;

    public MainMenu() {
        instance = this;
    }

    public static MainMenu getInstance() {
        return instance;
    }

    @FXML
    private AnchorPane defaultPane;

    @FXML
    void loadExerciseScreen(javafx.event.ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/FrontEnd/ExerciseManager.fxml"));
        defaultPane.getChildren().setAll(pane);
    }

    @FXML
    void loadWorkoutsScreen(javafx.event.ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/FrontEnd/Workout.fxml"));
        defaultPane.getChildren().setAll(pane);
    }

    @FXML
    void loadOtherScreen(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/FrontEnd/OtherScreen.fxml"));
        defaultPane.getChildren().setAll(pane);
    }

    @FXML
    void closeApplication(ActionEvent event) throws IOException {
        Stage windowQuit = new Stage();
        windowQuit.initModality(Modality.APPLICATION_MODAL);
        AnchorPane alert = FXMLLoader.load(getClass().getResource("/FrontEnd/QuitApplication.fxml"));
        windowQuit.setScene(new Scene(alert));
        windowQuit.show();

    }

}
