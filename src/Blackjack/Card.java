package Blackjack;

/**
 * Created by kld on 2014/11/15.
 */
public class Card {
    private int point;
    private char suit;
    public void setPoint(int point){
        this.point = point;
    }

    public void setSuit(char suit){
        this.suit = suit;
    }

    public Card getCard(){
        return this;
    }

    public int getPoint(){
        return point;
    }

    public char getSuit(){
        return suit;
    }
}
