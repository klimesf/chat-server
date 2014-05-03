package cvut.fel.klimefi1.actions;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import cvut.fel.klimefi1.*;

/**
 * Action - Disconnect
 * Invoked if client disconnects from the server
 * 
 * @author Filip Klimes <klimefi1@fel.cvut.cz>
 */
public class DisconnectAction extends Action {

    /**
     * Constructor
     * 
     * @param sender client
     */
    public DisconnectAction(Client sender) {
        super(sender);
    }

    /**
     * Executes the action
     */
    @Override
    public void execute() {
        try {
            sender.disconnect();
        } catch (IOException ex) {
            Logger.getLogger(DisconnectAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
