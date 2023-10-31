package org.example;

import org.example.Alg.BKT;
import org.example.Instance.Position;
import org.example.Instance.Sudoku;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Integer[][] instance = new Integer[][]{};
        Sudoku sudoku = new Sudoku(instance);
        List<Position> positions = Utils.getPositions(instance);
        Map<Position, List<Integer>> domain = Utils.getDomains(sudoku, positions);

        System.out.println(BKT.runBKTWithFC(sudoku, domain));
    }
}