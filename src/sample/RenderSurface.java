package sample;

/**
 * Created by yuechuan on 10/07/14.
 */
public interface RenderSurface {
    /**
     * displays the rendered text
     * @param renderedText
     * @throws IllegalArgumentException
     */
    public void display(String renderedText) throws IllegalArgumentException;

}