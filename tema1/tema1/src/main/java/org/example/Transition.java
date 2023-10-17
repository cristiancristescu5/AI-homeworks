package org.example;


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
        return state.getEmptyCell() > 2 && !state.getLastMovedCell().equals(Direction.TOP);
    }

    public static boolean isValidUp(State state) {
        return state.getEmptyCell() < 6 && !state.getLastMovedCell().equals(Direction.DOWN);
    }

    public static boolean isValidLeft(State state) {
        return state.getEmptyCell() % 3 < 2 && !state.getLastMovedCell().equals(Direction.RIGHT);
    }

    public static boolean isValidRight(State state) {
        return state.getEmptyCell() % 3 > 0 && !state.getLastMovedCell().equals(Direction.LEFT);
    }

}
