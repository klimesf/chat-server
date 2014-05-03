package cvut.fel.klimefi1.actions;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import cvut.fel.klimefi1.*;
import cvut.fel.klimefi1.exceptions.*;

/**
 * Action - Leave Room
 * Invoked if client leaves a room
 * 
 * @author Filip Klimes <klimefi1@fel.cvut.cz>
 */
public class LeaveRoom extends Action {
    
    private final String room;

    /**
     * Constructor
     * 
     * @param sender client
     * @param room name of the room
     */
    public LeaveRoom(Client sender, String room) {
        super(sender);
        this.room = room;
    }

    /**
     * Executes the action
     */
    @Override
    public void execute() {
        try {
            try {
                Room roomInstance = Room.get(room);
                sender.leaveRoom(roomInstance);
                roomInstance.leave(sender);
                System.out.printf("[%s] %s left\n", room, sender.getNickname());
            } catch (NoSuchRoomException ex) {
                sender.leaveRoom(null);
                Logger.getLogger(LeaveRoom.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(LeaveRoom.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
