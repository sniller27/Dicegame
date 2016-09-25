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
public class GameEngine {
//deklarerer attributter.

    private int tur;
    private int svar, svar1;
    private double bet;
    private Die DieObject;
    private Player playerObject;
    private GameTurn gameTurnObject;
    private GameLogic GameLogicObject;
    private boolean sandfalsk, fortsæt;
    private Scanner sc;

    public GameEngine() {
        //konstruktøren laver objekter af klasserne til brug.
        DieObject = new Die();
        playerObject = new Player();
        gameTurnObject = new GameTurn();
        sc = new Scanner(System.in);
        fortsæt = true;
        tur = 0;

    }

    public void run() {
        //gameloopet foregår her
        System.out.println("credits: " + playerObject.getCredit());
        while (playerObject.getCredit() > 0 && fortsæt) {
            rulle();    //ruller terningerne
            sats();     //satser
            gæt();      //gætter på tal
            check();    //ser om man har vundet og om man har lyst til spille igen
        }
        statistik();    //viser afsluttende statistisk
    }

    public void rulle() {
        //terning rull
        DieObject.roll();
        //terning sum
        System.out.println("sum: " + DieObject.getSum());

    }

    public void sats() {
        //her indtaster du dit sats
        boolean satsstop = false;
        while (!satsstop) {
            System.out.print("Hvor meget vil du satse: ");
            String str = sc.nextLine();
            if (!str.isEmpty()) {
                bet = Integer.parseInt(str);
                satsstop = true;
            } else {
                System.out.println("Du skal vælge et tal");
            }

        }

        //opretter object til GameLogic klassen.
        GameLogicObject = new GameLogic(playerObject, bet);
        //Du kan kun satse den mængde credits du har, hverken over eller under.
        while (bet > playerObject.getCredit() || bet < 0) {
            System.out.println("Du kan kun satse den mængde credits du har\nHvor meget vil du satse?");
            bet = sc.nextInt();
        }

    }

    public void gæt() {
        //her gætter du og du kan kun gætte tal
        System.out.println("Hvad gætter du?");
        System.out.print("Gæt 1: ");

        while (!sc.hasNextInt()) {
            System.out.println("Du skal vælge et tal");
            sc.nextLine();
        }

        //klienter skriver terninge gæt og kan kun vælge tal fra 1 til 6.
        svar = sc.nextInt();

        System.out.print("Gæt 2: ");
        while (!sc.hasNextInt()) {
            System.out.println("Du skal vælge et tal");
            sc.nextLine();
        }

        svar1 = sc.nextInt();
        while (svar <= 0 || svar1 <= 0 || svar > 6 || svar1 > 6) {
            System.out.println("Du kan kun vælge tal fra 1 til 6\nHvad gætter du?");
            svar = sc.nextInt();
            svar1 = sc.nextInt();
        }
        System.out.println("----------------------------------");

    }

    public void check() {
        boolean correct = GameLogicObject.checkGuess(DieObject.getDie1(), DieObject.getDie2(), svar, svar1);
        if (correct == true) {
            //hvis svarene passer får klienten de credits der blev satset samt en rewardbonus som afhænger af terningsummen.
            playerObject.rewards(DieObject, bet);
            playerObject.setCredit(bet);
            playerObject.totalrewards();

        } else {
            //her mister klienten sine satsede credits.
            playerObject.setCredit1(bet);
            playerObject.resetbet1();
        }

        tur++;
        //HISTORYBOARD for spillet, hvor nogle af objekterne bliver hentet fra de andre klasser.
        System.out.println("Credits: " + playerObject.getCredit());
        System.out.println("----------------------------------");
        System.out.println("Terning 1 viste: " + DieObject.getDie1());
        System.out.println("Terning 2 viste: " + DieObject.getDie2());
        System.out.println("Du gættede: " + svar + " og " + svar1);
        System.out.println("Du satsede: " + bet);
        System.out.println("Din reward: " + playerObject.historyreward());
        System.out.println("----------------------------------");
        //Hvis klienten har flere credits, så bliver klienten tilbudt at spille igen.
        if (playerObject.getCredit() > 0) {

            sc = new Scanner(System.in);
            //whileloop med yes/no om man vil spille videre.
            while (true) {
                System.out.println("\nVil du fortsætte med at spille? Y/N?");
                String igen = sc.nextLine();
                //hvis klienten taster "y" breaker loopet og spillet fortsætter.
                if (igen.equalsIgnoreCase("Y")) {
                    sandfalsk = true;
                    break;
                    //hvis klienten taster "n" breaker loopet som kører spillet og spillet slutter.
                } else if (igen.equalsIgnoreCase("N")) {
                    sandfalsk = false;
                    fortsæt = false;
                    break;
                }
            }
            System.out.println("----------------------------------");
        }
    }

    public void statistik() {
        //STATISTICS som viser antal ture, samlede antal rewards/gevinster og antal slut credit.
        System.out.println("----------------------------\nGAME OVER! - tak for spillet.\n----------------------------");
        System.out.println("STATISTICS:");
        gameTurnObject.totalture(tur);
        playerObject.visrewards();
        System.out.println("Final Account total: " + playerObject.getCredit());
    }

}
