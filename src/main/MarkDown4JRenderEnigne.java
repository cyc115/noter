package main;

import org.markdown4j.Markdown4jProcessor;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by yuechuan on 13/07/14.
 */
public class MarkDown4JRenderEnigne implements RenderEngine.IORenderEngin {
    /**
     * hosts a list of rendering surfaces
     */
    List<RenderSurface> surfaceLst;
    ApplicationInterface app;
    Editor editor;

    {
        engine = new Markdown4jProcessor();
    }

    private Markdown4jProcessor engine;

    @Override
    public void attachRenderSurface(RenderSurface renderSurface) throws IllegalArgumentException {
        assert renderSurface != null : "renderSurface cannot be null when attaching to to an RdrEgn";
        if (surfaceLst == null) {
            surfaceLst = new LinkedList<>();
        }
        surfaceLst.add(renderSurface);
    }

    @Override
    public void detachRenderSurface(RenderSurface renderSurface) throws IllegalArgumentException {
        assert renderSurface != null : "renderSurface cannot be null";
        surfaceLst.remove(renderSurface);
    }

    @Override
    public Editor[] obtainInputSource() {
        Editor[] e = {editor};
        return e;
    }

    @Override
    public String renderToSurface(String raw) throws IllegalStateException {
        assert surfaceLst != null : "did you forget to attach surface to Engine? call attachRenderSurface()";
        assert raw != null : "raw is null ";
        String rendered = null;
        rendered = render(raw);
        for (RenderSurface rs : surfaceLst) {
            rs.display(rendered);
        }
        return rendered;
    }

    @Override
    public String render(String raw) {
        String rendered = null;
        try {
            rendered = engine.process(raw);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();
        sb.append("<link rel=\"stylesheet\" href=\"../ui/mdimport.css\">");
        sb.append("<article>");
        sb.append(rendered);
        sb.append("</article>");

        rendered = sb.toString();
        return rendered;
    }

    @Override
    public void attachApplication(ApplicationInterface app) {
        assert app != null : "cannot attach null as application";
        this.app = app;
    }

    @Override
    public void attachInputSource(Editor editor) {
        this.editor = editor;
    }

    @Override
    public String renderToSurface() {
        assert editor != null : "editor is not defined ! use render(String raw) instead";
        String editorText = editor.getContent().getContentText();
        String rendered = renderToSurface(editorText);
        return rendered;
    }
}
