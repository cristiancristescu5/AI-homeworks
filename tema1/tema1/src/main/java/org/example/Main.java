package org.example;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        // 0 2 3
        // 1 4 5
        // 6 7 8
        State state = new State(new Integer[]{2, 5, 3, 1, 0, 6, 4, 7, 8});
//        System.out.println(state);
//        System.out.println(Transition.moveUp(state));
//        System.out.println(Transition.moveDown(state));
//        System.out.println(Transition.moveLeft(state));
//        System.out.println(Transition.moveRight(state));
//        List<State> solution = IDDFS.run(state, 30);
        System.out.println(ALG.IDDFS(state, 10));
    }
}