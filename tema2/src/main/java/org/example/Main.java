package org.example;

import org.example.Alg.BKT;
import org.example.Alg.Utils;
import org.example.Instance.Variable;
import org.example.Instance.Sudoku;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        Function<Sudoku, Function<List<Variable>, Variable>> getNextUnassignedVar = x -> y -> {
            for (int row = 0; row < Sudoku.SIZE; row++)
                for (int column = 0; column < Sudoku.SIZE; column++)
                    if (x.getVars()[row][column] <= 0)
                        return Utils.getVariable(y, row, column);

            return null;
        };

        Function<Sudoku, Function<List<Variable>, Variable>> getNextUnassignedMinVar = x -> y -> y.stream()
                .filter(variable -> x.getVars()[variable.getLine()][variable.getColumn()] <= 0)
                .min(Comparator.comparingInt(i -> i.getDomain().size())).orElseThrow();

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

        Integer[][] instanceTest = new Integer[][]{
                {-1, -1, 6, 3, 0, -1, 4, 0, -1},
                {3, 0, -1, -1, 0, 2, 8, -1, -1},
                {0, 5, 2, 0, 7, -1, -1, -1, 6},
                {-1, -1, -1, 6, 5, 0, 0, 0, -1},
                {5, 0, -1, 0, 0, -1, -1, 0, 1},
                {0, 8, 4, -1, -1, -1, -1, 0, 7},
                {4, 0, 7, -1, -1, 0, 3, -1, 0},
                {-1, 3, 8, -1, 0, 1, 6, -1, 0},
                {0, -1, -1, 2, -1, 0, -1, 9, 4}
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
        Sudoku sudokuTest = new Sudoku(instanceTest);

        List<Variable> domainTest = Utils.getInitialDomains(sudokuTest);
        List<Variable> domain = Utils.getInitialDomains(sudoku);

        BKT.setChoiceOfVariable(getNextUnassignedVar);
        System.out.println(BKT.run(new Sudoku(instance), domain));

        BKT.setChoiceOfVariable(getNextUnassignedMinVar);
        System.out.println("-----------------------------------------");
        System.out.println(BKT.run(new Sudoku(instance2), domain));

        BKT.setChoiceOfVariable(getNextUnassignedVar);
        System.out.println("-----------------------------------------");
        System.out.println(BKT.run(sudokuTest, domainTest));

    }
}