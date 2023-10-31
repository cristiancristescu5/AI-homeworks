package org.example.Instance;


public class Sudoku {
    public static final int SIZE = 81;
    private Integer[][] vars;

    public Sudoku(Integer[][] var) {
        if (var.length * var[0].length != SIZE) {
            throw new IllegalArgumentException("The length of the instance must be 81");
        }
        vars = var;
    }

    public void setCell(int line, int col, Integer val) {
        vars[line][col] = val;
    }

    public Integer[][] getVars() {
        return vars;
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
