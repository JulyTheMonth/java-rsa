package ch.fhnw.rsakeygen;

import java.math.BigInteger;

public class EuklidData {
    public BigInteger a, b, x0, y0, x1, y1, q, r;

    public EuklidData() {
    }

    public EuklidData(BigInteger a, BigInteger b, BigInteger x0, BigInteger y0, BigInteger x1, BigInteger y1, BigInteger q, BigInteger r) {
        this.a = a;
        this.b = b;
        this.x0 = x0;
        this.y0 = y0;
        this.x1 = x1;
        this.y1 = y1;
        this.q = q;
        this.r = r;
    }

    @Override
    public String toString() {
        return "EuklidData{" +
                "a=" + a +
                ", b=" + b +
                ", x0=" + x0 +
                ", y0=" + y0 +
                ", x1=" + x1 +
                ", y1=" + y1 +
                ", q=" + q +
                ", r=" + r +
                '}';
    }
}
