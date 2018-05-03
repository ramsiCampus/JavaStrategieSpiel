package net.ictcampus.rts.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JavalisationActionLinstener implements ActionListener {

    private Testframe frame;

    public JavalisationActionLinstener(Testframe frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int xClick = ((ButtonField) e.getSource()).getPosX();
        int yClick = ((ButtonField) e.getSource()).getPosY();

        frame.setAusgewX(xClick);
        frame.setAusgewY(yClick);
        frame.refreshKoordinaten();
        frame.getField()[xClick][yClick].setFeldIcon(2);
        
        if(frame.getField()[xClick][yClick].getFeldTyp()!=1) {
            frame.getBtnCreateP().setVisible(false);
        }
        else {
            frame.getBtnCreateP().setVisible(true);
        }
        

    }

}
