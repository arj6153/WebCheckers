package com.webcheckers.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;

import static com.webcheckers.appl.Game.Color.*;

/**
 * Class that contains the board, tiles, and checker pieces.
 *
 * @author: Alex Johannesson
 * @author: Bin Qiu
 * @author: Michael Merlino
 */
public class BoardView implements Iterable<Row>{
    private final List<Row> board;
    public static final int DIM= 8;
    private int redPieces = 12;
    private int whitePieces = 12;
    /**
     * Constructor of the checker board.
     */
    public BoardView() {
       this.board = new ArrayList<>();
       try {
           initializeConfigBoard("src/main/java/com/webcheckers/model/BoardConFigs/config3.txt");
       } catch (FileNotFoundException ignored) {
           System.out.println("file not found");
       }
    }

    public BoardView(List<Row> board) {
       this.board = board;
    }
    public BoardView(List<Row> board, int numRed, int numWhite) {
        this.board = board;
        redPieces = numRed;
        whitePieces = numWhite;
    }

    /**
     * Create a new standard board based on checkers rules.
     */
    public void initializeBoard() {
        boolean flag = false;
        for( int row = DIM-1; row >= 0; row--) {
            if (row <= 2) {
                board.add(new Row(row, RED, flag));
            } else if (row >= 5) {
                board.add(new Row(row, WHITE, flag));
            } else {
                board.add(new Row(row, NONE, flag));
            }
            flag = !flag;
        }
    }

    public void initializeConfigBoard(String fileName) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(fileName));
        int redNumLine = Integer.parseInt(sc.nextLine());
        int whiteNumLine = Integer.parseInt(sc.nextLine());
        this.redPieces = redNumLine;
        this.whitePieces = whiteNumLine;
        boolean flag = false;
        for( int row = DIM-1; row >= 0; row--) {
            String rowConfig = sc.nextLine();
            board.add(new Row(row, rowConfig, flag));
            flag = !flag;
        }
    }

    /**
     * Gets the checker board.
     *
     * @return
     *      checker board
     */
     public List<Row> getBoard() {
        return board;
    }

    /**
     * Given index, get the row from boards
     * @param rowIdx of the row
     * @return
     *      the specified row
     */
    public Row getRow(int rowIdx) {
        if (rowIdx >= DIM || rowIdx < 0) {
            throw new IndexOutOfBoundsException();
        }
        for(Row row: board) {
            if (row.getIndex() == rowIdx) {
                return row;
            }
        }
        return null;
    }

    /**
     * @return
     *      Iterator for the board
     */
    public Iterator<Row> iterator() {
        return this.board.iterator();
    }

    public int getRedPieces() {
        return redPieces;
    }

    public int getWhitePieces() {
        return whitePieces;
    }

    public void increaseOrDecreaseRedPieces(int amount) {
        redPieces += amount;
    }
    public void increaseOrDecreaseWhitePieces(int amount) {
        whitePieces += amount;
    }

    public void printBoard() {
        for(Row row: board) {
            System.out.print(row.getIndex()+ " ");
            for(Space space: row) {
                if(space.getPiece() == null) {
                    System.out.print(". ");
                } else if(space.getPiece().getColor() == RED) {
                    System.out.print("R ");
                } else if (space.getPiece().getColor() == WHITE) {
                    System.out.print("W ");
                }
            }
            System.out.println();
        }
    }

}