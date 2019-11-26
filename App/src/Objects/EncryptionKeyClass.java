package Objects;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptionKeyClass {

    private final byte[] Key;
    private final byte[] IV;

    public EncryptionKeyClass(String Key, String Iv) {
        this.Key = Key.getBytes();
        this.IV = Iv.getBytes();
    }

    public byte[] getKey() throws NoSuchAlgorithmException {
        try{
            MessageDigest mgds = MessageDigest.getInstance("MD5");
            byte[] key = mgds.digest(this.Key);
            return key;
        }
        catch(NoSuchAlgorithmException ex){
            throw ex;
        }
    }
    public byte[] getIV() throws NoSuchAlgorithmException{
        try{
            MessageDigest mgds = MessageDigest.getInstance("MD5");
            byte[] IV = mgds.digest(this.IV);
            return IV;
        }
        catch(NoSuchAlgorithmException ex){
            throw ex;
        }
    } 
}
