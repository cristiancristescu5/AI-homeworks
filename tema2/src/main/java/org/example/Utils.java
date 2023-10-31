package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils {
    public static Map<Position, List<Integer>> getDomains(Sudoku sudoku) {//creating the initial domains
        List<Position> pos = sudoku.getPositions();

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
                        if(!domain.get(p).remove(vars[i][col])){
                            throw new IllegalArgumentException("Duplicate elements.");
                        }
                    }
                }
                for (int i = 0; i < 9; i++) {//columns
                    if (even.contains(vars[line][i])) {
                        if(!domain.get(p).remove(vars[line][i])){
                            throw new IllegalArgumentException("Duplicate elements.");
                        }
                    }
                }
            }

            if (vars[line][col] == -1) {//any number
                domain.get(p).addAll(maxDom);
                for (int i = 0; i < 9; i++) {//lines
                    if (vars[i][col] > 0) {
                        if(!domain.get(p).remove(vars[i][col])){
                            throw new IllegalArgumentException("Duplicate elements.");
                        }
                    }
                }
                for (int i = 0; i < 9; i++) {
                    if (vars[line][i] > 0) {
                        if(!domain.get(p).remove(vars[i][col])){
                            throw new IllegalArgumentException("Duplicate elements.");
                        }
                    }
                }
            }
        }
        return domain;
    }

    public static boolean isComplete(Sudoku sudoku){//checking it an assignment is solution
        Integer[][] var = sudoku.getVars();
        for(int i = 0 ; i < 9 ; i++){
            for(int j = 0 ; j < 9 ; j++){
                if(var[i][j] == -1 || var[i][j] == 0){
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isConsistent() {

        return true;
    }
}
