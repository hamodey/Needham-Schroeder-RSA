package gui;

import java.io.IOException;
import java.math.*;
import java.util.Random;
import java.awt.FlowLayout;
import javax.swing.JFrame;

public class RSA extends JFrame{

    public BigInteger p, q, n, phi, e, d, c;
    int bit_len = 16;

    public RSA() {

        Random r = new Random();//new random is made
//        System.out.println("r " + r.toString());

        //random prime from 0 to bit_len 65000 is calculated
        // for p, q, e
        p = BigInteger.probablePrime(bit_len, r);
//        System.out.println("p " + p.toString());

        q = BigInteger.probablePrime(bit_len, r);
//        System.out.println("q " + q.toString());

        //n is calculated by p * q
        n = p.multiply(q);
//        System.out.println("n " + n.toString());


        BigInteger pMinus1 = p.subtract(BigInteger.ONE);
        BigInteger qMinus1 = q.subtract(BigInteger.ONE);

        //phi is (p-1)(q-1)
        phi = pMinus1.multiply(qMinus1);

        //e is random prime
        e = BigInteger.probablePrime(bit_len / 2, r);
//        System.out.println("e " + e.toString());

        //(e, n) make up the public Key

        //but then e is checked to see if it has a gcd with phi
        while (divisor() > 0 && e.compareTo(phi) < 0){//checks to see if e is diviable or not
            e.add(BigInteger.ONE);
        }
        d = e.modInverse(phi);//private key
//        System.out.println("d " + d.toString());
    }

    public int divisor() {
        return phi.gcd(e).compareTo(BigInteger.ONE);
    }//method checks if gcd is true

    public static String byteToString(byte[] message) {
        String output ="";
        for(byte i: message) {
            output += Byte.toString(i);
        }
        return output;
    }

    public byte[] encrypt(byte[] message) {//get cipher by encrypting message
        try {
            BigInteger temp = new BigInteger(message);
            c = temp.modPow(e, n);// encrypted using c = m^e mod n
            byte[] b = c.toByteArray();
            return b;
        }catch (NumberFormatException e){
            System.out.println("Please enter message");
            return null;
        }
    }


    public byte[] dencrypt(byte[] message) {//get message by decrypting the cipher
        try {
            BigInteger temp = new BigInteger(message);
            BigInteger m = temp.modPow(d, n);//decrypt  using the n = c^d mod n
            byte[] b = m.toByteArray();
            return b;
        }catch (NumberFormatException e){
            System.out.println("Please enter message");
            return null;
        }
    }

    public BigInteger getE(){
        return e;
    }

    public BigInteger getN(){
        return n;
    }

}