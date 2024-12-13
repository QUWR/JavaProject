package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {

    private String id = "19010952";
    private String password = "1111";

    public Login() {
        JFrame login = new JFrame("Login");
        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBackground(Color.GRAY);
        panel.setPreferredSize(new Dimension(190, 100));

        JLabel idLabel = new JLabel("Id               ");
        JLabel passwordLabel = new JLabel("Password ");

        JTextArea idArea = new JTextArea();
        JTextArea passwordArea = new JTextArea();
        idArea.setPreferredSize(new Dimension(100, 20));
        passwordArea.setPreferredSize(new Dimension(100, 20));

        JButton button = new JButton("login");

        panel.add(idLabel);
        panel.add(idArea);

        panel.add(passwordLabel);
        panel.add(passwordArea);

        panel.add(button);
        login.getContentPane().add(panel);
        login.pack();
        login.setLocationRelativeTo(null);
        login.setVisible(true);

        button.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (idArea.getText().equals(id)) {
                            if (passwordArea.getText().equals(password)) {
                                JOptionPane.showMessageDialog(null, "Login Success!");
                                login.setVisible(false);
                                new DiaryMain();
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "Wrong password!", "Error", JOptionPane.ERROR_MESSAGE);

                            }
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Wrong id!", "Error", JOptionPane.ERROR_MESSAGE);

                        }
                    }
                }
        );

    }
}
