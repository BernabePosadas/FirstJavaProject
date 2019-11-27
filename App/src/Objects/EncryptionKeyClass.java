package Objects;

import ConcreteImplementation.MD5ChecksumProvider;
import Interface.IHashChecksumProvider;

public class EncryptionKeyClass {

    private final byte[] Key;
    private final byte[] IV;
    private final IHashChecksumProvider Hasher;

    public EncryptionKeyClass(String Key, String Iv) {
        this.Key = Key.getBytes();
        this.IV = Iv.getBytes();
        this.Hasher = new MD5ChecksumProvider();
    }

    public byte[] getKey() {
         return this.Hasher.generateHashMessageDigest(this.Key);
    }

    public byte[] getIV() {
        return this.Hasher.generateHashMessageDigest(this.IV);
    }
}
