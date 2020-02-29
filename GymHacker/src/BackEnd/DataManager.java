package BackEnd;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DataManager implements Initializable {
    WorkoutEditor workoutEditor = new WorkoutEditor();

    @FXML
    private TableView<ExerciseContent> tableExercises;

    @FXML
    private TableColumn<ExerciseContent, String > nameColumn;

    @FXML
    private TableColumn<ExerciseContent, String> targetColumn;

    @FXML
    void addToTable(ActionEvent event) {
        String data = tableData();
        ExerciseContent newExercise = new ExerciseContent(data,"CHEST");
        WorkoutEditor.getInstance().tableWorkout.getItems().add(newExercise);
        saveToFile();

    }

    String tableData() {
        TablePosition pos = (TablePosition) tableExercises.getSelectionModel().getSelectedCells().get(0);
        int index = pos.getRow();
        ExerciseContent item = tableExercises.getItems().get(index);
        TableColumn col = pos.getTableColumn();
        String dataPicked = (String) col.getCellObservableValue(item).getValue();
        return dataPicked;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        targetColumn.setCellValueFactory(new PropertyValueFactory<>("target"));
        tableExercises.setItems(getExercisesPicker());
        tableExercises.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    }


    private ObservableList<ExerciseContent> getExercisesPicker() {
        ObservableList<ExerciseContent> exercisesPick = FXCollections.observableArrayList();
        exercisesPick.add(new ExerciseContent("Dumbbell Bench Press","CHEST"));
        exercisesPick.add(new ExerciseContent("Military Press","SHOULDERS"));
        exercisesPick.add(new ExerciseContent("Barbell Bench Press","CHEST"));
        exercisesPick.add(new ExerciseContent("Deadlift","BACK"));
        return exercisesPick;
    }

    private void saveToFile() {
        BufferedWriter bw = null;
        try {
            String ExerciseName = tableData();
            String TargetName = "PLACEHOLDER";
            File file = new File("C:\\Users\\matej\\GymHacker\\TextFiles\\workout.txt");

            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file,true);
            bw = new BufferedWriter(fw);
            bw.append(ExerciseName + " ");
            bw.append(TargetName + "\n");
            System.out.println("File written Successfully");

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        finally
        {
            try{
                if(bw!=null)
                    bw.close();
            }catch(Exception ex){
                System.out.println("Error in closing the BufferedWriter"+ex);
            }
        }
    }




}
