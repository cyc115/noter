package main;

/**
 * a common interface for the markdown rendering engines
 * .The engines may or may not be able to export to multiple
 * rendering surface depending on individual implementations
 * Created by yuechuan on 09/07/14.
 */
public interface RenderEngine {
    /**
     * connect to a render surface to the current render Engine
     *
     * @param renderSurfaceInterface
     */
    public void attachRenderSurface(RenderSurfaceInterface renderSurfaceInterface)
            throws IllegalArgumentException;

    /**
     * remove a render surface from the engine
     *
     * @param renderSurfaceInterface
     * @throws IllegalArgumentException
     */
    public void detachRenderSurface(RenderSurfaceInterface renderSurfaceInterface)
            throws IllegalArgumentException;

    /**
     * The rendering engine will take in the raw text(may be markdown)
     * render it in html (or other format) and pass it to the attached
     * RenderSurface
     *
     * @throws IllegalStateException
     */
    public void render(String raw) throws IllegalStateException;

}
