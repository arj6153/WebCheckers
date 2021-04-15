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
public class Game {

    public enum Color {RED, WHITE, NONE}

    private final Player redPlayer;
    private final Player whitePlayer;
    private final BoardView board;
    private final int ID;
    private Player playerTurn;
    private int redPieces = 12;
    private int whitePieces = 12;
    private Deque<Move> activeMove;
    private boolean gameOver;
    private String gameOverMessage = "game is over";


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
        activeMove = new LinkedList<>();
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
        if (!activeMove.isEmpty()) {
             activeMove.pollLast();
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
     *      false otherwise and an error message is sent to the player
     */
     public Message isValidMove(Move move) {
         if (simpleMoveCheck(move)) {
             if(canJump()) {
                 return new Message("Jump available. You must jump.", Message.Type.ERROR);
             }
             activeMove.add(move);
             activeMove.peekLast().setType(Move.MoveType.SINGLE_MOVE);
             move.printMove();
             return new Message("Move is valid.", Message.Type.INFO);
         } else if (jumpCheck(move)) {
             activeMove.add(move);
             assert activeMove.peekLast() != null;
             activeMove.peekLast().setType((Move.MoveType.CAPTURE_MOVE));
             move.printMove();
             return new Message("Jump is valid.", Message.Type.INFO);
         }
         return new Message("Move is invalid.", Message.Type.ERROR);
     }
     public String getGameOverMessage() {
         return gameOverMessage;
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
     *
     * @return
     */
    public Move getFrontMove() {
        if (!activeMove.isEmpty()) {
            return activeMove.peekFirst();
        }
        return null;
    }

    /**
     * @return
     *      A valid move submitted to the game.
     */
    public Move getLatestMove() {
        if (!activeMove.isEmpty()) {
            return activeMove.peekLast();
        }
        return null;
    }
    public Move pollMove() {
        if(!activeMove.isEmpty()) {
            return activeMove.pollFirst();
        }
        return null;
    }

    public Move pollLastMove() {
        if (!activeMove.isEmpty()) {
            return activeMove.pollLast();
        }
        return null;
    }

    public boolean addMove(Move move) {
        return activeMove.add(move);
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
            String whoLose = "";
            String whoWon = "";
            if (whitePieces==0) {
                whoLose += whitePlayer.getName();
                whoWon += redPlayer.getName();
            } else {
                whoLose += redPlayer.getName();
                whoWon += whitePlayer.getName();
            }
            gameOverMessage += whoLose + " loses the game." + whoWon + " has won";
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
        boolean b = ((endRow == startRow + 2) || (endRow == startRow - 2)) && ((endCol == startCol + 2) || (endCol == startCol - 2));
        if (activeMove.isEmpty()) {
            System.out.println("hello5");
            if (startSpace.getPiece() != null && startSpace.getPiece().getType() == Piece.Type.SINGLE) {
                System.out.println("hello6");
                if (playerTurn.equals(redPlayer) && endRow == startRow + 2 && (endCol == startCol + 2 || endCol == startCol - 2)) {
                    System.out.println("hello7");
                    return capture.getPiece() != null && endSpace.getPiece() == null && capture.getPiece().getColor() == Color.WHITE;
                } else if (playerTurn.equals(whitePlayer) && endRow == startRow - 2 && (endCol == startCol + 2 || endCol == startCol - 2)) {
                    System.out.println("hello8");
                    return capture.getPiece() != null && endSpace.getPiece() == null && capture.getPiece().getColor() == Color.RED;
                } else {
                    return false;
                }
            } else if (startSpace.getPiece() != null && startSpace.getPiece().getType() == Piece.Type.KING) {
                System.out.println("helloKing3");
                if (b) {
                    System.out.println("helloKing4");
                    return capture.getPiece() != null && capture.getPiece().getColor() != getPlayerColor()
                            && endSpace.getPiece() == null;
                }
            }
        }
        if (activeMove.peekFirst().getType() == Move.MoveType.CAPTURE_MOVE) {
            System.out.println("hello");
            Space originSpace = board.getRow(activeMove.peekFirst().getStart().getRow()).getSpace(activeMove.peekFirst().getStart().getCell());
            // checks original space is not null and it's a single checker piece
            if (originSpace.getPiece() != null && originSpace.getPiece().getType() == Piece.Type.SINGLE) {
                System.out.println("hello2");
                if (playerTurn.equals(redPlayer) && endRow == startRow + 2 && (endCol == startCol + 2 || endCol == startCol - 2)) {
                    System.out.println("hello3");
                    return capture.getPiece() != null && endSpace.getPiece() == null && capture.getPiece().getColor() == Color.WHITE;
                } else if (playerTurn.equals(whitePlayer) && endRow == startRow - 2 && (endCol == startCol + 2 || endCol == startCol - 2)) {
                    System.out.println("hello4");
                    return capture.getPiece() != null && endSpace.getPiece() == null && capture.getPiece().getColor() == Color.RED;
                } else {
                    return false;
                }
            } else if (originSpace.getPiece() != null && originSpace.getPiece().getType() == Piece.Type.KING) {
                Move previousMove = activeMove.peekLast();
                assert previousMove != null;
                if (previousMove.getStart().equal(move.getEnd())) {
                    return false;
                }
                System.out.println("hello king1");
                if (b) {
                    System.out.println("hello king2");
                    return capture.getPiece() != null && capture.getPiece().getColor() != getPlayerColor()
                            && endSpace.getPiece() == null;
                }
            }
        }
        return false;
    }

//    public boolean canJump(Object obj) {
//        ArrayList<Move> availJumpSpots = new ArrayList<>();
//        Position prevStartPos = null;
//        Position startPos = null;
//        if (obj instanceof Move) {
//            prevStartPos = activeMove.peek().getStart();
//            startPos = activeMove.peek().getEnd();
//        } else if (obj instanceof Position) {
//            startPos = (Position) obj;
//        }
//        availJumpSpots.add(new Move(startPos, new Position(startPos.getRow()+2, startPos.getCell()-2)));
//        availJumpSpots.add(new Move(startPos, new Position(startPos.getRow()+2, startPos.getCell()+2)));
//        availJumpSpots.add(new Move(startPos, new Position(startPos.getRow()-2, startPos.getCell()-2)));
//        availJumpSpots.add(new Move(startPos, new Position(startPos.getRow()-2, startPos.getCell()+2)));
//        for (Move move: availJumpSpots) {
//            if ((prevStartPos != null && (move.getEnd() == prevStartPos)) || !isInRange(move.getEnd())) {
//                continue;
//            }
//            if (jumpCheck(move)) {
//                return true;
//            }
//        }
//        return false;
//    }

    /**
     * Checks if a checker piece can jump again after jumping already.
     *
     * @param activeMove
     *      The previous jump move
     *
     * @return
     *       True is the checker piece can jump again, false otherwise
     */
    public boolean canJump(Move activeMove) {
        ArrayList<Move> availJumpSpots = new ArrayList<>();
        Position prevStartPos = activeMove.getStart();
        Position startPos = activeMove.getEnd();
        canJumpHelper(availJumpSpots, startPos);
        for (Move move: availJumpSpots) {
            if ((move.getEnd().equal(prevStartPos)) || isNotInRange(move.getEnd())) {
                continue;
            }
            if (jumpCheck(move)) {
                move.printMove();
                return true;
            }
        }
        return false;
    }


    private void canJumpHelper(ArrayList<Move> availJumpSpots, Position startPos) {
        availJumpSpots.add(new Move(startPos, new Position(startPos.getRow()+2, startPos.getCell()-2)));
        availJumpSpots.add(new Move(startPos, new Position(startPos.getRow()+2, startPos.getCell()+2)));
        availJumpSpots.add(new Move(startPos, new Position(startPos.getRow()-2, startPos.getCell()-2)));
        availJumpSpots.add(new Move(startPos, new Position(startPos.getRow()-2, startPos.getCell()+2)));
    }

    /**
     * Checks if checker piece can jump.
     *
     * @return
     *      True if checker piece can jump at initial position of the turn
     */
    public boolean canJump() {
        for (Row row : redBoardView()) {
            for (Space space : row) {
                Piece piece = space.getPiece();
                if ((piece == null) || (piece.getColor() != getPlayerColor())) {
                    continue;
                }
                Position startPos = new Position(row.getIndex(), space.getCellIdx());
                // stores all the possible jump moves of a piece
                ArrayList<Move> availJumpSpots = new ArrayList<>();
                canJumpHelper(availJumpSpots, startPos);
                // check if those jump moves are valid
                for (Move move : availJumpSpots) {
                    if (isNotInRange(move.getEnd())) {
                        continue;
                    }
                    if (jumpCheck(move)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Checks if space is in boundary of the checker board.
     *
     * @param position
     *      The position being checked
     *
     * @return
     *      True if in bounds, false otherwise
     */
    public boolean isNotInRange(Position position) {
        return ((position.getRow() >= DIM || position.getRow() < 0) ||
                (position.getCell() >= DIM || position.getCell() < 0));
    }

    /**
     * Resign from the game.
     *
     * @param player
     *      The player that resigns
     * @return
     *      A message stating that the player has resigned
     */
    public void resignGame(Player player) {
         this.gameOver = true;
         player.setPlaying(false);
         gameOverMessage = player.getName() + " has resigned the game. You won";
    }

    /**
     * Clears the current active move and set it to null.
     */
    public void clearActiveMove() {
         activeMove.clear();
    }

    public void setGameOverMessage(String message) {
        gameOverMessage = message;
    }
}