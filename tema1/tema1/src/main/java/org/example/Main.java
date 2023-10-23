package org.example;

import org.example.Alg.*;
import org.example.State.State;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        State state = new State(new Integer[]{2, 7, 5, 0, 8, 4, 3, 1, 6});
        State state1 = new State(new Integer[]{2, 5, 3, 1, 0, 6, 4, 7, 8});
        State state2 = new State(new Integer[]{8, 6, 7, 2, 5, 4, 0, 3, 1});
        IDDFSSolution.runIDDFS(state, 53);
        System.out.println("--------------------Manhattan-----------------------------");
        GreedySolution.runGreedy(state2, 1500, Comparator.comparingInt(Distances::manhattanDistance));
        System.out.println("--------------------Euclidean---------------------------");
        GreedySolution.runGreedy(state2, 1500, Comparator.comparingInt(Distances::customDistance));
        System.out.println("--------------------Hamming------------------------------");
        GreedySolution.runGreedy(state2, 1500, Comparator.comparingInt(Distances::hammingDistance));

    }
}