/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terningspil;

import java.util.Random;

/**
 *
 * @author Soren
 */
public class Die {

    private Random generator = new Random();

    //attributes
    private int die1, die2, sum;

    //constructor
    public Die() {

    }

    //Methods
    //metoden hvor terningerne rulles.
    public void roll() {

        die1 = generator.nextInt(6) + 1;
        die2 = generator.nextInt(6) + 1;

    }

    //metode for terning sum
    public int getSum() {

        sum = die1 + die2;

        return sum;
    }

    //getters for terningerne
    public int getDie1() {

        return die1;
    }

    public int getDie2() {

        return die2;
    }

}
