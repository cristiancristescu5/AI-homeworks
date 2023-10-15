package org.example;

public class Transition {
    public static boolean isFinal(State state) {
        Integer[] arr = state.getMatrix();
        boolean foundZero = false;
        int ignoredValue = 0;

        for (int i = 1; i < State.STATE_SIZE; i++) {
            if (arr[i] == 0) {
                foundZero = true;
                continue;
            }

            if (foundZero && arr[i] < arr[i - 1]) {
                return false;
            } else if (!foundZero && arr[i] <= arr[i - 1]) {
                return false;
            }
        }
        return true;
    }

    public static State moveUp(State state) {
        if (state.getLastMovedCell() == Direction.DOWN) {
            return state;
        }

//        return new State(state, state.getEmptyCell(), state.getEmptyCell() + 3, Direction.TOP);
        State newState = new State(state);
        newState.setCellAt(state.getEmptyCell(), newState.getCellAt(state.getEmptyCell() + 3));
        newState.setCellAt(state.getEmptyCell() + 3, 0);
        newState.setEmptyCell(state.getEmptyCell() + 3);
        newState.setLastMovedCell(Direction.TOP);
        return newState;
    }

    public static State moveDown(State state) {
        if (state.getLastMovedCell() == Direction.TOP) {
            return state;
        }

//        return new State(state, state.getEmptyCell(), state.getEmptyCell() - 3, Direction.DOWN);
        State newState = new State(state);
        newState.setCellAt(state.getEmptyCell(), newState.getCellAt(state.getEmptyCell() - 3));
        newState.setCellAt(state.getEmptyCell() - 3, 0);
        newState.setEmptyCell(state.getEmptyCell() - 3);
        newState.setLastMovedCell(Direction.DOWN);
        return newState;
    }

    public static State moveRight(State state) {
        if (state.getLastMovedCell() == Direction.LEFT) {
            return state;
        }

//        return new State(state, state.getEmptyCell(), state.getEmptyCell() - 1, Direction.RIGHT);
        State newState = new State(state);
        newState.setCellAt(state.getEmptyCell(), newState.getCellAt(state.getEmptyCell() - 1));
        newState.setEmptyCell(state.getEmptyCell() - 1);
        newState.setCellAt(state.getEmptyCell() - 1, 0);
        newState.setLastMovedCell(Direction.RIGHT);
        return newState;
    }

    public static State moveLeft(State state) {
        if (state.getLastMovedCell() == Direction.RIGHT) {
            return state;
        }

//        return new State(state, state.getEmptyCell(), state.getEmptyCell() + 1, Direction.LEFT);
        State newState = new State(state);
        newState.setCellAt(state.getEmptyCell(), newState.getCellAt(state.getEmptyCell() + 1));
        newState.setEmptyCell(state.getEmptyCell() + 1);
        newState.setCellAt(state.getEmptyCell() + 1, 0);
        newState.setLastMovedCell(Direction.LEFT);
        return newState;
    }

    public static boolean isValid(State oldState, State newState) {
        return !oldState.equals(newState);
    }

}
