package src;
import java.io.*;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;

public class RSAServer {
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

        // Receive encrypted data
        ServerSocket serverSocket = new ServerSocket(12345);
        System.out.println("Server: Waiting for connection...");
        Socket clientSocket = serverSocket.accept();
        System.out.println("Server: Client connected.");

        ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
        BigInteger doubleEncrypted = (BigInteger) in.readObject();
        in.close();
        clientSocket.close();
        serverSocket.close();

        // Step 1: Decrypt with sender's public key (verify signature)
        BigInteger step1 = doubleEncrypted.modPow(eSender, nSender);
        // Step 2: Decrypt with receiver's private key (get message)
        BigInteger decrypted = step1.modPow(dReceiver, nReceiver);
        String message = new String(decrypted.toByteArray());

        System.out.println("Server: Decrypted message is -> " + message);
    }
}
