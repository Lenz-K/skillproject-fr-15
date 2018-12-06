package alexasescape.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class GameTest {

    private Game game;

    @Before
    public void setUp() {
        List<Room> rooms = new LinkedList<>();
        final int maxAttempts = 2;
        List<Item> items = new ArrayList<>();
        List<Item> items2 = new ArrayList<>();
        items.add(new Item("Test", "xy",false));
        items.add(new Item("Test1", "xyz", true));
        items2.add(new Item("Room2","xyza", true));
        rooms.add(new Room("Room1", items));
        rooms.add(new Room("Room2", items2));
        rooms.add(new Room("Room3", items));
        game = new Game(maxAttempts,rooms, new Player("test", new Highscore()));
    }


    @Test(expected = NullPointerException.class)
    public void testConstructorWithNullAsRoom() {
        new Game(5,null, new Player("test", new Highscore()));
}

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithEmptyRooms() {
        new Game(5, new ArrayList<>(), new Player("test", new Highscore()));
    }

    @Test
    public void testMaxFailedAttempts(){
        assertTrue(game.failed());
        assertTrue(game.failed());
        assertFalse(game.failed());
    }

    @Test
    public void testNextTurn(){
        assertTrue(game.nextTurn("falsch").contains("Wie bitte"));
        assertTrue(game.nextTurn("Test").contains("Test"));
        assertTrue(game.nextTurn("Test1").contains("Room2"));
    }


    @Test
    public void testfinishRoom(){
        final List<Item> items = new ArrayList<>();
        items.add(new Item("Test", "xy",false));
        items.add(new Item("Test1", "xyz", true));
        final Room firstRoom = new Room("Room1", items);
        final Room thirdRoom = new Room("Room3", items);
        assertEquals(firstRoom ,game.getCurrentRoom());
        game.finishRoom();
        game.finishRoom();
        assertEquals(thirdRoom, game.getCurrentRoom());
    }

    @Test
    public void testEqualsSameObject() {
        assertEquals(game, game);
    }

    @Test
    public void testEqualsOtherType() {
        assertNotEquals(game, "Test");
    }

    @Test
    public void testEqualsNull() {
        assertNotEquals(game, null);
    }

}
