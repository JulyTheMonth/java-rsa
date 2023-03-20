package ch.fhnw.rsakeygen;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;

public class RsaKeyGen {
    public static void main(String[] args) {
        // Generates Secret and Public Key at the given Destination
        RsaKeyGen rsaKeyGen = new RsaKeyGen(new Random(), args[0]);
        System.out.println("Saving Keys");
        rsaKeyGen.savePrivateKey();
        rsaKeyGen.savePublicKey();
        System.out.println("Keys Saved");
    }

    Path directory;

    final int BIT_LENGTH = 2048;

    BigInteger p1;
    BigInteger p2;
    BigInteger n;
    BigInteger phiN;

    BigInteger e;
    BigInteger d;

    public RsaKeyGen(Random rnd, String path) {
        this.directory = Path.of(path);

        System.out.println(this.directory);

        this.e = BigInteger.valueOf(3);
        List<EuklidData> euk;
        int counter = 0;
        do {
            counter++;
            this.p1 = BigInteger.probablePrime(BIT_LENGTH, rnd);
            this.p2 = BigInteger.probablePrime(BIT_LENGTH, rnd);

            n = p1.multiply(p2);
            phiN = p1.subtract(BigInteger.ONE).multiply(p2.subtract(BigInteger.ONE));

            System.out.println("Try " + counter);
            System.out.println("n " + n);
            System.out.println("phiN " + phiN);

            euk = Euklid.euklid(phiN, e);
            this.d = euk.get(euk.size() - 1).y0;
            if (this.d.compareTo(BigInteger.ZERO) < 0){
                this.d = this.d.add(this.phiN);
            }
            System.out.println("d "+ this.d);
            System.out.println("ggt "+ euk.get(euk.size() - 1).a);
            System.out.println("d*e mod phiN " + this.d.multiply(this.e).mod(this.phiN) );
        } while (!euk.get(euk.size() - 1).a.equals(BigInteger.ONE));
        System.out.println("Key Generation Success");
    }

    public boolean savePrivateKey(){
        try {
            Path skPath = this.directory.resolve("sk.txt");
            FileWriter fw = new FileWriter(skPath.toFile());
            PrintWriter pw = new PrintWriter(fw);
            pw.printf("(%d,%d)", this.n,this.d);
            pw.close();
            System.out.println("Secretkey saved at: " + skPath.toString());
        } catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }


    public boolean savePublicKey(){
        try {
           Path pkPath = this.directory.resolve("pk.txt");
            FileWriter fw = new FileWriter(pkPath.toFile());
            PrintWriter pw = new PrintWriter(fw);
            pw.printf("(%d,%d)", this.n,this.e);
            pw.close();
            System.out.println("Secretkey saved at: " + pkPath.toString());
        } catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }
}
