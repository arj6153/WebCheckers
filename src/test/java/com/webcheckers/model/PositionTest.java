package com.webcheckers.model;
import org.junit.jupiter.api.Test;
import com.webcheckers.model.Player;
import org.mockito.internal.matchers.Not;
import spark.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for Position in model tier
 *
 * @author Alex Johannesson
 */
public class PositionTest {

    @Test
    public void positionCheck() {
        Position position = new Position(1,2);
        assertEquals(1, position.getRow(), "The actual Row position is" + position.getRow());
        assertEquals(2, position.getCell(), "The actual Cell position is" + position.getCell());
    }
}
