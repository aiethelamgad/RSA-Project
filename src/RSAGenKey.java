package src;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.io.*;

public class RSAGenKey {
    public static void main(String[] args) throws IOException {
        SecureRandom rand = new SecureRandom();
        int bitLength = 512; // You can adjust as needed (1024 for stronger security)

        BigInteger p = BigInteger.probablePrime(bitLength, rand);
        BigInteger q = BigInteger.probablePrime(bitLength, rand);
        BigInteger n = p.multiply(q);
        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));

        BigInteger e = BigInteger.valueOf(65537); // Common choice
        BigInteger d = e.modInverse(phi);

        // Save public key
        ObjectOutputStream pubOut = new ObjectOutputStream(new FileOutputStream("public.key"));
        pubOut.writeObject(e);
        pubOut.writeObject(n);
        pubOut.close();

        // Save private key
        ObjectOutputStream privOut = new ObjectOutputStream(new FileOutputStream("private.key"));
        privOut.writeObject(d);
        privOut.writeObject(n);
        privOut.close();

        System.out.println("Keys generated and saved to files.");
    }
}
