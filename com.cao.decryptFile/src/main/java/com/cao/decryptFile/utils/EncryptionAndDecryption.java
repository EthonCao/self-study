package com.cao.decryptFile.utils;

public class EncryptionAndDecryption {
    static String key;

    static {
        try {
            key = AESUtils.getSecretKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 加密
     * @param sourceFilePath
     * @param destFilePath
     * @throws Exception
     */
    public static String encryptFile(String sourceFilePath,String destFilePath) throws Exception {
        AESUtils.encryptFile(key, sourceFilePath, destFilePath);
        return key;
    }

    /**
     * 解密
     * @param sourceFilePath
     * @param destFilePath
     * @throws Exception
     */
    public static void decryptFile(String key,String sourceFilePath,String destFilePath) throws Exception {
        AESUtils.decryptFile(key, sourceFilePath, destFilePath);
    }

}
