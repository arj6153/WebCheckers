package com.webcheckers.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;

import static com.webcheckers.model.Game.color.NONE;

public class Row implements Iterable<Tile>{

    private int index;
    private List<Tile> tiles;

    public Row(int index, Game.color color, boolean flag){
        this.tiles = new ArrayList<>();
        this.index = index;
        initialize(color, flag);
    }

    public void initialize(Game.color color, boolean flag) {
        for(int col = 0; col < 8; col++) {
           if(flag) {
               tiles.add(new Tile( col,new Piece(Piece.Type.NORMAL, color),true));
           } else {
               tiles.add(new Tile(col, null,false));
           }
           flag = !flag;
        }
    }

    public Iterator<Tile> iterator() {
        return this.tiles.iterator();
    }

    public int getIndex() {
        return index;
    }

    public void setRow(int index) {
        this.index = index;
    }

    @Override
    public Spliterator<Tile> spliterator() {
        return null;
    }
}

