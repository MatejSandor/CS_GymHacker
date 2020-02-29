package BackEnd;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Workout {

    public Workout() {
        instance = this;
    }

    private static Workout instance;

    @FXML
    AnchorPane rootPane;

    @FXML
    void loadWorkoutEditor(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/FrontEnd/WorkoutEditor.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    static Workout getInstance() {
        return instance;
    }

}
