package gui;

import javax.swing.*;
import java.awt.*;

public class Cell extends JPanel {

    JLabel title;

    public Cell(Color color, int width, int height) {
        setPreferredSize(new Dimension(width, height));
        setBackground(color);

        title = new JLabel();

        this.add(title);
    }

    public void setTitle(String title) {
        this.title.setText(title);
        this.title.setForeground(Color.WHITE);
    }
}
