package concrete_implementation;

import interfaces.IEncryptionProvider;
import objects.EncryptionKeyClass;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESEncryptionProvider implements IEncryptionProvider {
    EncryptionKeyClass key;
    public AESEncryptionProvider(EncryptionKeyClass key){
        this.key = key;
    }
    @Override
    public byte[] encrypt(byte[] data){
        try {
            Cipher cip = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec ivParam = new IvParameterSpec(this.key.getIV());
            SecretKeySpec keyParam = new SecretKeySpec(this.key.getKey(), "AES");
            cip.init(Cipher.ENCRYPT_MODE, keyParam, ivParam);
            data = cip.doFinal(data);
            return data;
        } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException ex) {
            return null;
        }
    }

    @Override
    public byte[] decrypt(byte[] data) {
        try {
            Cipher cip = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec ivParam = new IvParameterSpec(this.key.getIV());
            SecretKeySpec keyParam = new SecretKeySpec(this.key.getKey(), "AES");
            cip.init(Cipher.DECRYPT_MODE, keyParam, ivParam);
            data = cip.doFinal(data);
            return data;
        } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException ex){
             return null;
        }
    }

}
