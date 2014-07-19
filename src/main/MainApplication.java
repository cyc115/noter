package main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import javax.naming.OperationNotSupportedException;
import java.util.Timer;
import java.util.TimerTask;

import static main.AppUtils.lookUp;
//DONE find a good markdown parser
//DONE try to display google.com in webview
//DONE change the menu into something prettier
//DONE implement a concrete RenderEngine
//DONE implement a concrete RenderSurface
//12/07/14
//DONE editor does not fill the pane
//DONE enable horizontal wraping
//DONE firebug hide by default
//17/07/14
//DONE smart render: only render on change.
//TODO implement search
//DONE implement save as... document
//18/07/14
//DONE implement open document
//TODO implement save document
//TODO implement New document menu
//TODO implement realtime Rendering on/off switch in menu


public class MainApplication extends Application implements ApplicationInterface {
    public double RENDER_DELAY = .5; //5 second delay
    private Editor editor;
    private RenderSurface displaySurface;
    private RenderEngine.IORenderEngin engine;
    private ControllerCommonInterface mainController;
    //stuff on the page
    private MenuItem mItemRender;


    private Scene scene;
    private Stage mainStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("enter Start()");

        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(
                getClass().getResource("../ui/note_main.fxml")
                        .openStream());

        primaryStage.setTitle("Noter 0.1");
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

        //obtain stage
        mainStage = primaryStage;
        //init display
        initEditor(root);
        initDisplay(root);
        initEngine();
        startRealTimeRendering();

        //obtain controller of the root
        mainController = fxmlLoader.getController();
        mainController.setControllerParentApplication(this);
        mainController.postInit();

        System.out.println("end Start()");
    }

    private void startRealTimeRendering() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                long t = System.currentTimeMillis();

                Platform.runLater(() -> {
                    Boolean editorChanged = (Boolean) ((WebView) editor.getEditor())
                            .getEngine()
                            .executeScript("editor.hasEditorChanged()");
                    if (editorChanged) engine.renderToSurface();
                });
                System.out.println("rerendered: " + (System.currentTimeMillis() - t));
            }
        }, 500, (int) (1000 * RENDER_DELAY));
    }

    private void initEngine() {
        engine = new MarkDown4JRenderEnigne();
        engine.attachRenderSurface(displaySurface);
        engine.attachInputSource(editor);
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

    public Editor getEditor() {
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

    public Stage getStage() {
        return mainStage;
    }

}
