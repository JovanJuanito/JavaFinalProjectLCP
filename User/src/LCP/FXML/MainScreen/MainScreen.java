package LCP.FXML.MainScreen;

import java.net.URL;
import java.util.ResourceBundle;

import LCP.FXML.ChatBox.ChatBox;
import LCP.FXML.Sidebar.Sidebar;
import LCP.FXML.Username.Username;
import LCP.Loaders.Client;
import LCP.Loaders.ObjectLoader;
import LCP.Loaders.ViewData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class MainScreen implements Initializable{
    
    private ViewData<Sidebar> SidebarData;
    private ViewData<Username> UsernameData;
    private ViewData<ChatBox> ChatBoxData;

    @FXML
    private BorderPane MainPane;
    @FXML
    private BorderPane MainParentBorderPane;
    @FXML
    private StackPane MainStackPane;

    public void setClient(Client x){
        ChatBoxData.getController().setClient(x);
        UsernameData.getController().setClient(x);
    }

    public void addChat(String chat){
        SidebarData.getController().addChat(chat);
    }

    public void togglePopUp(Boolean toggle){
        if(!toggle){
            MainStackPane.getChildren().removeLast();
        }
        else{
            MainStackPane.getChildren().add(UsernameData.getPane());
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        ObjectLoader SidebarLoader = new ObjectLoader();
        ObjectLoader UsernameLoader = new ObjectLoader();
        ObjectLoader ChatBoxLoader = new ObjectLoader();

        SidebarData = SidebarLoader.load("Sidebar");
        UsernameData = UsernameLoader.load("Username");
        ChatBoxData = ChatBoxLoader.load("ChatBox");

        MainStackPane.getChildren().add(UsernameData.getPane());

        MainParentBorderPane.setLeft(SidebarData.getPane());

        MainPane.setCenter(SidebarData.getController().getGlobalChatPane());
        MainPane.setBottom(ChatBoxData.getPane());

        SidebarData.getController().setContentPane(NewPane -> {MainPane.setCenter(NewPane);});
        UsernameData.getController().togglePopUp(toggle -> {togglePopUp(toggle);});
        ChatBoxData.getController().togglePopUp(toggle ->{togglePopUp(toggle);});

    }
    
}
