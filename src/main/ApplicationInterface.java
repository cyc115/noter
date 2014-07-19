package main;

import javafx.stage.Stage;

/**
 * Created by yuechuan on 13/07/14.
 */
public interface ApplicationInterface {

    Editor getEditor();

    RenderSurface getDisplaySurface();

    ControllerCommonInterface getMainController();

    RenderEngine getEngine();

    Stage getStage();
}
