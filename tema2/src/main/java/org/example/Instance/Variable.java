package org.example.Instance;

import java.util.ArrayList;
import java.util.List;

public class Variable {
    private final int line;
    private final int column;

    private final List<Integer> domain;

    public Variable(int line, int column) {
        this.line = line;
        this.column = column;
        domain = new ArrayList<>(Sudoku.SIZE);
    }

    public Variable(int line, int column, List<Integer> domain) {
        this.line = line;
        this.column = column;
        this.domain = new ArrayList<>();
        this.domain.addAll(domain);
    }

    public int getColumn() {
        return column;
    }

    public int getLine() {
        return line;
    }

    @Override
    public String toString() {
        return "(" + line + ", " + column + ")" + ": " + domain +  "\n";
    }

    public List<Integer> getDomain() {
        return domain;
    }
    public void setDomain(List<Integer> domain){
        this.domain.addAll(domain);
    }
    public void removeFromDomain(Integer i){
        domain.remove(i);
    }
    public void addToDomain(Integer i){
        domain.add(i);
    }
    public void addToDomain(List<Integer> dom){
        this.domain.addAll(dom);
    }


}
