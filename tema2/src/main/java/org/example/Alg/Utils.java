package org.example.Alg;

import org.example.Instance.Sudoku;
import org.example.Instance.Variable;

import java.util.*;
import java.util.stream.IntStream;

public class Utils {
    public static List<Variable> getInitialDomains(Sudoku sudoku) {
        Integer[][] configuration = sudoku.getVars();

        List<Variable> variables = new ArrayList<>();

        List<Integer> even = List.of(2, 4, 6, 8),
                maxDomain = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);

        for (int row = 0; row < Sudoku.SIZE; row++) {
            for (int column = 0; column < Sudoku.SIZE; column++) {
                variables.add(new Variable(row, column));
            }
        }
        for (Variable variable : variables) {
            int line = variable.getLine(),
                    col = variable.getColumn();

            if (configuration[line][col] > 0) {
                variable.addToDomain(configuration[line][col]);
                continue;
            }

            variable.addToDomain(configuration[line][col] == 0 ? even : maxDomain);

            if (configuration[line][col] == 0) {//even number
                for (int i = 0; i < 9; i++) {//lines
                    if (even.contains(configuration[i][col])) {
                        variable.removeFromDomain(configuration[i][col]);
                    }
                }
                for (int i = 0; i < 9; i++) {//columns
                    if (even.contains(configuration[line][i])) {
                        variable.removeFromDomain(configuration[line][i]);
                    }
                }
            }

            if (configuration[line][col] == -1) {//any number
                for (int i = 0; i < 9; i++) {//lines
                    if (configuration[i][col] > 0) {
                        variable.removeFromDomain(configuration[i][col]);
                    }
                }
                for (int i = 0; i < 9; i++) {
                    if (configuration[line][i] > 0) {
                        variable.removeFromDomain(configuration[line][i]);
                    }
                }
            }
        }

        return variables;
    }

    public static boolean isComplete(Sudoku sudoku) {
        return Arrays.stream(sudoku.getVars())
                .allMatch(row -> Arrays.stream(row).allMatch(val -> val > 0));
    }

    public static List<Variable> updateDomains(List<Variable> oldDomains, Variable variable, Integer value) {
        List<Variable> newDomains = new ArrayList<>();
        for (Variable v : oldDomains) {
            newDomains.add(new Variable(v.getLine(), v.getColumn(), v.getDomain()));
        }
        for (int i = variable.getColumn() + 1; i < Sudoku.SIZE; i++) {
            Variable v = getVariable(newDomains, variable.getLine(), i);
            assert v != null;
            if (v.getDomain().contains(value)) {
                v.removeFromDomain(value);
            }
        }

        for (int i = variable.getLine() + 1; i < Sudoku.SIZE; i++) {
            Variable v = getVariable(newDomains, i, variable.getColumn());
            assert v != null;
            if (v.getDomain().contains(value)) {
                v.removeFromDomain(value);
            }
        }

        int initLine = variable.getLine() - variable.getLine() % 3;
        int initCol = variable.getColumn() - variable.getColumn() % 3;

        int lastLine =  initLine + 3;
        int lastCol = initCol + 3;

//        List<Variable> dom = List.copyOf(newDomains);
//
//        dom.stream().filter(i -> (i.getLine() >= initLine && i.getLine() < lastLine && i.getColumn() >= initCol && i.getColumn() < lastCol))
//                .forEach(i -> i.removeFromDomain(value));
//
//        for(Variable v :){
//
//        }

        return newDomains;
    }

    public static boolean isConsistent(Sudoku sudoku, Variable var, Integer value) {
        var configuration = sudoku.getVars();
        int rowPos = var.getLine();
        int colPos = var.getColumn();

        // checks if :value: is unique in his square
        for (int row = rowPos - rowPos % 3, limitRow = row + 3; row < limitRow; row++)
            for (int column = colPos - colPos % 3, limitCol = column + 3; column < limitCol; column++)
                if (configuration[row][column].equals(value))
                    return false;

        // checks if :value: is unique on column and row
        return IntStream.range(0, Sudoku.SIZE)
                .noneMatch(ind -> configuration[ind][colPos].equals(value) || configuration[rowPos][ind].equals(value));
    }

    public static boolean existsEmptyDomain(List<Variable> variables) {
        return variables.stream().noneMatch(variable -> variable.getDomain().isEmpty());
    }

    public static Variable getVariable(List<Variable> variables, int row, int column) {
        return variables.stream()
                .filter(variable -> variable.getLine() == row && variable.getColumn() == column)
                .findFirst().orElseThrow();
    }
}
