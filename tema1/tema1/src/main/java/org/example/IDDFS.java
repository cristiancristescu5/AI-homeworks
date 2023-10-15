package org.example;

import java.util.*;

public class IDDFS {
    private static State depthLimitedDFS(State state, int depth, List<State> states, List<State> visited) {
        if (Transition.isFinal(state)) {
            states.add(state);
            return state;
        }
        if (depth == 0) {
            return null;
        }
        List<State> neighbours = new ArrayList<>();
        visited.add(state);
        State s;
        // 0 1 2
        // 3 4 5
        // 6 7 8
        if (state.getEmptyCell() >= 3) {
//            System.out.println("jos");
            s = Transition.moveUp(state);
//            System.out.println(s);
            if (Transition.isValid(s, state)) {
                neighbours.add(s);
            }
//            neighbours.add(Transition.moveDown())
        }
        if (state.getEmptyCell() < 6) {
//            System.out.println("sus");
            s = Transition.moveDown(state);
//            System.out.println(s);
            if (Transition.isValid(s, state)) {
                neighbours.add(s);
            }
        }
        if (state.getEmptyCell() % 3 < 2) {
//            System.out.println("stanga");
            s = Transition.moveLeft(state);
//            System.out.println(s);
            if (Transition.isValid(s, state)) {
                neighbours.add(s);
            }
        }
        if (state.getEmptyCell() % 3 > 0) {
//            System.out.println("dreapta");
            s = Transition.moveRight(state);
//            System.out.println(s);
            if (Transition.isValid(s, state)) {
                neighbours.add(s);
            }
        }
        for (State ignored : neighbours) {
            if (Transition.isValid(ignored, state) && !visited.contains(ignored)) {
                visited.add(ignored);
                var res = depthLimitedDFS(ignored, depth - 1, states, visited);
                if (res != null) {
                    return res;
                }
            }
        }
        return null;
    }

    public static List<State> run(State state, int maxDepth) {
        List<State> solution = new ArrayList<>();
        for (int depth = 1; depth <= maxDepth; depth++) {
            List<State> states = new ArrayList<>();
            State sol = depthLimitedDFS(state, depth, solution, states);
            if (sol != null) {
                return solution;
            }
        }
        return null;
    }
//    def IDDFS(init_state, max_depth):
//            for depth from 0 to max_depth:
//    visited = []
//    sol = depth_limited_DFS(init_state, depth, visited):
//            if sol is not None:
//            return sol

    //return None

//    def depth_limited_DFS(state, depth, visited):
//            if is_final(state):
//            return state
// if depth == 0:
//            return None
//visited.add(state)
//            for each neighbor of state: #transition & validation(s)functions
//if is_valid(neighbor) and neighbor not in visited:
//    res = depth_limited_DFS(neighbor, depth-1, visited)
//if res is not None:
//            return res
//return None
}
