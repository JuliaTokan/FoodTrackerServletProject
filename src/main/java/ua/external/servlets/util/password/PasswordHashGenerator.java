package ua.external.servlets.util.password;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHashGenerator {
    private final static String ALGORITHM = "SHA-1";
    private final static String CHARSET = "utf-8";
    private final static int SIGNUM = 1;
    private final static int RADIX = 16;
    public String hash(String password) {
        byte[] bytes = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM);
            messageDigest.update(password.getBytes(CHARSET));
            bytes = messageDigest.digest();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            //logger.log(Level.ERROR, "Hashing password error", e);
        }
        BigInteger bigInteger = new BigInteger(SIGNUM, bytes);
        return bigInteger.toString(RADIX);
    }
}
