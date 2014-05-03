package cvut.fel.klimefi1.actions;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import cvut.fel.klimefi1.*;
import cvut.fel.klimefi1.exceptions.*;

/**
 * Action - Send Message
 * Invoked if client sends a message to a given room
 * 
 * @author Filip Klimes <klimefi1@fel.cvut.cz>
 */
public class SendMessage extends Action {

    private final String roomId;
    
    private final String text;
    
    /**
     * Constructor
     * 
     * @param sender client
     * @param room name of the room
     * @param text message body
     */
    public SendMessage(Client sender, String room, String text) {
        super(sender);
        this.roomId = room;
        this.text = text;
    }

    /**
     * Executes the action
     */
    @Override
    public void execute() {
        try {
            try {
                Room room = Room.get(roomId);
                if(room.contains(sender)) {
                    room.send(sender, text);
                    sender.messageSent(room, text);
                } else {
                    sender.messageSent(null, "Not in room");
                }

            } catch (NoSuchRoomException ex) {
                Logger.getLogger(SendMessage.class.getName()).log(Level.SEVERE, null, ex);
                sender.messageSent(null, "No such room");
            }
        } catch (IOException ex) {
            Logger.getLogger(SendMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
