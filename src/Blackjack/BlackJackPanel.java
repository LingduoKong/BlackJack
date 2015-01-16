package Blackjack;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by kld on 2014/10/31.
 * Write a graphical application that produces a restaurant bill.
 Provide buttons for ten popular dishes or drink items. (You
 decide on the items and their prices.) Provide text fields for
 entering less popular items and prices. In a text area, show the
 bill, including tax and a suggested tip.
 */
public class BlackJackPanel extends JFrame{

    private static final int FRAME_WIDTH = 1000;
    private static final int FRAME_HEIGHT = 540;

    private static int totelMoney;
    private static int current_bid;
    private static int current_money;

    private JPanel rootPanel;
    private ImagePanel playerCardPanel;
    private ImagePanel dealerCardPanel;

    private JTextField currentMoney;
    private JTextField currentBid;

    private JButton addChip_10;
    private JButton addChip_20;
    private JButton addChip_50;
    private JButton addChip_100;
    private JButton hit;
    private JButton stand;
    private JButton restart;
    private JButton newTurn;

    private CardPool Cards;
    private ArrayList<Card> dealerCards;
    private ArrayList<Card> playerCards;

    public BlackJackPanel(){

        totelMoney = 1000;
        CreateBidButton();
        CreateActionButton();
        createTextField();
        createImage();
        createPanel();
        current_bid = Integer.parseInt(currentBid.getText());
        current_money = Integer.parseInt(currentMoney.getText());
        setSize(FRAME_WIDTH,FRAME_HEIGHT);
    }

    private void CreateBidButton() {
        addChip_10 = new JButton("Add Chip 10");
        addChip_10.addActionListener(new ActionListener() {
                                         @Override
                                         public void actionPerformed(ActionEvent e) {
                                             current_bid = Integer.parseInt(currentBid.getText());
                                             current_money = Integer.parseInt(currentMoney.getText());
                                             if (current_money - 10 >= 0) {
                                                 current_money = current_money - 10;
                                                 current_bid = current_bid + 10;
                                                 currentBid.setText("" + current_bid);
                                                 currentMoney.setText("" + current_money);
                                             }
                                         }
                                     }
        );

        addChip_20 = new JButton("Add Chip 20");
        addChip_20.addActionListener(new ActionListener() {
                                         @Override
                                         public void actionPerformed(ActionEvent e) {
                                             current_bid = Integer.parseInt(currentBid.getText());
                                             current_money = Integer.parseInt(currentMoney.getText());
                                             if (current_money - 20 >= 0) {
                                                 current_money = current_money - 20;
                                                 current_bid = current_bid + 20;
                                                 currentBid.setText("" + current_bid);
                                                 currentMoney.setText("" + current_money);
                                             }
                                         }
                                     }
        );

        addChip_50 = new JButton("Add Chip 50");
        addChip_50.addActionListener(new ActionListener() {
                                         @Override
                                         public void actionPerformed(ActionEvent e) {
                                             current_bid = Integer.parseInt(currentBid.getText());
                                             current_money = Integer.parseInt(currentMoney.getText());
                                             if (current_money - 50 >= 0) {
                                                 current_money = current_money - 50;
                                                 current_bid = current_bid + 50;
                                                 currentBid.setText("" + current_bid);
                                                 currentMoney.setText("" + current_money);
                                             }
                                         }
                                     }
        );

        addChip_100 = new JButton("Add Chip 100");
        addChip_100.addActionListener(new ActionListener() {
                                          @Override
                                          public void actionPerformed(ActionEvent e) {
                                              current_bid = Integer.parseInt(currentBid.getText());
                                              current_money = Integer.parseInt(currentMoney.getText());
                                              if (current_money - 100 >= 0) {
                                                  current_money = current_money - 100;
                                                  current_bid = current_bid + 100;
                                                  currentBid.setText("" + current_bid);
                                                  currentMoney.setText("" + current_money);
                                              }
                                          }
                                      }
        );
    }


    private void CreateActionButton(){
        restart = new JButton("New Turn");
        restart.addActionListener(new ActionListener() {
                                          @Override
                                          public void actionPerformed(ActionEvent e) {
                                              dealerCardPanel.clear();
                                              playerCardPanel.clear();
                                              playerCards=new ArrayList<Card>();
                                              dealerCards=new ArrayList<Card>();

                                              Cards = new CardPool();
                                              dealerCards.add(Cards.getCard());
                                              Cards = new CardPool();
                                              dealerCards.add(Cards.getCard());
                                              showNewCard(dealerCards, dealerCardPanel);

                                              Cards = new CardPool();
                                              playerCards.add(Cards.getCard());
                                              Cards = new CardPool();
                                              playerCards.add(Cards.getCard());
                                              showNewCard(playerCards, playerCardPanel);
                                              bidControl(false);
                                          }
                                      }
        );

        hit = new JButton("Hit");
        hit.addActionListener(new ActionListener() {
                                         @Override
                                         public void actionPerformed(ActionEvent e) {
                                             Cards = new CardPool();
                                             playerCards.add(Cards.getCard());
                                             showNewCard(playerCards, playerCardPanel);
                                             int score = calculateScore(playerCards);
                                             if(score>21){
                                                 JOptionPane.showMessageDialog(new JFrame(),
                                                         "Dealer Wins!");
                                                 clear();
                                             }
                                         }
                                     }
        );

        stand = new JButton("Stand");
        stand.addActionListener(new ActionListener() {
                                          @Override
                                          public void actionPerformed(ActionEvent e) {
                                              dealerChoice();
                                          }
                                      }
        );

        newTurn = new JButton("Add Chips");
        newTurn.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        clear();
                                        bidControl(true);
                                    }
                                }
        );
    }

    private void showNewCard(ArrayList<Card> whoseCard, ImagePanel whosePanel){
        whosePanel.drawCard(whoseCard);
    }

    private void createTextField(){
        final int FIELD_WIDTH = 10;
        currentBid = new JTextField("0", FIELD_WIDTH);
        currentMoney = new JTextField(Integer.toString(totelMoney), FIELD_WIDTH);
        currentBid.setEnabled(false);
        currentMoney.setEnabled(false);
    }

    private void createImage(){
        BufferedImage image1 = null;
        try {
            image1 = ImageIO.read(new File("Image/cardback.jpg"));
        }catch (IOException e){
        }
        dealerCardPanel = new ImagePanel(image1,0,0,200,350);
        playerCardPanel = new ImagePanel(image1,0,0,200,350);
    }

    public void createPanel() {
        JPanel PlayerMessage = new JPanel();
        JLabel Money = new JLabel("Current Money");
        JLabel Bid = new JLabel("Current Bid");
        PlayerMessage.setLayout(new GridLayout(4, 2));
        PlayerMessage.add(addChip_10);
        PlayerMessage.add(Bid);
        PlayerMessage.add(addChip_20);
        PlayerMessage.add(currentBid);
        PlayerMessage.add(addChip_50);
        PlayerMessage.add(Money);
        PlayerMessage.add(addChip_100);
        PlayerMessage.add(currentMoney);
        PlayerMessage.setBorder(BorderFactory.createLineBorder(Color.black));

        JPanel picture = new JPanel();
        picture.setLayout(new GridLayout(1,2));
        picture.add(playerCardPanel);
        picture.add(dealerCardPanel);
        picture.setBorder(BorderFactory.createLineBorder(Color.black));

        JPanel showIdentity = new JPanel();
        showIdentity.setLayout(new GridLayout(1,2));
        showIdentity.add(new JLabel("Player"));
        showIdentity.add(new JLabel("Dealer"));
        showIdentity.setBorder(BorderFactory.createLineBorder(Color.black));


        JPanel play = new JPanel();
        play.setLayout(new GridLayout(4,1));
        play.add(restart);
        play.add(hit);
        play.add(stand);
        play.add(newTurn);
        play.setBorder(BorderFactory.createLineBorder(Color.black));


        rootPanel = new JPanel();
        rootPanel.setLayout(new BorderLayout());
        rootPanel.add(PlayerMessage, BorderLayout.SOUTH);
        rootPanel.add(picture,BorderLayout.CENTER);
        rootPanel.add(showIdentity,BorderLayout.NORTH);
        rootPanel.add(play,BorderLayout.EAST);
        rootPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(rootPanel);
    }

    private int calculateScore(ArrayList<Card> Cards){
        boolean flag = false;
        int points = 0;
        for (int i = 0; i < Cards.size(); i++) {
            if(Cards.get(i).getPoint()>10)
                points+=10;
            else if(Cards.get(i).getPoint()==1){
                flag=true;
                points+=1;
            }
            else points+=Cards.get(i).getPoint();
        }
        if(points+10<=21&&flag){
            points+=10;
        }
        return points;
    }

    private void clear(){
        dealerCardPanel.clear();
        playerCardPanel.clear();
        playerCards=new ArrayList<Card>();
        dealerCards=new ArrayList<Card>();
        currentBid.setText("0");
    }

    private void dealerChoice(){
        boolean flag = false;
        boolean isSoft = false;
        int points = 0;;
        for (int i = 0; i < dealerCards.size(); i++) {
            if(dealerCards.get(i).getPoint()>10)
                points+=10;
            else if(dealerCards.get(i).getPoint()==1){
                flag=true;
                points+=1;
            }
            else points+=dealerCards.get(i).getPoint();
        }
        if(points+10<=21&&flag){
            points+=10;
            isSoft=true;
        }
        while (points<=17&&isSoft||points<calculateScore(playerCards)){
            Cards = new CardPool();
            dealerCards.add(Cards.getCard());
            showNewCard(dealerCards, dealerCardPanel);
            points=calculateScore(dealerCards);
        }
        if(points>21) {
            JOptionPane.showMessageDialog(new JFrame(),
                    "Player Wins!");
            current_bid = Integer.parseInt(currentBid.getText());
            current_money=Integer.parseInt(currentMoney.getText())+current_bid*2;
            currentMoney.setText(""+current_money);
            clear();
        }
        else judge();
    }

    private void judge(){
        int pScore = calculateScore(playerCards);
        int dScore = calculateScore(dealerCards);
        if(dScore>=pScore){
            JOptionPane.showMessageDialog(new JFrame(),
                    "Dealer Wins!");
            clear();
        }

        else {
            JOptionPane.showMessageDialog(new JFrame(),
                    "Player Wins!");
            current_bid = Integer.parseInt(currentBid.getText());
            current_money=Integer.parseInt(currentMoney.getText())+current_bid*2;
            currentMoney.setText(""+current_money);
            clear();
        }
    }

    private Card getNewCard(){
        int x = (int)(Math.random()*4);
        int y = (int)(Math.random()*13);
        int z = (int)(Math.random()*6);
        return Cards.getCard(x,y,z);
    }

    private void bidControl(boolean flag){
        addChip_10.setEnabled(flag);
        addChip_20.setEnabled(flag);
        addChip_50.setEnabled(flag);
        addChip_100.setEnabled(flag);
    }
}
