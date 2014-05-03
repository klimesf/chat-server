package cvut.fel.klimefi1.actions;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import cvut.fel.klimefi1.*;
import cvut.fel.klimefi1.exceptions.NoSuchRoomException;

/**
 * Action - Leave Room
 * Invoked if client leaves a room
 * 
 * @author Filip Klimes <klimefi1@fel.cvut.cz>
 */
public class LeaveRoom extends Action {
    
    /**
     * Name of the room to be left.
     */
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
     * Executes the action.
     */
    @Override
    public void execute() {
        Room roomInstance;
        try {
            roomInstance = Room.get(room);
        } catch (NoSuchRoomException ex) {
            roomInstance = null;
        }
        try {
            if(roomInstance != null && roomInstance.contains(sender)) {
                roomInstance.leave(sender);
                System.out.printf("[%s] %s left\n", room, sender.getNickname());
            }
            sender.leaveRoom(roomInstance);
        } catch (IOException ex) {
            Logger.getLogger(LeaveRoom.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
