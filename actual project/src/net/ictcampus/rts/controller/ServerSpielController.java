package net.ictcampus.rts.controller;

import java.io.IOException;

import net.ictcampus.rts.model.Spiel;
import net.ictcampus.rts.model.SpielLogik;

public class ServerSpielController {

	private int anzSpieler = 2;
	private static final int COMMANDCOUNT = 7;
	SpielLogik spielLogik;
	ServerController srvCtrl;
	int[][] commands;
	
	public ServerSpielController(int anzSpieler){
		this.spielLogik = new SpielLogik();
		this.srvCtrl = new ServerController(anzSpieler);
		this.anzSpieler = anzSpieler;
		commands = new int [anzSpieler][COMMANDCOUNT];
	}
	
	public void getCommands(){
		String allCommands = "";
		try{
			allCommands = this.srvCtrl.receiveCommands();
			String[] commandsByPlayer = allCommands.split("#");
			for(int i=0; i<anzSpieler; i++){
				String[] playerCommands = commandsByPlayer[i].split(",");
				for(int j=0; j<COMMANDCOUNT; j++){
					commands[i][j] = Integer.parseInt(playerCommands[j]);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
	}
	
	public void initGame() {
	    
	}
	
	public void printCommands(){
		for(int i=0; i<anzSpieler; i++){
			for(int j=0; j<COMMANDCOUNT; j++){
				System.out.print(commands[i][j]+" ");
			}
			System.out.println("");
		}
	}
	
	
	public void sendMessageToAll(String message){
	    try {
	        this.srvCtrl.sendMessageToAll(message);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public void sendGame(Spiel game) {
	    try {
	        this.srvCtrl.sendGameStateToAll(game);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public void sendObject(SmallSerial sm) {
        try {
            this.srvCtrl.sendNormalObject(sm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
	
	
	//-----------------------------Getter & Setter------------------------------------
	
	public int getAnzSpieler() {
	    return anzSpieler;
	}
	
	public void setAnzSpieler(int anzSpieler) {
	    this.anzSpieler = anzSpieler;
	}
	
	public SpielLogik getSpielLogik() {
	    return spielLogik;
	}
	
	public void setSpielLogik(SpielLogik spielLogik) {
	    this.spielLogik = spielLogik;
	}
	
	public ServerController getSrvCtrl() {
	    return srvCtrl;
	}
	
	public void setSrvCtrl(ServerController srvCtrl) {
	    this.srvCtrl = srvCtrl;
	}
	
	public void setCommands(int[][] commands) {
	    this.commands = commands;
	}
	
}
