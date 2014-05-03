package cvut.fel.klimefi1.exceptions;

/**
 * Exception thrown if clients tries to interact with non-existing room
 * 
 * @author Filip Klimes <klimefi1@fel.cvut.cz>
 */
public class NoSuchRoomException extends Exception {

    /**
     * Constructor
     * @param message test description of the exception
     */
    public NoSuchRoomException(String message) {
        super(message);
    }

}
