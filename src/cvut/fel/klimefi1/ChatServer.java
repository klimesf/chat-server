package cvut.fel.klimefi1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Chat Server
 * 
 * Provides a service for chatting between users connected to the server. A user
 * choose his nickname upon connection and then can enter a room on the server.
 * User can also retrieve list of rooms, create a new room, enter/leave an
 * existing room and send messages within entered room. 
 * 
 * Server is running on port configured in parameter (-p or --port) or by default on 4567.
 * 
 * @author Filip Klimes <klimefi1@fel.cvut.cz>
 */
public class ChatServer {
    
    /**
     * Main method of the server.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        ServerSocket socket;
        int port = ChatServer.parsePort(args);
        
        try {
            socket = new ServerSocket(port);
            new Thread( new Worker() ).start();
        } catch(IOException ex) {
            System.out.println("An error occured while starting the server. Aborting.");
            return;
        }
        
        System.out.printf("Server is running on port %d ...\n", port);
        
        while(true) {
            try {
                Socket client = socket.accept();
                // Start client's own thread
                Client handler = new Client(client);
                new Thread(handler).start();
                System.out.println("Client accepted.");
            } catch (IOException ex) {
                System.out.println("An error occured while accepting a client.");
            }
        }
    }

    /**
     * Parses port from the command line arguments
     * 
     * @param args Command line arguments
     * @return 
     */
    private static int parsePort(String[] args) {
        int port = 4567; // Default value
        Iterator<String> iterator = Arrays.asList(args).iterator();
        while(iterator.hasNext()) {
            String command = iterator.next();
            if(command.equals("-p") || command.equals("--port")) {
                    if(iterator.hasNext()) {
                        port = Integer.parseInt(iterator.next());
                    }
                break;
            }
        }
        return port;
    }
}
