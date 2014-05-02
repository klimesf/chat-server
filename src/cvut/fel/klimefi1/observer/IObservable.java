/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cvut.fel.klimefi1.observer;

import cvut.fel.klimefi1.actions.Action;

/**
 * Observable object can register and unregister observers and notify them about
 * actions
 * 
 * @author Filip Klimes <klimefi1@fel.cvut.cz>
 */
public interface IObservable {
    
    /**
     * Registers an observer
     * @param observer 
     */
    void registerObserver(Observer observer);
    
    /**
     * Unregisters an observer, if there is such one registered
     * @param observer 
     */
    void unregisterObserver(Observer observer);
    
    /**
     * Notifies all registered observers about action
     * @param action 
     */
    void notifyObservers(Action action);
    
}
