package LCP.FXML.Sidebar;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import LCP.FXML.GlobalChat.GlobalChat;
import LCP.FXML.PrivateChat.PrivateChat;
import LCP.Loaders.ObjectLoader;
import LCP.Loaders.ViewData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Sidebar implements Initializable {

    private Consumer<Pane> NewPane;
    private Pane currentPane;

    private ViewData<GlobalChat> GlobalChatData;
    private ViewData<PrivateChat> PrivateChatData;

    @FXML
    private VBox SidebarPane;

    public void setContentPane(Consumer<Pane> NewPane){
        this.NewPane = NewPane;
    }

    public void addChat(String chat){
        GlobalChatData.getController().addChat(chat);
    }

    public Pane getGlobalChatPane(){
        return GlobalChatData.getPane();
    }

    public void loadGlobalChat(){
        Pane GlobalPane = GlobalChatData.getPane();
        if(currentPane != GlobalPane){
            NewPane.accept(getGlobalChatPane());
        }
    }

    public void loadPrivateChat(){
        Pane privatePane = PrivateChatData.getPane();
        if(currentPane != privatePane){
            NewPane.accept(PrivateChatData.getPane());
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        // init Content
        try{
            ObjectLoader GlobalChatLoader = new ObjectLoader();
            ObjectLoader PrivateChatLoader = new ObjectLoader();

            GlobalChatData = GlobalChatLoader.load("GlobalChat");
            PrivateChatData = PrivateChatLoader.load("PrivateChat");
        }
        catch(Exception e){
            System.err.println("Error initializing content: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
}
