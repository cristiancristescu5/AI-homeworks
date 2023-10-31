package org.example;

import java.util.*;

public class Sudoku {
    public static final int SIZE = 81;
    private Integer[][] vars;
    private Map<Position, List<Integer>> domains;
    private List<Position> positions = new ArrayList<>(SIZE);

    public Sudoku(Integer[][] var) {
        if (var.length * var[0].length != SIZE) {
            throw new IllegalArgumentException("The length of the instance must be 81");
        }
        vars = var;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                positions.add(new Position(i, j));
            }
        }
        domains = Utils.getDomains(this);
    }

    public Integer[][] getVars() {
        return vars;
    }

    public Map<Position, List<Integer>> getDomains() {
        return domains;
    }

    public List<Integer> getDomain(int l, int c) {
        return domains.get(new Position(l, c));
    }

    public void setVars(Integer[][] vars) {
        this.vars = vars;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setDomain(int l, int c, List<Integer> domain) {

    }
}
