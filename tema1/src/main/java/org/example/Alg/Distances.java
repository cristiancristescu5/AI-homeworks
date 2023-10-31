package org.example.Alg;

import org.example.State.State;

import java.util.List;

public class Distances {
    private static final List<Integer[]> finalStates = List.of(
            new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8},
            new Integer[]{1, 0, 2, 3, 4, 5, 6, 7, 8},
            new Integer[]{1, 2, 0, 3, 4, 5, 6, 7, 8},
            new Integer[]{1, 2, 3, 0, 4, 5, 6, 7, 8},
            new Integer[]{1, 2, 3, 4, 0, 5, 6, 7, 8},
            new Integer[]{1, 2, 3, 4, 5, 0, 6, 7, 8},
            new Integer[]{1, 2, 3, 4, 5, 6, 0, 7, 8},
            new Integer[]{1, 2, 3, 4, 5, 6, 7, 0, 8},
            new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 0}
    );

    public static int hammingDistance(State state) {
        //distanta minima de la o stare la o stare finala
        int minDist = Integer.MAX_VALUE;
        Integer[] list = state.getMatrix();
        for (Integer[] s : finalStates) {
            int dist = 0;
            for (int i = 0; i < State.STATE_SIZE; i++) {
                if (list[i] != 0 && s[i].intValue() != list[i]) {
                    dist++;
                }
            }
            minDist = Math.min(minDist, dist);
        }
        return minDist;
    }

    public static int manhattanDistance(State state) {
        int dist = 0;
        Integer[] list = state.getMatrix();
        for (int i = 0; i < State.STATE_SIZE; i++) {
            if (list[i] == 0) {
                continue;
            }
            int row = (list[i] - 1) / 3;
            int col = (list[i] - 1) % 3;
            int currRow = i / 3;
            int currCol = i % 3;
            dist += Math.abs(row - currRow) + Math.abs(col - currCol);
        }
        return dist;
    }

    public static int customDistance(State state) {
        //Euclidean distance, distanta de la o celula la pozitia ei din matricea finala
        int dist;
        int minDist = Integer.MAX_VALUE;
        Integer[] list = state.getMatrix();
        for (Integer[] s : finalStates) {
            dist = 0;
            for (int i = 0; i < State.STATE_SIZE; i++) {
                if (list[i] != 0 && s[i].intValue() != list[i]) {
                    int row = (list[i] - 1) / 3;
                    int col = (list[i] - 1) % 3;
                    int currRow = i / 3;
                    int currCol = i % 3;
                    dist += (int) Math.sqrt(Math.pow((row - currRow), 2) + Math.pow((currCol - col), 2));
                }
            }
            minDist = Math.min(minDist, dist);
        }
        return minDist;
    }
}
