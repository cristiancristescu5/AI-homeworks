package org.example.Game;

import org.example.Player;
import org.example.State.State;
import org.example.State.Transitions;

import java.util.ArrayList;
import java.util.List;

public class AIStrategy {

    public static int minimax(State state, int depth, Player player) {
        if (depth == 0 || state.isFinal()) {
            return heuristic(state);
        }
        int value;
        if (player.getTag() == 1) {
            value = Integer.MIN_VALUE;
            for (State s : getNeighbours(state, Player.AI)) {
                value = Integer.max(value, minimax(s, depth - 1, Player.AI));
            }
        } else {
            value = Integer.MAX_VALUE;
            for (State s : getNeighbours(state, Player.HUMAN)) {
                value = Integer.min(value, minimax(s, depth - 1, Player.HUMAN));
            }
        }
        return value;
    }

    public static List<State> getNeighbours(State state, Player player) {
        List<State> states = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            State newState = Transitions.nextTransition(state, i, player);
            if (Transitions.isValid(newState)) {
                states.add(newState);
            }
        }
        return states;
    }


    private static int heuristic(State state) {
        int countHuman = 0;
        int countAI = 0;

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

        if(Transitions.isWinning(state, Player.HUMAN)){
            return Integer.MAX_VALUE;
        }
        if(Transitions.isWinning(state, Player.AI)){
            return Integer.MIN_VALUE;
        }

        return countHuman - countAI;
    }
}
