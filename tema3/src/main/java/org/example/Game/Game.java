package org.example.Game;

import org.example.Player;
import org.example.State.State;
import org.example.State.Transitions;

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
                    System.out.println(state);
                }else{
                    System.out.println("Invalid move! Try another one");
                }
                if(Transitions.isWinning(state, Player.HUMAN)){
                    System.out.println("Player Human is the winner!");
                    scanner.close();
                    return;
                }
                if(round == -1){
                    System.out.println("AI next move!");
                    System.out.println("-----------------------------------------------");
                    continue;
                }
            }
            if(round == -1){
                int bestMove = -1;
                int bestValue = Integer.MIN_VALUE;
                for(int i = 1 ; i < 10 ; i++){
                    if(!state.isChosen(i)){
                        int val = AIStrategy.minimax(Transitions.nextTransition(state, i, Player.AI), 10, Player.AI);
                        if(val > bestValue){
                            bestValue = val;
                            bestMove = i;
                        }
                    }
                }
                System.out.println(bestMove);
                var newState = Transitions.nextTransition(state, bestMove, Player.AI);
                state = newState;
                round = 1;
                System.out.println(state);
                if(Transitions.isWinning(state, Player.AI)){
                    System.out.println("Player AI is the winner!");
                    scanner.close();
                    return;
                }
                System.out.println("Human next move");
                System.out.println("--------------------------------------------------------------");
            }

        }
        scanner.close();
        System.out.println("Draw!");
    }
}
