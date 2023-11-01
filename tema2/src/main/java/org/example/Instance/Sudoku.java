package org.example.Instance;


public class Sudoku {
    public static final int SIZE = 9;

    private final Integer[][] vars;

    public Sudoku(Integer[][] var) {
        if(var.length != Sudoku.SIZE || var[0].length != Sudoku.SIZE)
            throw new IllegalArgumentException("Matrix dimension must be " + Sudoku.SIZE + "!");

        vars = var;
    }

    public Integer[][] getVars() {
        return vars;
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < Sudoku.SIZE; i++) {
            for (int j = 0; j < Sudoku.SIZE; j++) {
                stringBuilder.append(vars[i][j]).append("|");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
