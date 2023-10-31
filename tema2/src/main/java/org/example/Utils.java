package org.example;

import org.example.Instance.Position;
import org.example.Instance.Sudoku;

import java.util.*;

public class Utils {
    public static Map<Position, List<Integer>> getDomains(Sudoku sudoku, List<Position> pos) {//creating the initial domain
        Map<Position, List<Integer>> domain = new HashMap<>();//variables -> domain

        Integer[][] vars = sudoku.getVars();//instance

        List<Integer> even = List.of(2, 4, 6, 8);//even domain
        List<Integer> maxDom = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);//maximum domain

        for (Position p : pos) {
            domain.put(p, new ArrayList<>());

            int line = p.getLine();
            int col = p.getColumn();

            if (vars[line][col] > 0) {
                domain.get(p).add(vars[line][col]);
            }

            if (vars[line][col] == 0) {//even number
                domain.get(p).addAll(even);
                for (int i = 0; i < 9; i++) {//lines
                    if (even.contains(vars[i][col])) {
                        if (!domain.get(p).remove(vars[i][col])) {
                            throw new IllegalArgumentException("Duplicate elements.");
                        }
                    }
                }
                for (int i = 0; i < 9; i++) {//columns
                    if (even.contains(vars[line][i])) {
                        if (!domain.get(p).remove(vars[line][i])) {
                            throw new IllegalArgumentException("Duplicate elements.");
                        }
                    }
                }
            }

            if (vars[line][col] == -1) {//any number
                domain.get(p).addAll(maxDom);
                for (int i = 0; i < 9; i++) {//lines
                    if (vars[i][col] > 0) {
                        if (!domain.get(p).remove(vars[i][col])) {
                            throw new IllegalArgumentException("Duplicate elements.");
                        }
                    }
                }
                for (int i = 0; i < 9; i++) {
                    if (vars[line][i] > 0) {
                        if (!domain.get(p).remove(vars[i][col])) {
                            throw new IllegalArgumentException("Duplicate elements.");
                        }
                    }
                }
            }
        }
        return domain;
    }

    public static List<Position> getPositions(Integer[][] instance) {
        List<Position> positions = new ArrayList<>();
        for (int i = 0; i < instance.length; i++) {
            for (int j = 0; j < instance[0].length; j++) {
                positions.add(new Position(i, j));
            }
        }
        return positions;
    }

    public static boolean isComplete(Sudoku sudoku) {//checking it an assignment is solution
        Integer[][] var = sudoku.getVars();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (var[i][j] == -1 || var[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isConsistent(Sudoku sudoku, Position var, Integer value) {

        return true;
    }

    public static Sudoku updateInstance(Sudoku sudoku, Position var, Integer value) {
        sudoku.setCell(var.getLine(), var.getColumn(), value);
        return sudoku;
    }

    public static Position getNextUnassignedVar(Sudoku sudoku, Set<Position> positions) {
        int line = -1, col = -1;
        Integer[][] ins = sudoku.getVars();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (ins[i][j] <= 0) {
                    line = i;
                    col = j;
                }
            }
        }

        for (Position p : positions) {
            if (p.getColumn() == col && p.getLine() == line) {
                return p;
            }
        }
        return null;
    }

    public static Map<Position, List<Integer>> updateDomains(Map<Position, List<Integer>> dom, Position var, Integer value) {
        Map<Position, List<Integer>> domains = Map.copyOf(dom);
        Set<Position> positions = domains.keySet();

        int line = var.getLine();
        int col = var.getColumn();
        for (int i = 0; i < 9; i++) {//lines
            domains.get(getPosition(positions, i, col)).remove(value);
        }
        for (int j = 0; j < 9; j++) {
            domains.get(getPosition(positions, line, j)).remove(value);
        }
        return domains;
    }

    public static boolean existsEmptyDomain(Map<Position, List<Integer>> domain) {
        for (Position p : domain.keySet()) {
            if (domain.get(p).isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private static Position getPosition(Set<Position> pos, int line, int col) {
        for (Position p : pos) {
            if (p.getLine() == line && p.getColumn() == col) {
                return p;
            }
        }
        return null;
    }
}
