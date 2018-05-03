package net.ictcampus.rts.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

public class ClientTest {

	
	// ---------------------------variable_declaration---------------------------//

    // -------------------------------Constructor--------------------------------//

    // -----------------------------------Main-----------------------------------//
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClientController CC = new ClientController();
	   // servermet();
	   // clientmet();
	    ClientSocketFactory.closeClientSocket(ClientSocketFactory.getClientSocket());

	}
	
	// ---------------------------------Methods---------------------------------//
	public static void clientmet() throws IOException {
        BufferedReader input =
                new BufferedReader(new InputStreamReader((ClientSocketFactory.getClientSocket()).getInputStream()));
            String answer = input.readLine();
            JOptionPane.showMessageDialog(null, answer);

    }

}
