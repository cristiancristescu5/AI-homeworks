package org.example.State;

import org.example.Player;

import java.util.stream.IntStream;

public class Transitions {
    public static State nextTransition(State oldState, int value, Player player) {
        int[][] newTable = new int[State.SIZE][State.SIZE];
        int[][] old = oldState.getTable();

        IntStream.range(0, State.SIZE)
                .forEach(i -> IntStream.range(0, State.SIZE)
                        .forEach(j -> newTable[i][j] = old[i][j]));

        int line = State.getLine(value);
        int col = State.getColumn(value);

        if (newTable[line][col] != 0) {
            return null;
        }

        newTable[line][col] = player.getTag();
        return new State(newTable);
    }

    public static boolean isValid(State state) {
        return !(state == null);
    }

    public static boolean isWinning(State state, Player player) {
        int num = 0;
        int count = 0;
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        int[][] table = state.getTable();

        for (int i = 0; i < State.SIZE; i++) {
            for (int j = 0; j < State.SIZE; j++) {
                if (table[i][j] == player.getTag()) {
                    count++;
                }
                if (table[j][i] == player.getTag()) {
                    count3++;
                }
            }
            if (count3 == State.SIZE || count == State.SIZE) {
                return true;
            }
            count = 0;

            count3 = 0;
        }
        for (int i = 0; i < State.SIZE; i++) {
            if (table[i][i] == player.getTag()) {
                count1++;
            }
            if (table[i][State.SIZE - i - 1] == player.getTag()) {
                count2++;
            }
        }
        if (count1 == State.SIZE || count2 == State.SIZE) {
            return true;
        }
        return false;
    }

}
