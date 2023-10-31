package org.example.Alg;

import jdk.jshell.execution.Util;
import org.example.Instance.Position;
import org.example.Instance.Sudoku;
import org.example.Utils;

import java.util.List;

public class BKT {
    public static Sudoku runBKTWithFC(Sudoku sudoku){
        if(Utils.isComplete(sudoku)){
            return sudoku;
        }
        Position var = AlgUtils.getNextUnassignedVar(sudoku);
        if(var == null){
            return null;
        }
        List<Integer> domain = sudoku.getDomain(var);
        for(Integer i : domain){
            if(Utils.isConsistent(sudoku, var, i)){
                Utils.updateInstance(sudoku, var, i);
                Utils.updateDomains(sudoku, var, i);

            }
        }


        return null;
    }
}
