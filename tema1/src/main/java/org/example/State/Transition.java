package org.example.State;


import java.util.ArrayList;
import java.util.List;

public class Transition {
    public static boolean isFinal(State state) {
        Integer[] arr = state.getMatrix();
        int previous = -1;
        for (int cellValue : arr) {
            if (cellValue == 0) {
                continue;
            }

            if (cellValue < previous) {
                return false;
            }

            previous = cellValue;
        }

        return true;
    }

    public static State moveUp(State state) {
        return new State(state, state.getEmptyCell(), state.getEmptyCell() + 3, Direction.TOP);
    }

    public static State moveDown(State state) {
        return new State(state, state.getEmptyCell(), state.getEmptyCell() - 3, Direction.DOWN);
    }

    public static State moveRight(State state) {
        return new State(state, state.getEmptyCell(), state.getEmptyCell() - 1, Direction.RIGHT);
    }

    public static State moveLeft(State state) {
        return new State(state, state.getEmptyCell(), state.getEmptyCell() + 1, Direction.LEFT);
    }


    public static boolean isValidDown(State state) {
        return state.getEmptyCell() > 2 && !(state.getLastMovedCell() == Direction.TOP);
    }

    public static boolean isValidUp(State state) {
        return state.getEmptyCell() < 6 && !(state.getLastMovedCell() == Direction.DOWN);
    }

    public static boolean isValidLeft(State state) {
        return state.getEmptyCell() % 3 < 2 && !(state.getLastMovedCell() == Direction.RIGHT);
    }

    public static boolean isValidRight(State state) {
        return state.getEmptyCell() % 3 > 0 && !(state.getLastMovedCell() == Direction.LEFT);
    }

    public static List<State> getNeighbours(State state) {
        List<State> neighbours = new ArrayList<>();
        if (isValidDown(state)) {
            neighbours.add(moveDown(state));
        }
        if (isValidUp(state)) {
            neighbours.add(moveUp(state));
        }
        if (isValidLeft(state)) {
            neighbours.add(moveLeft(state));
        }
        if (isValidRight(state)) {
            neighbours.add(moveRight(state));
        }
        return neighbours;
    }

}
