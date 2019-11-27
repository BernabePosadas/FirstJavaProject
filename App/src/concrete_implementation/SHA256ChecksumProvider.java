package concrete_implementation;

import abstract_class.HashLogicProvider;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256ChecksumProvider extends HashLogicProvider {
    public SHA256ChecksumProvider(){
        try{
            MessageDigest mgds = MessageDigest.getInstance("SHA-256");
            super.setMessageDigest(mgds);
        }
        catch(NoSuchAlgorithmException ex){
            System.exit(-1);
        }
    }
}
