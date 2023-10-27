package agh.ics.oop.model;

import agh.ics.oop.OptionsParser;
import org.junit.jupiter.api.Test;
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
        MoveDirection[] moves = OptionsParser.parseArgs(textMoves);

        // then
        assertArrayEquals(moves, correctMoves);
    }

    @Test
    public void testParseArgsUnknown() {
        // given
        String[] textMoves1 = {"f", "f", "r", "l", "asd"};
        String[] textMoves2 = {"asd", "f", "f", "r", "l"};
        String[] textMoves3 = {"f", "asd", "f", "r", "l"};
        MoveDirection[] correctMoves = {MoveDirection.FORWARD,
                MoveDirection.FORWARD,
                MoveDirection.RIGHT,
                MoveDirection.LEFT};

        // when
        MoveDirection[] moves1 = OptionsParser.parseArgs(textMoves1);
        MoveDirection[] moves2 = OptionsParser.parseArgs(textMoves2);
        MoveDirection[] moves3 = OptionsParser.parseArgs(textMoves3);

        // then
        assertArrayEquals(moves1, correctMoves);
        assertArrayEquals(moves2, correctMoves);
        assertArrayEquals(moves3, correctMoves);
    }

    @Test
    public void testParseArgsEmpty() {
        // given
        String[] textMoves = {};
        MoveDirection[] correctMoves = {};

        // when
        MoveDirection[] moves = OptionsParser.parseArgs(textMoves);

        // then
        assertArrayEquals(moves, correctMoves);
    }

    @Test
    public void testParseArgsSingleCorrect() {
        // given
        String[] textMoves = {"f"};
        MoveDirection[] correctMoves = {MoveDirection.FORWARD};

        // when
        MoveDirection[] moves = OptionsParser.parseArgs(textMoves);

        // then
        assertArrayEquals(moves, correctMoves);
    }

    @Test
    public void testParseArgsSingleWrong() {
        // given
        String[] textMoves = {"asd"};
        MoveDirection[] correctMoves = {};

        // when
        MoveDirection[] moves = OptionsParser.parseArgs(textMoves);

        // then
        assertArrayEquals(moves, correctMoves);
    }
}