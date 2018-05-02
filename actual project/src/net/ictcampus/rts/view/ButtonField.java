package net.ictcampus.rts.view;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ButtonField extends JButton{

    public ButtonField(int x, int y) {
        this.setPreferredSize(new Dimension(50,50));
        JLabel content = new JLabel();
        content.setSize(50, 50);
        int gradNorth = 9 - y;
        content.setText(Integer.toString(gradNorth)+"°N "+Integer.toString(x)+"°O ");
        this.add(content);
    }
}
