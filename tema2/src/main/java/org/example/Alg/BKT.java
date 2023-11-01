package org.example.Alg;

import org.example.Instance.Variable;
import org.example.Instance.Sudoku;
import org.example.NewUtils;
import org.example.Utils;

import java.util.List;
import java.util.Map;

public class BKT {
    public static Sudoku runBKTWithFC(Sudoku sudoku, List<Variable> domain) {
        if (NewUtils.isComplete(sudoku)) {
            return sudoku;
        }

        Variable var = NewUtils.getNextUnassignedVar(sudoku, domain);

        for (Integer i : var.getDomain()) {
            if (NewUtils.isConsistent(sudoku, var, i)) {
                var newSudoku = NewUtils.updateInstance(sudoku, var, i);
                var newDomains = NewUtils.updateDomains(domain, var, i);
                if (!NewUtils.existsEmptyDomain(newDomains)) {
                    var res = runBKTWithFC(newSudoku, newDomains);
                    if (res != null) {
                        return res;
                    }
                }
            }
        }
        return null;
    }

    public static Sudoku bkt(Sudoku sudoku, List<Variable> domain) {
        if (NewUtils.isComplete(sudoku)) {
            return sudoku;
        }
        var variable = NewUtils.getNextUnassignedVar(sudoku, domain);
        for (Integer i : variable.getDomain()) {
            if (NewUtils.isConsistent(sudoku, variable, i)) {
                var newSudoku = NewUtils.updateInstance(sudoku, variable, i);
                var res = bkt(newSudoku, domain);
                if(res != null){
                    return res;
                }
            }
        }
        return null;

    }
}
