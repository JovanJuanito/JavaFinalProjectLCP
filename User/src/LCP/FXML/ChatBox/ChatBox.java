package LCP.FXML.ChatBox;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;

public class ChatBox implements Initializable{

    @FXML 
    HBox ChatBoxPane;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        
        ChatBoxPane.setPrefWidth( screenBounds.getWidth() * 0.75);
    }
    
}
