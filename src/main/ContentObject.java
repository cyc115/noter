package main;

/**
 * Stores the content of an editor.
 * first edit made on 12/07/14:
 * Just contain a string as of now ...
 * Created by yuechuan on 12/07/14.
 */
public class ContentObject {
    private String editorText;

    public String getEditorText() {
        return editorText;
    }

    public ContentObject setContentText(String editorText) {
        this.editorText = editorText;
        return this;
    }
}
