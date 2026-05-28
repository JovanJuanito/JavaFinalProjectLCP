package LCP.Loaders;

import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.io.IOException;

public class Client implements Runnable {

    private Socket client; 
    private BufferedReader in; // Socket input responsible for recieving input
    private PrintWriter out; // Socket output responsible for determining output
    private String username;

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
                    System.out.println(inMessage);
                }
            } catch (IOException e) {
                shutdown();
            }
        }
    }
}