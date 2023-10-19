package org.example;

import java.util.*;

public class IDDFS {
    private static List<State> solution = new ArrayList<>();

    private static State depthLimitedDFS(State state, int depth, List<State> visited) {
        if (Transition.isFinal(state)) {
            return state;
        }
        if (depth == 0) {
            return null;
        }
        List<State> neighbours = new ArrayList<>();
        visited.add(state);
        if (Transition.isValidDown(state)) {
            neighbours.add(Transition.moveDown(state));
        }
        if (Transition.isValidUp(state)) {
            neighbours.add(Transition.moveUp(state));
        }
        if (Transition.isValidLeft(state)) {
            neighbours.add(Transition.moveLeft(state));
        }
        if (Transition.isValidRight(state)) {
            neighbours.add(Transition.moveRight(state));
        }
        for (State neighbour : neighbours) {
            if (!visited.contains(neighbour)) {
                var res = depthLimitedDFS(neighbour, depth - 1, visited);
                if (res != null) {
                    solution.addAll(visited);
                    return res;
                }
            }
        }
        return null;
    }

    public static State run(State state, int maxDepth) {
        solution = new ArrayList<>();
        for (int depth = 0; depth <= maxDepth; depth++) {
            List<State> states = new ArrayList<>(100);
            State sol = depthLimitedDFS(state, depth, states);
            if (sol != null) {
                return sol;
            }
        }
        return null;
    }

    public static List<State> getSolution() {
        return solution;
    }

    public static void printSolution() {
        if(solution.isEmpty()){
            throw new IllegalStateException("The solution is empty");
        }
        for (State s : solution) {
            System.out.println(s.toString());
        }
    }
}
