package cvut.fel.klimefi1.actions;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import cvut.fel.klimefi1.*;

/**
 * Action - Leave Room
 * Invoked if client sends an unknown request
 * 
 * @author Filip Klimes <klimefi1@fel.cvut.cz>
 */
public class UnknownAction extends Action {

    /**
     * Constructor
     * 
     * @param sender client
     */
    public UnknownAction(Client sender) {
        super(sender);
    }

    /**
     * Executes the action.
     */
    @Override
    public void execute() {
        try {
            sender.unsupportedAction();
        } catch (IOException ex) {
            Logger.getLogger(UnknownAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
