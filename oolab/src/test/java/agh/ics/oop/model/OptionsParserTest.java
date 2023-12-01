package agh.ics.oop.model;

import agh.ics.oop.OptionsParser;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OptionsParserTest {
    @Test
    public void testParseArgsAllCorrect() {
        // given
        String[] textMoves = {"f", "f", "r", "l"};
        MoveDirection[] correctMoves = {MoveDirection.FORWARD,
                MoveDirection.FORWARD,
                MoveDirection.RIGHT,
                MoveDirection.LEFT};

        // when
        List<MoveDirection> moves = OptionsParser.parse(textMoves);

        // then
        assertArrayEquals(moves.toArray(), correctMoves);
    }

    @Test
    public void testParseArgsUnknown() {
        // given
        String[] textMoves1 = {"f", "f", "r", "l", "asd"};
        String[] textMoves2 = {"asd", "f", "f", "r", "l"};
        String[] textMoves3 = {"f", "asd", "f", "r", "l"};

        // when

        // then
        assertThrows(IllegalArgumentException.class, () -> OptionsParser.parse(textMoves1));
        assertThrows(IllegalArgumentException.class, () -> OptionsParser.parse(textMoves2));
        assertThrows(IllegalArgumentException.class, () -> OptionsParser.parse(textMoves3));
    }

    @Test
    public void testParseArgsEmpty() {
        // given
        String[] textMoves = {};
        MoveDirection[] correctMoves = {};

        // when
        List<MoveDirection> moves = OptionsParser.parse(textMoves);

        // then
        assertArrayEquals(moves.toArray(), correctMoves);
    }

    @Test
    public void testParseArgsSingleCorrect() {
        // given
        String[] textMoves = {"f"};
        MoveDirection[] correctMoves = {MoveDirection.FORWARD};

        // when
        List<MoveDirection> moves = OptionsParser.parse(textMoves);

        // then
        assertArrayEquals(moves.toArray(), correctMoves);
    }

    @Test
    public void testParseArgsSingleWrong() {
        // given
        String[] textMoves = {"asd"};

        // when

        // then
        Exception exception = assertThrows(IllegalArgumentException.class, () -> OptionsParser.parse(textMoves));
        String expectedMessage = "asd is not legal move specification";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}