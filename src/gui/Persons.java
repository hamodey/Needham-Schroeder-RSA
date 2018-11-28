package gui;

import java.math.BigInteger;

public class Persons {

    public RSA rsa;
    BigInteger nounce;

    Persons(RSA rsa){
        this.rsa = rsa;
    }

    public String getPublicKey(){
        return "(" + rsa.getE().toString() + ", " + rsa.getN().toString() + ")";
    }

    public BigInteger getE(){
        return rsa.getE();
    }

    public BigInteger getN(){
        return rsa.getN();
    }

    public BigInteger[] getPartnerKey(Server s, Persons p){
        return s.getPublicKey(p);
    }

    public byte[] serverEncrypt(Server s, byte[] message, BigInteger e, BigInteger n) {//get cipher
        BigInteger temp = new BigInteger(message);
        BigInteger c = temp.modPow(e, n);//public key
        byte[] b = c.toByteArray();

//        System.out.println(new String(b));

        return b;
    }

    public BigInteger verify(Server s, BigInteger e){//needs to be signed
        BigInteger temp = e.modPow(rsa.d,  rsa.n);
//        System.out.println("RSA n " + rsa.n.toString());
        return temp;
    }

    public BigInteger sendNounce(Persons to){
        return null;
    }
}
