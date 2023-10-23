package org.example.Alg;

import org.example.State.State;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
    public static void printSolution(State finalState) {
        List<State> solution = new ArrayList<>();
        State it = finalState;
        solution.add(it);
        while (it.getParrentState() != null) {
            solution.add(it.getParrentState());
            it = it.getParrentState();
        }
        Collections.reverse(solution);
        solution.forEach(st -> System.out.println(st + "\n"));
        System.out.println("Dimensiunea solutiei este: " + (solution.size()-1));
    }
}
