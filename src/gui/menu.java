package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;

public class menu {
    private JButton RSAAlgorithmButton;
    private JButton serverButton;
    private JPanel backPanel;
    private JTextPane paraPanel;

    public menu() {
        RSAAlgorithmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                (new rsaGUI()).show();
            }
        });
        serverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                (new serverGUI()).show();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("RSA by Ahmed Al-waili, Mahmudul Fakrul, Taha Zacholi");
        frame.setContentPane(new menu().backPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


    }
}
