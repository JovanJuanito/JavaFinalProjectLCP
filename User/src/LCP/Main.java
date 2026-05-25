package LCP;

import LCP.FXML.MainScreen.MainScreen;
import LCP.Loaders.ObjectLoader;
import LCP.Loaders.ViewData;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application{
    public static void main(String[] args) throws Exception {
        launch(args);
        Client client = new Client();
        client.run();
    }

    @Override
    public void start(Stage stage) throws Exception {

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        ObjectLoader MainLoader = new ObjectLoader();
        ViewData<MainScreen> MainData = MainLoader.load("MainScreen");

        stage.setScene(new Scene(MainData.getPane(),screenBounds.getWidth(),screenBounds.getHeight()));
        stage.show();
    }

}
