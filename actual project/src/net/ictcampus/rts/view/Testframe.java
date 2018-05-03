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

import net.ictcampus.rts.model.Spiel;

import java.awt.font.*;

public class Testframe extends JFrame {

    private JPanel map;
    private JPanel content;
    private JPanel title;
    private ButtonField[][] field;
    private Spiel spiel;

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

    private JLabel lblKoordinaten;
    private JLabel lblInfo;
    private JLabel lblFeldart;
    private JLabel lblBesitzer;
    private JLabel lblAnzahlMenschen;
    private JLabel lblGesammelteR;
    private JLabel lblVerfuegbareR;

    private int xSize = 20;
    private int ySize = 10;

    private int ausgewX;
    private int ausgewY;
    
    private String command;
    
    private int playerId;

    private JavalisationActionLinstener jAl;

    /**
     * What was that for?
     */
    // private static final long serialVersionUID = -6325432051352243062L;

    public Testframe() {

        super("Javalisation");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.initContent();
        this.setVisible(true);
    }

    public void initContent() {

        this.setSize(1800, 900);
        this.setLayout(new BorderLayout());
        map = new JPanel();
        content = new JPanel();
        title = new JPanel();

        jAl = new JavalisationActionLinstener(this);

        addFieldButtons();
        aktiverButton();
        setContent();
        setTitle();

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
        JPanel contentBorderCenter = new JPanel();
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
        contentBorderLeft.setLayout(new GridLayout(5, 2));

        contentBorderLeft.setBackground(new Color(92, 255, 150));

        menschenAnzahl = new JLabel("800000");
        ressourcenAnzahl = new JLabel("2022541100");
        stadtAnzahl = new JLabel("6");
        sammelAnzahl = new JLabel("600023");
        menschenInStadtAnzahl = new JLabel("400000");

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
        JButton btnBuildC = new JButton("Stadt bauen");
        JButton btnTransportP = new JButton("Menschen transportieren");
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
        p.add(new JLabel("Verfügbare Ressourcen:"));
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
    
    public void refreshDataRight() {
        
        if (spiel.getSpielFeld().getFelder()[ausgewX][ausgewY].getStadt() != null) {
            lblFeldart.setText("Stadt");
            String besitzer = (spiel.getSpielFeld().getFelder()[ausgewX][ausgewY].getStadt().getBesitzer().getName());
            lblBesitzer.setText(besitzer);
            lblVerfuegbareR.setText("-");
            lblAnzahlMenschen.setText(spiel.getSpielFeld().getFelder()[ausgewX][ausgewY].getStadt().getVolk().size());
            
        }
        else if(spiel.getSpielFeld().getFelder()[ausgewX][ausgewY].getLoot().size() > 0) {
            lblFeldart.setText("Ressource");
            lblBesitzer.setText("-");
            lblVerfuegbareR.setText(spiel.getSpielFeld().getFelder()[ausgewX][ausgewY].getAnzahlRessourcen());
            lblAnzahlMenschen.setText(spiel.getSpielFeld().getFelder()[ausgewX][ausgewY].getEinheit().size());
        }
        else {
            lblFeldart.setText("Wüste");
            lblBesitzer.setText("-");
            lblVerfuegbareR.setText("-");
            lblAnzahlMenschen.setText(spiel.getSpielFeld().getFelder()[ausgewX][ausgewY].getEinheit().size());
        }
        
        
        
        
    }
    
    public void refreshDataLeft() {
        int anzMenschen=0;
        int anzRessourcen=0;
        int anzStaedte=0;
        int anzMenschenStadt=0;
        int anzMenschenSammeln;
        for (int i = 0; i < ySize; i++) {
          for (int j = 0; j < xSize; j++) {
              anzMenschenSammeln+=spiel.getSpielFeld().getFelder()[i][j].countPlayerEinheiten(playerId);
              
              if (spiel.getSpielFeld().getFelder()[i][j].getStadt().getPlayer().equals(spiel.getPlayerByID(playerId))) {
                  anzStaedte++;
                  anzRessourcen+=spiel.getSpielFeld().getFelder()[i][j].getStadt().getVorrrat("Geld");
                  anzMenschenStadt+=spiel.getSpielFeld().getFelder()[i][j].getStadt().getVolk().size();
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
}
