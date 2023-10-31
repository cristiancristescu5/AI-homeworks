package org.example.Solutions;

import org.example.Alg.Greedy;
import org.example.Solutions.Solution;
import org.example.State.State;

import java.util.Comparator;

public class GreedySolution {
    public static void runGreedy(State initialState, int numIteration, Comparator<State> comparator) {
        long initTime, finalTime, totalTime;
        initTime = System.currentTimeMillis();
        State finalState = Greedy.run(initialState, numIteration, comparator);
        finalTime = System.currentTimeMillis();
        totalTime = finalTime - initTime;
        if (finalState == null) {
            System.out.println("Solutia nu a fost gasita");
        } else {
            Solution.printSolution(finalState);
        }
        System.out.println("Durata executiei algoritmului Greedy este: " + totalTime + ".");
    }
}
