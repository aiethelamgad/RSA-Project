package src;
// Assumes receiver has private.key and sender’s public.key
import java.io.*;
import java.math.BigInteger;

public class DoubleDecrypt {
    public static void main(String[] args) throws Exception {
        // Load sender's public key
        ObjectInputStream senderPub = new ObjectInputStream(new FileInputStream("keys/sender_public.key"));
        BigInteger eSender = (BigInteger) senderPub.readObject();
        BigInteger nSender = (BigInteger) senderPub.readObject();
        senderPub.close();

        // Load receiver's private key
        ObjectInputStream receiverPriv = new ObjectInputStream(new FileInputStream("keys/receiver_private.key"));
        BigInteger dReceiver = (BigInteger) receiverPriv.readObject();
        BigInteger nReceiver = (BigInteger) receiverPriv.readObject();
        receiverPriv.close();

        // Read encrypted message
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("data/double_encrypted.txt"));
        BigInteger encrypted = (BigInteger) in.readObject();
        in.close();

        // Step 1: decrypt with sender's public key
        BigInteger step1 = encrypted.modPow(eSender, nSender);
        // Step 2: decrypt with receiver's private key
        BigInteger decrypted = step1.modPow(dReceiver, nReceiver);
        String message = new String(decrypted.toByteArray());

        System.out.println("Double-decrypted message: " + message);
    }
}
