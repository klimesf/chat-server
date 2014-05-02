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
public class ListRooms extends Action {

    /**
     *
     * @param sender
     */
    public ListRooms(Client sender) {
        super(sender);
    }

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
