package LCP.FXML.GlobalChat;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class GlobalChat implements Initializable{

    @FXML
    private BorderPane ContentPane; 

    private Pane ChatBoxPane;

    public void setChatBox(Pane ChatBox){
        ChatBoxPane = ChatBox;
    }

    public void activateChatBox(){
        ContentPane.setBottom(ChatBoxPane);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

    }
    
}
