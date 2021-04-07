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
 * @author Bin Qiu
 * @author Alex Johannesson
 */
public class Game extends GameCenter {

    public enum Color {RED, WHITE, NONE}

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
    private String resignMessage = "";
//    private Piece redKing = new Piece(Piece.Type.KING, Color.RED);
//    private Piece whiteKing = new Piece(Piece.Type.KING, Color.WHITE);

    /**
     * Constructor of a Game.
     *
     * @param red
     *      The red player
     * @param white
     *      The white player
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
     * @return
     *      The red player
     */
    public Player getRedPlayer() {
        return redPlayer;
    }

    /**
     * @return
     *      The white player
     */
    public Player getWhitePlayer() {
        return whitePlayer;
    }

    /**
     * @return
     *      The red player on red's turn, the white player on white's turn
     */
    public Color getPlayerColor() {
        if(isRedPlayer(playerTurn)) {
            return Color.RED;
        } else if(isWhitePlayer(playerTurn)) {
            return Color.WHITE;
        }
        return null;
    }

    /**
     * Checks if player is white player.
     *
     * @param player
     *      The white player
     * @return
     *      True if player is the white player, else false
     */
    public boolean isWhitePlayer(Player player) {
        return player.equals(this.whitePlayer);
    }

    /**
     * Checks if player is red player.
     *
     * @param player
     *      The red player
     * @return
     *      True if player is red player, else false
     */
    public boolean isRedPlayer(Player player) {
        return player.equals(this.redPlayer);
    }

    /**
     * Checks if a given player is participating in this game.
     *
     * @param player
     *      The player being checked
     * @return
     *      True if the player is in this game, false otherwise
     */
    public boolean isPlayerInGame(Player player) {
        return isRedPlayer(player) || isWhitePlayer(player);
    }

    /**
     * @return
     *      The game ID of this game
     */
    public int getID() {
        return this.ID;
    }

    /**
     * @return
     *      True if this game has ended, false otherwise
     */
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * @return
     *      True if it is currently the red player's turn
     */
    public boolean isRedTurn() {
        return playerTurn.equals(redPlayer);
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
     *
     * @param player
     *      The player
     */
    public void setPlayerTurn(Player player) {
        this.playerTurn = player;
    }

    /**
     * Backup the piece to the previous position.
     *
     * @return
     *      True if there is a position to backup to, else false
     */
    public boolean backupMove(){
        if (this.activeMove != null) {
             activeMove = null;
             return true;
        }
        return false;
    }

    /**
     * Get the number of pieces a player has on the game board.
     *
     * @param color
     *      The color of the player to get number pieces
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
     * Sets the game to game over.
     */
    public void setGameOver() {
        redPlayer.setPlaying(false);
        whitePlayer.setPlaying(false);
        gameOver = true;
    }

    /**
     * Checks if a move is valid according to American Checker Rules.
     *
     * @param move
     *      The move being checked
     * @return
     *      True if move is valid and a valid message is sent to the player,
     *      false otherwise and an error messageis sent to the player
     */
     public Message isValidMove(Move move) {
         if(activeMove == null) {
             if (jumpCheck(move)) {
                 activeMove = move;
                 activeMove.setType((Move.MoveType.CAPTURE_MOVE));
                 return new Message("Jump is valid.", Message.Type.INFO);
             }
             if (simpleMoveCheck(move)) {
                 if(canJump(move.getStart())) {
                     return new Message("Jump available. You must jump.", Message.Type.ERROR);
                 }
                 activeMove = move;
                 activeMove.setType(Move.MoveType.SINGLE_MOVE);
                 return new Message("Move is valid.", Message.Type.INFO);
             }
             return new Message("Move is invalid.", Message.Type.ERROR);
         }
         return new Message("You already moved.", Message.Type.ERROR);
     }

    /**
     * @return
     *      The red player's board view
     */
     public BoardView redBoardView() {
         return this.board;
     }

    /**
     * @return
     *      The white player's board view
     */
    public BoardView whiteBoardView() {
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
     * @return
     *      A valid move submitted to the game.
     */
    public Move getActiveMove() {
        return activeMove;
    }

    /**
     * The move made by a player in the game.
     *
     * @param move
     *      The move made on the game board
     * @param type
     *      The type of move (simple, single jump, multiple jump)
     */
    public void move(Move move, Move.MoveType type){
        int startRow = move.getStart().getRow();
        int startCell = move.getStart().getCell();
        int endRow = move.getEnd().getRow();
        int endCell = move.getEnd().getCell();
        Space space = board.getRow(startRow).getSpace(startCell);
        Piece piece = space.getPiece();
        if (isWhitePlayer(playerTurn) && board.getRow(endRow).getIndex() == 0) {
            piece.setKing();
        } else if (isRedPlayer(playerTurn) && board.getRow(endRow).getIndex()== DIM-1) {
            piece.setKing();
        }
        space.setPiece(null);
        if (type == Move.MoveType.SINGLE_MOVE) {
            board.getRow(endRow).getSpace(endCell).setPiece(piece);
        }
        else if(type == Move.MoveType.CAPTURE_MOVE) {
            board.getRow(endRow).getSpace(endCell).setPiece(piece);
            board.getRow((endRow+startRow)/2).getSpace((endCell+startCell)/2).setPiece(null);
            if(isRedPlayer(playerTurn)) {
                whitePieces--;
            }
            else if(isWhitePlayer(playerTurn)) {
                redPieces--;
            }
        }
        if (whitePieces == 0 || redPieces == 0) {
            setGameOver();
        }
    }

    /**
     * Checks if a move is a simple move.
     *
     * @param move
     *      The move made
     * @return
     *      True if the move is a simple move, false otherwise
     */
    public boolean simpleMoveCheck(Move move) {
        int endRow = move.getEnd().getRow();
        int startRow = move.getStart().getRow();
        int endCol = move.getEnd().getCell();
        int startCol = move.getStart().getCell();
        Space space = getSpace(startRow, startCol);
        if(space.getPiece() != null && space.getPiece().getType() == Piece.Type.SINGLE) {
            if (isRedPlayer(playerTurn) && (endRow == startRow + 1) &&
                    ((endCol == startCol + 1) || (endCol == startCol - 1))) {
                return true;
            } else return isWhitePlayer(playerTurn) && (endRow == startRow - 1) &&
                    ((endCol == startCol + 1) || (endCol == startCol - 1));
        }
        else if(space.getPiece() != null && space.getPiece().getType() == Piece.Type.KING) {
            return ((endRow == startRow + 1) || (endRow == startRow - 1)) &&
                    ((endCol == startCol + 1) || (endCol == startCol - 1));
        }
        return false;
    }

    /**
     * Gets the space using specified row and column number of the game board.
     *
     * @param rowIdx
     *      The row number
     * @param cellIdx
     *      The column number
     * @return
     *      The space specified by the row and column number
     */
    public Space getSpace(int rowIdx, int cellIdx) {
        return this.board.getRow(rowIdx).getSpace(cellIdx);
    }

    /**
     * Checks if a move is a jump move.
     *
     * @param move
     *      The move made
     * @return
     *      True if the move is a jump move, false otherwise
     */
    public boolean jumpCheck(Move move) {
        int endRow = move.getEnd().getRow();
        int startRow = move.getStart().getRow();
        int endCol = move.getEnd().getCell();
        int startCol = move.getStart().getCell();
        Space capture = board.getRow((endRow + startRow) / 2).getSpace((endCol + startCol) / 2);
        Space startSpace = board.getRow(startRow).getSpace(startCol);
        Space endSpace = board.getRow(endRow).getSpace(endCol);
        if(startSpace.getPiece() != null && startSpace.getPiece().getType()== Piece.Type.SINGLE) {
            if (playerTurn.equals(redPlayer) && endRow == startRow + 2 && (endCol == startCol + 2 || endCol == startCol - 2)) {
                return capture.getPiece() != null && endSpace.getPiece() == null && capture.getPiece().getColor() == Color.WHITE;
            } else if (playerTurn.equals(whitePlayer) && endRow == startRow - 2 && (endCol == startCol + 2 || endCol == startCol - 2)) {
                return capture.getPiece() != null && endSpace.getPiece() == null && capture.getPiece().getColor() == Color.RED;
            } else {
                return false;
            }
        }
        else if(startSpace.getPiece() != null && startSpace.getPiece().getType() == Piece.Type.KING) {
            if(((endRow == startRow + 2) || (endRow == startRow - 2)) &&
                    ((endCol == startCol + 2) || (endCol == startCol - 2))) {
                return capture.getPiece() != null && capture.getPiece().getColor() != getPlayerColor();
            }
        }
        return false;
    }

    public boolean canJump(Object obj) {
        ArrayList<Move> availJumpSpots = new ArrayList<>();
        Position prevStartPos = null;
        Position startPos = null;
        if (obj instanceof Move) {
            prevStartPos = activeMove.getStart();
            startPos = activeMove.getEnd();
        } else if (obj instanceof Position) {
            startPos = (Position) obj;
        }
        availJumpSpots.add(new Move(startPos, new Position(startPos.getRow()+2, startPos.getCell()-2)));
        availJumpSpots.add(new Move(startPos, new Position(startPos.getRow()+2, startPos.getCell()+2)));
        availJumpSpots.add(new Move(startPos, new Position(startPos.getRow()-2, startPos.getCell()-2)));
        availJumpSpots.add(new Move(startPos, new Position(startPos.getRow()-2, startPos.getCell()+2)));
        for (Move move: availJumpSpots) {
            if ((prevStartPos != null && (move.getEnd() == prevStartPos)) || !isInRange(move.getEnd())) {
                continue;
            }
            if (jumpCheck(move)) {
                return true;
            }
        }
        return false;
    }

//    /**
//     * Checks if a checker piece can jump again after jumping already.
//     *
//     * @param activeMove
//     *      The previous jump move
//     *
//     * @return
//     *       True is the checker piece can jump again, false otherwise
//     */
//    public boolean canJump(Move activeMove) {
//        ArrayList<Move> availJumpSpots = new ArrayList<>();
//        Position prevStartPos = activeMove.getStart();
//        Position startPos = activeMove.getEnd();
//        availJumpSpots.add(new Move(startPos, new Position(startPos.getRow()+2, startPos.getCell()-2)));
//        availJumpSpots.add(new Move(startPos, new Position(startPos.getRow()+2, startPos.getCell()+2)));
//        availJumpSpots.add(new Move(startPos, new Position(startPos.getRow()-2, startPos.getCell()-2)));
//        availJumpSpots.add(new Move(startPos, new Position(startPos.getRow()-2, startPos.getCell()+2)));
//        for (Move move: availJumpSpots) {
//            if ((move.getEnd() == prevStartPos) || !isInRange(move.getEnd())) {
//                continue;
//            }
//            if (jumpCheck(move)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public boolean canJump(Position startPos) {
//        ArrayList<Move> availJumpSpots = new ArrayList<>();
//        availJumpSpots.add(new Move(startPos, new Position(startPos.getRow()+2, startPos.getCell()-2)));
//        availJumpSpots.add(new Move(startPos, new Position(startPos.getRow()+2, startPos.getCell()+2)));
//        availJumpSpots.add(new Move(startPos, new Position(startPos.getRow()-2, startPos.getCell()-2)));
//        availJumpSpots.add(new Move(startPos, new Position(startPos.getRow()-2, startPos.getCell()+2)));
//        for (Move move: availJumpSpots) {
//            if (jumpCheck(move)) {
//                return true;
//            }
//        }
//        return false;
//    }

    /**
     * Checks if space is in boundary of the checker board.
     *
     * @param position
     *      The position being checked
     *
     * @return
     *      True if in bounds, false otherwise
     */
    public boolean isInRange(Position position) {
        return ((position.getRow() < DIM && position.getRow() >= 0) &&
                (position.getCell() < DIM && position.getCell() >= 0));
    }

    /**
     * Resign from the game.
     *
     * @param player
     *      The player that resigns
     * @return
     *      A message stating that the player has resigned
     */
    public Message resignGame(Player player) {
         this.gameOver = true;
         player.setPlaying(false);
         return Message.info(gameOverMessage + ", " + player.getName() + "has resigned the game");
    }

    /**
     * Clears the current active move and set it to null.
     */
    public void clearActiveMove() {
         activeMove = null;
    }

//    public Piece kingCheck(Move move) {
//        int endRow = move.getEnd().getRow();
//        int startRow = move.getStart().getRow();
//        int startCol = move.getStart().getCell();
//        Piece piece = new Piece(board.getRow(startRow).getSpace(startCol).getPiece().getType(), getPlayerColor());
//        if( board.getRow(startRow).getSpace(startCol).getPiece().getType() == Piece.Type.SINGLE) {
//            if (playerTurn.equals(redPlayer)){
//                if(endRow == 7) {
//                    piece = redKing;
//                } else {
//                    return piece;
//                }
//            } else if (playerTurn.equals(whitePlayer)) {
//                if(endRow == 0) {
//                    piece = whiteKing;
//                } else {
//                    return piece;
//                }
//            }
//        }
//        return piece;
//    }
}