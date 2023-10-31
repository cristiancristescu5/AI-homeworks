package org.example.Alg;

import org.example.Instance.Position;
import org.example.Instance.Sudoku;
import org.example.Utils;

import java.util.List;
import java.util.Map;

public class BKT {
    public static Sudoku runBKTWithFC(Sudoku sudoku, Map<Position, List<Integer>> domain, List<Position> positions) {
        if (Utils.isComplete(sudoku)) {
            return sudoku;
        }
        Position var = Utils.getNextUnassignedVar(sudoku, positions);
        if (var == null) {
            return null;
        }
        for (Integer i : domain.get(var)) {
            if (Utils.isConsistent(sudoku, var, i)) {
                var newSudoku = Utils.updateInstance(sudoku, var, i);
                var newDomains = Utils.updateDomains(domain, var, i);
                if (!Utils.existsEmptyDomain(newDomains)) {
                    var res = runBKTWithFC(newSudoku, newDomains, positions);
                    if (res != null) {
                        return res;
                    }
                }
            }
        }
        return null;
    }
}
