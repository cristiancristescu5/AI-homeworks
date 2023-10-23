package org.example.Alg;

import org.example.State.State;

public class IDDFSSolution {
    public static void runIDDFS(State initialState, int depth) {
        long initTime, finalTime;
        initTime = System.currentTimeMillis();
        State finalState = IDDFS.run(initialState, depth);
        finalTime = System.currentTimeMillis() - initTime;
        Solution.printSolution(finalState);
        System.out.println("Durata executiei algoritmului IDDFS este: " + finalTime +".");
    }
}
