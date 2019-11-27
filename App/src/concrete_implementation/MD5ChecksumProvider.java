package concrete_implementation;
import abstract_class.HashLogicProvider;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class MD5ChecksumProvider extends HashLogicProvider{
    
    public MD5ChecksumProvider(){
        try{
            MessageDigest mgds = MessageDigest.getInstance("MD5");
            super.setMessageDigest(mgds);
        }
        catch(NoSuchAlgorithmException ex){
            System.exit(-1);
        }
    }
}
