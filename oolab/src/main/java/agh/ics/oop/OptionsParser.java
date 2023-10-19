package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

public class OptionsParser {

    public static MoveDirection[] parse_args(String[] args) {
        MoveDirection[] moves = new MoveDirection[args.length];
        int i = 0;
        for (String arg: args){
             switch (arg) {
                 case "f":
                     moves[i] = MoveDirection.FORWARD;
                     ++i;
                     break;
                 case "b":
                     moves[i] = MoveDirection.BACKWARD;
                     ++i;
                     break;
                 case "r":
                     moves[i] = MoveDirection.RIGHT;
                     ++i;
                     break;
                 case "l":
                     moves[i] = MoveDirection.LEFT;
                     ++i;
                     break;
                 default:
                     break;
             };
        }
        return moves;
    }
}
