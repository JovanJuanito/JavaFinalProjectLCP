package LCP.FXML.Sidebar;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import LCP.FXML.GlobalChat.GlobalChat;
import LCP.FXML.PrivateChat.PrivateChat;
import LCP.Loaders.ObjectLoader;
import LCP.Loaders.ViewData;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

public class Sidebar implements Initializable {

    private Consumer<Pane> NewPane;

    private ViewData<GlobalChat> GlobalChatData;
    private ViewData<PrivateChat> PrivateChatData;

    public Pane getGlobalChatPane(){
        return GlobalChatData.getPane(); // add a try execpt block!
    }

    public void setContentPane(Consumer<Pane> NewPane){
        this.NewPane = NewPane;
    }

    public void loadGlobalChat(){
        if(NewPane != GlobalChatData.getPane())NewPane.accept(getGlobalChatPane());
    }

    public void loadPrivateChat(){
        if(NewPane != GlobalChatData.getPane())NewPane.accept(PrivateChatData.getPane());
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        ObjectLoader GlobalChatLoader = new ObjectLoader();
        ObjectLoader PrivateChatLoader = new ObjectLoader();

        GlobalChatData = GlobalChatLoader.load("GlobalChat");
        PrivateChatData = PrivateChatLoader.load("PrivateChat");
    }
    
}
