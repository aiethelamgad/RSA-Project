package src;
import java.io.*;
import java.math.BigInteger;
import java.net.Socket;

public class RSAClient {
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

        // Message to send
        String message = "Aiethel";
        BigInteger msg = new BigInteger(message.getBytes());

        // Step 1: Encrypt with receiver's public key (confidentiality)
        BigInteger step1 = msg.modPow(eReceiver, nReceiver);
        // Step 2: Encrypt with sender's private key (authenticity)
        BigInteger doubleEncrypted = step1.modPow(dSender, nSender);

        // Send to server
        Socket socket = new Socket("localhost", 12345);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(doubleEncrypted);
        out.close();
        socket.close();

        System.out.println("Client: Message sent.");
    }
}
