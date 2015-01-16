package Blackjack;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by kld on 2014/11/15.
 */
public class ImagePanel extends JPanel{

    private BufferedImage image;
    private int x1,y1;
    private int x2,y2;
    private boolean flag = true;
    private ArrayList<Card> Cards = new ArrayList<Card>();

    public ImagePanel(BufferedImage image,int x1, int y1, int x2, int y2) {
        this.image = image;
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(flag) {
            g.drawImage(image, x1, y1, x2, y2, null); // see javadoc for more info on the parameters
            g.drawImage(image, x1+25, y1, x2, y2, null); // see javadoc for more info on the parameters
        }
        else
        {
            String address;
            for(int i = 0; i< Cards.size();i++){
                address ="Image/"+Cards.get(i).getPoint()
                        +Cards.get(i).getSuit()+".png";
                BufferedImage img = null;
                try {
                    img = ImageIO.read(new File(address));
                }catch (IOException e){
                    System.out.println("no file");
                }
                g.drawImage(img, x1+25*i, y1, x2, y2, null);
            }
        }
    }

    public void drawCard(ArrayList<Card> Cards){
        this.Cards = Cards;
        flag = false;
        repaint();
    }

    public void clear(){
        flag = true;
        repaint();
    }

}
