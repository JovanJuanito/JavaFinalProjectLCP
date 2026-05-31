package LCP.Loaders;

import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.function.Consumer;

import javafx.application.Platform;

import java.io.IOException;

public class Client implements Runnable {

    private Socket client; 
    private BufferedReader in; // Socket input responsible for recieving input
    private PrintWriter out; // Socket output responsible for determining output
    private String username;

    private Consumer<String> transferMessage;

    @Override
    public void run(){
        try{
            client = new Socket("127.0.0.1", 9999);
            out = new PrintWriter(client.getOutputStream(), true); // setting client Socket output
            in = new BufferedReader(new InputStreamReader(client.getInputStream())); // setting client Socket input

            InputHandler inHandler = new InputHandler();
            Thread t = new Thread(inHandler);
            t.start();

        } catch(IOException e){
            shutdown();
        }
    }

    public void sendMessage(String msg){
        out.println(msg);
        if(username == null) username = msg; // set username for the first input recieved
    }

    public void addChat(Consumer<String> signal){
        this.transferMessage = signal;
    }

    public void shutdown(){
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
            String inMessage;
            try {
                while((inMessage = in.readLine()) != null){// waits server
                    final String msg = inMessage; // msg need to be final for runLater
                    Platform.runLater(() ->{transferMessage.accept(msg);}); // because client and javafx in different threads we
                }
            } catch (IOException e) {
                shutdown();
            }
        }
    }
}