package src;
import java.io.*;
import java.math.BigInteger;

public class RSAEncrypt {
    public static void main(String[] args) throws Exception {
        // Load public key
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("keys/receiver_public.key"));
        BigInteger e = (BigInteger) in.readObject();
        BigInteger n = (BigInteger) in.readObject();
        in.close();

        // Read plaintext
        BufferedReader reader = new BufferedReader(new FileReader("data/input.txt"));
        String plaintext = reader.readLine();
        reader.close();

        // Convert to BigInteger
        BigInteger message = new BigInteger(plaintext.getBytes());

        // Encrypt
        BigInteger encrypted = message.modPow(e, n);

        // Save encrypted message
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data/encrypted.txt"));
        out.writeObject(encrypted);
        out.close();

        System.out.println("Message encrypted.");
    }
}
