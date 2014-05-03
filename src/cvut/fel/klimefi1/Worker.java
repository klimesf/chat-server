package cvut.fel.klimefi1;

import cvut.fel.klimefi1.actions.*;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Main thread of the server. Handles all the actions, which are stored in a
 * queue.
 *
 * @author Karel Cemus
 */
public class Worker implements Runnable {

    /**
     * Queue of the actions.
     */
    private static final Queue<Action> actions = new LinkedList<>();

    /**
     * Adds action to the Worker queue.
     * 
     * @param action to be added and executed
     */
    public static void addAction(Action action) {
        synchronized (actions) {
            actions.add(action);
            actions.notify();
        }
    }

    /**
     * Runs the thread. Implementation of Runnable interface.
     */
    @Override
    public void run() {
        try {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    break;
                }

                Action action;
                synchronized (actions) {
                    // wait for new action
                    while (actions.isEmpty()) {
                        actions.wait();
                    }
                    // retrieve the action
                    action = actions.poll();
                }

                action.execute();
            }
        } catch (InterruptedException e) {
            System.out.println("Worker prerusen.");
        } finally {
            System.out.println("Worker se ukoncuje.");
        }
    }
}
