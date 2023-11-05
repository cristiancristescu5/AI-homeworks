package org.example;


import java.util.Arrays;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {

        int[][] instance = {
                {1, 0, 0},
                {0, 0, 0},
                {0, -1, 0}
        };
        State state = new State(instance);
        System.out.println(state);
//        System.out.println(AIStrategy.heuristic(state, Player.AI));
    }
}