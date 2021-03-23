package com.webcheckers.model;
import org.junit.jupiter.api.Test;
import com.webcheckers.model.Player;
import spark.*;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Tests for Player in model tier
 *
 * @author Alex Johannesson
 */
public class PlayerTest {
    private String name = "Test Name";


    @Test
    public void getNameTest() {
        Player player = new Player(name);
        String actualName = player.getName();
        assertEquals(name, actualName, "The Name of the player should be: " + actualName);
    }

    @Test
    public void hashTest() {
        int hash = this.name.hashCode();
        Player player = new Player(name);
        int hashValue = player.hashCode();
        assertEquals(hashValue, hash, "The correct hash of the player should be: " + hash);
    }

    @Test
    public void isPlayingTest() {
        Player player = new Player("Player");
        if(player.isPlaying() == true) {
            player.isPlaying();
        }

    }

    @Test
    public void equalsTest() {
        Player player1 = new Player("Player1");
        Player player2 = new Player("Player2");
        boolean equals = player1.equals(player2);
        assertTrue(equals);
    }

}
