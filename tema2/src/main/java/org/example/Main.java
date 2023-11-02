package org.example;

import org.example.Alg.ArcConsistency;
import org.example.Alg.BKT;
import org.example.Alg.Utils;
import org.example.Instance.Variable;
import org.example.Instance.Sudoku;

import java.sql.Time;
import java.util.Comparator;
import java.util.List;
import java.util.Timer;

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

        Sudoku sudoku = new Sudoku(instance);
//        Sudoku sudokuTest = new Sudoku(instanceTest);

//        List<Variable> domainTest = Utils.getInitialDomains(sudokuTest);
        List<Variable> domain = Utils.getInitialDomains(sudoku);
//        ArcConsistency.arcConsistency(domain);

        var time = System.currentTimeMillis();
        System.out.println(BKT.runBKTWithFC(sudoku, domain));
        System.out.println(System.currentTimeMillis() - time);
        System.out.println("-----------------------------------------");
        System.out.println(BKT.runBKTfcMRV(sudoku, domain));
        System.out.println("-----------------------------------------");
        BKT.setChoiceOfVariable(x -> y -> Utils.getNextUnassignedVar(x, y));
        time = System.currentTimeMillis();
        System.out.println(BKT.run(new Sudoku(instance), domain));
        System.out.println(System.currentTimeMillis() - time);
        System.out.println("-----------------------------------------");
        BKT.setChoiceOfVariable(x -> y -> domain.stream()
                .filter(variable -> x.getVars()[variable.getLine()][variable.getColumn()] <= 0)
                .min(Comparator.comparingInt(i -> i.getDomain().size())).orElseThrow()
        );
        time = System.currentTimeMillis();
        System.out.println(BKT.run(new Sudoku(instance2), domain));
//        System.out.println(System.currentTimeMillis() - time);
//        System.out.println(BKT.runBKTWithFC(sudokuTest, domainTest));

//        time = System.currentTimeMillis();
//        System.out.println("-----------------------------------------");
//        System.out.println(BKT.run(sudokuTest, domainTest));
//        System.out.println(System.currentTimeMillis() - time);

    }
}