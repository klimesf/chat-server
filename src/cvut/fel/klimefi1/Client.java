/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cvut.fel.klimefi1;

import cvut.fel.klimefi1.*;
import cvut.fel.klimefi1.actions.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author filip
 */
public class Client implements Runnable {
    
    private String nickname;
    
    private final Socket socket;

    private final Scanner input;
    
    private final DataOutputStream output;
    
    private final Set<Room> rooms = new HashSet<>();
    
    /**
     * Constructor
     * @param socket Client's socket
     * @throws java.io.IOException
     */
    public Client(Socket socket) throws IOException {
        this.socket = socket;
        
        // streamy pro posilani a cteni dat
        input = new Scanner( socket.getInputStream() );
        output = new DataOutputStream( socket.getOutputStream() );
    }
    
    /**
     * Sends message to this client
     * @param sender
     * @param message
     * @return 
     */
    public boolean send( String sender, String message ) {
        try {
            output.writeBytes("[" + sender + "] : " + message + "\n");
            return true;
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     * Sends message to this client
     * @param room
     * @param message
     * @return 
     * @throws java.io.IOException 
     */
    public boolean messageSent( Room room, String message ) throws IOException {
        if(room != null) {
            output.writeBytes("RECV " + room.getName() + " " + message + "\n");
            return true;
        } else {
            output.writeBytes("ERR " + message + "\n");
            return false;
        }
    }
    
    /**
     * Notifies this client about someone entering a room
     * @param room 
     * @throws java.io.IOException 
     */
    public void enterRoom( Room room ) throws IOException {
        if(room == null) {
            output.writeBytes("ERR Room does not exist\n");
        } else {
            if(room.contains(this)) {
                output.writeBytes("STATUS OK\n");
                rooms.add(room);
            } else {
                output.writeBytes("ERR Could not enter the room\n");
            }
        }
    }

    /**
     * Notifies this client about someone leaving a room
     * @param room 
     */
    public void leaveRoom( Room room ) throws IOException {
        if(room == null) {
            output.writeBytes("ERR Room does not exist\n");
        } else if(!room.contains(this)) {
            output.writeBytes("STATUS OK\n");
            rooms.remove(room);
        } else {
            output.writeBytes("ERR Could not leave the room\n");
        }
    }
    
    /**
     * Notifies the client about room being created
     * @param success
     * @param name
     * @throws java.io.IOException
     */
    public void roomCreated( boolean success, String name ) throws IOException {
        if(success) {
            output.writeBytes("STATUS Created room " + name + "\n");
        } else {
            output.writeBytes("ERR Room " + name + " already exists\n");
        }
    }
    
    /**
     * Lists rooms
     * @param rooms
     * @throws IOException 
     */
    public void listRooms( String rooms ) throws IOException {
        output.writeBytes("LIST " + rooms + "\n");
    }
    
    /**
     *
     * @throws IOException
     */
    public void unsupportedAction() throws IOException {
        output.writeBytes("ERR Unsupported command\n");
    }
    
    /**
     *
     * @throws IOException
     */
    public void disconnect() throws IOException {
        output.writeBytes("GOODBYE\n");
        for ( Room room : rooms ){
            room.leave(this);
        }
        System.out.println("Client " + nickname + " left");
    }
    
    @Override
    public void run() {
        // Init
        String room;
        Action action;
        
        try {

            if(input.next().equals("NICK")) {
                nickname = input.nextLine();
                nickname = nickname.trim();
                output.writeBytes("STATUS Your nick is " + nickname + "\n");
            } else {
                output.writeBytes("ERR NICK must be the first command!\n");
                socket.close();
                return;
            }
            
            while(input.hasNext()) {
                // Server shutting down
                if ( Thread.currentThread().isInterrupted() ) break;
                // Load next command
                String command = input.next();
                // Supported commands
                switch(command) {
                    case "SEND":
                        room = input.next().trim();
                        String text = input.nextLine().trim();
                        action = new SendMessage(this, room, text);
                        break;

                    case "LIST":
                        action = new ListRooms(this);
                        break;
                        
                    case "CREATE":
                        room = input.nextLine().trim();
                        action = new CreateRoom(this, room);
                        break;
                        
                    case "ENTER":
                        room = input.nextLine().trim();
                        action = new EnterRoom(this, room);
                        break;
                        
                    case "LEAVE":
                        room = input.nextLine().trim();
                        action = new LeaveRoom(this, room);
                        break;
                        
                    // BYE turns off the communication and closes the socket
                    case "BYE":
                        //action = new DiinputonnectAction(this);
                        this.disconnect();
                        return;

                    default:
                        action = new UnknownAction(this);
                        input.nextLine();
                        break;
                }
                Worker.addAction(action);
            }
            
            // When client signs off, close the socket
            socket.close();
            
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getNickname() {
        return nickname;
    }

    private void close() {
        try {
            socket.close();
        } catch ( IOException e ) {}
    }
    
}
