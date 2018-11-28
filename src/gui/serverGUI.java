package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;

public class serverGUI {
    private JTextPane cipher;
    private JPanel panel1;
    private JTextPane bobMessage;
    private JTextPane aliceMessage;
    private JTextField aliceInput;
    private JButton sendToBobButton;
    private JLabel publicKeyLabel;
    private JTextPane bobRec;
    private JTextPane aliceRec;
    private JTextPane cipherRec;
    private JLabel recName;

    RSA aliceRSA = new RSA();
    RSA bobRSA = new RSA();
    Persons alice = new Persons(aliceRSA);
    Persons bob = new Persons(bobRSA);
    Server ser = new Server(alice, bob);
    byte[] denm;
    byte[] decipher;


    public serverGUI() {
        sendToBobButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                messaging(aliceInput.getText(), alice, bob, ser);
                aliceMessage.setText(aliceInput.getText());

                cipher.setText(new String(denm));

                bobMessage.setText(new String(decipher));

                publicKeyLabel.setText("(" + ser.getPublicKey(bob)[0] + ", " + ser.getPublicKey(bob)[1] + ")");
                recName.setText("Bob");


            }
        });
    }

    public void messaging(String message, Persons p1 , Persons p2, Server ser){

        BigInteger[] arr = p1.getPartnerKey(ser, p2);

        BigInteger[] bigArr = ser.sign(p1, p2);

        BigInteger tempE = p1.verify(ser, bigArr[0]);

        denm = p1.serverEncrypt(ser, message.getBytes(), tempE, arr[1]);//encrypt

        decipher = p2.rsa.dencrypt(denm);

    }

    public void show() {
        JFrame frame = new JFrame("RSA by Ahmed Al-waili, Mahmudul Fakrul, Taha Zacholi");
        frame.setContentPane(new serverGUI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
