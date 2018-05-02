package net.ictcampus.rts.view;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ButtonField extends JButton{

    public ButtonField(int x, int y) {
        JLabel content = new JLabel();
        content.setSize(50, 50);
        content.setText(Integer.toString(x)+" "+Integer.toString(y));
        this.add(content);
    }
}
