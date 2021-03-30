package com.webcheckers.model;
import com.webcheckers.appl.Game;
import net.bytebuddy.jar.asm.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.webcheckers.model.Player;
import org.mockito.internal.matchers.Equality;
import org.mockito.internal.matchers.Equals;
import org.mockito.internal.matchers.Not;
import spark.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for Piece in model tier
 *
 * @author Alex Johannesson
 */
public class RowTest {
    private Row row;
    private Game.Color color;
    private List<Space> space;



    @BeforeEach
    public void setup() {
        color = Game.Color.WHITE;
        row = new Row(3, color, true);
    }

    @Test
    public void testRowIndex() {
        assertEquals(3, row.getIndex(),"The actual row position is " + row.getIndex());
    }


}

