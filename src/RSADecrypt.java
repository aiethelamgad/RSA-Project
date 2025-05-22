package src;
import java.io.*;
import java.math.BigInteger;

public class RSADecrypt {
    public static void main(String[] args) throws Exception {
        // Load private key
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("keys/receiver_private.key"));
        BigInteger d = (BigInteger) in.readObject();
        BigInteger n = (BigInteger) in.readObject();
        in.close();

        // Read encrypted message
        ObjectInputStream encIn = new ObjectInputStream(new FileInputStream("data/encrypted.txt"));
        BigInteger encrypted = (BigInteger) encIn.readObject();
        encIn.close();

        // Decrypt
        BigInteger decrypted = encrypted.modPow(d, n);
        String message = new String(decrypted.toByteArray());

        // Write to output
        BufferedWriter writer = new BufferedWriter(new FileWriter("data/decrypted.txt"));
        writer.write(message);
        writer.close();

        System.out.println("Message decrypted: " + message);
    }
}
