package com.webcheckers.model;

import com.webcheckers.appl.Game;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
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
    public Row(int index, Game.Color color, boolean flag){
        this.spaces = new ArrayList<>();
        this.index = index;
        initialize(color, flag);
    }
    public Row(int index) {
        this.spaces = new ArrayList<>();
        this.index = index;
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

    public List<Space> reverseSpace() {
        List<Space> revSpaces = new ArrayList<>(this.spaces);
        Collections.reverse(revSpaces);
        return revSpaces;
    }
    /**
     * Given index, get the Space inside the Row
     * @return
     *      the specified Space
     */
    public Space getSpace(int cellIdx) {
        for(Space space: spaces) {
            if (space.getCellIdx() == cellIdx) {
                return space;
            }
        }
        return null;
    }

    public List<Space> getSpaces() {
        return this.spaces;
    }

    public void add(Space space) {
        this.spaces.add(space);
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

}