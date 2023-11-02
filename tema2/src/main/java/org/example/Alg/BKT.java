package org.example.Alg;

import org.example.Instance.Variable;
import org.example.Instance.Sudoku;

import java.util.List;
import java.util.function.Function;

public class BKT {
    private static Function<Sudoku, Function<List<Variable>, Variable>> choiceOfVariable;

    public static Sudoku run(Sudoku sudoku, List<Variable> domains){

        if(Utils.isComplete(sudoku))
            return sudoku;

        Variable variable = choiceOfVariable.apply(sudoku).apply(domains);

        for (Integer value: variable.getDomain())
            if(Utils.isConsistent(sudoku, variable, value)){
                Integer previousValue = sudoku.getVars()[variable.getLine()][variable.getColumn()];
                sudoku.getVars()[variable.getLine()][variable.getColumn()] = value;

                var newDomains = Utils.updateDomains(domains, variable, value);

                if(Utils.existsEmptyDomain(newDomains)){
                    var result = run(sudoku, domains);
                    if(result != null)
                        return result;
                }

                sudoku.getVars()[variable.getLine()][variable.getColumn()] = previousValue;
            }

        return null;
    }

    public static void setChoiceOfVariable(Function<Sudoku, Function<List<Variable>, Variable>> choiceOfVariable) {
        BKT.choiceOfVariable = choiceOfVariable;
    }
}
