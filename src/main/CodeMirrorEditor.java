package main;

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

    @Override
    public Editor setContent(ContentObject content)
            throws UnsupportedOperationException {
        throw new UnsupportedOperationException(
                "CodeMirrotrEditor's content canno" +
                        "t be set right now, feature not implemented");
    }

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
    }

    @Override
    public ContentObject getContent() {
        assert (editorView != null);
        content.setContentText((String) (editorView.getEngine().executeScript("editor.getValue();")));
        return content;
    }
}
