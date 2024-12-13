    package gui;

    import domain.DiaryDTO;

    import javax.imageio.ImageIO;
    import javax.swing.*;
    import java.awt.*;
    import java.awt.event.MouseAdapter;
    import java.awt.event.MouseEvent;
    import java.awt.image.BufferedImage;
    import java.io.IOException;
    import java.net.URL;
    import java.util.ArrayList;
    import java.util.Calendar;
    import java.util.List;

    public class Diary extends JFrame {
        DiaryMain diaryMain;
        JLabel header;
        JTextArea area;
        JPanel icon;

        String[] path = {"weather/cloud.png", "weather/rain.png", "weather/snow.png", "weather/sun.png"};
        List<JLabel> icons;
        JButton save;
        JButton load;
        JLabel selected;
        NumCell numcell;
        int index = 3;
        int day;

        public Diary(DiaryMain diaryMain) {
            this.diaryMain = diaryMain;
            header = new JLabel();
            area = new JTextArea();
            icon = new JPanel();
            icons = new ArrayList<JLabel>();
            save = new JButton("save");
            load = new JButton("load");

            header.setFont(new Font("", Font.BOLD, 25));
            area.setPreferredSize(new Dimension(380, 150));
            icon.setPreferredSize(new Dimension(380, 100));

            area.setText("");
            setLayout(new FlowLayout());

            add(header);
            add(area);
            add(icon);
            createIcon();
            add(save);
            add(load);

            setBounds(400, 300, 400, 400);

            setLocationRelativeTo(null);

            save.addActionListener((e) -> {
                try {
                    save();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                area.setText("");
            });
            load.addActionListener((e) -> {
                try {
                    load();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            });
        }



        public void createIcon() {

            for (int i = 0; i < path.length; i++) {
                URL url = ClassLoader.getSystemResource(path[i]);
                try {
                    BufferedImage img = ImageIO.read(url);
                    Image image = img;
                    image = image.getScaledInstance(60, 60, Image.SCALE_SMOOTH);

                    JLabel label = new JLabel(new ImageIcon(image));
                    icons.add(label);
                    icon.add(label);

                    label.addMouseListener((new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            selected = (JLabel) e.getSource();
                            index = icons.indexOf(selected);
                        }
                    }));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public void save() throws IOException {
            DiaryDTO day = new DiaryDTO();
            URL url=ClassLoader.getSystemResource(path[index]);

            day.setYear(diaryMain.calendar.get(Calendar.YEAR));
            day.setMonth(diaryMain.calendar.get(Calendar.MONTH)+1);
            day.setDay(this.day);
            day.setDiary(area.getText());
            day.setIcon(index);

            int result = diaryMain.diaryDAO.insert(day);

            if (result > 0) {
                JOptionPane.showMessageDialog(this, "saved!");
                BufferedImage buffImg = ImageIO.read(url);
                Image image=buffImg;
                image=image.getScaledInstance(60, 60, Image.SCALE_SMOOTH);

                JLabel laIcon=new JLabel(new ImageIcon(image));
                for (Component comp : numcell.iconBox.getComponents()) {
                    if (comp instanceof JLabel) {
                        numcell.iconBox.remove(comp);
                    }
                }

                numcell.iconBox.add(laIcon);
                numcell.updateUI();


                this.setVisible(false);
            }

        }
        public void load() throws IOException {
            DiaryDTO day = new DiaryDTO();
            day.setYear(diaryMain.calendar.get(Calendar.YEAR));
            day.setMonth(diaryMain.calendar.get(Calendar.MONTH)+1);
            day.setDay(this.day);
            day = diaryMain.diaryDAO.select(day);
            area.setText(day.getDiary());

            URL url=ClassLoader.getSystemResource(path[day.getIcon()]);
            BufferedImage buffImg = ImageIO.read(url);
            Image image=buffImg;
            image=image.getScaledInstance(60, 60, Image.SCALE_SMOOTH);

            JLabel laIcon=new JLabel(new ImageIcon(image));
            for (Component comp : numcell.iconBox.getComponents()) {
                if (comp instanceof JLabel) {
                    numcell.iconBox.remove(comp);
                }
            }

            numcell.iconBox.add(laIcon);
            numcell.updateUI();

        }

        public void showPop(NumCell numcell, String pHeader) {
            this.setVisible(true);
            header.setText(pHeader);

            this.numcell = numcell;

        }


    }
