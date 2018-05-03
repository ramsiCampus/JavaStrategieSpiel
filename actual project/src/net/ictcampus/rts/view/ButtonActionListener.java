package net.ictcampus.rts.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.naming.directory.InvalidAttributeValueException;
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
        String anzahlString = frame.getTxtCreateP().getText();
        try {
            int anzahl = Integer.parseInt(anzahlString);
            if (anzahl <= 0) {
                throw new InvalidAttributeValueException("CreateHumans invalid Textfield input, number below 1");
            }
        } catch (Exception e){
            System.out.println("CreateHumans invalid Textfield input - no Action was triggered.");
        }
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
