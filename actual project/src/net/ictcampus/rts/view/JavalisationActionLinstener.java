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
        int xClick = ((ButtonField)e.getSource()).getPosX();
        int yClick = ((ButtonField)e.getSource()).getPosY();
        
        frame.setAusgewX(xClick);
        frame.setAusgewY(9-yClick);
        frame.refreshKoordinaten();        }
    
    
    
}
