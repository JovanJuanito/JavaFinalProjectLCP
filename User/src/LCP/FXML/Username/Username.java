package LCP.FXML.Username;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import LCP.Loaders.Client;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
// import javafx.geometry.Rectangle2D;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
// import javafx.stage.Screen;

public class Username implements Initializable{

    private Client client;

    private Consumer<Boolean> activatePopUp;

    @FXML
    private Pane UsernameContainer;
    @FXML
    private TextField inputField;

    public void setClient(Client c){
        client = c;
    }

    public void togglePopUp(Consumer<Boolean> toggle){
        this.activatePopUp = toggle;
    }

    public void enterUsername(KeyEvent e){
        if(e.getCode() == KeyCode.ENTER){
            if(inputField.getText().isEmpty() || inputField.getText().contains(" ")){
                //popup failed nick change ui
                return;
            }
            else{
                if(inputField.getText().contains("/nick")){
                    client.sendMessage(inputField.getText());
                }
                else{
                    client.sendMessage("/nick " + inputField.getText());
                }
                activatePopUp.accept(false);
            }
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
        // Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        
        // UsernameContainer.setMaxWidth(screenBounds.getWidth() * 0.8);
        // UsernameContainer.setMaxHeight(screenBounds.getHeight() * 0.8);

    }
    
}
