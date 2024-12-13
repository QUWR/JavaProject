package gui;

import dao.DiaryDAO;
import util.Database;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Calendar;


public class DiaryMain extends JFrame {
    JPanel pNorth;
    JPanel pWest;
    JPanel pEast;
    JPanel pCenter;

    JButton prev;
    JButton next;
    JLabel title;
    String[] day = {"Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Set"};

    Calendar calendar;
    int currentYear;
    int currentMonth;

    Database db = new Database();

    DiaryDAO diaryDAO = new DiaryDAO(db);

    Diary diary;




    NumCell[][] numCells = new NumCell[6][7];

    public DiaryMain(){
        pNorth = new JPanel();
        pWest = new JPanel();
        pEast = new JPanel();
        pCenter = new JPanel();

        prev = new JButton("prev");
        next = new JButton("next");
        title = new JLabel("");

        calendar = Calendar.getInstance();

        currentYear = calendar.get(Calendar.YEAR);
        currentMonth = calendar.get(Calendar.MONTH);



        title.setFont(new Font("", Font.BOLD, 45));
        pWest.setPreferredSize(new Dimension(20, 750));
        pEast.setPreferredSize(new Dimension(20, 750));
        pCenter.setPreferredSize(new Dimension(800, 750));


        Border border = new LineBorder(Color.GRAY, 1, true);

        pNorth.setBackground(Color.GRAY);
        pWest.setBackground(Color.GRAY);
        pEast.setBackground(Color.GRAY);
        pCenter.setBackground(Color.GRAY);


        pWest.setBorder(new TitledBorder(border));
        pEast.setBorder(new TitledBorder(border));
        pCenter.setBorder(new TitledBorder(border));

        pNorth.add(prev);
        pNorth.add(title);
        pNorth.add(next);

        pWest.setLayout(null);

        add(pNorth, BorderLayout.NORTH);
        add(pWest, BorderLayout.WEST);
        add(pCenter, BorderLayout.CENTER);
        add(pEast, BorderLayout.EAST);

        createCell();
        printTitle();
        printNum();

        setSize(850, 850);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        diary = new Diary(this);



        prev.addActionListener((e)->{
            prev();
        });

        next.addActionListener((e)->{
            next();
        });


    }

    public void createCell() {
        for(int i=0;i<day.length;i++) {
            DayCell cell = new DayCell(Color.DARK_GRAY, 100, 45);
            cell.setTitle(day[i]);
            pCenter.add(cell);
        }

        for(int a=0;a<6;a++) {
            for(int i=0;i<7;i++) {
                NumCell cell = new NumCell(this, Color.LIGHT_GRAY, 100, 100);
                cell.setTitle("0");
                numCells[a][i]=cell;
                pCenter.add(cell);
            }
        }
    }

    public void printTitle() {
        int year=calendar.get(Calendar.YEAR);
        int mm=calendar.get(Calendar.MONTH);

        title.setText(year+"-"+StringManager.getNumString(mm+1));
    }

    public void prev() {
        int mm=calendar.get(Calendar.MONTH);
        calendar.set(Calendar.MONTH, mm-1);
        printTitle();
        printNum();
    }

    public void next() {
        int mm=calendar.get(Calendar.MONTH);
        calendar.set(Calendar.MONTH, mm+1);
        printTitle();
        printNum();

    }

    public int getStartDayOfWeek() {
        Calendar c = Calendar.getInstance();

        int yy=calendar.get(Calendar.YEAR);
        int mm=calendar.get(Calendar.MONTH);

        c.set(yy, mm, 1);

        int day=c.get(Calendar.DAY_OF_WEEK);

        return day;
    }

    public int getLastDateOfMonth() {
        int yy=calendar.get(Calendar.YEAR);
        int mm=calendar.get(Calendar.MONTH);

        Calendar c = Calendar.getInstance();
        c.set(yy, mm+1, 0);
        int dd=c.get(Calendar.DATE);

        return dd;
    }


    public void printNum() {
        for(int a=0;a<numCells.length; a++) {
            for(int i=0;i<numCells[a].length;i++) {
                numCells[a][i].setTitle("");
                numCells[a][i].iconBox.removeAll();

            }
        }



        int startDay=getStartDayOfWeek();
        int lastDate=getLastDateOfMonth();


        int count=0;
        int num=0;

        for(int a=0;a<numCells.length;a++) {
            for(int i=0;i<numCells[a].length;i++) {
                count++;
                if(count>=startDay && num<lastDate ) {
                    num++;
                    numCells[a][i].setTitle(Integer.toString(num));
                }
            }
        }

    }



    public static void main(String[] args) {


        new Login();

    }


}
