package main;

import javax.naming.OperationNotSupportedException;

/**
 * A wrapper for Different surfaces that can be used
 * as a "canvas" of the generated markdown html strings
 * .
 * <br>
 * A canvas is a webView in the most general cases but can
 * also handle things like pdf export.
 * Created by yuechuan on 10/07/14.
 */
public interface RenderSurfaceInterface {
    /**
     * displays the rendered text
     *
     * @param renderedText
     * @throws IllegalArgumentException
     */
    public void display(String renderedText) throws IllegalArgumentException;

    /**
     * Returns a reference to the underlying surface.
     * This method enables features that need
     * to call a specific method of the underlying webView.
     * This can be a webView or any other kinds of views.
     *
     * @return
     * @throws OperationNotSupportedException if user of this object should never
     *                                        obtain a reference of the surface
     */
    public Object getSurface() throws OperationNotSupportedException;

    /**
     * Set the underlying surface of this RenderSurface
     *
     * @param surface
     * @throws OperationNotSupportedException     if API user is not
     *                                            suppose to explicitly set the surface.
     * @throws java.lang.IllegalArgumentException if API user has
     *                                            passed in a type not supported by the RenderSurface
     */
    public void setSurface(Object surface)
            throws OperationNotSupportedException,
            IllegalArgumentException;

}