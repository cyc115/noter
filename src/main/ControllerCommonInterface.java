package main;

import javafx.application.Application;

/**
 * Created by yuechuan on 13/07/14.
 */
public interface ControllerCommonInterface {
    /**
     * save a reference of the main application to the controller
     * tight cuppling with the MainApplication and Loose cuppling
     * from the MainApplication side.
     */
    void setControllerParentApplication(Application application);

    /**
     * must be called after the application has finished
     * initializing, this handles initialization of some
     * of the UI element controllers.
     */
    void postInit();
}
