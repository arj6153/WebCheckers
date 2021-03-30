package com.webcheckers.appl;

import com.webcheckers.model.*;
import com.webcheckers.util.Message;

import java.util.*;

import static com.webcheckers.model.BoardView.DIM;

/**
 * Game logic of Webcheckers.
 *
 * @author Michael Merlino
 * @author Truong Anh Tuan Hoang
 */
public class Game {
    private final Player redPlayer;
    private final Player whitePlayer;
    private final BoardView board;
    private final int ID;
    private Player playerTurn;
    private int redPieces = 12;
    private int whitePieces = 12;
    private Move activeMove;
    private boolean gameOver;
    private String gameOverMessage = " game is over";
    private boolean resigned = false;
    private String resignMessage = "";

    public enum Color {RED, WHITE, NONE}

    /**
     * Constructor of Game.
     *
     * @param red   player color
     * @param white opponent color
     */
    public Game(Player playerTurn, Player red, Player white) {
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
     * @return red player
     */
    public Player getRedPlayer() {
        return redPlayer;
    }

    /**
     * Get white player, this is the opponent.
     *
     * @return white player
     */
    public Player getWhitePlayer() {
        return whitePlayer;
    }

    /**
     * Gets current player color.
     *
     * @return Red player on red's turn, White player on white's turn.
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
     * @param player white player
     * @return true if player is white player, else false
     */
    public boolean isWhitePlayer(Player player) {
        return player.equals(this.whitePlayer);
    }

    /**
     * Checks if player is red player, user.
     *
     * @param player red player
     * @return true if player is red player, else false
     */
    public boolean isRedPlayer(Player player) {
        return player.equals(this.redPlayer);
    }

    /**
     * Checks if a given player object is participating in this game.
     *
     * @param player The player to check
     * @return True if the player is in the game, false otherwise
     */
    public boolean isPlayerInGame(Player player) {
        return player.equals(redPlayer) || player.equals(whitePlayer);
    }

    /**
     * Gets the game session's ID.
     *
     * @return game ID
     */
    public int getID() {
        return this.ID;
    }

    /**
     * @return True if the game has ended, false otherwise
     */
    public boolean isGameOver() {
        return gameOver;
    }


    /**
     * @return True if it is currently the red player's turn
     */
    public boolean isRedTurn() {
        return playerTurn == redPlayer;
    }

    /**
     * @return The player whose turn it is
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
     *
     * @return the board
     */
    public BoardView getBoard() {
        return this.board;
    }

    public boolean undoMove(){
        if (this.activeMove != null) {
             activeMove = null;
             return true;
        }
        return false;
    }

    /**
     * Get the number of pieces players has
     *
     * @param color: the color of the player to get num pieces from
     * @return The num pieces
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

    /**
     *
     * NEED DOCSTRING
     *
     *
     */
     public Message isValidMove(Move move) {

         if(activeMove == null) {
             if (jumpCheck(move)) {
                 activeMove = move;
                 activeMove.setType((Move.MoveType.CAPTURE_MOVE));
                 return new Message("Jump is available, please make a jump move.", Message.Type.INFO);
             }
             if (simpleMoveCheck(move)) {
                 activeMove = move;
                 activeMove.setType(Move.MoveType.SINGLE_MOVE);
                 return new Message("Move is valid.", Message.Type.INFO);
             }
             return new Message("Move is invalid.", Message.Type.ERROR);
         }
         return new Message("You already moved.", Message.Type.ERROR);
     }

     public BoardView getRedBoardView() {
         return this.board;
     }

     public BoardView getWhiteBoardView() {
        List<Row> whiteBoard = new ArrayList<>();
        for(int r = 0; r < DIM; r++) {
            Row row = new Row(board.getRow(r).getIndex());
            for (int c = DIM-1; c >=0; c--) {
                Space space = board.getRow(r).getSpace(c);
                row.add(space);
            }
            whiteBoard.add(row);
        }
        return new BoardView(whiteBoard);
    }

    /**
     * Gets the valid move to be submitted to the game.
     *
     * @return
     *      A valid move.
     */
    public Move getActiveMove() {
        return activeMove;
    }

    public void move(Move move, Move.MoveType type){
        int startRow = move.getStart().getRow();
        int startCell = move.getStart().getCell();
        int endRow = move.getEnd().getRow();
        int endCell = move.getEnd().getCell();
        if (type == Move.MoveType.SINGLE_MOVE) {
            Space space = board.getRow(startRow).getSpace(startCell);
            Piece piece = space.getPiece();
            space.setPiece(null);
            board.getRow(endRow).getSpace(endCell).setPiece(piece);
        }
        else if(type == Move.MoveType.CAPTURE_MOVE) {
            Space space = board.getRow(startRow).getSpace(startCell);
            Piece piece = space.getPiece();
            space.setPiece(null);
            board.getRow(endRow).getSpace(endCell).setPiece(piece);
            board.getRow((endRow+startRow)/2).getSpace((endCell+startCell)/2).setPiece(null);
            if(isRedPlayer(playerTurn)) {
                whitePieces--;
            }
            else if(isWhitePlayer(playerTurn)) {
                redPieces--;
            }
        }
    }

    public boolean simpleMoveCheck(Move move) {
        int endRow = move.getEnd().getRow();
        int startRow = move.getStart().getRow();
        int endCol = move.getEnd().getCell();
        int startCol = move.getStart().getCell();
        Space space = board.getRow(startRow).getSpace(startCol);
        if(space.getPiece() != null && space.getPiece().getType() == Piece.Type.SINGLE) {
            if (playerTurn.equals(redPlayer) && (endRow == startRow + 1) &&
                    ((endCol == startCol + 1) || (endCol == startCol - 1))) {
                return true;
            } else return playerTurn.equals(whitePlayer) && (endRow == startRow - 1) &&
                    ((endCol == startCol + 1) || (endCol == startCol - 1));
        }
        else if(space.getPiece() != null && space.getPiece().getType() == Piece.Type.KING) {
            return ((endRow == startRow + 1) || (endRow == startRow - 1)) &&
                    ((endCol == startCol + 1) || (endCol == startCol - 1));
        }
        return false;
    }

    public boolean jumpCheck(Move move) {
        int endRow = move.getEnd().getRow();
        int startRow = move.getStart().getRow();
        int endCol = move.getEnd().getCell();
        int startCol = move.getStart().getCell();
        Space capture = board.getRow((endRow + startRow) / 2).getSpace((endCol + startCol) / 2);
        Space space = board.getRow(startRow).getSpace(startCol);
        if(space.getPiece() != null && space.getPiece().getType()== Piece.Type.SINGLE) {
            if (playerTurn.equals(redPlayer) && endRow == startRow + 2 && (endCol == startCol + 2 || endCol == startCol - 2)) {
                return capture.getPiece() != null && capture.getPiece().getColor() == Color.WHITE;
            } else if (playerTurn.equals(whitePlayer) && endRow == startRow - 2 && (endCol == startCol + 2 || endCol == startCol - 2)) {
                return capture.getPiece() != null && capture.getPiece().getColor() == Color.RED;
            } else {
                return false;
            }
        }
        return false;
    }

    public void resignGame(Player player) {
         resigned = true;
         player.setPlaying(false);
         resignMessage = gameOverMessage + ", " + player.getName() + "has resigned the game";
    }

    public boolean isResigned() {
         return resigned;
    }

    public void endResignGame() {
        redPlayer.setPlaying(false);
        whitePlayer.setPlaying(false);
        setGameOver();
    }

    public void clearActiveMove() {
         activeMove = null;
    }


}
