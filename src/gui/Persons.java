package gui;

import java.math.BigInteger;
import java.util.Random;


public class Persons {


    //all variables are mad
    public RSA rsa;
    String nounce;
    String partnerNounce;
    //nounces are set for demonstration purposes
    //we used string as concatanation is easier to do
    String[] nounceArr = {"abcd","edfg","hijk","lmno","pqrs","tuvw","xyza","aabb","bbaa","baab",};
    int rnd = new Random().nextInt(9);

    Persons(RSA rsa){
        this.rsa = rsa;
        nounce = nounceArr[rnd];//get noounce when inialised
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
        return s.getPublicKey(p);//get the public key of who the partner is from the server  s
    }

    //same encryption as from the RSA.java but different as e and n are parameters
    public byte[] serverEncrypt(Server s, byte[] message, BigInteger e, BigInteger n) {//get cipher
        BigInteger temp = new BigInteger(message);
        BigInteger c = temp.modPow(e, n);//public key
        byte[] b = c.toByteArray();

//        System.out.println(new String(b));

        return b;
    }

    //verification is made by the user and checks if the public key is usable
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


    //send nounce to a person to and encrytps it
    public byte[] sendNounce(Persons to){
        System.out.println(nounce);
        byte[] b = to.rsa.encrypt(nounce.getBytes());

//        System.out.println("EN " + new String(b));

        to.setPartnerNounce(b);
        return nounce.getBytes();
    }


    //make the partenr connecting to a partner and give them their nounce
    public void setPartnerNounce(byte[] b){
        partnerNounce = new String(b);
    }

    //decrypt and able to read the nonce
    public byte[] decryptNounce(Persons from){
        byte[] b = partnerNounce.getBytes();
        return rsa.dencrypt(b);
    }
}
