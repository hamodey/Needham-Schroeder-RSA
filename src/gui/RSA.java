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

        Random r = new Random();
//        System.out.println("r " + r.toString());

        p = BigInteger.probablePrime(bit_len, r);
//        System.out.println("p " + p.toString());

        q = BigInteger.probablePrime(bit_len, r);
//        System.out.println("q " + q.toString());


        n = p.multiply(q);
//        System.out.println("n " + n.toString());


        BigInteger pMinus1 = p.subtract(BigInteger.ONE);
        BigInteger qMinus1 = q.subtract(BigInteger.ONE);

        phi = pMinus1.multiply(qMinus1);

        e = BigInteger.probablePrime(bit_len / 2, r);
//        System.out.println("e " + e.toString());

        //(e, n) make up the public Key

        while (divisor() > 0 && e.compareTo(phi) < 0){
            e.add(BigInteger.ONE);
        }
        d = e.modInverse(phi);//private key
//        System.out.println("d " + d.toString());
    }

    public int divisor() {
        return phi.gcd(e).compareTo(BigInteger.ONE);
    }

    public static String byteToString(byte[] message) {
        String output ="";
        for(byte i: message) {
            output += Byte.toString(i);
        }
        return output;
    }

    public byte[] encrypt(byte[] message) {//get cipher
        BigInteger temp = new BigInteger(message);
        c = temp.modPow(e, n);//public key
        byte[] b = c.toByteArray();
        return b;
    }


    public byte[] dencrypt(byte[] message) {
        BigInteger temp = new BigInteger(message);
        BigInteger m = temp.modPow(d, n);//decrypt
        byte[] b = m.toByteArray();
        return b;
    }

    public BigInteger getE(){
        return e;
    }

    public BigInteger getN(){
        return n;
    }

}