/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terningspil;

import java.util.Scanner;

/**
 *
 * @author Soren
 */
public class GameLogic {
//deklarerer attributter

    Player player;
    double bet;

    private Scanner sc = new Scanner(System.in);

    //attributterne bliver initieret med konstruktørens parametre.
    public GameLogic(Player player, double bet) {

        this.player = player;
        this.bet = bet;

    }

    //metode til at tjekke om klientens svar er rigtigt/forkert 
    public boolean checkGuess(int terning1, int terning2, int svar, int svar1) {

        if (terning1 == svar && terning2 == svar1 || terning2 == svar && terning1 == svar1) {

            System.out.println("Rigtigt!");
            return true;
        } else {

            System.out.println("Forkert");
            return false;

        }

    }

}
