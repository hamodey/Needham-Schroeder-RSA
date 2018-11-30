package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class rsaGUI extends  NumberFormatException {
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
    private JLabel error;


    public rsaGUI() {
        RSA rsa = new RSA();//inistalise RSA method
        Persons alice = new Persons(rsa);//inistialise person with an rsa
        Persons bob = new Persons(rsa);//inistialise person with an rsa
        Hacker charlie = new Hacker();//inistialise person that is a hacker to read whats going on

        //all charliers brute force calculations before betton pressed
        charlie.isPrime();
        charlie.brute(rsa.n);
        charlie.findD(rsa.e);

        //Action listener for when enter is pressed
        textInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = textInput.getText();//gets the input of the text field that user inputs
                if(input.equals("")){//check if empty to throw an error
                    error.setText("Please enter a message");
                }

                encryptText.setText(input);//the message is shown on screen with a .setText method

                //the string is then converted into bytes
                byte[] encrypted = rsa.encrypt(encryptText.getText().getBytes());//please reference RSA.java for comments here
//                System.out.println(rsa.byteToString(encrypted) + "EN");

                try {//try and catch to see if the input is empty, if so then an error is thrown in console
                    cipherText.setText(new String(encrypted));//the bytes are then converted back into strings so it can be displayed in gui
                }catch (NullPointerException cipherText){
                    System.out.println("Enter message");
                }
//                System.out.println(new String(encrypted));
//                System.out.println("heyo " + cipherText.getText());
//                byte[] testing = cipherText.getText().getBytes();
//                System.out.println(rsa.byteToString(testing) + "DE");

                //the string is then converted into bytes
//                byte[] decrypted = rsa.dencrypt(cipherText.getText().());//please reference RSA.java for comments here
                try {//try and catch to see if the values are empty or null to throw an error
                    byte[] decrypted = rsa.dencrypt(encrypted);//please reference RSA.java for comments here
                    decryptText.setText(new String(decrypted));//the bytes are then converted back into strings so it can be displayed in gui
//                System.out.println(new String(decrypted)
                    if(input.equals(new String(decrypted))){
                        decryptText.setBackground(Color.GREEN);
                    }else{
                        decryptText.setBackground(Color.RED);
                    }
                } catch (NullPointerException decrpytText ){
                    System.out.println("Enter message");
                }
            }
        });
        //Action listener for when submit button is pressed
        submitButton.addActionListener(new ActionListener() {


            //this section shows the same as above but this is Action Listener is put on the button instead of the 'enter' key


            @Override
            public void actionPerformed(ActionEvent e) {
                String input = textInput.getText();
                if(input.equals("")){
                    error.setText("Please enter a message");
                }
                encryptText.setText(input);//the message is shown on screen with a .setText method

                //the string is then converted into bytes
                byte[] encrypted = rsa.encrypt(encryptText.getText().getBytes());//please reference RSA.java for comments here
//                System.out.println(rsa.byteToString(encrypted) + "EN");


                try {
                    cipherText.setText(new String(encrypted));//the bytes are then converted back into strings so it can be displayed in gui
                }catch (NullPointerException cipherText){
                    System.out.println("Enter message");
                }//                charlieDec.setText(new String(encrypted));//the bytes are then converted back into strings so it can be displayed in gui


//                System.out.println(new String(encrypted));


//                System.out.println("heyo " + cipherText.getText());

                byte[] testing = cipherText.getText().getBytes();
//                System.out.println(rsa.byteToString(testing) + "DE");

                //the string is then converted into bytes
//                byte[] decrypted = rsa.dencrypt(cipherText.getText().());//please reference RSA.java for comments here
                try {
                    byte[] decrypted = rsa.dencrypt(encrypted);//please reference RSA.java for comments here
                    decryptText.setText(new String(decrypted));//the bytes are then converted back into strings so it can be displayed in gui
//                System.out.println(new String(decrypted)
                    if(input.equals(new String(decrypted))){
                        decryptText.setBackground(Color.GREEN);
                    }else{
                        decryptText.setBackground(Color.RED);
                    }
                } catch (NullPointerException decrpytText ){
                    System.out.println("Enter message");
                }

            }
        });


        //for the code below please go to the hacker file to understand in full detail


        brutePAndQButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //charlie brute force to check p and q, go to hacker file to understand more
                charlie.brute(rsa.n);
                brutePnQ.setText("p = " + charlie.arr[0] + " q = "+ charlie.arr[1]);
            }
        });
        workOutDAndButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //charlie works out d by getting the values of p and q and phi
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

                //the code ran checks to see if p and q are accidently swapped although this cannot happen
                //this still does check just in case
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
        //gui coded to show on screen
        JFrame frame = new JFrame("RSA by Ahmed Al-waili, Mahmudul Fakrul, Taha Zacholi");
        frame.setContentPane(new rsaGUI().backPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
