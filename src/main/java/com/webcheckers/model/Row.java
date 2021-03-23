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
public class Row implements Iterable<Space>{

    private int rowIdx;
    private final List<Space> spaces;

    /**
     * Constructor of Row.
     *
     * @param rowIdx
     *      row number
     * @param color
     *      tile color
     * @param flag
     *      true puts a checker piece on the tile, and
     *      tile is empty if false
     */
    public Row(int rowIdx, Game.Color color, boolean flag){
        this.spaces = new ArrayList<>();
        this.rowIdx = rowIdx;
        initialize(color, flag);
    }

    /**
     * Initializes a board based on the color of the tiles.
     *
     * @param color
     *      tile colors
     * @param flag
     *      true puts a checker piece on the tile, and
     *             tile is empty if false
     */
    public void initialize(Game.Color color, boolean flag) {
        for(int col = 0; col < 8; col++) {
            if(flag && color != NONE) {
                spaces.add(new Space( col,new Piece(Piece.Type.SINGLE, color),true));
            } else {
                spaces.add(new Space(col, null, flag));
            }
            flag = !flag;
        }
    }

    /**
     * Iterator for the space in the row.
     *
     * @return
     *      iterator of the row
     */
    public Iterator<Space> iterator() {
        return this.spaces.iterator();
    }

    /**
     * Given index, get the Space inside the Row
     * @return
     *      the specified Space
     */
    public Space getSpace(int cellIdx) {
       return this.spaces.get(cellIdx);
    }


    /**
     * Gets the row number.
     *
     * @return
     *      row number
     */
    public int getRowIdx() {
        return rowIdx;
    }

}