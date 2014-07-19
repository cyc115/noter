package main;

import javafx.application.Platform;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * Created by yuechuan on 12/07/14.
 */
public class CodeMirrorEditor implements Editor {
    private WebView editorView = null;
    private ContentObject content;

    {
        content = new ContentObject();
    }

    private WebEngine webEngine;


    /**
     * Get a reference of the encapsulated editor. in this case
     * it is a webView
     *
     * @return return the underlying WebView that hosts the
     * CodeMirror Editor
     * @throws UnsupportedOperationException
     */
    @Override
    public Object getEditor() throws UnsupportedOperationException {
        return editorView;
    }

    @Override
    public void setEditor(Object editor)
            throws UnsupportedOperationException, IllegalArgumentException {
        if (!(editor instanceof WebView))
            throw new IllegalArgumentException(
                    "CodeMirrorEditor does not support " +
                            "Editor of the type: " + editor.getClass().getName());

        editorView = (WebView) editor;
        webEngine = editorView.getEngine();
    }

    @Override
    public ContentObject getContent() {
        assert (editorView != null);
        content.setContentText((String) (webEngine.executeScript("editor.getValue();")));
        return content;
    }

    @Override
    public Editor setContent(ContentObject content)
            throws UnsupportedOperationException {
        this.content = content;
        AppUtils.li(this, "update content to :" + content.getContentText());
        Platform.runLater(() -> updateNewContentUI(content));
        return this;
    }

    /**
     * updates the codeMirror to the new content
     */
    private void updateNewContentUI(ContentObject content) {
        setCodeMirrorText(content.getContentText());
    }

    /**
     * set the given string to codemirror
     *
     * @param s
     */
    private void setCodeMirrorText(String s) {

        String script = "var tempText ='" + s + "';";
        webEngine.executeScript(script);
        webEngine.executeScript("editor.setValue(tempText);");
    }


}
