# RSA Encryption & Decryption in Java

This project was developed by **Aiethel Amgad** as part of the **Computer Security** course at the **Arab Academy for Science, Technology & Maritime Transport (AASTMT)**. It demonstrates how to securely transmit data using the RSA cryptographic algorithm.

> Includes both basic RSA encryption/decryption and extra features like double encryption and network communication.

---

## Objective

To implement RSA encryption and decryption between two entities (Sender and Receiver) using Java. The encrypted data is transmitted securely using the receiver’s public key, and only the receiver can decrypt it using their private key.

---

## Files Included

| File/Folder              | Description                                                        |
|-------------------------|--------------------------------------------------------------------|
| `src/RSAGenKey.java`      | Generates RSA key pair (`public.key` and `private.key`)            |
| `src/RSAEncrypt.java`     | Encrypts the message from `data/input.txt` using the public key   |
| `src/RSADecrypt.java`     | Decrypts the message from `data/encrypted.txt` using the private key |
| `src/DoubleEncrypt.java`  | Double encryption for enhanced security (bonus feature)           |
| `src/DoubleDecrypt.java`  | Double decryption and verification (bonus feature)                |
| `src/RSAClient.java`      | Sends encrypted data over network (socket client, bonus feature)  |
| `src/RSAServer.java`      | Receives encrypted data over network (socket server, bonus feature) |
| `keys/`                   | Contains generated RSA key files (`sender_public.key`, etc.)       |
| `data/input.txt`          | Plaintext file to encrypt (e.g., name ≤ 10 characters)             |
| `data/encrypted.txt`      | Output file containing encrypted data                             |
| `data/double_encrypted.txt` | Output file for double-encrypted data (bonus feature)             |
| `data/decrypted.txt`      | Output file containing decrypted message                          |

---

## How to Run

### Step 1: Generate Key Pair
```bash
cd src
javac RSAGenKey.java
java RSAGenKey

### Step 2: Encrypt a Message
```bash
javac RSAEncrypt.java
java RSAEncrypt ../data/input.txt ../data/encrypted.txt

### Step 3: Decrypt a Message
```bash
javac RSADecrypt.java
java RSADecrypt ../data/encrypted.txt ../data/decrypted.txt

### Step 4: Double Encryption (Authentication + Confidentiality)
```bash
javac DoubleEncrypt.java DoubleDecrypt.java
java DoubleEncrypt ../data/input.txt ../data/double_encrypted.txt
java DoubleDecrypt ../data/double_encrypted.txt ../data/decrypted.txt

### Step 5: Network Communication (Client/Server)
```bash
javac RSAServer.java RSAClient.java
java RSAServer
# In another terminal:
java RSAClient


