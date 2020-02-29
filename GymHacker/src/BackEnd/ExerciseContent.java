package BackEnd;

import javafx.beans.property.SimpleStringProperty;

public class ExerciseContent {

    private SimpleStringProperty name;
    private SimpleStringProperty target;

    ExerciseContent(String name, String target) {
        this.name = new SimpleStringProperty(name);
        this.target = new SimpleStringProperty(target);
    }

    public String getName() { return name.get(); }

    public SimpleStringProperty nameProperty() { return name; }

    void setName(String name) { this.name.set(name); }

    public String getTarget() { return target.get(); }

    public SimpleStringProperty targetProperty() { return target; }

    public void setTarget(String target) { this.target.set(target); }



}
