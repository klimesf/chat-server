/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cvut.fel.klimefi1.actions;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import cvut.fel.klimefi1.*;
import cvut.fel.klimefi1.exceptions.*;

/**
 *
 * @author filip
 */
public class SendMessage extends Action {

    private final String roomId;
    
    private final String text;
    
    /**
     *
     * @param sender
     * @param room
     * @param text
     */
    public SendMessage(Client sender, String room, String text) {
        super(sender);
        this.roomId = room;
        this.text = text;
    }

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
