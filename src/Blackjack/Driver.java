package Blackjack;

import javax.swing.*;

/**
 * Created by kld on 2014/10/31.
 */
public class Driver {

    public static void main(String[] args){
        JFrame f = new BlackJackPanel();
        f.setTitle("BlackJack");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}
