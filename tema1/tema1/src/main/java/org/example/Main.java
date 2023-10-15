package org.example;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        State state = new State(new Integer[]{8, 6, 7, 2, 5, 4, 0, 3, 1});
        System.out.println(state.toString());
        System.out.println(Transition.moveLeft(state));

    }
}