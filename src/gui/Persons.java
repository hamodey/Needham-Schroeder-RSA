package gui;

import java.math.BigInteger;
import java.util.Random;


public class Persons {

    public RSA rsa;
    String nounce;
    String partnerNounce;
    String[] nounceArr = {"abcd","edfg","hijk","lmno","pqrs","tuvw","xyza","aabb","bbaa","baab",};
    int rnd = new Random().nextInt(9);

    Persons(RSA rsa){
        this.rsa = rsa;
        nounce = nounceArr[rnd];
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
//        System.out.println("RSA n " + rsa.n);
        return temp;
    }

    public String viewNounce(){
        return nounce;
    }

    public byte[] getNounce(){
        return nounce.getBytes();
    }

    public byte[] sendNounce(Persons to){
        System.out.println(nounce);
        byte[] b = to.rsa.encrypt(nounce.getBytes());

//        System.out.println("EN " + new String(b));

        to.setPartnerNounce(b);
        return nounce.getBytes();
    }

    public void setPartnerNounce(byte[] b){
        partnerNounce = new String(b);
    }

    public byte[] decryptNounce(Persons from){
        byte[] b = partnerNounce.getBytes();
        return rsa.dencrypt(b);
    }
}
