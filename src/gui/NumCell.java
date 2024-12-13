package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;

public class NumCell extends Cell{

    DiaryMain diary;
    JPanel iconBox;

    public NumCell(DiaryMain diaryMain, Color color, int width, int height) {
        super(color, width, height);

        this.diary = diaryMain;
        iconBox = new JPanel();
        iconBox.setBackground(Color.lightGray);

        add(iconBox);

        title.setFont(new Font("돋움", Font.BOLD, 20));

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int year = diaryMain.calendar.get(Calendar.YEAR);
                int month = diaryMain.calendar.get(Calendar.MONTH);
                int n = Integer.parseInt(title.getText());
                diaryMain.diary.day = n;
                diaryMain.diary.showPop(NumCell.this, year+"-"+StringManager.getNumString(month+1)+"-"+StringManager.getNumString(n));
            }
        });
    }
}
