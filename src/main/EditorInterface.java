package main;

/**
 * Created by yuechuan on 12/07/14.
 */
public interface EditorInterface {
    /**
     * Obtains a reference to the Editor's content object
     *
     * @return editor's content
     */
    ContentObject getContent();

    /**
     * set the content object of the editor
     *
     * @param content
     * @return content
     * @throws UnsupportedOperationException
     */
    EditorInterface setContent(ContentObject content) throws UnsupportedOperationException;

    /**
     * obtain a reference of the underlying editor object. in the case of
     * the CodeMirrorEditor, this will return a reference to the webView
     * that hosts codeMirror.
     *
     * @return
     * @throws UnsupportedOperationException
     */
    Object getEditor() throws UnsupportedOperationException;

    /**
     * setter for the editor: refer to getEditor.
     *
     * @param editor
     * @throws UnsupportedOperationException
     * @throws IllegalArgumentException
     */
    void setEditor(Object editor) throws UnsupportedOperationException, IllegalArgumentException;

}
