package ch.fhnw.rsakeygen;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Euklid {

    public static List<EuklidData> euklid(BigInteger a, BigInteger b) {
        List<EuklidData> euklidData = new ArrayList<>();
        EuklidData startingEuklid = new EuklidData();

        startingEuklid.a = a;
        startingEuklid.b = b;
        startingEuklid.x0 = BigInteger.valueOf(1);
        startingEuklid.y0 = BigInteger.ZERO;
        startingEuklid.x1 = BigInteger.ZERO;
        startingEuklid.y1 = BigInteger.ONE;

        euklidData.add(startingEuklid);

        while (euklidData.get(euklidData.size() - 1).b.compareTo(BigInteger.ZERO) != 0) {
            int index = euklidData.size() - 1;
            int indexNext = euklidData.size();
            euklidData.add(new EuklidData());

            EuklidData current = euklidData.get(index);
            EuklidData next = euklidData.get(indexNext);

            current.q = current.a.divide(current.b);
            current.r = current.a.mod(current.b);

            next.a = current.b;
            next.b = current.r;

            next.x0 = current.x1;
            next.y0 = current.y1;
            next.x1 = current.x0.subtract(current.x1.multiply(current.q));
            next.y1 = current.y0.subtract(current.y1.multiply(current.q));
        }

        return euklidData;
    }


    public Euklid() {
    }


}
