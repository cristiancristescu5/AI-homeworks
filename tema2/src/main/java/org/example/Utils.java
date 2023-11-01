package org.example;

import org.example.Instance.Variable;
import org.example.Instance.Sudoku;

import java.util.*;

public class Utils {
    public static Map<Variable, List<Integer>> getInitialDomains(Sudoku sudoku, List<Variable> pos) {//creating the initial domain
        Map<Variable, List<Integer>> domain = new HashMap<>();//variables -> domain

        Integer[][] vars = sudoku.getVars();//instance

        List<Integer> even = List.of(2, 4, 6, 8);//even domain
        List<Integer> maxDom = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);//maximum domain

        for (Variable p : pos) {
            domain.put(p, new ArrayList<>());

            int line = p.getLine();
            int col = p.getColumn();
            
            if (vars[line][col] > 0) {
                domain.get(p).add(vars[line][col]);
            }
            if(vars[line][col] == 0){
                domain.get(p).addAll(even);
            }
            if(vars[line][col] == -1){
                domain.get(p).addAll(maxDom);
            }


//            if (vars[line][col] == 0) {//even number
//                domain.get(p).addAll(even);
//                for (int i = 0; i < 9; i++) {//lines
//                    if (even.contains(vars[i][col])) {
//                        domain.get(p).remove(vars[i][col]);
//                    }
//                }
//                for (int i = 0; i < 9; i++) {//columns
//                    if (even.contains(vars[line][i])) {
//                        domain.get(p).remove(vars[line][i]);
//                    }
//                }
//            }
//
//            if (vars[line][col] == -1) {//any number
//                domain.get(p).addAll(maxDom);
//                for (int i = 0; i < 9; i++) {//lines
//                    if (vars[i][col] > 0) {
//                        domain.get(p).remove(vars[i][col]);
//                    }
//                }
//                for (int i = 0; i < 9; i++) {
//                    if (vars[line][i] > 0) {
//                        domain.get(p).remove(vars[line][i]);
//                    }
//                }
//            }
        }
        return domain;
    }

    public static List<Variable> getPositions(Integer[][] instance) {
        List<Variable> positions = new ArrayList<>();
        for (int i = 0; i < instance.length; i++) {
            for (int j = 0; j < instance[0].length; j++) {
                positions.add(new Variable(i, j));
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

    public static boolean isConsistent(Sudoku sudoku, Variable var, Integer value) {
        Integer[][] vars = sudoku.getVars();

        int line = var.getLine();
        int col = var.getColumn();

        for (int i = 0; i < 9; i++) {
            if ((vars[line][i].equals(value) && i != var.getColumn()) || (vars[i][col].equals(value) && i != var.getLine())) {
                return false;
            }
        }
        return true;
    }

    public static Sudoku updateInstance(Sudoku sudoku, Variable var, Integer value) {
        Integer[][] newVars = new Integer[9][9];
        Integer[][] old = sudoku.getVars();
        for (int i = 0; i < 9; i++) {
            newVars[i] = Arrays.copyOf(old[0], 9);
        }
        newVars[var.getLine()][var.getColumn()] = value;
        return new Sudoku(newVars);
    }

    public static Variable getNextUnassignedVar(Sudoku sudoku, List<Variable> positions) {
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

        for (Variable p : positions) {
            if (p.getColumn() == col && p.getLine() == line) {
                return p;
            }
        }
        return null;
    }

    public static Map<Variable, List<Integer>> updateDomains(final Map<Variable, List<Integer>> dom, Variable var, Integer value) {
        Map<Variable, List<Integer>> domains = new HashMap<>();
        Set<Variable> positions = domains.keySet();
        for(Variable p : dom.keySet()){
            domains.put(p, new ArrayList<>());
            domains.get(p).addAll(dom.get(p));
        }
        int line = var.getLine();
        int col = var.getColumn();
        for(int i = 0 ; i < 9 ; i++){
            if(i != line || i != col) {
                domains.get(getPosition(positions, i, col)).remove(value);
                domains.get(getPosition(positions, line, i)).remove(value);
            }
        }
//        for (int i = 0; i < 9; i++) {//lines
//
//            domains.get(getPosition(positions, i, col)).remove(value);
//        }
//        for (int j = 0; j < 9; j++) {
//            domains.get(getPosition(positions, line, j)).remove(value);
//        }
        return domains;
    }

    public static boolean existsEmptyDomain(Map<Variable, List<Integer>> domain) {
        for (Variable p : domain.keySet()) {
            if (domain.get(p).isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private static Variable getPosition(Set<Variable> pos, int line, int col) {
        for (Variable p : pos) {
            if (p.getLine() == line && p.getColumn() == col) {
                return p;
            }
        }
        return null;
    }
    public static void printDomain(Map<Variable, List<Integer>> dom, List<Variable> positions) {
        for(Variable p : positions){
            System.out.println(p + ":" + dom.get(p));
        }
        System.out.println();
    }
}
