package com.webcheckers.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;

import static com.webcheckers.model.Game.color.NONE;

/**
 * Class represents the rows of the checker board.
 *
 * @author Truong Anh Tuan Hoang
 */
public class Row implements Iterable<Space>{

    private int index;
    private final List<Space> spaces;

    /**
     * Constructor of Row.
     *
     * @param index
     *      row number
     * @param color
     *      tile color
     * @param flag
     *      true puts a checker piece on the tile, and
     *      tile is empty if false
     */
    public Row(int index, Game.color color, boolean flag){
        this.spaces = new ArrayList<>();
        this.index = index;
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
    public void initialize(Game.color color, boolean flag) {
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
     * Gets the row number.
     *
     * @return
     *      row number
     */
    public int getIndex() {
        return index;
    }

    /**
     * Sets the row number.
     *
     * @param index
     *      row number
     */
    public void setRow(int index) {
        this.index = index;
    }

    @Override
    public Spliterator<Space> spliterator() {
        return null;
    }
}