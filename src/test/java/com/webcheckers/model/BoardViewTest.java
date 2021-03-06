package com.webcheckers.model;

import static com.webcheckers.appl.Game.Color.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class BoardViewTest {
    private final int RED_NUM = 12;
    private final int WHITE_NUM = 12;
    private final int BLANK_NUM = 40;
    private final BoardView CuT = new BoardView();
    @BeforeEach
    public void test_make_board_view() {
        assertNotNull(CuT);
        List<Row> board = CuT.getBoard();
        // check if the board exists
        assertNotNull(board);
        int redPieces = 0;
        int whitePieces = 0;
        int blankSpaces = 0;
        boolean flag = false;
        for(int r = 0; r < 8; r++) {
            for(int c = 0; c < 8; c++) {
                Space space = board.get(r).getSpace(c);
                assertNotNull(space);
                Piece piece = space.getPiece();
                if (r <= 2) {
                    //Check pieces color and validity
                    if(piece != null) {
                        assertSame(piece.getColor(), WHITE);
                        assertFalse(space.isValid());
                        whitePieces++;
                    } else {
                        blankSpaces++;
                    }
                } else if ( r >= 5) {
                    if(piece != null) {
                        assertSame(piece.getColor(), RED);
                        assertFalse(space.isValid());
                        redPieces++;
                    } else {
                        blankSpaces++;
                    }
                } else {
                    assertNull(piece);
                    assertEquals(flag,space.isValid());
                    flag = !flag;
                    blankSpaces++;
                }
            }
            flag = !flag;
        }
        // check number of pieces
        assertEquals(redPieces, RED_NUM);
        assertEquals(whitePieces, WHITE_NUM);
        assertEquals(blankSpaces, BLANK_NUM);
    }

    @Test
    public void test_get_row() {
        assertThrows(IndexOutOfBoundsException.class, () -> CuT.getRow(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> CuT.getRow(10));
        assertThrows(IndexOutOfBoundsException.class, () -> CuT.getRow(-5));
        assertThrows(IndexOutOfBoundsException.class, () -> CuT.getRow(8));


    }
}
