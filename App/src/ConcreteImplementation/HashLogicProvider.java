
package ConcreteImplementation;

import Interface.IHashChecksumProvider;
import java.security.MessageDigest;
import java.util.Base64;
public abstract class HashLogicProvider implements IHashChecksumProvider{
    private MessageDigest mgds = null;
    
    public void setMessageDigest(MessageDigest algorithm){
        this.mgds = algorithm;
    }
    @Override
    public boolean checkIfMatch(byte[] data, String HashCode) {
       String DataChecksum = Base64.getEncoder().encodeToString(this.mgds.digest(data));
       return HashCode.equals(DataChecksum);
    }

    @Override
    public byte[] generateHashMessageDigest(byte[] data) {
        return this.mgds.digest(data);
    }

    @Override
    public String generateHashMessageDigestBase64(byte[] data) {
        return Base64.getEncoder().encodeToString(this.mgds.digest(data));
    }
}
