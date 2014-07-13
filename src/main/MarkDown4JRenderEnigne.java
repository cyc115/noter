package main;

import org.markdown4j.Markdown4jProcessor;

import java.io.IOException;
import java.util.*;

/**
 * Created by yuechuan on 13/07/14.
 */
public class MarkDown4JRenderEnigne implements RenderEngine {
    /**
     * hosts a list of rendering surfaces
     */
    List<RenderSurface> surfaceLst;
    ApplicationInterface app;

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
    public void render(String raw) throws IllegalStateException {
        assert surfaceLst != null : "did you forget to attach surface to Engine? call attachRenderSurface()";

        String rendered = "no text";
        try {
            rendered = engine.process(raw);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        for (RenderSurface rs : surfaceLst) {
            rs.display(rendered);
        }
    }

    @Override
    public void attachApplication(ApplicationInterface app) {
        assert app != null : "cannot attach null as application";
        this.app = app;
    }
}