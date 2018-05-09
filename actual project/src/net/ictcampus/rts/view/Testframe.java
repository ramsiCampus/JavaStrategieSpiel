package net.ictcampus.rts.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import net.ictcampus.rts.model.Armee;
import net.ictcampus.rts.model.Mensch;
import net.ictcampus.rts.model.Spiel;
import net.ictcampus.rts.model.SpielLogik;

import java.awt.font.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Testframe extends JFrame {

    private JPanel map;
    private JPanel content;
    private JPanel title;
    private ButtonField[][] field;
    private Testdata testdata = new Testdata();
    private static volatile Spiel spiel;
    private SpielLogik spielLogik;

    private JLabel titleRight = new JLabel();

    private JButton playButton;
    private JTextField txtCreateP;
    private JTextField txtTransportPkoo;
    private JTextField txtTransportPanz;
    private JTextField txtCreateC;

    private JLabel menschenAnzahl;
    private JLabel ressourcenAnzahl;
    private JLabel stadtAnzahl;
    private JLabel sammelAnzahl;
    private JLabel menschenInStadtAnzahl;
    private JButton btnCreateP;
    private JButton btnBuildC;
    private JButton btnTransportP;
    private JPanel contentBorderCenter;

    private JLabel lblKoordinaten;
    private JLabel lblInfo;
    private JLabel lblFeldart;
    private JLabel lblBesitzer;
    private JLabel lblAnzahlMenschen;
    private JLabel lblGesammelteR;
    private JLabel lblVerfuegbareR;
    private JLabel lblTitelVerfuegbareR;

    private int xSize = 20;
    private int ySize = 10;

    private int ausgewX;
    private int ausgewY;
    
    private static volatile String command;
    private static volatile Boolean ready;
    
    private int playerId = 1;

    private JavalisationActionLinstener jAl;
    private ButtonActionListener bAl;

    /**
     * What was that for?
     */
    // private static final long serialVersionUID = -6325432051352243062L;
    
    //wieso gnau brucheder SpielLogik wenner e nu spielLogik.getSpiel() im Konstruktor mached?
    //Gruess Keya
    public Testframe(SpielLogik spielLogik) {

        super("Javalisation (Dini Mueter isch fett(not kidding!))");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.spielLogik = spielLogik;
        this.spiel = spielLogik.getSpiel();
        this.initContent();
        this.setVisible(true);
    }
    
    /**
     * Alternativer Konstruktor, da vom Client nur ein Spiel und die Spieler-ID entegegengenommen wird.
     * 
     * Gruess Keya
     * @param spiel
     */
    public Testframe(Spiel spiel, int playerID) {
        super("Javalisation (Dini Mueter isch fetter als fett (not kidding!)) Gruess Keya");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.playerId = playerID;
        this.spiel = spiel;
        this.initContent();
        this.setVisible(true);
        this.ready = false;
    }

    public void initContent() {

        this.setSize(1800, 900);
        this.setLayout(new BorderLayout());
        map = new JPanel();
        content = new JPanel();
        title = new JPanel();

        jAl = new JavalisationActionLinstener(this);
        bAl = new ButtonActionListener(this, spiel);
        addFieldButtons();
        aktiverButton();
        setContent();
        setTitle();
        setBackgroundImg();
        refreshDataRight();
        refreshDataLeft();
        setArmy();

        this.add(map, "North");
        this.add(title, "Center");
        this.add(content, "South");

    }

    public void addFieldButtons() {

        map.setLayout(new GridLayout(ySize, xSize));

        field = new ButtonField[xSize][ySize];

        for (int i = 0; i < ySize; i++) {
            for (int j = 0; j < xSize; j++) {
                field[j][i] = new ButtonField(j, i);
                field[j][i].setBackground(new Color(57, 211, 214));
                map.add(field[j][i]);
                field[j][i].addActionListener(jAl);
            }
        }
    }

    public void setContent() {
        content.setLayout(new GridLayout(1, 3));
        JPanel contentLeft = new JPanel();
        JPanel contentCenter = new JPanel();
        JPanel contentRight = new JPanel();
        contentBorderCenter = new JPanel();
        JPanel contentBorderRight = new JPanel();
        JPanel contentBorderLeft = new JPanel();

        content.setPreferredSize(new Dimension(300, 300));

        contentLeft.setLayout(new BoxLayout(contentLeft, BoxLayout.Y_AXIS));
        contentCenter.setLayout(new BoxLayout(contentCenter, BoxLayout.Y_AXIS));
        contentRight.setLayout(new BoxLayout(contentRight, BoxLayout.Y_AXIS));

        contentLeft.setBorder(BorderFactory.createLineBorder(getForeground()));
        contentCenter.setBorder(BorderFactory.createLineBorder(getForeground()));
        contentRight.setBorder(BorderFactory.createLineBorder(getForeground()));

        contentLeft.add(contentBorderLeft);
        contentRight.add(contentBorderRight);
        contentCenter.add(contentBorderCenter);

        contentBorderCenter.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contentBorderLeft.setBorder(BorderFactory.createEmptyBorder(80, 100, 80, 20));
        contentBorderRight.setBorder(BorderFactory.createEmptyBorder(60, 100, 60, 20));

        Font titel = new Font("Calibri", 1, 20);

        setContentLeft(contentBorderLeft);
        setContentCenter(contentBorderCenter, titel);
        setContentRight(contentBorderRight, titel);

        content.add(contentLeft);
        content.add(contentCenter);
        content.add(contentRight);
    }

    public void setTitle() {
        title.setLayout(new GridLayout(1, 4));
        JLabel titleLeft = new JLabel("Spielerinfo");
        JLabel titleCenter = new JLabel("Ausgewähltes Feld: ");
        playButton = new JButton("Play");
        playButton.addActionListener(bAl);

        Font font = new Font("Calibri", 1, 40);
        titleLeft.setFont(font);
        titleCenter.setFont(font);
        titleRight.setFont(font);

        title.add(titleLeft);
        title.add(titleCenter);
        title.add(titleRight);
        title.add(playButton);
    }

    private void setContentLeft(JPanel contentBorderLeft) {
        contentBorderLeft.setLayout(new GridLayout(6, 2));

        contentBorderLeft.setBackground(new Color(92, 255, 150));
        
        JLabel playername = new JLabel("Du Bist: "+playerId+"");
        menschenAnzahl = new JLabel("-");
        ressourcenAnzahl = new JLabel("-");
        stadtAnzahl = new JLabel("-");
        sammelAnzahl = new JLabel("-");
        menschenInStadtAnzahl = new JLabel("-");
        contentBorderLeft.add(playername);
        contentBorderLeft.add(new JLabel(""));
        contentBorderLeft.add(new JLabel("Menschen:"));
        contentBorderLeft.add(menschenAnzahl);
        contentBorderLeft.add(new JLabel("Ressourcen:"));
        contentBorderLeft.add(ressourcenAnzahl);
        contentBorderLeft.add(new JLabel("Anz. Städte:"));
        contentBorderLeft.add(stadtAnzahl);
        contentBorderLeft.add(new JLabel("M. am Sammeln:"));
        contentBorderLeft.add(sammelAnzahl);
        contentBorderLeft.add(new JLabel("M. in der Stadt:"));
        contentBorderLeft.add(menschenInStadtAnzahl);

    }

    private void setContentCenter(JPanel p, Font titel) {
        p.setLayout(new GridLayout(6, 3));

        p.setBackground(new Color(150, 218, 255));

        JLabel lblTitel = new JLabel("Aktionen");
        btnCreateP = new JButton("Menschen erstellen");
        btnBuildC = new JButton("Stadt bauen");
        btnTransportP = new JButton("Menschen transportieren");
        
        btnCreateP.addActionListener(bAl);
        btnBuildC.addActionListener(bAl);
        btnTransportP.addActionListener(bAl);     
        
        txtCreateP = new JTextField();
        txtTransportPkoo = new JTextField();
        txtTransportPanz = new JTextField();
        txtCreateC = new JTextField();

        lblTitel.setFont(titel);

        p.add(lblTitel);
        p.add(new JLabel(""));
        p.add(new JLabel(""));

        p.add(btnCreateP);
        p.add(txtCreateP);
        p.add(new JLabel("Anzahl"));

        p.add(btnBuildC);
        p.add(txtCreateC);
        p.add(new JLabel("Koordinaten"));

        p.add(btnTransportP);
        p.add(txtTransportPkoo);
        p.add(txtTransportPanz);

        p.add(new JLabel(""));
        p.add(new JLabel("Koordinaten"));
        p.add(new JLabel("Anzahl"));
        
        disableContentCenter(true);
        btnTransportP.setBackground(new Color(255,255,255));
        btnCreateP.setBackground(new Color(255,255,255));
        btnBuildC.setBackground(new Color(255,255,255));
        

    }
    
    public void disableContentCenter(boolean b) {
        this.getContentBorderCenter().setVisible(!b);
        btnTransportP.setBackground(new Color(255,255,255));
        if(ready != null) {
            if(ready) {
                this.getContentBorderCenter().setVisible(!ready);
                btnTransportP.setBackground(new Color(255,255,255));
            }
        }
        
        
        
    }

    private void setContentRight(JPanel p, Font titel) {
        p.setLayout(new GridLayout(7, 2));
        p.setBackground(new Color(247, 76, 76));
        lblInfo = new JLabel("Info");
        lblFeldart = new JLabel("-");
        lblBesitzer = new JLabel("-");
        lblKoordinaten = new JLabel("-");
        lblAnzahlMenschen = new JLabel("-");
        lblGesammelteR = new JLabel("-");
        lblVerfuegbareR = new JLabel("-");
        lblTitelVerfuegbareR = new JLabel("Verfügbare Ressourcen:");

        lblInfo.setFont(titel);

        p.add(lblInfo);
        p.add(new JLabel());
        p.add(new JLabel("Feldart"));
        p.add(lblFeldart);
        p.add(new JLabel("Besitzer:"));
        p.add(lblBesitzer);
        p.add(new JLabel("Koordinaten:"));
        p.add(lblKoordinaten);
        p.add(new JLabel("Menschen:"));
        p.add(lblAnzahlMenschen);
        p.add(new JLabel("Gesammelte Ressourcen:"));
        p.add(lblGesammelteR);
        p.add(lblTitelVerfuegbareR);
        p.add(lblVerfuegbareR);
    }

    private void aktiverButton() {
        for (int i = 0; i < ySize; i++) {
            for (int j = 0; j < xSize; j++) {
                if (field[j][i].getAusgew()) {
                    ausgewX = field[j][i].getPosX();
                    ausgewY = field[j][i].getPosY();
                }
            }
        }
    }

    public void refreshKoordinaten() {
        titleRight.setText(9 - ausgewY + "°N " + ausgewX + "°O");
        lblKoordinaten.setText(9 - ausgewY + "°N " + ausgewX + "°O");
    }

    public void setBackgroundImg() {

        for (int i = 0; i < ySize; i++) {
            for (int j = 0; j < xSize; j++) {
                if (spiel.getSpielFeld().getFelder()[j][i].getStadt() != null) {
                    field[j][i].setFeldIcon(2);
                }
                else if (spiel.getSpielFeld().getFelder()[j][i].getLoot().size() > 0) {
                    field[j][i].setFeldIcon(1);
                }
                else {
                    field[j][i].setFeldIcon(0);
                }
            }
        }

    }
    
    public void setArmy() {
        Boolean b;
        for (int i = 0; i < ySize; i++) {
            for (int j = 0; j < xSize; j++) {
                if(spiel.getSpielFeld().getFelder()[j][i].getEinheiten().size()>0) {
                    b=true;
                }
                else {
                    b=false;
                }
                field[j][i].setFontColor(b);
            }
        }
    }
    
    public void refreshDataRight() {
        
        if (spiel.getSpielFeld().getFelder()[ausgewX][ausgewY].getStadt() != null) {
            lblFeldart.setText("Stadt");
            String besitzer = (spiel.getSpielFeld().getFelder()[ausgewX][ausgewY].getStadt().getBesitzer().getName());
            lblBesitzer.setText(besitzer);
            lblVerfuegbareR.setText(spiel.getSpielFeld().getFelder()[ausgewX][ausgewY].getStadt().getVorratGUI("Geld")+"");
            lblAnzahlMenschen.setText(spiel.getSpielFeld().getFelder()[ausgewX][ausgewY].getStadt().getVolk().size()+"");
            lblTitelVerfuegbareR.setText("Anz. Ressourcen im Lager");
            
        }
        else if(spiel.getSpielFeld().getFelder()[ausgewX][ausgewY].getLoot().size() > 0) {
            lblFeldart.setText("Ressource");
            lblBesitzer.setText("-");
            lblVerfuegbareR.setText(spiel.getSpielFeld().getFelder()[ausgewX][ausgewY].getAnzahlRessource()+"");
            lblAnzahlMenschen.setText(spiel.getSpielFeld().getFelder()[ausgewX][ausgewY].countPlayerEinheiten(playerId)+"");
            lblTitelVerfuegbareR.setText("Verfügbare Ressourcen");
            int counter = 0;
            for (Armee a : spiel.getSpielFeld().getFelder()[ausgewX][ausgewY].getEinheiten()) {
                if(a.getBesitzer().getID() == playerId) {
                    for (Mensch m : a.getArmee()) {
                        if (m.checkTasche("Geld")>0) {
                            counter += m.checkTasche("Geld");
                        }
                    }
                }
            }
        }
        else {
            lblFeldart.setText("Wüste");
            lblBesitzer.setText("-");
            lblVerfuegbareR.setText("-");
            lblAnzahlMenschen.setText(spiel.getSpielFeld().getFelder()[ausgewX][ausgewY].countPlayerEinheiten(playerId)+"");
        }
    }
    
    public void refreshDataLeft() {
        int anzMenschen=0;
        int anzRessourcen=0;
        int anzStaedte=0;
        int anzMenschenStadt=0;
        int anzMenschenSammeln=0;
        for (int i = 0; i < ySize; i++) {
          for (int j = 0; j < xSize; j++) {
              anzMenschenSammeln+=spiel.getSpielFeld().getFelder()[j][i].countPlayerEinheiten(playerId);
              
              if(spiel.getSpielFeld().getFelder()[j][i].getStadt()!=null) {
                  if (spiel.getSpielFeld().getFelder()[j][i].getStadt().getBesitzer().equals(spiel.getPlayerByID(playerId))) {
                      anzStaedte++;
                      anzRessourcen+=spiel.getSpielFeld().getFelder()[j][i].getStadt().getVorratGUI("Geld");
                      anzMenschenStadt+=spiel.getSpielFeld().getFelder()[j][i].getStadt().getVolk().size();
                      }
                  }
            }
        }
        anzMenschen=anzMenschenSammeln+anzMenschenStadt;
        menschenAnzahl.setText(anzMenschen+"");
        ressourcenAnzahl.setText(anzRessourcen+"");
        stadtAnzahl.setText(anzStaedte+"");
        sammelAnzahl.setText(anzMenschenSammeln+"");
        menschenInStadtAnzahl.setText(anzMenschenStadt+"");
    }
    
    public void updateMapAndInfo(){
    	this.setBackgroundImg();
    	this.refreshDataLeft();
    	this.refreshDataRight();
    	this.setArmy();
    	btnTransportP.removeActionListener(bAl); 
    	playButton.removeActionListener(bAl);
    	btnCreateP.removeActionListener(bAl); 
    	btnBuildC.removeActionListener(bAl); 
    	bAl = new ButtonActionListener(this, spiel);
        btnCreateP.addActionListener(bAl);
        btnBuildC.addActionListener(bAl);
        playButton.addActionListener(bAl);
        btnTransportP.addActionListener(bAl); 
    	
    }
    
//    public void notYourTurn() {
//        while(ready) {
//            System.out.println("disable");
//            disableContentCenter(true);
//            try {
//                TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
//        disableContentCenter(false);
//    }
    
    //----------------------Getter & Setter-----------------------------

    public int getAusgewX() {
        return ausgewX;
    }

    public void setAusgewX(int ausgewX) {
        this.ausgewX = ausgewX;
    }

    public int getAusgewY() {
        return ausgewY;
    }

    public void setAusgewY(int ausgewY) {
        this.ausgewY = ausgewY;
    }

    public JTextField getTxtCreateP() {
        return txtCreateP;
    }

    public JTextField getTxtTransportPkoo() {
        return txtTransportPkoo;
    }

    public JTextField getTxtTransportPanz() {
        return txtTransportPanz;
    }

    public ButtonField[][] getField() {
        return field;
    }

    public void setField(ButtonField[][] field) {
        this.field = field;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String befehl) {
        this.command = befehl;
    }

    public int getxSize() {
        return xSize;
    }

    public void setxSize(int xSize) {
        this.xSize = xSize;
    }

    public int getySize() {
        return ySize;
    }

    public void setySize(int ySize) {
        this.ySize = ySize;
    }

    public Spiel getSpiel() {
        return spiel;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public JButton getBtnCreateP() {
        return btnCreateP;
    }

    public void setBtnCreateP(JButton btnCreateP) {
        this.btnCreateP = btnCreateP;
    }

    public JTextField getTxtCreateC() {
        return txtCreateC;
    }

    public void setTxtCreateC(JTextField txtCreateC) {
        this.txtCreateC = txtCreateC;
    }

    public void setTxtCreateP(JTextField txtCreateP) {
        this.txtCreateP = txtCreateP;
    }

    public void setTxtTransportPkoo(JTextField txtTransportPkoo) {
        this.txtTransportPkoo = txtTransportPkoo;
    }

    public void setTxtTransportPanz(JTextField txtTransportPanz) {
        this.txtTransportPanz = txtTransportPanz;
    }

    public JButton getBtnBuildC() {
        return btnBuildC;
    }

    public void setBtnBuildC(JButton btnBuildC) {
        this.btnBuildC = btnBuildC;
    }

    public JButton getBtnTransportP() {
        return btnTransportP;
    }

    public void setBtnTransportP(JButton btnTransportP) {
        this.btnTransportP = btnTransportP;
    }

    public JPanel getContentBorderCenter() {
        return contentBorderCenter;
    }

    public void setContentBorderCenter(JPanel contentBorderCenter) {
        this.contentBorderCenter = contentBorderCenter;
    }

    public Boolean getReady() {
        return ready;
    }
    
    public void setReady(boolean ready){
    	Testframe.ready = ready;
    	disableContentCenter(ready);
    }

    public JLabel getLblAnzahlMenschen() {
        return lblAnzahlMenschen;
    }

    public void setLblAnzahlMenschen(JLabel lblAnzahlMenschen) {
        this.lblAnzahlMenschen = lblAnzahlMenschen;
    }

    public void setSpiel(Spiel spiel) {
        this.spiel = spiel;
        setReady(false);
        this.updateMapAndInfo();
    }
}
