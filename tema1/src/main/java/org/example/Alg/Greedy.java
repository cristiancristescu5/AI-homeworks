package org.example.Alg;

import org.example.State.State;
import org.example.State.Transition;

import java.util.*;

public class Greedy {
    public static State run(State initialState, int numIterations, Comparator<State> comparator) {
        Queue<State> pq = new PriorityQueue<>(comparator);
        List<State> visited = new ArrayList<>();
        visited.add(initialState);
        initialState.setParentState(null);
        if (!pq.offer(initialState)) {
            throw new IllegalStateException("The queue is full");
        }

        while (!pq.isEmpty() && numIterations != 0) {
            State state = pq.poll();
            if (Transition.isFinal(state)) {
                return state;
            }
            List<State> neighbours = Transition.getNeighbours(state);
            neighbours.forEach(s -> s.setParentState(state));
            for (State neighbour : neighbours) {
                if (!visited.contains(neighbour)) {
                    pq.add(neighbour);
                    visited.add(neighbour);
                }
            }
            numIterations--;
        }
        return null;
    }
}
