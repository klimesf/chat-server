/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cvut.fel.klimefi1.observer;

import cvut.fel.klimefi1.actions.Action;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author filip
 */
public class LogObserver extends Observer {

    private final DataOutputStream dos;
    
    private final File file;
    
    /**
     * Constructor
     * This observer will be logging things into file specified by filename
     * @param filename Name of the file
     * @throws FileNotFoundException
     */
    public LogObserver(String filename) throws FileNotFoundException {
        this.file = new File(filename);
        this.dos = new DataOutputStream(new FileOutputStream(this.file));
    }
    
    /**
     * Handles action
     * @param action 
     */
    @Override
    public void handle(Action action) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        
        StringBuilder sb = new StringBuilder();
        sb.append("[" + dateFormat.format(date) + "] ");
        sb.append(action.toString());
        
        try {
            this.dos.writeBytes(sb.toString());
        } catch (IOException ex) {
            // ...
        }
    }
    
}
