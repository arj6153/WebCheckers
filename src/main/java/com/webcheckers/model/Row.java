package com.webcheckers.model;

import com.webcheckers.appl.Game;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.webcheckers.appl.Game.Color.NONE;

/**
 * Class represents the rows of the checker board.
 *
 * @author Truong Anh Tuan Hoang
 */
public class Row implements Iterable<Space> {

    private final int index;
    private final List<Space> spaces;

    /**
     * Constructor of Row for initial rows.
     *
     * @param index
     *      The row number
     * @param color
     *      The space color
     * @param isBlackSpace
     *      True puts a checker piece on the space, and
     *      false does not put a checker piece on the space
     */
    public Row(int index, Game.Color color, boolean isBlackSpace){
        this.spaces = new ArrayList<>();
        this.index = index;
        initialize(color, isBlackSpace);
    }

    /**
     * Constructor of Row for empty rows.
     *
     * @param index
     *      The row number
     */
    public Row(int index) {
        this.spaces = new ArrayList<>();
        this.index = index;
    }

    /**
     * Initializes a board based on the color of the spaces.
     *
     * @param color
     *      The player's color
     * @param isBlackSpace
     *      True puts a checker piece on the tile, and
     *             tile is empty if false
     */
    public void initialize(Game.Color color, boolean isBlackSpace) {
        for(int col = 0; col < BoardView.DIM; col++) {
            if(isBlackSpace && color != NONE) {
                spaces.add(new Space( col,new Piece(Piece.Type.KING, color),true));
            } else {
                spaces.add(new Space(col, null, isBlackSpace));
            }
            isBlackSpace = !isBlackSpace;
        }
    }

    /**
     * Iterator for the space in the row.
     *
     * @return
     *      Iterator of the row
     */
    public Iterator<Space> iterator() {
        return this.spaces.iterator();
    }

    /**
     * Given index, get the space inside the row.
     *
     * @param cellIdx
     *      The column number of game board
     *
     * @return
     *      The specified space
     */
    public Space getSpace(int cellIdx) {
        for(Space space: spaces) {
            if (space.getCellIdx() == cellIdx) {
                return space;
            }
        }
        return null;
    }

    /**
     * Adds a space into the current row.
     *
     * @param space
     *      The specified space
     */
    public void add(Space space) {
        this.spaces.add(space);
    }

    /**
     * @return
     *      The row number
     */
    public int getIndex() {
        return index;
    }
}