package org.example.Alg;

import org.example.State.State;
import org.example.State.Transition;

import java.util.*;

public class IDDFS {

    private static State depthLimitedDFS(State state, int depth, List<State> visited) {
        if (Transition.isFinal(state)) {
            return state;
        }
        if (depth == 0) {
            return null;
        }
        visited.add(state);
        List<State> neighbours = Transition.getNeighbours(state);
        for (State s : neighbours) {
            s.setParentState(state);
        }
        for (State neighbour : neighbours) {
            if (!visited.contains(neighbour)) {
                var res = depthLimitedDFS(neighbour, depth - 1, visited);
                if (res != null) {
                    return res;
                }
            }
        }
        return null;
    }

    public static State run(State state, int maxDepth) {
        state.setParentState(null);
        for (int depth = 0; depth <= maxDepth; depth++) {
            List<State> states = new ArrayList<>(100);
            State sol = depthLimitedDFS(state, depth, states);
            if (sol != null) {
                return sol;
            }
        }
        return null;
    }
}
