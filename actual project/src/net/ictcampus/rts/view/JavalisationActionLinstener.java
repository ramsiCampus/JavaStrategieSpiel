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
        frame.disableContentCenter(false);
        int xClick = ((ButtonField) e.getSource()).getPosX();
        int yClick = ((ButtonField) e.getSource()).getPosY();

        frame.setAusgewX(xClick);
        frame.setAusgewY(yClick);
        frame.refreshKoordinaten();
        frame.refreshDataRight();
//        frame.getField()[xClick][yClick].setFeldIcon((int)(Math.random()*3));
        setCorrectEnabledContent(xClick, yClick);
        
        

    }
    
    public void setCorrectEnabledContent(int xClick, int yClick) {
        if(frame.getField()[xClick][yClick].getFeldTyp()!=2) {
            frame.getBtnCreateP().setEnabled(false);
            frame.getTxtCreateP().setEnabled(false);
            frame.getBtnBuildC().setEnabled(false);
            frame.getTxtCreateC().setEnabled(false);
            frame.getTxtTransportPanz().setEnabled(false);
            frame.getBtnTransportP().setEnabled(true);
            frame.getTxtTransportPkoo().setEnabled(true);
        }
        else {
            if (frame.getPlayerId() == frame.getSpiel().getSpielFeld().getFelder()[xClick][yClick].getStadt().getBesitzer().getID()) {
                frame.getBtnCreateP().setEnabled(true);
                frame.getTxtCreateP().setEnabled(true);
                frame.getBtnBuildC().setEnabled(true);
                frame.getTxtCreateC().setEnabled(true);
                frame.getTxtTransportPanz().setEnabled(true);
                frame.getBtnTransportP().setEnabled(true);
                frame.getTxtTransportPkoo().setEnabled(true);
            } else {
                frame.getBtnCreateP().setEnabled(false);
                frame.getTxtCreateP().setEnabled(false);
                frame.getBtnBuildC().setEnabled(false);
                frame.getTxtCreateC().setEnabled(false);
                frame.getTxtTransportPanz().setEnabled(false);
                frame.getBtnTransportP().setEnabled(false);
                frame.getTxtTransportPkoo().setEnabled(false);
            }
        }
    }

}
