package interfaces;

public interface IEncryptionProvider {
     public byte[] encrypt(byte[] data);
     public byte[] decrypt(byte[] encryptedData);
}
