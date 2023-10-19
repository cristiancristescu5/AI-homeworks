package org.example;

public class Main {

    public static void main(String[] args) {
        State state = new State(new Integer[]{2, 7, 5, 0, 8, 4, 3, 1, 6});
        State state1 = new State(new Integer[]{2, 5, 3, 1, 0, 6, 4, 7, 8});
        State state2 = new State(new Integer[]{8, 6, 7, 2, 5, 4, 0, 3, 1});
        System.out.println("Initial state:\n" + state);
        System.out.println(IDDFS.run(state, 21));
        IDDFS.printSolution();
        System.out.println("Initial state:\n" + state1);
        System.out.println(IDDFS.run(state1, 21));
        IDDFS.printSolution();
        System.out.println("Initial state:\n" + state2);
        System.out.println(IDDFS.run(state2, 33));
        IDDFS.printSolution();
    }
}