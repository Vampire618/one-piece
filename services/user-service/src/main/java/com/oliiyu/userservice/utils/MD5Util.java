package com.oliiyu.userservice.utils;

import org.bouncycastle.util.encoders.Hex;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @Auther: oliiyu
 * @Date: 2019/4/26 17:21
 * @Description:
 */
public class MD5Util {

    /**
     * 加密(带盐)
     *
     * @param rawPass 原始密码
     * @param salt    盐内容（如：用户名）
     * @return
     */
    public static String encodePassword(String rawPass, Object salt) {
        String saltedPass = mergePasswordAndSalt(rawPass, salt, false);
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        }
        byte[] digest;
        digest = messageDigest.digest(saltedPass.getBytes(Charset.forName("UTF-8")));
        return new String(Hex.encode(digest));
    }

    private static String mergePasswordAndSalt(String password, Object salt, boolean strict) {
        if (password == null) {
            password = "";
        }

        if (((salt.toString().lastIndexOf("{") != -1) || (salt.toString().lastIndexOf("}") != -1))
                && (strict) && (salt != null)) {
            throw new IllegalArgumentException("Cannot use { or } in salt.toString()");
        }

        if (salt == null || "".equals(salt)) {
            return password;
        }
        return password + "{" + salt.toString() + "}";
    }

    public static void main(String[] args) {
        String salt = "";
        try{
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            salt = random.nextInt() + "";

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        System.out.println(encodePassword("admin", salt));
    }
}
