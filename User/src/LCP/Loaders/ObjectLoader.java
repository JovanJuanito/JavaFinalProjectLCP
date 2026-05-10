package LCP.Loaders;

import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class ObjectLoader {
    
    public <T> ViewData<T> load(String name) {
        try {
            String base = "/LCP/FXML/" + name + "/" + name;

            FXMLLoader loader = new FXMLLoader(getClass().getResource(base + ".fxml"));
            Pane view = loader.load();

            // CSS
            URL css = getClass().getResource(base + ".css");
            if (css != null) {
                view.getStylesheets().add(css.toExternalForm());
            }

            T controller = loader.getController();

            return new ViewData<>(view, controller);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
