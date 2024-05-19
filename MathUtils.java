import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class MathUtils {
    private static final String ALGORITHM = "AES";
    private static final int KEY_SIZE = 128;

    private byte[] key;

    public MathUtils(String password) throws Exception {
        // 将自定义字符串转换为密钥
        key = generateKey(password);
    }

    private byte[] generateKey(String password) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        keyGenerator.init(KEY_SIZE, new SecureRandom(password.getBytes()));
        SecretKey secretKey = keyGenerator.generateKey();
        return secretKey.getEncoded();
    }

    public byte[] encrypt(byte[] data) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        return cipher.doFinal(data);
    }

    public byte[] decrypt(byte[] data) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        return cipher.doFinal(data);
    }

    public static String AES(String string, String password) throws Exception {
        MathUtils mathUtils = new MathUtils(password);
        String plaintext = string;
        byte[] data = plaintext.getBytes();
        // AES加密
        byte[] encryptedData = mathUtils.encrypt(data);
        // Base64加密
        return Base64.getEncoder().encodeToString(encryptedData);
    }

    public static String AntiAES(String base64String, String password) throws Exception {
        MathUtils mathUtils = new MathUtils(password);
        // Base64解密
        byte[] plaintext = Base64.getDecoder().decode(base64String);
        // AES解密
        byte[] decryptedData = mathUtils.decrypt(plaintext);
        return new String(decryptedData);
    }
}
