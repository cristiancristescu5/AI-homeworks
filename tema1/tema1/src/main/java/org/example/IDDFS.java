package org.example;

import java.util.*;

public class IDDFS {
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
        for (State ignored : neighbours) {
            if (!visited.contains(ignored)) {
                visited.add(ignored);
                var res = depthLimitedDFS(ignored, depth - 1, visited);
                if (res != null) {
                    return res;
                }
            }
        }
        return null;
    }

    public static State run(State state, int maxDepth) {
        for (int depth = 0; depth <= maxDepth; depth++) {
            List<State> states = new ArrayList<>();
            State sol = depthLimitedDFS(state, depth, states);
            if (sol != null) {
                return sol;
            }
        }
        return null;
    }
}
