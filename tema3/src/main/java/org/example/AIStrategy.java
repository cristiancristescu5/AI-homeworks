package org.example;

public class AIStrategy {

    private static int heuristic(State state, Player player) {
        int countHuman = 0;
        int countAI = 0;
        int[][] fullTable = State.validationTable;

        for (int i = 0; i < State.SIZE; i++) {//rows
            boolean winHuman = true;
            boolean winAI = true;
            for (int j = 0; j < State.SIZE; j++) {
                int val = state.getFromCell(i, j);
                if (val == 1) {//HUMAN
                    winAI = false;
                }
                if (val == -1) {//AI
                    winHuman = false;
                }
            }
            if (winHuman) {
                countHuman++;//Human
            }
            if (winAI) {
                countAI++;//AI
            }
        }
        for (int i = 0; i < State.SIZE; i++) {//column
            boolean winHuman = true;
            boolean winAI = true;
            for (int j = 0; j < State.SIZE; j++) {
                int val = state.getFromCell(j, i);
                if (val == 1) {//HUMAN
                    winAI = false;
                }
                if (val == -1) {//AI
                    winHuman = false;
                }
            }
            if (winHuman) {
                countHuman++;//Human
            }
            if (winAI) {
                countAI++;//AI
            }
        }
        boolean winHuman = true, winAI = true;
        for (int i = 0; i < State.SIZE; i++) {
            int val = state.getFromCell(i, i);
            if (val == -1) {
                winHuman = false;
            }
            if (val == 1) {
                winAI = false;
            }

        }
        if (winHuman) {
            countHuman++;
        }
        if (winAI) {
            countAI++;
        }
        winAI = true;
        winHuman = true;
        for (int i = 0; i < State.SIZE; i++) {
            int val = state.getFromCell(i, State.SIZE - i - 1);
            if (val == 1) {
                winAI = false;
            }
            if (val == -1) {
                winHuman = false;
            }
        }
        if (winHuman) {
            countHuman++;//Human
        }
        if (winAI) {
            countAI++;//AI
        }
        System.out.println(countHuman + " " + countAI);
        switch (player.getTag()) {
            case 1 -> {
                return countHuman - countAI;
            }
            case -1 -> {
                return countAI - countHuman;
            }
        }
        return 0;
    }
}
