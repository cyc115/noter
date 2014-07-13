package main;

/**
 * Created by yuechuan on 13/07/14.
 */
public interface ApplicationInterface {

    EditorInterface getEditor();

    RenderSurface getDisplaySurface();

    ControllerCommonInterface getMainController();

    RenderEngine getEngine();
}
