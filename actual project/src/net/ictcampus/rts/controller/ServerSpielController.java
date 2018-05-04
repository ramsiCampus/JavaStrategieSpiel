package net.ictcampus.rts.controller;

import java.io.IOException;

import net.ictcampus.rts.model.SpielLogik;

public class ServerSpielController {

    // ---------------------------variable_declaration---------------------------//
    private int anzSpieler = 2;
    private static final int COMMANDCOUNT = 5;
    SpielLogik spielLogik;
    ServerController srvCtrl;
    int[][] commands;

    // -------------------------------Constructor--------------------------------//
    public ServerSpielController(int anzSpieler) {
        this.spielLogik = new SpielLogik();
        this.srvCtrl = new ServerController();
        this.anzSpieler = anzSpieler;
        commands = new int[anzSpieler][COMMANDCOUNT];
    }

    // ---------------------------------Methods---------------------------------//
    public void getCommands() {
        String allCommands = "";
        try {
            allCommands = this.srvCtrl.receiveCommands();
            System.out.println(allCommands);
            String[] commandsByPlayer = allCommands.split("#");
            for (int i = 0; i < anzSpieler; i++) {
                String[] playerCommands = commandsByPlayer[i].split(":");
                for (int j = 0; j < COMMANDCOUNT; j++) {
                    commands[i][j] = Integer.parseInt(playerCommands[j]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

    }

    public void printCommands() {
        for (int i = 0; i < anzSpieler; i++) {
            for (int j = 0; j < COMMANDCOUNT; j++) {
                System.out.print(commands[i][j] + " ");
            }
            System.out.println("");
        }
    }

}
