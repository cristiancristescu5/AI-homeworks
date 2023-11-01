package org.example;

import org.example.Instance.Sudoku;
import org.example.Instance.Variable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        Integer[][] vars = sudoku.getVars();
        for (int i = 0; i < Sudoku.SIZE; i++) {
            for (int j = 0; j < Sudoku.SIZE; j++) {
                if (vars[i][j] <= 0) {
                    return getVariable(domain, i, j);
                }
            }
        }
        return null;
    }

    public static boolean isComplete(Sudoku sudoku) {
        Integer[][] values = sudoku.getVars();
        for (int i = 0; i < Sudoku.SIZE; i++) {
            for (int j = 0; j < Sudoku.SIZE; j++) {
                if (values[i][j] <= 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static List<Variable> updateDomains(List<Variable> oldDomains, Variable variable, Integer value) {
        int line = variable.getLine();
        int col = variable.getColumn();
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

    public static boolean isConsistent(Sudoku sudoku, Variable var, Integer value) {//verificare 3x3
        Integer[][] values = sudoku.getVars();
        int line = var.getLine();
        int col = var.getColumn();

        // check if element on the same row
        for (int i = 0; i < Sudoku.SIZE; i++) {
            if (values[line][i].equals(value)) {
                return false;
            }
        }

        // check if element on the same column
        for (int i = 0; i < Sudoku.SIZE; i++) {
            if (values[i][col].equals(value)) {
                return false;
            }
        }

        // check if in a 3x3 square it is unique
        if (line <= 2) {
            if (col <= 2)
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (value.equals(values[i][j])) {
                            return false;
                        }
                    }
                }
            else if(col <= 5){
                for (int i = 3; i <= 5; i++) {
                    for (int j = 3; j <= 5; j++) {
                        if (value.equals(values[i][j])) {
                            return false;
                        }
                    }
                }
            }
            else{
                for (int i = 6; i < 9; i++) {
                    for (int j = 6; j < 9; j++) {
                        if (value.equals(values[i][j])) {
                            return false;
                        }
                    }
                }
            }

        }
        if (line >= 3 && line <= 5) {
            if (col <= 2) {
                for (int i = 3; i <= 5; i++) {
                    for (int j = 0; j <= 2; j++) {
                        if (value.equals(values[i][j])) {
                            return false;
                        }
                    }
                }
            }
            if (col >= 3 && col <= 5) {
                for (int i = 3; i <= 5; i++) {
                    for (int j = 3; j <= 5; j++) {
                        if (value.equals(values[i][j])) {
                            return false;
                        }
                    }
                }
            }
            if (col >= 6) {
                for (int i = 3; i <= 5; i++) {
                    for (int j = 6; j <= 8; j++) {
                        if (values[i][j].equals(value)) {
                            return false;
                        }
                    }
                }
            }
        }
        if (line >= 6) {
            if (col <= 2) {
                for (int i = 6; i <= 8; i++) {
                    for (int j = 0; j <= 2; j++) {
                        if (value.equals(values[i][j])) {
                            return false;
                        }
                    }
                }
            }
            if (col >= 3 && col <= 5) {
                for (int i = 6; i <= 8; i++) {
                    for (int j = 3; j <= 5; j++) {
                        if (value.equals(values[i][j])) {
                            return false;
                        }
                    }
                }
            }
            if (col >= 6) {
                for (int i = 6; i <= 8; i++) {
                    for (int j = 6; j <= 8; j++) {
                        if (values[i][j].equals(value)) {
                            return false;
                        }
                    }
                }
            }
        }


        return true;
    }

    public static boolean existsEmptyDomain(List<Variable> variables) {
        for (Variable v : variables) {
            if (v.getDomain().isEmpty()) {
                return true;
            }
        }
        return false;
    }


    public static Sudoku updateInstance(final Sudoku sudoku, Variable var, Integer value) {
        Integer[][] newTable = Arrays.stream(sudoku.getVars()).map(Integer[]::clone).toArray(Integer[][]::new);
        newTable[var.getLine()][var.getColumn()] = value;
        return new Sudoku(newTable);
    }

    private static Variable getVariable(final List<Variable> variables, int i, int j) {
        for (Variable v : variables) {
            if (v.getLine() == i && v.getColumn() == j) {
                return v;
            }
        }
        return null;
    }
}
