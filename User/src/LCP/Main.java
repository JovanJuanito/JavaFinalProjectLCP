package LCP;

import LCP.FXML.MainScreen.MainScreen;
import LCP.Loaders.ObjectLoader;
import LCP.Loaders.ViewData;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.io.IOException;

public class Main extends Application{
    
    class Client implements Runnable {

        private Socket client;
        private BufferedReader in;
        private PrintWriter out;
        private boolean done = false;

        @Override
        public void run(){
            try{
                client = new Socket("127.0.0.1", 9999);
                out = new PrintWriter(client.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(client.getInputStream()));

                InputHandler inHandler = new InputHandler();
                Thread t = new Thread(inHandler);
                t.start();

                String inMessage;
                while((inMessage = in.readLine()) != null){
                    System.out.println(inMessage);
                }
            } catch(IOException e){
                shutdown();
            }
        }

        public void shutdown(){
            done = true;
            try{
                in.close();
                out.close();
                if(!client.isClosed()){
                client.close();
                }
            } catch(IOException e){
            //ignore
            }
        }

        class InputHandler implements Runnable {

            @Override
            public void run(){
                try{
                    BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
                    while(!done){
                        String message = inReader.readLine();
                        if(message.contains("/quit")){
                            out.println(message);
                            inReader.close();
                            shutdown();
                        } else{
                            out.println(message);
                        }
                    }
                } catch(IOException e){
                    shutdown();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        launch(args);
        Client client = new Client();
        client.run();
    }

    @Override
    public void start(Stage stage) throws Exception {

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        ObjectLoader MainLoader = new ObjectLoader();
        ViewData<MainScreen> MainData = MainLoader.load("MainScreen");

        stage.setScene(new Scene(MainData.getPane(),screenBounds.getWidth(),screenBounds.getHeight()));
        stage.show();
    }

}
