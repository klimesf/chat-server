package cvut.fel.klimefi1.actions;

import cvut.fel.klimefi1.*;

/**
 * Action abstract class. Actions are executed by worker thread.
 * 
 * @author Filip Klimes <klimesf@fel.cvut.cz>
 */
public abstract class Action {

    /**
     * Sender of the action.
     */
    protected final Client sender;

    /**
     * Constructor
     * 
     * @param sender 
     */
    public Action(Client sender) {
        this.sender = sender;
    }
    
    /**
     * Executes the action.
     */
    abstract public void execute();    
}
