package main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

public class MainController implements ControllerCommonInterface {

    @FXML
    private MenuItem renderBtn;
    private ApplicationInterface application;
    private EditorInterface editor;

    @FXML
    void initialize() {
        System.out.println("enter init()");
        assert renderBtn != null : "fx:id=\"renderBtn\" was not injected: check your FXML file 'note_main.fxml'.";
        System.out.println("end init()");
    }

    public void renderBtnAction(ActionEvent ae) {
        application.getEngine().render(editor.getContent().getEditorText());
    }

    @Override
    public void setControllerParentApplication(Application application) {
        assert application != null : "argument cannot be null";
        assert application instanceof MainApplication : "given app is not an instance of MainApplication";
        this.application = (MainApplication) application;
    }

    @Override
    public void postInit() {
        editor = application.getEditor();
        assert editor != null : "editor is null when initializing ";
    }
}
