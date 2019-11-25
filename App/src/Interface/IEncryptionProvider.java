package Interface;
import Objects.EncryptionKeyClass;

public interface IEncryptionProvider {
     public byte[] encrypt(EncryptionKeyClass key, byte[] data);
     public byte[] decrypt(EncryptionKeyClass key, byte[] encryptedData);
}
