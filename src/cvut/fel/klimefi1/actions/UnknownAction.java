/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cvut.fel.klimefi1.actions;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import cvut.fel.klimefi1.*;

/**
 *
 * @author filip
 */
public class UnknownAction extends Action {

    public UnknownAction(Client sender) {
        super(sender);
    }

    @Override
    public void execute() {
        try {
            sender.unsupportedAction();
        } catch (IOException ex) {
            Logger.getLogger(UnknownAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
