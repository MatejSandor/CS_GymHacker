package BackEnd;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;

public class WorkoutEditor implements Initializable {

    Workout workout = new Workout();

    private static WorkoutEditor instance;

    @FXML
    TableView<ExerciseContent> tableWorkout;

    @FXML
    private TableColumn<ExerciseContent, String > nameColumn;

    @FXML
    private TableColumn<ExerciseContent, String> targetColumn;

    @FXML
    void addExercise(ActionEvent event) throws IOException {
        Stage windowExercises = new Stage();
        AnchorPane alert = FXMLLoader.load(getClass().getResource("/FrontEnd/DataManager.fxml"));
        alert.getStylesheets().add(getClass().getResource("/FrontEnd/style.css").toExternalForm());
        windowExercises.setScene(new Scene(alert));
        windowExercises.show();

    }

    @FXML
    void saveWorkout(ActionEvent event) throws ParseException {
        Label workoutLabel = new Label("");
        workoutLabel.getStylesheets().add(getClass().getResource("/FrontEnd/style.css").toExternalForm());

        try {
            MainMenu.getInstance().loadWorkoutsScreen(null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Workout.getInstance().rootPane.getChildren().add(workoutLabel);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance = this;
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        targetColumn.setCellValueFactory(new PropertyValueFactory<>("target"));
        tableWorkout.setItems(getWorkoutExercises());
        tableWorkout.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    }


    private ObservableList<ExerciseContent> getWorkoutExercises() {
        ObservableList<ExerciseContent> workoutExercises = FXCollections.observableArrayList();
        return workoutExercises;
    }

    static WorkoutEditor getInstance() {
        return instance;
    }





}
