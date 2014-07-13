package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import javax.naming.OperationNotSupportedException;
import java.util.logging.Logger;

import static main.AppUtils.li;
import static main.AppUtils.lookUp;
//TODO find a good markdown parser
//DONE try to display google.com in webview
//DONE change the menu into something prettier
//TODO implement a concrete RenderEngine
//TODO implement a concrete RenderSurface

//12/07/14

//TODO editor does not fill the pane
//TODO enable horizontal wraping
//DONE firebug hide by default


public class Main extends Application {
    private Logger l = Logger.getLogger(Main.class.getName());
    private WebView editor;
    private WebViewSurface displaySurface;

    private Scene scene;

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../ui/note_main.fxml"));
        primaryStage.setTitle("Noter 0.1");
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        li(this,"initialized main");

        initEditor(root);
        initDisplay(root);

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
        editor = lookUp(root, "#editor", WebView.class);
        editor.getEngine().load(getClass().getResource("../ui/editor.html").toExternalForm());
    }
}