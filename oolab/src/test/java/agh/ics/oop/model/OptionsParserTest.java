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
        List<MoveDirection> moves = OptionsParser.parseArgs(textMoves);

        // then
        assertArrayEquals(moves.toArray(), correctMoves);
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
        List<MoveDirection> moves1 = OptionsParser.parseArgs(textMoves1);
        List<MoveDirection> moves2 = OptionsParser.parseArgs(textMoves2);
        List<MoveDirection> moves3 = OptionsParser.parseArgs(textMoves3);

        // then
        assertArrayEquals(moves1.toArray(), correctMoves);
        assertArrayEquals(moves2.toArray(), correctMoves);
        assertArrayEquals(moves3.toArray(), correctMoves);
    }

    @Test
    public void testParseArgsEmpty() {
        // given
        String[] textMoves = {};
        MoveDirection[] correctMoves = {};

        // when
        List<MoveDirection> moves = OptionsParser.parseArgs(textMoves);

        // then
        assertArrayEquals(moves.toArray(), correctMoves);
    }

    @Test
    public void testParseArgsSingleCorrect() {
        // given
        String[] textMoves = {"f"};
        MoveDirection[] correctMoves = {MoveDirection.FORWARD};

        // when
        List<MoveDirection> moves = OptionsParser.parseArgs(textMoves);

        // then
        assertArrayEquals(moves.toArray(), correctMoves);
    }

    @Test
    public void testParseArgsSingleWrong() {
        // given
        String[] textMoves = {"asd"};
        MoveDirection[] correctMoves = {};

        // when
        List<MoveDirection> moves = OptionsParser.parseArgs(textMoves);

        // then
        assertArrayEquals(moves.toArray(), correctMoves);
    }
}