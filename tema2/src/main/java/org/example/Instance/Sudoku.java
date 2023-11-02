package org.example.Instance;


public class Sudoku {
    public static final int SIZE = 9;

    private final Integer[][] vars;

    public Sudoku(Integer[][] startingConfiguration) {
        if(startingConfiguration.length != Sudoku.SIZE || startingConfiguration[0].length != Sudoku.SIZE)
            throw new IllegalArgumentException("Matrix dimension must be " + Sudoku.SIZE + "!");

        vars = startingConfiguration;
    }

    public Integer[][] getVars() {
        return vars;
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int row = 0; row < Sudoku.SIZE; row++)
            for (int column = 0; column < Sudoku.SIZE; column++)
                stringBuilder.append(vars[row][column]).append(column == (Sudoku.SIZE - 1) ? "\n" : " | ");

        return stringBuilder.toString();
    }
}
