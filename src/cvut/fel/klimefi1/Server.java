package cvut.fel.klimefi1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 * @author Filip Klimes <klimefi1@fel.cvut.cz>
 */
public class Server {
    
    public static void main(String[] args) throws IOException {
    
        ServerSocket socket = new ServerSocket(4567);   // 4567 je port
        new Thread( new Worker() ).start();
        
        System.out.println("Server is running ...");
        
        while(true) {
            Socket client = socket.accept();            
            // Start client's own thread
            Client handler = new Client(client);
            new Thread(handler).start();
            System.out.println("Client accepted");

        }
    }
    
}
