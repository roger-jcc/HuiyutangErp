package com.HuiyutangErp.utils;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

@Service
public class AESUtils {
	
	
	
	  // 加密方法
    public static String encrypt(String plaintext, String key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
    	Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
    	cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes(), "AES"));
    	byte[] result = cipher.doFinal(plaintext.getBytes());
        return Base64.getEncoder().encodeToString(result);
    }

    // 解密方法
    public static String decrypt(String ciphertext, String key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes(), "AES"));
        byte[] encryptedBytes = Base64.getDecoder().decode(ciphertext);
        byte[] result = cipher.doFinal(encryptedBytes);
        return new String(result);
    }

}
