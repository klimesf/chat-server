package cvut.fel.klimefi1;

import cvut.fel.klimefi1.actions.*;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Main thread of the server
 * Handles all the actions
 *
 * @author Karel Cemus
 */
public class Worker implements Runnable {

    private static final Queue<Action> actions = new LinkedList<>();

    public static void addAction( Action action ) {
        synchronized ( actions ) {
            actions.add( action );
            actions.notify();
        }
    }

    @Override
    public void run() {
        try {
            while ( true ) {

                if ( Thread.currentThread().isInterrupted() ) break;

                Action action;

                synchronized ( actions ) {

                    // pockej dokud neni pripravena nejaka akce
                    while ( actions.isEmpty() ) actions.wait();
                    // ziskej akci ke zpracovani
                    action = actions.poll();
                }

                // zpracuj akci mimo synchronized, uz nepracujeme v chranenem bloku
                action.execute();
            }
        } catch ( InterruptedException e ) {
            System.out.println( "Worker prerusen." );
        } finally {
            System.out.println( "Worker se ukoncuje." );
        }
    }
}
