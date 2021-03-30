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
public class SpaceTest {
    private int cellSpace = 3;

    @Test
    public void isSpaceValid() {
        Space space = new Space(cellSpace, null, true );
        assertTrue(space.isValid(), "Not a Valid space");
        space.setPiece(new Piece(Piece.Type.SINGLE, Game.Color.RED));
        assertFalse(space.isValid(), "Space should not be legal");
        Space nextSpace = new Space(cellSpace, null, false);
        assertFalse(nextSpace.isValid(), "Space should not be legal");
        nextSpace.setPiece(new Piece(Piece.Type.SINGLE, Game.Color.RED));
        assertFalse(nextSpace.isValid(), "space should not be legal");
    }
}
