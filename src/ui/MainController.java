package ui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.web.WebView;

public class MainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private MenuItem renderBtn;

    @FXML
    void initialize() {
        assert renderBtn != null : "fx:id=\"renderBtn\" was not injected: check your FXML file 'note_main.fxml'.";


    }
}
