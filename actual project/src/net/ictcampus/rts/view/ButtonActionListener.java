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
            checkCityOrField();
            break;
        }
    }
    
    private void sendCommand() {
        //TODO, transmit Befehl
        String command = frame.getCommand();
        System.out.println("Command nach Aufruf des PlayButtons: "+command);
        //frame.setCommand(command);
        frame.setReady(true);
//        frame.notYourTurn();
        System.out.println(frame.getReady());
    }
    
    //PARTIALLY FINISHED TOCHECK XYMISSING
    private void createHumans() {
        String anzahlString = frame.getTxtCreateP().getText();
        int id = frame.getPlayerId();
        try {
            int anzahl = Integer.parseInt(anzahlString);
            if (anzahl <= 0) {
                throw new InvalidAttributeValueException("CreateHumans invalid textfield input, number below 1");
            }
            //FEHLER UNTER DEM HIER
            int vorrat = spiel.getSpielFeld().getStadt(frame.getAusgewX(), frame.getAusgewY()).getVorratGUI("Geld");
            //FEHLER èBER DEM HIER
            if (vorrat < (anzahl*spiel.getMenschPreis())){
                throw new InvalidAttributeValueException("CreateHumans not possible, money not sufficient");
            }
            String befehl = frame.getPlayerId()+",1,"+frame.getAusgewX()+","+frame.getAusgewY()+","+frame.getAusgewX()+","+frame.getAusgewY()+","+anzahlString;
            frame.setCommand(befehl);
        } catch (Exception e){
            System.out.println("CreateHumans invalid textfield input - no action was triggered." +"\n" + e.toString());
        }
    }

    //PARTIALLY FINISHED TOCHECK XYMISSING
    private void buildCity() {
        Feld field;
        try {
            field = spiel.getSpielFeld().getFelder()[frame.getAusgewX()][frame.getAusgewY()];
            String coordinates = frame.getTxtCreateC().getText();
            try {
                String[] coordinateArr = coordinates.split("-");
                int x = Integer.parseInt(coordinateArr[1]);
                int y = 9-Integer.parseInt(coordinateArr[0]);
                coordinateArr[0] = Integer.toString(y);
                Feld finalField = spiel.getSpielFeld().getFelder()[x][y];
                
                if (x < 0 || x > frame.getxSize() || y < 0 || y > frame.getySize()) {
                    throw new InvalidAttributeValueException("Coordinates not inside the gamearea");
                }
                
                if (finalField.getStadt() != null) {
                    throw new InvalidAttributeValueException("Coordinates are pointing on a city");
                }
                
                int vorrat = spiel.getSpielFeld().getStadt(frame.getAusgewX(), frame.getAusgewY()).getVorratGUI("Geld");
                if (vorrat < (spiel.getStadtPreis())){
                    throw new InvalidAttributeValueException("CreateCity not possible, money not sufficient");
                }
                String befehl = frame.getPlayerId()+",3,"+frame.getAusgewX()+","+frame.getAusgewY()+","+coordinateArr[1]+","+coordinateArr[0]+",0";
                frame.setCommand(befehl);
            } catch (Exception e){
                System.out.println("CreateCity invalid textfield input or money not sufficient - no action was triggered." +"\n" + e.toString());
            }
        } catch (Exception e) {
            System.out.println("Couldn't fetch the game-data correctly, it might be a server issue -- no action was triggered." +"\n" + e.toString());
        }
    }
    
    private void checkCityOrField() {
        if (spiel.getSpielFeld().getFelder()[frame.getAusgewX()][frame.getAusgewY()].getStadt() != null) {
            transportCitizens();
        } else {
            transportHumans();
        }
    }
    
    //
    private void transportHumans() {
        Feld field;
        try {
            field = spiel.getSpielFeld().getFelder()[frame.getAusgewX()][frame.getAusgewY()];
            String coordinates = frame.getTxtTransportPkoo().getText();
            try {
                String[] coordinateArr = coordinates.split("-");
                int x = Integer.parseInt(coordinateArr[1]);
                int y = 9-Integer.parseInt(coordinateArr[0]);
                coordinateArr[0] = Integer.toString(y);
                System.out.println("PlayerID: "+frame.getPlayerId()+"  X: "+frame.getAusgewX()+" Y: "+frame.getAusgewY());
                System.out.println("Wie viu Lütli druffe: "+field.getEinheiten().size()+"");
                if (field.countPlayerEinheiten(frame.getPlayerId()) <= 0) {
                    throw new InvalidAttributeValueException("No people on the selected field.");
                }
                int verschiebungX = 0;
                if (x-frame.getAusgewX() > spiel.getProtMensch().getAusdauer()) {
                    throw new InvalidAttributeValueException("Stamina not sufficient for the travel in X-direction");
                }
                if(x-frame.getAusgewX() < 0) {
                    verschiebungX = frame.getAusgewX()-x;
                } else {
                    verschiebungX = x-frame.getAusgewX();
                }
                int verschiebungY;
                if (y-frame.getAusgewY() > spiel.getProtMensch().getAusdauer()) {
                    throw new InvalidAttributeValueException("Stamina not sufficient for the travel in Y-direction");
                }
                if(y-frame.getAusgewY() < 0) {
                    verschiebungY = frame.getAusgewY()-y;
                } else {
                    verschiebungY = y-frame.getAusgewY();
                }
                if(verschiebungY + verschiebungX > spiel.getProtMensch().getAusdauer()) {
                    throw new InvalidAttributeValueException("Stamina not sufficient for the travel in combined direction");
                }
                String befehl = frame.getPlayerId()+",4,"+frame.getAusgewX()+","+frame.getAusgewY()+","+coordinateArr[1]+","+coordinateArr[0]+","+0;
                frame.setCommand(befehl);
            } catch (Exception e) {
                System.out.println("No people on the selected field - no action was triggered." +"\n" + e.toString());
            }
        } catch (Exception e) {
            System.out.println("Couldn't fetch the game-data correctly, it might be a server issue -- no action was triggered." +"\n" + e.toString());
        }
    }
    
    //
    private void transportCitizens() {
        try {
        Stadt stadt = spiel.getSpielFeld().getFelder()[frame.getAusgewX()][frame.getAusgewY()].getStadt();
        String coordinates = frame.getTxtTransportPkoo().getText();
        String numberOfCitizens = frame.getTxtTransportPanz().getText();
            try {
                String[] coordinateArr = coordinates.split("-");
                int x = Integer.parseInt(coordinateArr[1]);
                int y = 9-Integer.parseInt(coordinateArr[0]);
                coordinateArr[0] = Integer.toString(y);
                if (stadt.getVolk().size() <= 0) {
                    throw new InvalidAttributeValueException("No people on the selected City.");
                }
                int verschiebungX = 0;
                if (x-frame.getAusgewX() > spiel.getProtMensch().getAusdauer()) {
                    throw new InvalidAttributeValueException("Stamina not sufficient for the travel in X-direction");
                }
                if(x-frame.getAusgewX() < 0) {
                    verschiebungX = frame.getAusgewX()-x;
                } else {
                    verschiebungX = x-frame.getAusgewX();
                }
                int verschiebungY;
                if (y-frame.getAusgewY() > spiel.getProtMensch().getAusdauer()) {
                    throw new InvalidAttributeValueException("Stamina not sufficient for the travel in Y-direction");
                }
                if(y-frame.getAusgewY() < 0) {
                    verschiebungY = frame.getAusgewY()-y;
                } else {
                    verschiebungY = y-frame.getAusgewY();
                }
                if(verschiebungY + verschiebungX > spiel.getProtMensch().getAusdauer()) {
                    throw new InvalidAttributeValueException("Stamina not sufficient for the travel in combined direction");
                }
                if (Integer.parseInt(numberOfCitizens) <= 0) {
                    throw new InvalidAttributeValueException("No people selected to send.");
                }
                if (stadt.getVolk().size() < Integer.parseInt(numberOfCitizens)) {
                    throw new InvalidAttributeValueException("Selected to send more people than existing in the city.");
                }
                String befehl = frame.getPlayerId()+",2,"+frame.getAusgewX()+","+frame.getAusgewY()+","+coordinateArr[1]+","+coordinateArr[0]+","+numberOfCitizens;
                frame.setCommand(befehl);
            } catch (Exception e) {
                System.out.println("No or not enaugh people on the selected field - no action was triggered." +"\n" + e.toString());
            }
        } catch (Exception e) {
            System.out.println("Couldn't fetch the game-data correctly, it might be a server issue -- no action was triggered." +"\n" + e.toString());
        }
    }
}
