/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cvut.fel.klimefi1.actions;

import cvut.fel.klimefi1.*;
import cvut.fel.klimefi1.exceptions.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author filip
 */
public class EnterRoom extends Action {
    
    private final String room;

    public EnterRoom(Client sender, String room) {
        super(sender);
        this.room = room;
    }

    @Override
    public void execute() {
        try {
            try {
                Room roomInstance = Room.get(room);
                if(!roomInstance.contains(sender)) {
                    roomInstance.enter(sender);
                    System.out.printf("[%s] %s entered\n", room, sender.getNickname());
                }
                sender.enterRoom(roomInstance);
            } catch (NoSuchRoomException ex) {
                Logger.getLogger(EnterRoom.class.getName()).log(Level.SEVERE, null, ex);
                sender.enterRoom(null);
            }
        } catch (IOException ex) {
            Logger.getLogger(EnterRoom.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
