package ch.fhnw.rsacrypt;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class RsaCryptor {
    //Args[0] Filepath
    //Args[1] Mode Decrypt/Encrypt
    public static void main(String[] args) {
        RsaCryptor rsaCryptor = new RsaCryptor(args[0]);
        if (args[1].equals("decrypt")) {
            rsaCryptor.decrypt();
            System.out.println("test");
        } else {
            rsaCryptor.encrypt();
            System.out.println("test2");
        }
    }

    final String SKFN = "sk.txt";
    final String PKFN = "pk.txt";
    final String CHIFFREFN = "chiffre.txt";
    final String OUTPUTFN = "text-d.txt";
    final String INPUTFN = "text.txt";

    Path directory;

    public RsaCryptor(String directory) {
        this.directory = Path.of(directory);
    }

    public void encrypt() {
        try {
            BigInteger[] key = parseKey(getFileContent(PKFN));
            String stringToEncrypt = getFileContent(INPUTFN);
            char[] chars = stringToEncrypt.toCharArray();
            String outputString = "";
            for (char charInString :
                    chars) {
                System.out.println(charInString);
                outputString += RapidExponentation.rapidExponentation(BigInteger.valueOf(charInString), key[1], key[0]) + ",";
            }
            writeToFile(CHIFFREFN, outputString);
        } catch (Exception e) {

        }

    }

    public void decrypt() {
        try {
            BigInteger[] key = parseKey(getFileContent(SKFN));
            String stringToDecrypt = getFileContent(CHIFFREFN);
            String[] stringParts = stringToDecrypt.split(",");
            StringBuilder outputString = new StringBuilder();
            for (String stringPart :
                    stringParts) {
                System.out.println(stringPart);
                outputString.append((char) RapidExponentation.rapidExponentation(new BigInteger(stringPart), key[1], key[0]).intValueExact());
            }
            writeToFile(OUTPUTFN, outputString.toString());
        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.getMessage());
        }
    }

    private String getFileContent(String fileInPath) throws IOException {
        return Files.readString(this.directory.resolve(fileInPath));
    }

    private BigInteger[] parseKey(String key) {
        String[] parts = key.replaceAll("(\\(|\\))", "").split(",");
        BigInteger[] partsB = new BigInteger[2];

        partsB[0] = new BigInteger(parts[0]);
        partsB[1] = new BigInteger(parts[1]);

        return partsB;
    }

    private boolean writeToFile(String fileInPath, String string) {
        try {
            Path path = this.directory.resolve(fileInPath);
            FileWriter fw = new FileWriter(path.toFile());
            PrintWriter pw = new PrintWriter(fw);
            pw.print(string);
            pw.close();
            System.out.println("File saved at: " + path.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }
}
