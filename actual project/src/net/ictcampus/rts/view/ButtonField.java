package net.ictcampus.rts.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ButtonField extends JButton{
    
    private ImageIcon icon;
    private Image img1;
    private Image img2;
    private Image img3;
    
    private int posX;
    private int posY;
    
    private Boolean ausgew = false;

    public ButtonField(int x, int y) {
        this.setPreferredSize(new Dimension(50,50));
        this.posX=x;
        this.posY=y;
        
        try {
            img1 = ImageIO.read(getClass().getResource("leer.jpg"));
            img2 = ImageIO.read(getClass().getResource("ressource.jpg"));
            img3 = ImageIO.read(getClass().getResource("stadt.jpg"));
        } catch ( IOException e) {
            e.printStackTrace();
        }
        
        icon = new ImageIcon(img1);
        this.setIcon(icon);
        
        JLabel content = new JLabel();
        content.setSize(50, 50);
        content.setForeground(new Color(255, 255, 255));
        int gradNorth = 9 - y;
        content.setText(Integer.toString(gradNorth)+"°N "+Integer.toString(x)+"°O ");
        this.add(content);
    }
    
    public void setFeldIcon(int feldTyp) {
        if(feldTyp==0) {
            icon = new ImageIcon(img1);
                       
        }
        else if(feldTyp==1) {
            icon = new ImageIcon(img2);
        }
        else if(feldTyp==2) {
            icon = new ImageIcon(img3);
        }
        this.setIcon(icon);
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public Boolean getAusgew() {
        return ausgew;
    }

    public void setAusgew(Boolean ausgew) {
        this.ausgew = ausgew;
    }
    

}
