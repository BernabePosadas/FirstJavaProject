
package Interface;

public interface IHashChecksumProvider {
    public boolean checkIfMatch(byte[] data, String HashCode);
    public String generateHashMessageDigestBase64(byte[] data);
    public byte[] generateHashMessageDigest(byte[] data);
}
