package BackEnd;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class QuitApplication {

    @FXML
    private AnchorPane windowQuit;

    @FXML
    void quit(ActionEvent event) {
        Platform.exit();
    }

}
