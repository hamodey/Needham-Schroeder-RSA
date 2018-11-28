package gui;

import java.math.BigInteger;
import java.util.ArrayList;

public class Hacker {

    ArrayList<BigInteger> series = new ArrayList<BigInteger>();
    BigInteger[] arr = new BigInteger[2];

    Hacker(){
    }

    public void isPrime(){
        BigInteger n = new BigInteger("65536");
        for(int i = 0; i < 65536; i++){
//            System.out.println(n.toString());
            if(n.isProbablePrime(100)){
                series.add(n);
            }
            n = n.subtract(BigInteger.ONE);
        }
    }

    public BigInteger[] brute(BigInteger n){
        arr[0] = BigInteger.ZERO;
        arr[1] = BigInteger.ZERO;
        for(int i = 0; i < series.size(); i++){
            BigInteger p = series.get(i);

            for(int j = 1; j < series.size(); j++){
                BigInteger q = series.get(j);

                BigInteger guess = p.multiply(q);
                if(guess.equals(n)){
                    arr[0] = p;
//                    System.out.println(arr[0]);
                    arr[1] = q;
//                    System.out.println(arr[1]);
                    return arr;
                }
            }
        }
        return null;
    }

    public BigInteger findPHI(){
        BigInteger p = arr[0];
        BigInteger q = arr[1];



        System.out.println(arr[0]);
        System.out.println(arr[1]);
       //System.out.println("phiiiii :" +p);


        BigInteger pMinus1 = p.subtract(BigInteger.ONE);
        BigInteger qMinus1 = q.subtract(BigInteger.ONE);

        BigInteger phi = pMinus1.multiply(qMinus1);


        //System.out.println("phi " + phi.toString());
        return phi;
    }

    public BigInteger findD(BigInteger e){
        return e.modInverse(findPHI());
    }



    public byte[] dencrypt(byte[] message, BigInteger d, BigInteger n) {
//        System.out.println("Before calc " + byteToString(message));


        BigInteger temp = new BigInteger(message);
        BigInteger m = temp.modPow(d, n);//decrypt

//        System.out.println("My message" + m.toString());

        byte[] b = m.toByteArray();

//        System.out.println("After calc " + byteToString(b));

        return b;
    }

    private static String byteToString(byte[] message) {
        String output ="";
        for(byte i: message) {
            output += Byte.toString(i);
        }
        return output;
    }
//8
}
