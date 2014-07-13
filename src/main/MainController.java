
package main;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import static main.AppUtils.li;

public class MainController {



    public static ChangeListener textAreaKeyTyped = new ChangeListener<String>() {
        public void changed(final ObservableValue<? extends String> observableValue, final String oldValue,
                            final String newValue) {
            li(this,"keypressed");
        }
    };
}
