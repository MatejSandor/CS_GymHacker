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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ExerciseManager implements Initializable{

    @FXML
    private TableView<ExerciseContent> table;

    @FXML
    private TableColumn<ExerciseContent, String> nameColumn;

    @FXML
    private TableColumn<ExerciseContent, String> targetColumn;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField targetTextField;


    @FXML
    void addToTable(ActionEvent event) {
        ExerciseContent newExercise = new ExerciseContent(nameTextField.getText(),targetTextField.getText());
        table.getItems().add(newExercise);
        saveToFile();

    }

    @FXML
    void deleteFromTable(ActionEvent event) {
        ObservableList<ExerciseContent> selectedRow,allExercises;
        allExercises = table.getItems();
        selectedRow = table.getSelectionModel().getSelectedItems();

        for(ExerciseContent exerciseContent : selectedRow) { allExercises.remove(exerciseContent);}
    }

    public void changeNameCellEvent(TableColumn.CellEditEvent edittedCell)
    {
        ExerciseContent exerciseSelected =  table.getSelectionModel().getSelectedItem();
        exerciseSelected.setName(edittedCell.getNewValue().toString());
    }

    public void changeTargetCellEvent(TableColumn.CellEditEvent edittedCell)
    {
        ExerciseContent targetSelected =  table.getSelectionModel().getSelectedItem();
        targetSelected.setTarget(edittedCell.getNewValue().toString());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        targetColumn.setCellValueFactory(new PropertyValueFactory<>("target"));
        table.setItems(getExercises());
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        table.setEditable(true);
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        targetColumn.setCellFactory(TextFieldTableCell.forTableColumn());


    }

    private ObservableList<ExerciseContent> getExercises() {
        ObservableList<ExerciseContent> exercises = FXCollections.observableArrayList();
        return exercises;
    }

    private void saveToFile() {
        BufferedWriter bw = null;
        try {
            String ExerciseName = this.nameTextField.getText();
            String TargetName = this.targetTextField.getText();
            File file = new File("C:\\Users\\matej\\GymHacker\\TextFiles\\exercises.txt");

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
