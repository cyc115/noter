package main;

/**
 * a common interface for the markdown rendering engines
 * .The engines may or may not be able to export to multiple
 * rendering surface depending on individual implementations
 * Created by yuechuan on 09/07/14.
 *

 *
 */
public interface RenderEngine {

    /**
     * connect to a renderToSurface surface to the current renderToSurface Engine
     *
     * @param renderSurface
     */
    public void attachRenderSurface(RenderSurface renderSurface)
            throws IllegalArgumentException;

    /**
     * remove a renderToSurface surface from the engine
     *
     * @param renderSurface
     * @throws IllegalArgumentException
     */
    public void detachRenderSurface(RenderSurface renderSurface)
            throws IllegalArgumentException;

    /**
     * The rendering engine will take in the raw text(may be markdown)
     * renderToSurface it in html (or other format) and pass it to the attached
     * RenderSurface.
     * <p>
     * raw text --> html --> output to rendersurface
     *
     * @throws IllegalStateException
     */
    public String renderToSurface(String raw) throws IllegalStateException;

    public void attachApplication(ApplicationInterface app);

    /**
     * return the rendered String instead of rendering it to the surface
     * @param raw
     * @return
     */
    public String render(String raw);

    /**
     * RenderEngine that can attach an Editor and renderToSurface
     * directly from Editor to Render surface by calling
     * renderToSurface()
     * updated on 14/07/14 to include obtainInputSource()
     * to allow process in the background instead of the
     * UI thread.
     *
     */
    public interface IORenderEngin extends RenderEngine {

        void attachInputSource(Editor editor);

        /**
         * returns the copy of the rendered text
         *
         * @return
         */
        String renderToSurface();

        /**
         * Obtain a array of attached editors
         *
         * @return
         */
        Editor[] obtainInputSource();

    }
}
