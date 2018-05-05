package net.ictcampus.rts.controller;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import net.ictcampus.rts.model.Spiel;
import net.ictcampus.rts.model.SpielLogik;

public class ServerSpielController {

	private int anzSpieler = 2;
	private static final int COMMANDCOUNT = 7;
	SpielLogik spielLogik;
	ServerController srvCtrl;
	int[][] commands;
	
	public ServerSpielController(int anzSpieler){
		this.anzSpieler = anzSpieler;
		this.srvCtrl = new ServerController(anzSpieler);
		this.commands = new int [anzSpieler][COMMANDCOUNT];
		initGame();
	}
	
	public void getCommands(){
		String allCommands = "";
		try{
			allCommands = this.srvCtrl.receiveCommands(); //hier werden die Commands von allen Spielern empfangen
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
		this.spielLogik = new SpielLogik(anzSpieler);
//		while(!this.areClientsReady()){
//			System.out.println("Clients are not yet ready to receive the game");
//			
//			try {TimeUnit.MILLISECONDS.sleep(3000);}//wait 3 seconds before trying again
//			catch (Exception e) {e.printStackTrace(); return;}
//		}
		hereComesTheGame();
		System.out.println("Game initialised and sent");
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
	
	public void sendGameToAll(Spiel game) {
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
    
	/**
	 * Sendet eine 0 an alle Clients und nimmt ihre Antort auf, nur falls alle Clients ready sind (Aktion = 0) wird true 
	 * zurückgegeben. Sonst false
	 * @return true wenn alle Clients ready sind, sonst false
	 */
	public boolean areClientsReady(){
		sendMessageToAll("0");
		getCommands();
		for(int i=0; i<this.anzSpieler; i++){
			if(this.commands[i][1]!=0) { return false; }
		}
		return true;
	}
	
	/**
	 * Sendet eine 1 and alle Clients und nimmt dann die von den Spielern gesendeten Commands entgegen.
	 * Diese Funktion darf erst nach areClientsReady() ausgeführt werden, da sonst falsche Commands entgegengenommen werden.
	 */
	public void getPlayCommands(){
		sendMessageToAll("1");
		getCommands();
	}
	
	/**
	 * Sendet eine 2 an alle Clients und sendet anschliessend das Spiel an alle Clients.
	 * Wird nach ausführen der commands auf dem Spiel ausgeführt.
	 */
	public void hereComesTheGame(){
		sendMessageToAll("2");
		sendGameToAll(spielLogik.getSpiel());
	}
	
	public void play(){
		//anstatt while true, könnte man in der Spiellogik prüfen ob das Spiel vorbei ist und
		//dann anhand von jenem Zustand die Schlaufe beenden.
		while(true){
			if(areClientsReady()){
				getPlayCommands();
				for(int i=0; i<anzSpieler; i++){
					spielLogik.commandAusfuehren(commands[i]);
				}
				hereComesTheGame();
			}
			
			try {
	    		TimeUnit.MILLISECONDS.sleep(1000);
	    	} catch (Exception e) {
	    	    System.out.println("Error while sleeping between areClientsReady on the server.");
	    	}
			
		}
	}
	
	
	//-----------------------------Getter & Setter------------------------------------
	
	public int getAnzSpieler() {
	    return anzSpieler;
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
