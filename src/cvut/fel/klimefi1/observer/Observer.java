/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cvut.fel.klimefi1.observer;

import cvut.fel.klimefi1.actions.Action;

/**
 * Observer class used for observing an IObservable object
 * 
 * @author Filip Klimes <klimefi1@fel.cvut.cz>
 */
public abstract class Observer {
    
    /**
     * Handles an action
     * @param action 
     */
    public abstract void handle(Action action);
    
}
