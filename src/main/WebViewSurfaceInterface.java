package main;

import javafx.scene.web.WebView;

import javax.naming.OperationNotSupportedException;

/**
 * Created by yuechuan on 12/07/14.
 */
public class WebViewSurfaceInterface implements RenderSurfaceInterface {
    private WebView webView = null;

    WebViewSurfaceInterface() {
    }

    @Override
    public Object getSurface() throws OperationNotSupportedException {
        return webView;
    }

    @Override
    public void setSurface(Object surface)
            throws OperationNotSupportedException,
            IllegalArgumentException {
        if (!(surface instanceof WebView))
            throw new IllegalArgumentException("Argument is not a WebView");

        webView = (WebView) surface;
    }

    @Override
    public void display(String renderedText) throws IllegalArgumentException {
        webView.getEngine().loadContent(renderedText);
    }
}
