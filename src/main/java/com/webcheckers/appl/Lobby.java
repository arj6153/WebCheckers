package com.webcheckers.appl;

import com.webcheckers.model.BoardView;
import com.webcheckers.model.Player;

import java.util.HashMap;

/**
 * Class contains all the active players on the WebChecker Server.
 *
 * @author: Truong Anh Tuan Hoang
 * @author: Alex Johannesson
 * @author: Bin Qiu
 */
public class Lobby {

    // lobby holds the username of a player as a key and the Player as the value
    private final HashMap<String, Player> map;

    /**
     * Constructor of the lobby holding the hashmap.
     */
    public Lobby() {
        this.map = new HashMap<>();
    }

    /**
     * Add player to hashmap.
     * @param name player name
     */
    public synchronized void addPlayer(String name) {
        Player player = new Player(name);
        this.map.put(name, player);
    }

    /**
     * Gets player in hashmap.
     * @param name player name
     * @return player
     */
    public synchronized Player getPlayer(String name) {
       return this.map.get(name);
    }

    /**
     * Hashmap of the players and their names.
     * @return hashmap
     */
    public synchronized HashMap<String,Player> getMap () {
        return this.map;
    }

    /**
     * Gets the size of the hashmap.
     * @return lobby size
     */
    public synchronized int getLobbySize() {
        return this.map.size();
    }

    /**
     * Removes player from hashmap.
     * @param name player name
     */
    public synchronized void removePlayer(String name) {
        this.map.remove(name);
    }

    /**
     * Checks if player exists in hashmap.
     * @param name player name
     * @return true if player exists, else false
     */
    public synchronized boolean playerExists(String name) {
        return this.map.containsKey(name);
    }

    /**
     * Game logic of Webcheckers.
     *
     * @author Michael Merlino
     * @author Truong Anh Tuan Hoang
     */
    public static class Game{
        private final Player redPlayer;
        private final Player whitePlayer;
        private final BoardView board;
        private final int ID;
        private Player playerTurn;
        private int redPieces = 12;
        private int whitePieces = 12;
        private boolean gameOver;

        public enum Color {RED, WHITE, NONE}

        /**
         * Constructor of Game.
         *
         * @param red
         *      player color
         * @param white
         *      opponent color
         */
        public Game(Player playerTurn, Player red, Player white)
        {
            this.playerTurn = playerTurn;
            this.redPlayer = red;
            this.whitePlayer = white;
            this.board = new BoardView();
            this.ID = redPlayer.hashCode() * 31 + whitePlayer.hashCode() * 67;
            gameOver = false;
        }






        /**
         * Get red player, this is the user.
         *
         * @return
         *      red player
         */
        public Player getRedPlayer() {
            return redPlayer;
        }

        /**
         * Get white player, this is the opponent.
         *
         * @return
         *      white player
         */
        public Player getWhitePlayer() {
            return whitePlayer;
        }

        /**
         * Gets current player color.
         *
         * @return
         *      Red player on red's turn, White player on white's turn.
         */
        public Color getPlayerColor() {
            if (playerTurn == redPlayer) {
                return Color.RED;
            } else if (playerTurn == whitePlayer) {
                return Color.WHITE;
            }
            return null;
        }
        /**
         * Checks if player is white player, opponent.
         *
         * @param player
         *      white player
         *
         * @return
         *      true if player is white player, else false
         */
        public boolean isWhitePlayer(Player player) {
            return player.equals(this.whitePlayer);
        }

        /**
         * Checks if player is red player, user.
         *
         * @param player
         *      red player
         *
         * @return
         *      true if player is red player, else false
         */
        public boolean isRedPlayer(Player player) {
            return player.equals(this.redPlayer);
        }

        /**
         * Checks if a given player object is participating in this game.
         * @param player
         *      The player to check
         *
         * @return
         *      True if the player is in the game, false otherwise
         */
        public boolean isPlayerInGame(Player player) {
            return player.equals(redPlayer) || player.equals(whitePlayer);
        }

        /**
         * Gets the game session's ID.
         *
         * @return
         *      game ID
         */
        public int getID()
        {
            return this.ID;
        }

        /**
         * @return
         *      True if the game has ended, false otherwise
         */
        public boolean isGameOver() {
            return gameOver;
        }


        /**
         * @return
         *      True if it is currently the red player's turn
         */
        public boolean isRedTurn() {
            return playerTurn == redPlayer;
        }

        /**
         * @return
         *      The player whose turn it is
         */
        public Player getPlayerTurn() {
            return playerTurn;
        }

        /**
         * Sets which player's turn it is.
         */
        public void setPlayerTurn(Player player) {
            this.playerTurn = player;
        }

        /**
         * Get board
         * @return
         *      the board
         */
         public BoardView getBoard() {
             return this.board;
         }

        /**
         * Get the number of pieces players has
         * @param
         *      color: the color of the player to get num pieces from
         * @return
         *      The num pieces
         */
         public int getNumPieces(Color color) {
            if (color == Color.RED) {
                return this.redPieces;
            }
            return this.whitePieces;
        }

        /**
         *
         */
         public void setGameOver() {
             gameOver = true;
         }





    }
}
