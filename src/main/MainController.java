package main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;

import java.awt.*;
import java.io.*;

public class MainController implements ControllerCommonInterface {

    FileChooser fileChooser;
    Desktop desktop;
    @FXML
    private MenuItem renderBtn;
    private ApplicationInterface application;
    private Editor editor;

    {
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("markdown file", "*.md"));
        desktop = Desktop.getDesktop(); //used to open file uri with desktop default programs
    }

    @FXML
    void initialize() {
        System.out.println("enter init()");
        assert renderBtn != null : "fx:id=\"renderBtn\" was not injected: check your FXML file 'note_main.fxml'.";
        System.out.println("end init()");
    }

    //========the following are handlers for menu items

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

    public void renderBtnAction(ActionEvent ae) {
        //case the engin to IORenderEngin to renderToSurface on renderToSurface() call
        RenderEngine.IORenderEngin engine = (RenderEngine.IORenderEngin) application.getEngine();
        String s = engine.renderToSurface();
        System.out.println(s);
        return;
    }

    public void OpenFileFromDir(ActionEvent ae) {

        File file = fileChooser.showOpenDialog(application.getStage());
        if (file != null) {
            //read from file then create a new content from file, then set the content
            application.getEditor().setContent(
                    new ContentObject().setContentText(readFile(file))
            );
        }
    }

    private String readFile(File file) {
        StringBuilder sb = new StringBuilder("");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append("\\n");
            }
            AppUtils.beautifyStringForCodeMirror(sb);

        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return sb.toString();
    }



    //TODO unimplemented
    public void exportToHTML(ActionEvent ae) {

    }

    //TODO unimplemented
    public void exitEditor(ActionEvent ae) {

    }

    //TODO unimplemented
    public void saveToFile(ActionEvent ae) {
        File file = fileChooser.showSaveDialog(application.getStage());
        if (file != null) {
            Platform.runLater(() -> {
                ContentObject c = editor.getContent();
                String content = c.getContentText();
                AppUtils.saveToFile(file, content);
            });
        }
    }

}
