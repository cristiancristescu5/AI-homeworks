package org.example;

import org.example.Alg.BKT;
import org.example.Instance.Variable;
import org.example.Instance.Sudoku;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Integer[][] instance = new Integer[][]{
                {8, 4, -1, -1, 5, -1, 0, -1, -1},
                {3, -1, -1, 6, -1, 8, -1, 4, -1},
                {-1, -1, 0, 4, -1, 9, -1, -1, 0},
                {-1, 2, 3, -1, 0, -1, 9, 8, -1},
                {1, -1, -1, 0, -1, 0, -1, -1, 4},
                {-1, 9, 8, -1, 0, -1, 1, 6, -1},
                {0, -1, -1, 5, -1, 3, 0, -1, -1},
                {-1, 3, -1, 1, -1, 6, -1, -1, 7},
                {-1, -1, 0, -1, 2, -1, -1, 1, 3}
        };
        Integer[][] instance2 = new Integer[][]{
                {8, 4, 9, 2, 5, 7, 6, 3, -1},
                {3, 5, 7, 6, 1, -1, 2, 4, -1},
                {6, 1, 0, 4, 3, 9, -1, 5, 0},
                {4, 2, 3, 7, 0, 1, -1, 8, 5},
                {1, 6, 5, 0, 9, 0, 3, 7, 4},
                {7, 9, 8, 3, 0, 5, -1, 6, 2},
                {0, 8, 1, 5, 7, 3, 0, 9, 6},
                {9, 3, -1, -1, -1, 6, 5, 2, 7},
                {5, 7, 0, 9, 2, -1, 8, 1, 3}
        };
//        Arrays.stream(instance2).forEach(arr -> Arrays.stream(arr).forEach(System.out::println));
        Sudoku sudoku = new Sudoku(instance);
        List<Variable> domain = NewUtils.getInitialDomains(sudoku);
//        System.out.println(NewUtils.isConsistent(sudoku, new Variable(1, 5),8));
//        System.out.println(domain);
        System.out.println(BKT.runBKTWithFC(sudoku, domain));
//        System.out.println(NewUtils.updateInstance(sudoku, new Variable(0, 2), 2));
    }
}