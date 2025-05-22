package src;
// Assumes sender has private.key and receiver’s public.key
import java.io.*;
import java.math.BigInteger;

public class DoubleEncrypt {
    public static void main(String[] args) throws Exception {
        // Load sender's private key
        ObjectInputStream senderPriv = new ObjectInputStream(new FileInputStream("keys/sender_private.key"));
        BigInteger dSender = (BigInteger) senderPriv.readObject();
        BigInteger nSender = (BigInteger) senderPriv.readObject();
        senderPriv.close();

        // Load receiver's public key
        ObjectInputStream receiverPub = new ObjectInputStream(new FileInputStream("keys/receiver_public.key"));
        BigInteger eReceiver = (BigInteger) receiverPub.readObject();
        BigInteger nReceiver = (BigInteger) receiverPub.readObject();
        receiverPub.close();

        // Read message
        String message = "Aiethel";
        BigInteger msg = new BigInteger(message.getBytes());

        // Encrypt with receiver's public key
        BigInteger step1 = msg.modPow(eReceiver, nReceiver);
        // Then encrypt with sender's private key
        BigInteger finalEncrypted = step1.modPow(dSender, nSender);

        // Save to file
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data/double_encrypted.txt"));
        out.writeObject(finalEncrypted);
        out.close();

        System.out.println("Message double-encrypted.");
    }
}
