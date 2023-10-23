package org.example.Alg;

import org.example.State.State;

import java.util.Comparator;

public class GreedySolution {
    public static void runGreedy(State initialState, int numIteration, Comparator<State> comparator) {
        long initTime, finalTime;
        initTime = System.currentTimeMillis();
        State finalState = Greedy.run(initialState, numIteration, comparator);
        finalTime = System.currentTimeMillis() - initTime;
        if (finalState == null) {
            System.out.println("Solutia nu a fost gasita");
        } else {
            Solution.printSolution(finalState);
        }
        System.out.println("Durata executiei algoritmului Greedy este: " + finalTime + ".");
    }
}
