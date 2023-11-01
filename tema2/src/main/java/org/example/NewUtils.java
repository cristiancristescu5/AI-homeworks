package org.example;

import org.example.Instance.Sudoku;
import org.example.Instance.Variable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class NewUtils {
    public static List<Variable> getInitialDomains(Sudoku sudoku) {
        Integer[][] values = sudoku.getVars();
        List<Variable> variables = new ArrayList<>();
        List<Integer> even = List.of(2, 4, 6, 8);
        List<Integer> maxDomain = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);

        for (int i = 0; i < Sudoku.SIZE; i++) {
            for (int j = 0; j < Sudoku.SIZE; j++) {
                variables.add(new Variable(i, j));
            }
        }
        for (Variable v : variables) {
            int line = v.getLine();
            int col = v.getColumn();
            if (values[line][col] > 0) {
                v.addToDomain(values[line][col]);
            }
            if (values[line][col] == 0) {//even number
                v.addToDomain(even);
                for (int i = 0; i < 9; i++) {//lines
                    if (even.contains(values[i][col])) {
                        v.removeFromDomain(values[i][col]);
                    }
                }
                for (int i = 0; i < 9; i++) {//columns
                    if (even.contains(values[line][i])) {
                        v.removeFromDomain(values[line][i]);
                    }
                }
            }

            if (values[line][col] == -1) {//any number
                v.addToDomain(maxDomain);
                for (int i = 0; i < 9; i++) {//lines
                    if (values[i][col] > 0) {
                        v.removeFromDomain(values[i][col]);
                    }
                }
                for (int i = 0; i < 9; i++) {
                    if (values[line][i] > 0) {
                        v.removeFromDomain(values[line][i]);
                    }
                }
            }
        }

        return variables;
    }

    public static Variable getNextUnassignedVar(Sudoku sudoku, List<Variable> domain) {
        var configuration = sudoku.getVars();
        for (int row = 0; row < Sudoku.SIZE; row++)
            for (int column = 0; column < Sudoku.SIZE; column++)
                if (configuration[row][column] <= 0)
                    return getVariable(domain, row, column);

        return null;
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

        return newDomains;
    }

    public static boolean isConsistent(Sudoku sudoku, Variable var, Integer value) {
        var configuration = sudoku.getVars();
        int line = var.getLine();
        int col = var.getColumn();

        var found = IntStream.range(0, Sudoku.SIZE)
                .noneMatch(ind -> configuration[ind][col].equals(value) || configuration[line][ind].equals(value));

        for(int row = line - line % 3, limitRow = row + 3; row < limitRow; row++)
            for(int column = col - col % 3, limitCol = column + 3; column < limitCol; column++)
                if(configuration[row][column].equals(value))
                    return false;
        return found;
    }

    public static boolean existsEmptyDomain(List<Variable> variables) {
        return variables.stream().anyMatch(variable -> variable.getDomain().isEmpty());
    }


    public static Sudoku updateInstance(final Sudoku sudoku, Variable var, Integer value) {
        Integer[][] newTable = Arrays.stream(sudoku.getVars()).map(Integer[]::clone).toArray(Integer[][]::new);
        newTable[var.getLine()][var.getColumn()] = value;
        return new Sudoku(newTable);
    }

    private static Variable getVariable(final List<Variable> variables, int row, int column) {
        return variables.stream()
                .filter(variable -> variable.getLine() == row && variable.getColumn() == column)
                .findFirst().orElseThrow();
    }
}
