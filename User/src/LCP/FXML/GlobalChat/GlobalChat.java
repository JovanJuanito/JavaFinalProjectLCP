package LCP.FXML.GlobalChat;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import LCP.FXML.Chat.Chat;
import LCP.Loaders.ObjectLoader;
import LCP.Loaders.ViewData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

public class GlobalChat implements Initializable{

    private ArrayList<ViewData<Chat>> Chats = new ArrayList<>();

    private ObjectLoader ChatLoader = new ObjectLoader();
 
    @FXML
    private VBox chatContainer;

    public void addChat(String chat){
        
        ViewData<Chat> currentChat = ChatLoader.load("Chat");
        currentChat.getController().setChat(chat);
        Chats.add(currentChat);

        chatContainer.getChildren().add(currentChat.getPane());
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
    }
    
}
