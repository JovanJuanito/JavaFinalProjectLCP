package LCP.FXML.ChatBox;

import java.net.URL;
import java.util.ResourceBundle;

import LCP.Loaders.Client;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;

public class ChatBox implements Initializable{

    @FXML 
    private HBox ChatBoxPane;

    @FXML
    private TextField TextField;

    private Client client;

    private String msg;

    public void setClient(Client x){
        this.client = x;
    }

    public void sendMessage(KeyEvent e){
        if (e.getCode() == KeyCode.ENTER){
            msg = TextField.getText();
            client.sendMessage(msg); //send anyway to server cuz server want to broadcast nick change to everyone
            if(msg.startsWith("/nick")){
                String[] messageSplit = msg.split(" ", 2);
                if(messageSplit.length == 2){
                    //popup nick ui 
                }
                else{
                    //popup failed nick change ui
                }
            }   
            else if(msg.startsWith("/quit")){
                //quit javafx
            }
        }
    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        
        ChatBoxPane.setPrefWidth( screenBounds.getWidth() * 0.75);
    }
    
}
