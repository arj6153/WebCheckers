package com.webcheckers.model;

import com.webcheckers.appl.Game;
import com.webcheckers.appl.GameCenter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for Move in model tier
 *
 * @author Alex Johannesson
 */
public class MoveTest {

    private static final int START_ROW = 1;
    private static final int START_CELL = 1;
    private static final int END_ROW = 3;
    private static final int END_CELL = 3;
    private Row actualRow;

    private Position start;
    private Position end;
    private Move move;


    @BeforeEach
    public void setup() {
        start = new Position(START_ROW, START_CELL);
        end = new Position(END_ROW, END_CELL);

    }

    @Test
    public boolean testStart() {
        if(start.getRow() == START_ROW && start.getCell() == START_CELL) {
            return true;
        }
        else {
            return false;
        }
    }

    @Test
    public boolean testEnd() {
        if(end.getRow() == START_ROW && end.getCell() == START_CELL) {
            return true;
        }
        else {
            return false;
        }
    }

    @Test
    public void testMoveType() {
        Move.MoveType expectedMoveType = Move.MoveType.INVALID_MOVE;
    }
}
