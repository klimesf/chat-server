/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cvut.fel.klimefi1.actions;

import cvut.fel.klimefi1.*;

/**
 *
 * @author filip
 */
public abstract class Action {

    protected final Client sender;

    public Action(Client sender) {
        this.sender = sender;
    }
    
    abstract public void execute();    
}
