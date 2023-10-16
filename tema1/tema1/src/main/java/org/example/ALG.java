package org.example;

import java.util.ArrayList;
import java.util.List;

public class ALG {

    private static State depthLimitedDFS(State state, int depth, List<State> visited) {
        if (Transition.isFinal(state)) {
            return state;
        }
        if (depth == 0) {
            return null;
        }
        List<State> neighbours = new ArrayList<>();
        visited.add(state);
        if (state.getEmptyCell() < 6) {
            neighbours.add(Transition.moveUp(state));
        }
        if (state.getEmptyCell() > 2) {
            neighbours.add(Transition.moveDown(state));
        }
        if (state.getEmptyCell() % 3 != 2) {
            neighbours.add(Transition.moveLeft(state));
        }
        if (state.getEmptyCell() % 3 > 0) {
            neighbours.add(Transition.moveRight(state));
        }
        for (var neighbour : neighbours) {
            if (Transition.isValid(state, neighbour) && !visited.contains(neighbour)) {
                visited.add(neighbour);
                var res = depthLimitedDFS(state, depth - 1, visited);
                if (res != null) {
                    return res;
                }
            }
        }
        return null;
    }

    public static State IDDFS(State initialState, int maxDepth) {
        for (int i = 0; i <= maxDepth; i++){
            List<State> visited = new ArrayList<>();
            State sol = depthLimitedDFS(initialState, i, visited);
            if(sol != null){
                return sol;
            }
        }
        return null;
    }
}
