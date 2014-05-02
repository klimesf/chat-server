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
public class DisconnectAction extends Action {

    public DisconnectAction(Client sender) {
        super(sender);
    }

    @Override
    public void execute() {
        try {
            sender.disconnect();
        } catch (IOException ex) {
            Logger.getLogger(DisconnectAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
