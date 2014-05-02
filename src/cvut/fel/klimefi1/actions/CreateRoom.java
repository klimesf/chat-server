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
public class CreateRoom extends Action {

    private final String name;

    public CreateRoom(Client sender, String name) {
        super(sender);
        this.name = name;
    }
    
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
