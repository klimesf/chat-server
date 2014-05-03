package cvut.fel.klimefi1;

import cvut.fel.klimefi1.exceptions.NoSuchRoomException;
import java.util.*;

/**
 * Class representing a single room
 * 
 * @author Karel Cemus
 * @author Filip Klimes <klimefi1@fel.cvut.cz>
 */
public class Room {

    /**
     * Map representing a list of all rooms on the server
     */
    private static final Map<String, Room> rooms = new HashMap<String, Room>();

    /**
     * Room name
     */
    private final String name;

    /**
     * List of present clients
     */
    private final Set<Client> clients = new HashSet<Client>();

    /**
     * Creates a room with given name
     * @param name
     * @return
     */
    public static boolean createRoom( String name ) {
        if(Room.rooms.containsKey(name)) {
            return false;
        } else {
            Room room = new Room(name);
            Room.rooms.put(name, room);
            return true;
        }
    }

    /**
     * Lists all the rooms on the server
     * @return list of the servers
     */
    public static Set<String> list() {
        return rooms.keySet();
    }

    /**
     * Returns room with the given name
     * @param name
     * @return
     * @throws cvut.fel.klimefi1.exceptions.NoSuchRoomException
     */
    public static Room get( String name ) throws NoSuchRoomException {
        if(!rooms.containsKey(name)) {
            throw new NoSuchRoomException("Room " + name + " does not exist on this server!");
        }
        return rooms.get(name);
    }

    /**
     * Creates a new room
     * Protected because we want to follow factory pattern, therefore we don't
     * want anyone else than the siblings (and any other class in the package -
     * - because Java sucks) to instantiate Room class
     * @param name Name of the room
     */
    protected Room( String name ) {
        this.name = name;
    }

    /**
     * Sends message to all clients in the room
     * @param sender
     * @param message 
     */
    public void send( Client sender, String message ) {
        System.out.println( String.format( "[%s] %s: %s", name, sender.getNickname(), message ) );
        
        for(Client client : clients) {
            client.send(name, sender.getNickname(), message);
        }
    }

    /**
     * Enter client to the room
     * @param client client instance
     */
    public void enter( Client client ) {
        this.clients.add(client);
    }

    /**
     * Finds out whether the client is in the room
     * @param client
     * @return client instance
     */
    public boolean contains( Client client ) {
        return this.clients.contains(client);
    }

    /**
     * Leave client from the room
     * @param client client instance
     */
    public void leave( Client client ) {
        this.clients.remove(client);
    }

    /**
     * Returns name of the room
     * @return name of the room
     */
    public String getName() {
        return name;
    }
}
