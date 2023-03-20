package ch.fhnw.rsacrypt;

import java.math.BigInteger;

public class RapidExponentation {

    public static BigInteger rapidExponentation(BigInteger x, BigInteger e, BigInteger m){
        BigInteger h,k;

        String[] bytes = e.toString(2).split("");

        h = BigInteger.ONE;

        k = x;

        for (BigInteger i = BigInteger.valueOf(bytes.length); i.compareTo(BigInteger.ZERO) > 0; i = i.subtract(BigInteger.ONE)) {
            if (bytes[i.intValue()-1].equals("1")){
                h = h.multiply(k).mod(m);
            }
            k = k.pow(2).mod(m);
        }

        return h;


    }

}
