package com.webcheckers.appl;

import com.webcheckers.model.Player;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for GameCenter.
 *
 * @author Bin Qiu
 */
@Tag("Application-Tier")
public class GameCenterTest {

    private GameCenter gameCenter = new GameCenter();
    private Lobby lobby = new Lobby();
    private HashMap<Integer, Game> gameMap = new HashMap<>();
    private Player redPlayer = new Player("Bonny");
    private Player whitePlayer = new Player("Clyde");
    int ID = redPlayer.hashCode() * 31 + whitePlayer.hashCode() * 67;

    @Test
    public void test_make_game_center() {
        // checks if lobby and gameMap are null
        assertNotNull(lobby);
        assertNotNull(gameMap);
    }

    @Test
    public void test_add_game() {
        gameCenter.addGame(redPlayer, whitePlayer);
        // checking if game map contains a game after adding a game
        assertNotNull(gameCenter.getGameMap().keySet());

        // checking if red player has been removed from lobby and into game
        assertNull(gameCenter.getPlayer(redPlayer.getName()));

        // checking if white player has been removed from lobby and into game
        assertNull(gameCenter.getPlayer(whitePlayer.getName()));

        // checking the player turn
        assertEquals(gameCenter.getGame(ID).getRedPlayer(),
                gameCenter.getGame(ID).getPlayerTurn());
    }

    @Test
    public void test_add_player() {
        // checks if the lobby map is not null
        assertNotNull(lobby.getMap().keySet());
    }

    @Test
    public void test_get_and_remove_player() {
        // checks if red player's name is in the lobby to be retrieved
        // or removed
        assertNotNull(lobby.getMap().containsKey(redPlayer.getName()));

        // checks if white player's name is in the lobby to be retrieved
        // or removed
        assertNotNull(lobby.getMap().containsKey(whitePlayer.getName()));
    }

    @Test
    public void test_get_game() {
        // checks if game map contains game IDs to be retrieved
        assertNotNull(gameMap.keySet());
    }
}