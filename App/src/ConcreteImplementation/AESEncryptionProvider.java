package ConcreteImplementation;

import Interface.IEncryptionProvider;
import Objects.EncryptionKeyClass;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESEncryptionProvider implements IEncryptionProvider {

    public byte[] encrypt(EncryptionKeyClass key, byte[] data){
        try {
            Cipher cip = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec ivParam = new IvParameterSpec(key.getIV());
            SecretKeySpec keyParam = new SecretKeySpec(key.getKey(), "AES");
            cip.init(Cipher.ENCRYPT_MODE, keyParam, ivParam);
            data = cip.doFinal(data);
            return data;
        } catch (Exception ex) {
            return null;
        }
    }

    public byte[] decrypt(EncryptionKeyClass key, byte[] data) {
        try {
            Cipher cip = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec ivParam = new IvParameterSpec(key.getIV());
            SecretKeySpec keyParam = new SecretKeySpec(key.getKey(), "AES");
            cip.init(Cipher.DECRYPT_MODE, keyParam, ivParam);
            return data;
        } catch (Exception ex){
             return null;
        }
    }

}
