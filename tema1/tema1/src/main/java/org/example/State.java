package org.example;

import java.util.Arrays;
import java.util.concurrent.ConcurrentMap;

public class State {//3 partitions
    public static final Integer STATE_SIZE = 9;
    private final Integer[] matrix;
    private Direction lastMovedCell;
    private Integer emptyCell;

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

    public State(State toCopy) {
        this.matrix = new Integer[STATE_SIZE];
        for (int i = 0; i < STATE_SIZE; i++) {
            this.matrix[i] = toCopy.getCellAt(i);
        }
        this.emptyCell = -1;
        this.lastMovedCell = Direction.NONE;
    }

    public State(State toCopy, Integer emptyCellPos, Integer initialCell, Direction direction){
        this.matrix = Arrays.copyOf(toCopy.getMatrix(), State.STATE_SIZE);
        this.emptyCell = emptyCellPos;
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

    public void setLastMovedCell(Direction lastMovedCell) {
        this.lastMovedCell = lastMovedCell;
    }

    public void setEmptyCell(Integer emptyCell) {
        this.emptyCell = emptyCell;
    }

    public Integer getCellAt(int i) {
        return matrix[i];
    }

    public void setCellAt(int index, int num) {
        matrix[index] = num;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < STATE_SIZE; i++) {
            if ((i - 3) % 3 == 0) {
                stringBuilder.append("\n");
            }
            stringBuilder.append(matrix[i]).append(" | ");
        }
        return stringBuilder.toString();
    }

//    @Override
//    public int compareTo(State o) {
//        return 0;
//    }
}
