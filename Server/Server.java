package Server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

<<<<<<< HEAD
public class Server implements Runnable{
=======
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable{ // has a relationship with ConnectionHandler
>>>>>>> 387d86b3054560a2735c891aa192ef4f8d77c1ad

    private ArrayList<ConnectionHandler> connections;
    private ServerSocket server;
    private boolean done = false;
    private ExecutorService pool;

    public Server(){
        connections = new ArrayList<>();
        done = false;
    }

    @Override
    public void run(){
        try{
            server = new ServerSocket(9999);
            pool = Executors.newCachedThreadPool();
<<<<<<< HEAD
=======
            System.out.println("Server active");
>>>>>>> 387d86b3054560a2735c891aa192ef4f8d77c1ad
            while(!done){
                Socket client = server.accept();
                ConnectionHandler handler = new ConnectionHandler(client);
                connections.add(handler);
                pool.execute(handler);
            }
        } catch(Exception e){
            shutdown();
        }
    }

    public void broadcast(String message){
        for(ConnectionHandler ch : connections){
            if(ch != null){
                ch.sendMessage(message);
            }
        }
    }

    public void shutdown(){
        try{
            done = true;
            pool.shutdown();
            if(!server.isClosed()){
                server.close();
            }

            for(ConnectionHandler ch : connections){
                ch.shutdown();
            }
        } catch(IOException e){
            // ignore
        }
    }

<<<<<<< HEAD
    class ConnectionHandler implements Runnable{
        
        private Socket client;
        private BufferedReader in;
        private PrintWriter out;
=======
    class ConnectionHandler implements Runnable{ // storing client info and handle client request
        
        private Socket client;
        private BufferedReader in; // revieng client input socket
        private PrintWriter out; // socket sending to client
>>>>>>> 387d86b3054560a2735c891aa192ef4f8d77c1ad
        private String nickname;

        public ConnectionHandler(Socket client){
            this.client = client;
        }

        @Override
        public void run(){
            try{
<<<<<<< HEAD
                out = new PrintWriter(client.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                out.println("Please enter a nickname: \n");
                nickname = in.readLine();
                System.out.println(nickname + " connected!\n");
                broadcast(nickname + " joined the chat!");
                String message;
                while((message = in.readLine()) != null){
=======
                out = new PrintWriter(client.getOutputStream(), true); // setting connection via socket (spreading end)
                in = new BufferedReader(new InputStreamReader(client.getInputStream())); // setting connection via socket (recieving end)
                
                nickname = in.readLine();// wait for the first input which will be the name
                System.out.println(nickname + " connected!\n");
                broadcast(nickname + " joined the chat!");
                
                String message;
                while((message = in.readLine()) != null){ // waiting for input from clients
>>>>>>> 387d86b3054560a2735c891aa192ef4f8d77c1ad
                    if(message.startsWith("/nick ")){
                        String[] messageSplit = message.split(" ", 2);
                        if(messageSplit.length == 2){
                            broadcast(nickname + " renamed themselves to " + messageSplit[1]);
<<<<<<< HEAD
                            System.out.println(nickname + " renamed themselves to " + messageSplit[1] + "\n");
                            nickname = messageSplit[1];
                            out.println("Successfully changed nickname to " + nickname + "\n");
                        } else{
                            out.println("No nickname provided!\n");
                        }
                    } else if(message.startsWith("/quit")){
                        broadcast(nickname + " left the chat!\n");
=======
                            System.out.println(nickname + " renamed themselves to " + messageSplit[1]);
                            nickname = messageSplit[1];
                        }
                    } else if(message.startsWith("/quit")){
                        broadcast(nickname + " left the chat!");
>>>>>>> 387d86b3054560a2735c891aa192ef4f8d77c1ad
                        shutdown();
                    } else{
                        broadcast(nickname + ": a" + message);
                    }
                }
            } catch(IOException e){
<<<<<<< HEAD
                shutdown();
=======
                shutdown(); // connection is not establish 
>>>>>>> 387d86b3054560a2735c891aa192ef4f8d77c1ad
            }
        }

        public void sendMessage(String message){
<<<<<<< HEAD
            out.println(message + "\n");
        }

        public void shutdown(){
=======
            out.println(message);// sending msg via socket
        }

        public void shutdown(){ 
>>>>>>> 387d86b3054560a2735c891aa192ef4f8d77c1ad
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
    }

    public static void main(String[] args){
        Server server = new Server();
        server.run();
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 387d86b3054560a2735c891aa192ef4f8d77c1ad
}