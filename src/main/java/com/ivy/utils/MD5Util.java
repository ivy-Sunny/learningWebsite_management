package com.ivy.utils;

//import sun.misc.BASE64Encoder;

import org.apache.commons.codec.binary.Base64;

public class MD5Util {

    /**
     * 密码加密
     *
     * @param password
     * @return
     * @throws Exception
     */
    public static String md5(String password) {
        try {
            byte[] bytes = password.getBytes();
            return new Base64().encodeToString(bytes);
            //解码
            // byte[] pwdDec = new Base64().decode(pwdEnc);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
