package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.LinkedList;
import java.util.List;

public class OptionsParser {

    public static List<MoveDirection> parse(String[] args) {
        List<MoveDirection> moves = new LinkedList<>();
        for (String arg: args){
             switch (arg) {
                 case "f":
                     moves.add(MoveDirection.FORWARD);
                     break;
                 case "b":
                     moves.add(MoveDirection.BACKWARD);
                     break;
                 case "r":
                     moves.add(MoveDirection.RIGHT);
                     break;
                 case "l":
                     moves.add(MoveDirection.LEFT);
                     break;
                 default:
                     break;
             };
        }
        return moves;
    }
}
