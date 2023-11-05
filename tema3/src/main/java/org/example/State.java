package org.example;

import java.util.Arrays;
import java.util.stream.IntStream;

public class State {
    public static final Integer SIZE = 3;
    public static final int[][] validationTable = {{2, 7, 6}, {9, 5, 1}, {4, 3, 8}};
    private int[][] table;

    public State() {
        table = new int[SIZE][SIZE];
    }

    public State(int[][] toCopy) {
        table = new int[SIZE][SIZE];
        IntStream.range(0, SIZE)
                .forEach(i -> IntStream.range(0, SIZE)
                        .forEach(j -> table[i][j] = toCopy[i][j]));
    }

    public int[][] getTable() {
        return table;
    }

    public void setTable(int[][] table) {
        this.table = table;
    }


    public boolean isFinal() {
        return Arrays.stream(table)
                .allMatch(row -> Arrays.stream(row)
                        .allMatch(element -> element != 0));
    }

    public static int getColumn(int value) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (validationTable[i][j] == value) {
                    return j;
                }
            }
        }

        return -1;
    }

    public static int getLine(int value) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (validationTable[i][j] == value) {
                    return i;
                }
            }
        }

        return -1;
    }

    public int getFromCell(int line, int column) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (i == line && j == column) {
                    return table[i][j];
                }
            }
        }
        return -2;
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (table[i][j] == -1) {
                    stringBuilder.append("AI: ").append(validationTable[i][j]).append(" ");
                }
                if (table[i][j] == 1) {
                    stringBuilder.append("Human: ").append(validationTable[i][j]).append(" ");
                }
            }
        }
        return stringBuilder.toString();
    }
}
