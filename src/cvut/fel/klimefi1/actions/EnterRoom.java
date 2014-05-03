package cvut.fel.klimefi1.actions;

import cvut.fel.klimefi1.*;
import cvut.fel.klimefi1.exceptions.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Action - Enter Room
 * Invoked if client enters a room
 * 
 * @author Filip Klimes <klimefi1@fel.cvut.cz>
 */
public class EnterRoom extends Action {
    
    private final String room;

    /**
     * Constructor
     * 
     * @param sender client
     * @param room name of the room
     */
    public EnterRoom(Client sender, String room) {
        super(sender);
        this.room = room;
    }

    /**
     * Executes the action.
     */
    @Override
    public void execute() {
        try {
            try {
                Room roomInstance = Room.get(room);
                if(!roomInstance.contains(sender)) {
                    roomInstance.enter(sender);
                    System.out.printf("[%s] %s entered\n", room, sender.getNickname());
                }
                sender.enterRoom(roomInstance);
            } catch (NoSuchRoomException ex) {
                Logger.getLogger(EnterRoom.class.getName()).log(Level.SEVERE, null, ex);
                sender.enterRoom(null);
            }
        } catch (IOException ex) {
            Logger.getLogger(EnterRoom.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
