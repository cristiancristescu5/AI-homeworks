package org.example;

public class Transitions {
    public static State nextTransition(State oldState, int value, Player player) {
        int[][] old = oldState.getTable();
        int line = State.getLine(value);
        int col = State.getColumn(value);

        if (old[line][col] != 0) {
            return null;
        }

        old[line][col] = player.getTag();
        return new State(old);
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
                if (i == j && table[i][j] == player.getTag()) {
                    count1++;
                }
                if (i == State.SIZE - j - 1 && table[i][j] == player.getTag()) {
                    count2++;
                }
                if (table[j][i] == player.getTag()) {
                    count3++;
                }
            }
            if (count3 == State.SIZE || count2 == State.SIZE || count == State.SIZE || count1 == State.SIZE) {
                return true;
            }
            count = 0;
            count1 = 0;
            count2 = 0;
            count3 = 0;
        }
        return false;
    }

}
