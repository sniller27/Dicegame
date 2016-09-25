/*  
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terningspil;

/**
 *
 * @author Soren
 */
public class Player {
//attributter deklareres og initieres

    private double credits = 100;
    private double bet1;
    private double rewards = 0;
    private double gevinst = 0;

//getters og setters
    public double getCredit() {

        return credits;
    }

    public void setCredit(double bet) {

        credits = credits + bet1;

    }

    public void setCredit1(double bet) {
        credits = credits - bet;

    }

    public void rewards(Die DieObject, double bet) {

        if (DieObject.getSum() == 2 || DieObject.getSum() == 3 || DieObject.getSum() == 11 || DieObject.getSum() == 12) {
            bet1 = bet * 1.5;

        } else if (DieObject.getSum() == 4 || DieObject.getSum() == 5 || DieObject.getSum() == 9 || DieObject.getSum() == 10) {
            bet1 = bet * 2;
        } else if (DieObject.getSum() == 6 || DieObject.getSum() == 7 || DieObject.getSum() == 8) {
            bet1 = bet * 3;
        }

    }

    public double getCredits() {
        return credits;
    }

    public void setCredits(double credits) {
        this.credits = credits;
    }

    public void totalrewards() {

        rewards = rewards + bet1;

    }

    public double historyreward() {

        return bet1;
    }

    public void resetbet1() {
        bet1 = 0;
    }

    public void visrewards() {

        System.out.println("Total rewards: " + rewards);

    }

}
