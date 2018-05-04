package net.ictcampus.rts.controller;

import java.net.Socket;

public class Handler extends Thread {

    // ---------------------------variable_declaration---------------------------//
    private Socket socket;

    // -------------------------------Constructor--------------------------------//
    public Handler(Socket socket) {
        this.socket = socket;
    }
    // -----------------------------------Main-----------------------------------//

    // ---------------------------------Methods---------------------------------//

    // ------------------------------Getter_Setter------------------------------//
}
