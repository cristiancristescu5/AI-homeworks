package org.example.Alg;

import org.example.Instance.Variable;
import org.example.Instance.Sudoku;

import java.util.List;

public class BKT {
    public static Sudoku runBKTWithFC(Sudoku sudoku, List<Variable> domain) {
        if (Utils.isComplete(sudoku)) {
            return sudoku;
        }

        Variable var = Utils.getNextUnassignedVar(sudoku, domain);

        for (Integer i : var.getDomain()) {
            if (Utils.isConsistent(sudoku, var, i)) {
                var newSudoku = Utils.updateInstance(sudoku, var, i);
                var newDomains = Utils.updateDomains(domain, var, i);
                if (!Utils.existsEmptyDomain(newDomains)) {
                    var res = runBKTWithFC(newSudoku, newDomains);
                    if (res != null) {
                        return res;
                    }
                }
            }
        }
        return null;
    }
    public static Sudoku runBKTfcMRV(Sudoku sudoku, List<Variable> domain){
        if(Utils.isComplete(sudoku)){
            return sudoku;
        }

        Variable variable = Utils.getNextUnassignedVarMRV(sudoku, domain).get();

        for(Integer i : variable.getDomain()){
            if(Utils.isConsistent(sudoku, variable, i)){
                var newSudoku = Utils.updateInstance(sudoku, variable, i);
                var newDomains = Utils.updateDomains(domain, variable, i);
                if(!Utils.existsEmptyDomain(newDomains)){
                    var res = runBKTfcMRV(newSudoku, newDomains);
                    if(res != null){
                        return res;
                    }
                }
            }
        }
        return null;
    }
}
