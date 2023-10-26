package org.example.Solutions;

import org.example.Alg.IDDFS;
import org.example.Solutions.Solution;
import org.example.State.State;

public class IDDFSSolution {
    public static void runIDDFS(State initialState, int depth) {
        long initTime, finalTime, totalTime;
        initTime = System.currentTimeMillis();
        State finalState = IDDFS.run(initialState, depth);
        finalTime = System.currentTimeMillis();
        totalTime = finalTime - initTime;
        Solution.printSolution(finalState);
        System.out.println("Durata executiei algoritmului IDDFS este: " + totalTime +".");
    }
}
