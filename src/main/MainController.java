package main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

public class MainController implements ControllerCommonInterface {

    @FXML
    private MenuItem renderBtn;
    private MainApplication application;
    private RenderSurfaceInterface displaySurface;
    private EditorInterface editor;

    @FXML
    void initialize() {
        System.out.println("enter init()");
        assert renderBtn != null : "fx:id=\"renderBtn\" was not injected: check your FXML file 'note_main.fxml'.";
        System.out.println("end init()");
    }

    public void renderBtnAction(ActionEvent ae) {
        displaySurface.display(editor.getContent().getEditorText());
    }

    @Override
    public void setControllerParentApplication(Application application) {
        assert application != null : "argument cannot be null";
        assert application instanceof MainApplication : "given app is not an instance of MainApplication";
        this.application = (MainApplication) application;
    }

    @Override
    public void postInit() {
        displaySurface = application.getDisplaySurface();
        editor = application.getEditor();

        assert displaySurface != null : "displaySUreface is null when initializing ";
        assert editor != null : "editor is null when initializing ";
    }
}
