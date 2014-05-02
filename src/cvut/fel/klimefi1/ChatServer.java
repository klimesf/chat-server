/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cvut.fel.klimefi1;

import cvut.fel.klimefi1.actions.Action;
import cvut.fel.klimefi1.observer.IObservable;
import cvut.fel.klimefi1.observer.Observer;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Chat Server
 * 
 * Provides a service for chatting between users connected to the server. A user
 * choose his nickname upon connection and then can enter a room on the server.
 * User can also retrieve list of rooms, create a new room, enter/leave an
 * existing room and send messages within entered room. 
 * 
 * Server is running on port configured in ini file.
 * 
 * @author Filip Klimes <klimefi1@fel.cvut.cz>
 */
public class ChatServer implements IObservable {

    private static final List<Observer> observers = new ArrayList<>();
    
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

    @Override
    public void registerObserver(Observer observer) {
        ChatServer.observers.add(observer);
    }

    @Override
    public void unregisterObserver(Observer observer) {
        ChatServer.observers.remove(observer);
    }

    @Override
    public void notifyObservers(Action action) {
        for(Observer observer : ChatServer.observers) {
            observer.handle(action);
        }
    }
    
}
