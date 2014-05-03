package cvut.fel.klimefi1.actions;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import cvut.fel.klimefi1.*;

/**
 * Action - Create Room
 * Invoked if client creates a room
 * 
 * @author Filip Klimes <klimefi1@fel.cvut.cz>
 */
public class CreateRoom extends Action {

    /**
     * Name of the room to be created.
     */
    private final String name;

    /**
     * Constructor
     * 
     * @param sender client
     * @param name name of the room
     */
    public CreateRoom(Client sender, String name) {
        super(sender);
        this.name = name;
    }
    
    /**
     * Executes the action.
     */
    @Override
    public void execute() {
        try {
            sender.roomCreated( Room.createRoom(name), name );
            System.out.printf("[%s] Created\n", name);
        } catch (IOException ex) {
            Logger.getLogger(CreateRoom.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
