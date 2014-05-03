package cvut.fel.klimefi1.actions;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import cvut.fel.klimefi1.*;

/**
 * Action - List Rooms
 * Invoked if client requests a list of rooms
 * 
 * @author Filip Klimes <klimefi1@fel.cvut.cz>
 */
public class ListRooms extends Action {

    /**
     * Constructor
     * 
     * @param sender client
     */
    public ListRooms(Client sender) {
        super(sender);
    }

    /**
     * Executes the action
     */
    @Override
    public void execute() {
        StringBuilder sb = new StringBuilder();
        for(String room : Room.list()) {
            sb = sb.append(room).append(" ");
        }
        try {
            sender.listRooms(sb.toString());
        } catch (IOException ex) {
            Logger.getLogger(ListRooms.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
