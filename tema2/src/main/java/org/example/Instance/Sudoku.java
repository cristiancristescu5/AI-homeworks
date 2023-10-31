package org.example.Instance;

import org.example.Instance.Position;
import org.example.Utils;

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

    public void setCell(int line, int col, Integer val) {
        vars[line][col] = val;
    }

    public Integer[][] getVars() {
        return vars;
    }

    public Map<Position, List<Integer>> getDomains() {
        return domains;
    }

    public List<Integer> getDomain(Position p) {
        return domains.get(p);
    }

    public Position getPosition(int line, int col) {
        for (Position p : positions) {
            if (p.getLine() == line && p.getColumn() == col) {
                return p;
            }
        }
        return null;
    }


    public List<Position> getPositions() {
        return positions;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                stringBuilder.append(vars[i][j]).append("|");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
