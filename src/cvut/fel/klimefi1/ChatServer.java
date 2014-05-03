package cvut.fel.klimefi1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Chat Server
 * 
 * Provides a service for chatting between users connected to the server. A user
 * choose his nickname upon connection and then can enter a room on the server.
 * User can also retrieve list of rooms, create a new room, enter/leave an
 * existing room and send messages within entered room. 
 * 
 * @todo Server is running on port configured in parameter or by default on 4567.
 * 
 * @author Filip Klimes <klimefi1@fel.cvut.cz>
 */
public class ChatServer {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        ServerSocket socket;
        
        try {
            socket = new ServerSocket(4567);
            new Thread( new Worker() ).start();
        } catch(IOException ex) {
            System.out.println("An error occured while starting the server. Aborting.");
            return;
        }
        
        System.out.println("Server is running ...");
        
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
}
