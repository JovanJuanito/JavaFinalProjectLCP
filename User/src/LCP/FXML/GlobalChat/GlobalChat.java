package LCP.FXML.GlobalChat;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import LCP.FXML.Chat.Chat;
import LCP.Loaders.ObjectLoader;
import LCP.Loaders.ViewData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class GlobalChat implements Initializable{

    private ArrayList<ViewData<Chat>> Chats = new ArrayList<>();

    private ObjectLoader ChatLoader = new ObjectLoader();
    
    @FXML
    private ScrollPane contentPane;

    @FXML
    private VBox chatContainer;

    public void addChat(String chat){
        try{
            ViewData<Chat> currentChat = ChatLoader.load("Chat");
            currentChat.getController().setChat(chat);
            Chats.add(currentChat);

            chatContainer.getChildren().add(currentChat.getPane());
            contentPane.setVvalue(1.0);
        }
        catch(Exception e){
            System.err.println("Error loading chat: " + e.getMessage());
            e.printStackTrace();
        }
        
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
    }
    
}
