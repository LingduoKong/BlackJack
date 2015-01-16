package Blackjack;

/**
 * Created by kld on 2014/11/15.
 */
public class CardPool {
    private int[][][] cardpool = new int[4][13][6];
    private char[] suits = {'c','d','h','s'};
    private int count = 0;
    private Card newCard = new Card();

    public Card getCard(){
        count++;
        int x = (int)(Math.random()*4);
        int y = (int)(Math.random()*13);
        int z = (int)(Math.random()*6);
        newCard.setPoint(y+1);
        newCard.setSuit(suits[x]);
        if(cardpool[x][y][z]!=1) {
            cardpool[x][y][z]=1;
            return newCard;
        }
        else if(count<300)getCard();
        return null;
    }

    public Card getCard(int x, int y, int z){
        count++;
        newCard.setPoint(y+1);
        newCard.setSuit(suits[x]);
        if(cardpool[x][y][z]!=1) {
            cardpool[x][y][z]=1;
            return newCard;
        }
        else if(count<300)getCard();
        return null;
    }

    public void restart(){
        int[][][] newcardpool = new int[4][13][6];
        cardpool = newcardpool;
    }

}
