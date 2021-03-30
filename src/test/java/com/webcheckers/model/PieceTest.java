package com.webcheckers.model;
import com.webcheckers.appl.Game;
import net.bytebuddy.jar.asm.Type;
import org.junit.jupiter.api.Test;
import com.webcheckers.model.Player;
import org.mockito.internal.matchers.Equality;
import org.mockito.internal.matchers.Equals;
import org.mockito.internal.matchers.Not;
import spark.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for Piece in model tier
 *
 * @author Alex Johannesson
 */
public class PieceTest {
    private boolean Equals = true;
    private boolean notEquals = false;

    @Test
    public void testPieceType() {
        Piece.Type testType = Piece.Type.SINGLE;
        Piece piece = new Piece(Piece.Type.SINGLE, Game.Color.RED);
        Piece.Type actualType = piece.getType();
        assertEquals(testType, actualType, "The normal piece type should be " + actualType);
    }

    @Test
    public void testKing() {
        Piece.Type type = Piece.Type.KING;
        if (type == Piece.Type.KING) {
            assertTrue(Equals);
        } else {
            assertTrue(notEquals);
        }
    }
}
