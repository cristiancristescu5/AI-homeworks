package org.example.State;

import java.util.Arrays;

public class State {
    public static final Integer STATE_SIZE = 9;
    private final Integer[] matrix;
    private final Direction lastMovedCell;
    private Integer emptyCell;
    private State parentState = null;
    public State(Integer[] matrix) {
        if (matrix.length != STATE_SIZE) {
            throw new IllegalArgumentException("");
        }
        this.matrix = matrix;
        lastMovedCell = Direction.NONE;
        for (int i = 0; i < STATE_SIZE; i++) {
            if (matrix[i] == 0) {
                emptyCell = i;
            }
        }
    }

    public State(State toCopy, Integer emptyCellPos, Integer initialCell, Direction direction) {
        this.matrix = Arrays.copyOf(toCopy.getMatrix(), State.STATE_SIZE);
        this.emptyCell = initialCell;
        this.matrix[emptyCellPos] = this.matrix[initialCell];
        this.matrix[initialCell] = 0;
        this.lastMovedCell = direction;
    }

    public Integer getEmptyCell() {
        return emptyCell;
    }

    public Direction getLastMovedCell() {
        return lastMovedCell;
    }

    public Integer[] getMatrix() {
        return matrix;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < STATE_SIZE; i++) {
            if (i % 3 == 0 && i != 0) {
                stringBuilder.append("\n");
            }
            stringBuilder.append(matrix[i]).append(" | ");
        }
        return stringBuilder.toString();
    }

    public State getParrentState() {
        return parentState;
    }

    public void setParentState(State parrentState) {
        this.parentState = parrentState;
    }
}
