package LCP.FXML.MainScreen;

import java.net.URL;
import java.util.ResourceBundle;

import LCP.FXML.Sidebar.Sidebar;
import LCP.Loaders.Client;
import LCP.Loaders.ObjectLoader;
import LCP.Loaders.ViewData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

public class MainScreen implements Initializable{
    
    @FXML
    private BorderPane MainPane;

    private ViewData<Sidebar> SidebarData;
    private Client client;

    public void setClient(Client x){
        this.client = x;
        SidebarData.getController().setClient(client);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        ObjectLoader SidebarLoader = new ObjectLoader();
        SidebarData = SidebarLoader.load("Sidebar");

        MainPane.setLeft(SidebarData.getPane());
        MainPane.setCenter(SidebarData.getController().getGlobalChatPane());

        SidebarData.getController().setContentPane(NewPane -> {MainPane.setCenter(NewPane);});        
    }
    
}
