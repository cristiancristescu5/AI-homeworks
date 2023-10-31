package org.example.Alg;

import org.example.Instance.Position;
import org.example.Instance.Sudoku;

public class AlgUtils {
    public static Position getNextUnassignedVar(Sudoku sudoku) {
        Integer[][] vars = sudoku.getVars();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(vars[i][j] == 0 || vars[i][j] == -1){
                    return sudoku.getPosition(i,j);
                }
            }
        }
        return null;
    }
}
