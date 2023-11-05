package org.example;

import java.util.Scanner;

public class Game {
    public static void run(){
        State state = new State();
        Scanner scanner = new Scanner(System.in);
        int move;
        int round = 1;
        System.out.println("Human first move: ");
        while(!state.isFinal()) {
            if(round == 1){
                move = scanner.nextInt();
                var newState = Transitions.nextTransition(state, move, Player.HUMAN);
                if(Transitions.isValid(newState)){
                    state = newState;
                    round = -1;
                    System.out.println("AI next move");
                }
            }
            if(round == -1){
                System.out.println("Human next move");
            }
        }
        scanner.close();
        if(Transitions.isWinning(state, Player.AI)){
            System.out.println("Player AI is the winner!");
            return;
        }
        if(Transitions.isWinning(state, Player.HUMAN)){
            System.out.println("Player Human si the winner!");
            return;
        }
        System.out.println("Draw!");
    }
}
