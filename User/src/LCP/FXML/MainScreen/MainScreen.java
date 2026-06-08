package LCP.FXML.MainScreen;

import java.net.URL;
import java.util.ResourceBundle;

import LCP.FXML.ChatBox.ChatBox;
import LCP.FXML.Sidebar.Sidebar;
import LCP.FXML.Username.Username;
import LCP.Loaders.AnimationHelper;
import LCP.Loaders.Client;
import LCP.Loaders.ObjectLoader;
import LCP.Loaders.ViewData;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

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
        try {
            ChatBoxData.getController().setClient(x);
            UsernameData.getController().setClient(x);
        } catch (Exception e) {
            System.err.println("Error setting client: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void addChat(String chat){
        try {
            SidebarData.getController().addChat(chat);
        } catch (Exception e) {
            System.err.println("Error adding chat: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void togglePopUp(Boolean toggle){
        try {
            if(!toggle){
                Platform.runLater(() -> {
                    try {
                        ChatBoxData.getController().getInputField().setDisable(false);
                        AnimationHelper.slideOutFromBottom(MainStackPane.getChildren().getLast(), 250);
                        
                        new Timeline(new KeyFrame(Duration.millis(250), e -> {
                            MainStackPane.getChildren().removeLast();
                        })).play();
                    } catch (Exception e) {
                        System.err.println("Error in toggle animation out: " + e.getMessage());
                        e.printStackTrace();
                    }
                });
            }
            else{
                Platform.runLater(() -> {
                    try {
                        AnimationHelper.slideInFromTop(UsernameData.getPane(), 250);
                        MainStackPane.getChildren().add(UsernameData.getPane());
                        UsernameData.getController().getInputField().requestFocus();
                        ChatBoxData.getController().getInputField().setDisable(true);
                    } catch (Exception e) {
                        System.err.println("Error in toggle animation in: " + e.getMessage());
                        e.printStackTrace();
                    }
                });
            }
        } catch (Exception e) {
            System.err.println("Error in togglePopUp: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            ObjectLoader SidebarLoader = new ObjectLoader();
            ObjectLoader UsernameLoader = new ObjectLoader();
            ObjectLoader ChatBoxLoader = new ObjectLoader();

            SidebarData = SidebarLoader.load("Sidebar");
            MainParentBorderPane.setLeft(SidebarData.getPane());
            
            ChatBoxData = ChatBoxLoader.load("ChatBox");
            MainPane.setBottom(ChatBoxData.getPane());
            ChatBoxData.getController().getInputField().setDisable(true);

            UsernameData = UsernameLoader.load("Username");
            MainStackPane.getChildren().add(UsernameData.getPane());
            UsernameData.getController().getInputField().requestFocus();

            MainPane.setCenter(SidebarData.getController().getGlobalChatPane());

            SidebarData.getController().setContentPane(NewPane -> {MainPane.setCenter(NewPane);});
            UsernameData.getController().togglePopUp(toggle -> {togglePopUp(toggle);});
            ChatBoxData.getController().togglePopUp(toggle ->{togglePopUp(toggle);});
        } catch (Exception e) {
            System.err.println("Error initializing MainScreen: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
}
