package net.ictcampus.rts.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.naming.directory.InvalidAttributeValueException;
import javax.swing.JButton;

import net.ictcampus.rts.model.Feld;
import net.ictcampus.rts.model.Spiel;
import net.ictcampus.rts.model.Stadt;

public class ButtonActionListener implements ActionListener{
    
    private Testframe frame;
    private Spiel spiel;
    
    public ButtonActionListener(Testframe frame, Spiel spiel) {
        this.frame = frame;
        this.spiel = spiel;
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
        case "Menschen transportieren":
            transportHumans();
            break;
        case "Menschen aus Stadt senden":
            transportCitizens();
            break;
        }
    }
    
    private void sendCommand() {
        //TODO, transmit Befehl
        String command = frame.getCommand();
        System.out.println(command);
    }
    
    //PARTIALLY FINISHED TOCHECK XYMISSING
    private void createHumans() {
        String anzahlString = frame.getTxtCreateP().getText();
        int id = frame.getPlayerId();
        try {
            int anzahl = Integer.parseInt(anzahlString);
            if (anzahl <= 0) {
                throw new InvalidAttributeValueException("CreateHumans invalid Textfield input, number below 1");
            }
            int vorrat = spiel.getSpielfeld().getStadt(frame.getAusgewX(), frame.getAusgewY()).getVorratGUI("Geld");
            if (vorrat < (anzal*spiel.getMenschPreis()){
                throw new InvalidAttributeValueException("CreateHumans not possible, money not sufficient");
            }
            String befehl = frame.getPlayerId()+","+frame.getAusgewX()+","+frame.getAusgewY()+"2,1,"+ anzahlString + ",1";
            frame.setCommand(befehl);
        } catch (Exception e){
            System.out.println("CreateHumans invalid Textfield input - no Action was triggered.");
        }
    }

    //PARTIALLY FINISHED TOCHECK XYMISSING
    private void buildCity() {
        Feld field = spiel.getSpielfeld().getFelder()[frame.getAusgewX()][frame.getAusgewY()];//TODO
        String coordinates = frame.getTxtCreateC().getText();
        try {
            String[] coordinateArr = coordinates.split("-");
            int x = Integer.parseInt(coordinateArr[0]);
            int y = Integer.parseInt(coordinateArr[1]);
            Feld finalField = spiel.getSpielfeld().getFelder()[x][y];
            
            if (x < 0 || x > frame.getxSize() || y < 0 || y > frame.getySize()) {
                throw new InvalidAttributeValueException("Coordinates not inside the Gamearea");
            }
            
            if (finalField.getStadt() != null) {
                throw new InvalidAttributeValueException("Coordinates are pointing on a City");
            }
            
            int vorrat = spiel.getSpielfeld().getStadt(frame.getAusgewX(), frame.getAusgewY()).getVorratGUI("Geld");
            if (vorrat < (spiel.getStadtPreis()){
                throw new InvalidAttributeValueException("CreateCity not possible, money not sufficient");
            }
            String befehl = frame.getPlayerId()+","+frame.getAusgewX()+","+frame.getAusgewY()+",2,3,"+coordinateArr[0]+","+coordinateArr[1];
            frame.setCommand(befehl);
        } catch (Exception e){
            System.out.println("CreateCity invalid Textfield input or Money not sufficient - no Action was triggered.");
        }
    }
    
    //
    private void transportHumans() {
        Feld field = spiel.getSpielfeld().getFelder()[frame.getAusgewX()][frame.getAusgewY()];
        String coordinates = frame.getTxtTransportPkoo().getText();
        try {
            String[] coordinateArr = coordinates.split("-");
            int x = Integer.parseInt(coordinateArr[0]);
            int y = Integer.parseInt(coordinateArr[1]);
            if (field.countPlayerEinheiten(frame.getPlayerId()).size() <= 0) {
                throw new InvalidAttributeValueException("No people on the selected field.");
            }
            String befehl = frame.getPlayerId()+","+frame.getAusgewX()+","+frame.getAusgewY()+",1,1,"+coordinateArr[0]+","+coordinateArr[1];
            frame.setCommand(befehl);
        } catch (Exception e) {
            System.out.println("No People on the selected field - no Action was triggered.");
        }
    }
    
    //
    private void transportCitizens() {
        Stadt stadt = spiel.getSpielfeld().getFelder()[frame.getAusgewX()][frame.getAusgewY()].getStadt();
        String coordinates = frame.getTxtTransportPkoo().getText();
        String numberOfCitizens = frame.getTxtTransportPanz();
        try {
            String[] coordinateArr = coordinates.split("-");
            int x = Integer.parseInt(coordinateArr[0]);
            int y = Integer.parseInt(coordinateArr[1]);
            if (stadt.getVolk().size() <= 0) {
                throw new InvalidAttributeValueException("No people on the selected City.");
            }
            if (Integer.parseInt(numberOfCitizens) <= 0) {
                throw new InvalidAttributeValueException("No people selected to send.");
            }
            if (stadt.getVolk().size() <= Integer.parseInt(numberOfCitizens)) {
                throw new InvalidAttributeValueException("Selected to send more people than existing in the city.");
            }
            String befehl = frame.getPlayerId()+","+frame.getAusgewX()+","+frame.getAusgewY()+",2,3,"+coordinateArr[0]+","+coordinateArr[1]+","+numberOfCitizens;
            frame.setCommand(befehl);
        } catch (Exception e) {
            System.out.println("No People or not enaugh Prople on the selected field - no Action was triggered.");
        }
    }
}
