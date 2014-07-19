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

    FileChooser markDownFileChooser;
    Desktop desktop;
    @FXML
    private MenuItem renderBtn;
    private ApplicationInterface application;
    private Editor editor;

    {
        markDownFileChooser = new FileChooser();
        markDownFileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("markdown file", "*.md"));
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
        AppUtils.printWorkingDir();
        return;
    }

    public void OpenFileFromDir(ActionEvent ae) {

        File file = markDownFileChooser.showOpenDialog(application.getStage());
        if (file != null) {
            //read from file then create a new content from file, then set the content
            application.getEditor().setContent(
                    new ContentObject().setContentText(readFile(file))
            );
            GlobalVar.setCurrentWorkingFile(file);

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

    public void exportToHTML(ActionEvent ae) {
        String s = application.getEngine().renderToSurface(editor.getContent().getContentText());
        File file = AppUtils.buildFileChooser("Export to webpage", "*html")
                .showSaveDialog(application.getStage());
        AppUtils.saveToFile(file, s);
    }

    //TODO if file not saved then ask to save
    public void exitEditor(ActionEvent ae) {
        System.exit(0);
    }

    public void saveFile(ActionEvent ae){
        File file = GlobalVar.getCurrentWorkingFile();
        saveFileHelper(file);
    }

    private void saveFileHelper(File file) {
        if (file == null){
            //TODO ask where file will be saved.
        }
        else {
            Platform.runLater(() -> {
                ContentObject c = editor.getContent();
                String content = c.getContentText();
                AppUtils.saveToFile(file, content);
            });
            //update current file
            GlobalVar.setCurrentWorkingFile(file);
        }
    }

    public void saveToFile(ActionEvent ae) {
        File file = markDownFileChooser.showSaveDialog(application.getStage());
        saveFileHelper(file);
    }

}
