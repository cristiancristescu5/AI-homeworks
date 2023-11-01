package org.example.Alg;

import javafx.util.Pair;
import org.example.Instance.Variable;

import java.util.*;

public class ArcConsistency {
    private static Queue<Pair<Variable, Variable>> pairing(List<Variable> domain) {
        Queue<Pair<Variable, Variable>> pairs = new LinkedList<>();
        for (int i = 0; i < domain.size() - 1; i++) {
            for (int j = i + 1; j < domain.size(); j++) {
                if (isCommon(domain.get(i), domain.get(j))) {
                    pairs.add(new Pair<>(domain.get(i), domain.get(j)));
                }
            }
        }
        return pairs;
    }

    private static boolean isCommon(Variable a, Variable b) {
        if (a.getLine() == b.getLine() || a.getColumn() == b.getColumn()) {
            return true;
        }

        return a.getLine() - a.getLine() % 3 == b.getLine() - b.getLine() % 3 && a.getColumn() - a.getColumn() % 3 == b.getColumn() - b.getColumn() % 3;

    }

    private static boolean checkConstraints(Integer x, Variable y) {
        for(Integer i : y.getDomain()){
            if(x.equals(i)){
                return true;
            }
        }
        return false;
    }

    public static void arcConsistency(List<Variable> domain) {
        Queue<Pair<Variable, Variable>> pairs = pairing(domain);

        while (!pairs.isEmpty()) {
            var pair = pairs.poll();
            boolean ok = true;
            List<Integer> visited = new ArrayList<>();
            for (Integer x : pair.getKey().getDomain()) {
                if (!checkConstraints(x, pair.getValue())) {
                    ok = false;
                    visited.add(x);
                }
            }
            for(Integer i : visited){
                pair.getKey().removeFromDomain(i);
            }
            if (!ok) {
                List<Variable> neighbours = getNeighbours(pair.getKey(), domain);
                neighbours.forEach(i -> pairs.add(new Pair<>(pair.getKey(), i)));
            }
        }
    }

    private static List<Variable> getNeighbours(Variable key, List<Variable> domain) {
        List<Variable> variables = new ArrayList<>();
        int line = key.getLine();
        int col = key.getColumn();

        if(line < 8){
            variables.add(Utils.getVariable(domain, line+1, col));
        }
        if(line > 0){
            variables.add(Utils.getVariable(domain, line-1, col));
        }
        if(col < 8){
            variables.add(Utils.getVariable(domain, line ,col+1));
        }
        if(col > 0){
            variables.add(Utils.getVariable(domain, line, col-1));
        }
        return variables;
    }
}
