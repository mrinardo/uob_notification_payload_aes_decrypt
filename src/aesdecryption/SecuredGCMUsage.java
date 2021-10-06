package aesdecryption;

import java.security.InvalidAlgorithmParameterException ;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException ;
import java.security.SecureRandom ;
import java.util.Base64 ;

import javax.crypto.BadPaddingException ;
import javax.crypto.Cipher ;
import javax.crypto.IllegalBlockSizeException ;
import javax.crypto.NoSuchPaddingException ;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec ;
import javax.crypto.spec.SecretKeySpec;


public class SecuredGCMUsage {
	
    //public static int AES_KEY_SIZE = 256 ;
    public static int IV_SIZE = 96 ;
    public static int TAG_BIT_LENGTH = 128 ;
    public static String ALGO_TRANSFORMATION_STRING = "AES/GCM/NoPadding";
    public static String ERROR_MSG = "";

    public static String decryptData (String sData, String sAAD, String sKey, String sIV) {
        byte[] aData = Base64.getDecoder().decode(sData);
    	
        byte[] aadData = sAAD.getBytes();
        byte[] aKey = Base64.getDecoder().decode(sKey);

        SecretKey aesKey = new SecretKeySpec(aKey, 0, aKey.length, "AES");

        byte iv[] = Base64.getDecoder().decode(sIV);
        
        // Initialize GCM Parameters
        GCMParameterSpec gcmParamSpec = new GCMParameterSpec(TAG_BIT_LENGTH, iv) ;      
        
        byte[] decryptedText = aesDecrypt(aData, aesKey, gcmParamSpec, aadData) ; // Same key, IV and GCM Specs for decryption as used for encryption.          
        String sDecryptedText = new String(decryptedText);

    	return sDecryptedText;
    }


    public static byte[] aesDecrypt(byte[] encryptedMessage, SecretKey aesKey, GCMParameterSpec gcmParamSpec, byte[] aadData) {
           Cipher c = null ;
           
           ERROR_MSG = "";
    
           try {
               c = Cipher.getInstance(ALGO_TRANSFORMATION_STRING); // Transformation specifies algortihm, mode of operation and padding
            } catch(NoSuchAlgorithmException noSuchAlgoExc) {ERROR_MSG = ("Exception while decrypting. Algorithm being requested is not available in environment " + noSuchAlgoExc);}
             catch(NoSuchPaddingException noSuchAlgoExc) {ERROR_MSG = ("Exception while decrypting. Padding scheme being requested is not available in environment " + noSuchAlgoExc);}

           if (ERROR_MSG != "") {
        	   	return "".getBytes();
           }

            try {
                c.init(Cipher.DECRYPT_MODE, aesKey, gcmParamSpec, new SecureRandom()) ;
            } catch(InvalidKeyException invalidKeyExc) {ERROR_MSG = ("Exception while encrypting. Key being used is not valid. It could be due to invalid encoding, wrong length or uninitialized " + invalidKeyExc) ; }
             catch(InvalidAlgorithmParameterException invalidParamSpecExc) {ERROR_MSG = ("Exception while encrypting. Algorithm Param being used is not valid. " + invalidParamSpecExc) ; }

            if (ERROR_MSG != "") {
        	   	return "".getBytes();
           }

            try {
                c.updateAAD(aadData) ; // Add AAD details before decrypting
            }catch(IllegalArgumentException illegalArgumentExc) {ERROR_MSG = ("Exception thrown while encrypting. Byte array might be null " +illegalArgumentExc );}
            catch(IllegalStateException illegalStateExc) {ERROR_MSG = ("Exception thrown while encrypting. CIpher is in an illegal state " +illegalStateExc); }

            if (ERROR_MSG != "") {
        	   	return "".getBytes();
           }
            
            byte[] plainTextInByteArr = null ;
            try {
                plainTextInByteArr = c.doFinal(encryptedMessage) ;
            } catch(IllegalBlockSizeException illegalBlockSizeExc) {ERROR_MSG = ("Exception while decryption, due to block size " + illegalBlockSizeExc) ; }
             catch(BadPaddingException badPaddingExc) {ERROR_MSG = ("Exception while decryption, due to padding scheme " + badPaddingExc) ; }

            if (ERROR_MSG != "") {
        	   	return "".getBytes();
           }

           return plainTextInByteArr ;
    }
}
