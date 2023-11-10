package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.List;

public class World {
    public static void main(String[] args) {
        List<MoveDirection> directions = OptionsParser.parse(args);
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        Simulation simulation = new Simulation(positions, directions);
        simulation.run();
    }
}
//    public static void run(MoveDirection[] moves) {
//        for (MoveDirection move : moves) {
//            String text_move = switch (move) {
//                case FORWARD -> "Zwierzak idzie do przodu.";
//                case BACKWARD -> "Zwierzak idzie do tyłu.";
//                case RIGHT -> "Zwierzak skręca w prawo.";
//                case LEFT -> "Zwierzak skręca w lewo.";
//            };
//            System.out.println(text_move);
//        }
//    }

//
//    private static void parse_moves(String[] args) {
//        for (String arg: args) {
//            String text_move = switch (arg) {
//                case "f" -> "Zwierzak idzie do przodu.";
//                case "b" -> "Zwierzak idzie do tyłu.";
//                case "r" -> "Zwierzak skręca w prawo.";
//                case "l" -> "Zwierzak skręca w lewo.";
//                default -> throw new IllegalStateException("Unexpected value: " + arg);
//            };
//            System.out.println(text_move);
//        }
//    }
//
//    public static void print_args(String[] args) {
//        if (args.length >= 1) {
//            System.out.printf("%s", args[0]);
//        }
//        for (int i = 1; i < args.length; ++i){
//            System.out.printf(", %s", args[i]);
//        }
//    }