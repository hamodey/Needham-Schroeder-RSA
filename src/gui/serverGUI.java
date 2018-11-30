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
    private JTextPane bobN;
    private JTextPane aliceN;
    private JTextPane cipherN;
    private JLabel recName;
    private JTextPane bobN2;
    private JTextPane cipherN2;
    private JTextPane aliceN2;

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


    //this method below allows any message sent to any person that is inistialised
    //hence why this is a method and can be called in the main function
    public void messaging(String message, Persons p1 , Persons p2, Server ser){


        //get the partner public key by asking the server
        BigInteger[] arr = p1.getPartnerKey(ser, p2);

        //server then signs the public key and sends it back to the user p1
        BigInteger[] bigArr = ser.sign(p1, p2);//server signs for alice

        //person 1 sends to person2 encrypted nounce with signed public key
        byte[] en = p1.sendNounce(p2);
        ///alice front
        aliceN.setText(p1.viewNounce());

        bobN.setText(new String(en));

        //decryption of nounce
        byte[] a = p2.decryptNounce(p1);
//        System.out.println("DE" + new String(a));
        cipherN.setText(new String(a));


        //person 2 sends back a nounce wiht person 1
        byte[] en2 = p2.sendNounce(p1);
        ///alice front
        aliceN2.setText(p2.viewNounce());

        bobN2.setText(new String(en2));

        //decrypts it
        byte[] a2 = p1.decryptNounce(p2);
//        System.out.println("DE" + new String(a));
        cipherN2.setText(new String(a2));

        BigInteger tempE = p1.verify(ser, bigArr[0]);

        //decrtpyes the nounces and also the messages
        denm = p1.serverEncrypt(ser, message.getBytes(), tempE, arr[1]);//encrypt

        //message is shown
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
