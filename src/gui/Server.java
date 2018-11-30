package gui;

import java.math.BigInteger;

public class Server {

    Persons a;
    Persons b;
    BigInteger[] arr = new BigInteger[2];
    Server(Persons a, Persons b){

        this.a = a;
        this.b = b;

    }



    //get public key of person pretty simple stuff
    public BigInteger[] getPublicKey(Persons p){
        arr[0] = p.getE();
//        System.out.println("get e " + p.getE().toString());

        arr[1] = p.getN();
//        System.out.println("get n " + p.getN().toString());

        return arr;
    }


    //sign the nonce that is sent by encrypting the partner public key
    public BigInteger[] sign(Persons to, Persons p){
        BigInteger[] newArr = getPublicKey(p);
        BigInteger[] fin = new BigInteger[2];

        fin[0] = signEn(to, newArr[0]);
        fin[1] = signEn(to, newArr[1]);

        return fin;//return encrypted signed
    }


    //sign the encryption and send it back to the person
    public BigInteger signEn(Persons to, BigInteger i){
        BigInteger c = i.modPow(to.rsa.e, to.rsa.n);
        return c;
    }

    //server signs the public key, by encrypting it with receiver public key
    //if server  >> alice, encrypt the bob(e, n) with alice's public key
    //then alice decrpyts with her own private key
    //now alice has bobs public key without anyone else knowing
    //alice then proceeds with Needham - Schorlder by sending a nonce
    //

    //alice request, so server sends

}
