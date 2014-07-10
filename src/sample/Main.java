package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.util.logging.Logger;

import static sample.AppUtils.li;
import static sample.AppUtils.lookUp;
//TODO find a good markdown parser
//TODO try to display google.com in webview
//TODO change the menu into something prettier
//TODO implement a concrete RenderEngine
//TODO implement a concrete RenderSurface

public class Main extends Application {
    private Logger l = Logger.getLogger(Main.class.getName());
    private TextArea textArea ;
    private WebView webView ;

    private Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../ui/note_main.fxml"));
        primaryStage.setTitle("Noter 0.1");
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        li(this,"initialized main");

        textArea = lookUp(root, "#textarea", TextArea.class);
        textArea.setText("hello world set text");
        textArea.setWrapText(true);
        textArea.textProperty().addListener(MainController.textAreaKeyTyped);
    }
}
