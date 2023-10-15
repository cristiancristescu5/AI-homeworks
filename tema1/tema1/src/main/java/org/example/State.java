package org.example;

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
}
