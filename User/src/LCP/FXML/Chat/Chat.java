package LCP.FXML.Chat;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class Chat implements Initializable {

    @FXML
    private Label chatLabel; 

    public void setChat(String chat){
        chatLabel.setText(chat);
    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
    }
    
}
