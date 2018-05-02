package net.ictcampus.rts.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sun.prism.paint.Color;

public class Testframe extends JFrame {

    private JPanel map;
    private JPanel content;
    private JPanel title;
    private ButtonField[][] field;
    
	/**
	 * What was that for?
	 */
	//private static final long serialVersionUID = -6325432051352243062L;

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
        
        //
        JButton contentB = new JButton();
        content.add(contentB);
        JButton titleB = new JButton();
        title.add(titleB);
        //
        
        addButtons(20,10);
        
        this.add(map, "North");
        this.add(title, "Center");
        this.add(content, "South");
    }
    
    public void addButtons(int xSize, int ySize) {
        
        map.setLayout(new GridLayout(ySize,xSize));
        
        field = new ButtonField[xSize][ySize];
        
        for (int i = 0; i < ySize; i++) {
            for (int j = 0; j < xSize; j++) {
                field[j][i] = new ButtonField(j,i);
                map.add(field[j][i]);
                //ActionListener added here TODO
            }
        }
    }
}
