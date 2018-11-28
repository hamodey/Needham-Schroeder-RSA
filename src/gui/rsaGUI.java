package gui;

import oracle.jvm.hotspot.jfr.JFR;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class rsaGUI {
    private JPanel backPanel;
    private JTextField textInput;
    private JButton submitButton;
    private JLabel encryptLabel;
    private JLabel cipherLabel;
    private JLabel decryptLabel;
    private JScrollPane encryptPanel;
    private JTextArea decryptText;
    private JTextArea cipherText;
    private JTextArea encryptText;
    private JTextArea charlieDec;
    private JButton workOutDAndButton;
    private JButton brutePAndQButton;
    private JButton factoriseForNButton;
    private JLabel brutePnQ;
    private JLabel workOutD;
    private JLabel findN;
    private JButton checkIfCharlieIsButton;
    private JLabel pqCheck;
    private JLabel dphicorrect;
    private JLabel nCheck;


    public rsaGUI() {
        RSA rsa = new RSA();
        Persons alice = new Persons(rsa);
        Persons bob = new Persons(rsa);
        Hacker charlie = new Hacker();
        charlie.isPrime();
        charlie.brute(rsa.n);
        charlie.findD(rsa.e);

        //Action listener for when enter is pressed
        textInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = textInput.getText();
                encryptText.setText(input);//the message is shown on screen with a .setText method

                //the string is then converted into bytes
                byte[] encrypted = rsa.encrypt(encryptText.getText().getBytes());//please reference RSA.java for comments here
//                System.out.println(rsa.byteToString(encrypted) + "EN");


                cipherText.setText(new String(encrypted));//the bytes are then converted back into strings so it can be displayed in gui


//                System.out.println(new String(encrypted));


//                System.out.println("heyo " + cipherText.getText());

                byte[] testing = cipherText.getText().getBytes();
//                System.out.println(rsa.byteToString(testing) + "DE");



                //the string is then converted into bytes
//                byte[] decrypted = rsa.dencrypt(cipherText.getText().());//please reference RSA.java for comments here
                byte[] decrypted = rsa.dencrypt(encrypted);//please reference RSA.java for comments here
                decryptText.setText(new String(decrypted));//the bytes are then converted back into strings so it can be displayed in gui
//                System.out.println(new String(decrypted));

                if(input.equals(new String(decrypted))){
                    decryptText.setBackground(Color.GREEN);
                }else{
                    decryptText.setBackground(Color.RED);
                }
            }
        });
        //Action listener for when submit button is pressed
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = textInput.getText();
                encryptText.setText(input);//the message is shown on screen with a .setText method

                //the string is then converted into bytes
                byte[] encrypted = rsa.encrypt(encryptText.getText().getBytes());//please reference RSA.java for comments here
//                System.out.println(rsa.byteToString(encrypted) + "EN");


                cipherText.setText(new String(encrypted));//the bytes are then converted back into strings so it can be displayed in gui
//                charlieDec.setText(new String(encrypted));//the bytes are then converted back into strings so it can be displayed in gui


//                System.out.println(new String(encrypted));


//                System.out.println("heyo " + cipherText.getText());

                byte[] testing = cipherText.getText().getBytes();
//                System.out.println(rsa.byteToString(testing) + "DE");

                //the string is then converted into bytes
//                byte[] decrypted = rsa.dencrypt(cipherText.getText().());//please reference RSA.java for comments here
                byte[] decrypted = rsa.dencrypt(encrypted);//please reference RSA.java for comments here
                decryptText.setText(new String(decrypted));//the bytes are then converted back into strings so it can be displayed in gui
//                System.out.println(new String(decrypted));

                if(input.equals(new String(decrypted))){
                    decryptText.setBackground(Color.GREEN);
                }else{
                    decryptText.setBackground(Color.RED);
                }

            }
        });
        brutePAndQButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                charlie.brute(rsa.n);
                brutePnQ.setText("p = " + charlie.arr[0] + " q = "+ charlie.arr[1]);
            }
        });
        workOutDAndButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                workOutD.setText("d = " + String.valueOf(charlie.findD(rsa.e)) + " phi = " + charlie.findPHI());
            }
        });
        factoriseForNButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                findN.setText(String.valueOf(rsa.n));
            }
        });
        checkIfCharlieIsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                 JLabel pqCheck;
//                 JLabel dphicorrect;
//                 JLabel nCheck;
                if(rsa.p.equals(charlie.arr[0])
                        || rsa.p.equals(charlie.arr[1])
                        || rsa.q.equals(charlie.arr[0])
                        || rsa.q.equals(charlie.arr[1])){
                    pqCheck.setText("CORRECT!");
                    pqCheck.setBackground(Color.GREEN);
                }

                if(charlie.findD(rsa.e).equals(rsa.d) && charlie.findPHI().equals(rsa.phi)){
                    dphicorrect.setText("CORRECT!");
                    dphicorrect.setBackground(Color.GREEN);
                }

                nCheck.setText(charlie.arr[0] + " * " + charlie.arr[1] + " = " + String.valueOf(charlie.arr[0].multiply(charlie.arr[1])) );

            }
        });
    }

    public void show() {
        JFrame frame = new JFrame("RSA by Ahmed Al-waili, Mahmudul Fakrul, Taha Zacholi");
        frame.setContentPane(new rsaGUI().backPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
