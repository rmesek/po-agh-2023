package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import static agh.ics.oop.OptionsParser.parse_args;

public class World {
    public static void main(String[] args) {
        System.out.println("system wystartował");
        MoveDirection[] moves = OptionsParser.parse_args(args);
        run(moves);
        System.out.println("system zakończył działanie");
    }

    public static void run(MoveDirection[] moves) {
        for (MoveDirection move : moves) {
            if (move == null) break;

            String text_move = switch (move) {
                case FORWARD -> "Zwierzak idzie do przodu.";
                case BACKWARD -> "Zwierzak idzie do tyłu.";
                case RIGHT -> "Zwierzak skręca w prawo.";
                case LEFT -> "Zwierzak skręca w lewo.";
            };
            System.out.println(text_move);
        }
    }
}

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