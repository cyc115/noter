package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import javax.naming.OperationNotSupportedException;


import static main.AppUtils.lookUp;
//TODO find a good markdown parser
//DONE try to display google.com in webview
//DONE change the menu into something prettier
//TODO implement a concrete RenderEngine
//TODO implement a concrete RenderSurface

//12/07/14

//DONE editor does not fill the pane
//TODO enable horizontal wraping
//DONE firebug hide by default


public class MainApplication extends Application implements ApplicationInterface {
    private EditorInterface editor;
    private RenderSurface displaySurface;
    private RenderEngine engine;
    private ControllerCommonInterface mainController;


    //stuff on the page
    private MenuItem mItemRender;


    private Scene scene;

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        System.out.println("enter Start()");

        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(
                getClass().getResource("../ui/note_main.fxml")
                        .openStream());

        primaryStage.setTitle("Noter 0.1");
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

        //obtain controller of the root
        mainController = (ControllerCommonInterface) fxmlLoader.getController();
        mainController.setControllerParentApplication(this);

        initEditor(root);
        initDisplay(root);
        initEngine();

        onPostInit();
        System.out.println("end Start()");
    }

    private void initEngine() {
        engine = new MarkDown4JRenderEnigne();
        engine.attachRenderSurface(displaySurface);
        engine.attachApplication(this);
    }

    /**
     * handles what ever is needed to do after application has finished initialization
     * this usually calls the Controller's postInit()
     */
    private void onPostInit() {
        mainController.postInit();
    }
    private void initDisplay(Parent root) throws ObjectNotFoundException {
        WebView display = lookUp(root, "#webdisplay", WebView.class);
        display.getEngine().load("https://google.com");
        try {
            displaySurface = new WebViewSurface();
            displaySurface.setSurface(display);
        } catch (OperationNotSupportedException onse) {
            onse.printStackTrace();
        }
        displaySurface.display("HelloWorldText");
    }

    private void initEditor(Parent root) throws ObjectNotFoundException {
        WebView editorWV = lookUp(root, "#editor", WebView.class);
        editorWV.getEngine().load(getClass().getResource("../ui/editor.html").toExternalForm());
        editor = new CodeMirrorEditor();
        try {
            editor.setEditor(editorWV);
        } catch (UnsupportedOperationException usoe) {
            usoe.printStackTrace();
        } catch (IllegalArgumentException iae) {
            iae.printStackTrace();
        }
    }

    public EditorInterface getEditor() {
        return editor;
    }

    public RenderSurface getDisplaySurface() {
        return displaySurface;
    }

    public ControllerCommonInterface getMainController() {
        return mainController;
    }

    public RenderEngine getEngine() {
        return engine;
    }
}
