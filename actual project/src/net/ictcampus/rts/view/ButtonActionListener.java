package net.ictcampus.rts.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ButtonActionListener implements ActionListener{
    
    private Testframe frame;
    
    public ButtonActionListener(Testframe frame) {
        this.frame = frame;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        JButton sender = (JButton)e.getSource();
        
        methodSelector(sender);
    }
    
    private void methodSelector(JButton sender) {
        
        switch (sender.getText()) {
            
        case "Play":
            sendCommand();
            break;
        case "Menschen erstellen":
            createHumans();
            break;
        case "Stadt bauen":
            buildCity();
            break;
        case "Ressourcen sammeln":
            collectRessources();
            break;
        case "Ressourcen lagern":
            storeRessources();
            break;
        case "Menschen transportieren":
            transportHumans();
            break;
        }
    }
    
    private void sendCommand() {
        
    }
    
    private void createHumans() {
        
    }

    private void buildCity() {
        
    }
    
    private void collectRessources() {
        
    }
    
    private void storeRessources() {
        
    }
    
    private void transportHumans() {
        
    }
}
