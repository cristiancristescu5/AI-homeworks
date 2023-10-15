package org.example;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        // 0 2 3
        // 1 4 5
        // 6 7 8
        State state = new State(new Integer[]{2, 7, 5, 0, 8, 4, 3, 1, 6});
//        System.out.println(state);
//        System.out.println(Transition.moveUp(state));
//        System.out.println(Transition.moveDown(state));
//        System.out.println(Transition.moveLeft(state));
//        System.out.println(Transition.moveRight(state));
        List<State> solution = IDDFS.run(state, 30);
        assert solution != null;
        for(var s : solution){
            System.out.println(s);
            System.out.println();
        }
    }
}