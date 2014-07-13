package main;

import javafx.scene.Node;

import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.logging.Logger.getAnonymousLogger;

/**
 * stores global variables as well as utility classes
 * Created by yuechuan on 07/07/14.
 */
public class AppUtils {
    private static Logger log = null;

    static {
        log = getAnonymousLogger();
    }

    private AppUtils() {
    }


    /**
     * look up the UI element of type T with fx:id of id in the ui node.
     * All arguments must not be null. id must not be empty and must start
     * with the string "#".
     * {@literal
     * INFO:
     * SplitPane puts all items in separate stack panes (fancied as
     * SplitPaneSkin$Content). For unknown reason FXMLLoader assign
     * them the same id as root child. You can get VBox you need by
     * next utility method:
     * }
     *
     * @param node parent node that hosts the element with id "id"
     * @param id   id of the element
     * @param <T>  Type of the element
     * @return the element found
     * @throws main.ObjectNotFoundException when no element is found
     */
    public static <T> T lookUp(Node parent, String id, Class<T> clazz)
            throws ObjectNotFoundException {
        assert parent != null && id != null && clazz != null;
        assert !id.equals("");
        assert id.startsWith("#");

        for (Node node : parent.lookupAll(id)) {
            if (node.getClass().isAssignableFrom(clazz)) {
                return (T) node;
            }
        }

        throw new ObjectNotFoundException("cannot find element with id" + id);
    }

    /**
     * customized logger
     *
     * @param objectType the class instance where the logging method is invoked
     * @param lvl        logging level
     * @param msg        log message
     * @param <T>        class type where logging is invoked
     */
    private static <T> void l(T objectType, Level lvl, String msg) {
        log.log(lvl, "[" + objectType.getClass().getName() + "]: " + msg);
    }

    /**
     * fine lvl logging
     */
    public static <T> void lf(T objectTriype, String msg) {
        l(objectTriype, Level.FINE, msg);
    }

    /**
     * info lvl logging
     */
    public static <T> void li(T objectTriype, String msg) {
        l(objectTriype, Level.INFO, msg);
    }

    /**
     * warning lvl logging
     */
    public static <T> void lw(T objectTriype, String msg) {
        l(objectTriype, Level.WARNING, msg);
    }


}
