package com.cao.decryptFile.main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import com.cao.decryptFile.utils.EncryptionAndDecryption;
import com.cao.decryptFile.utils.ReadFile;
import com.cao.decryptFile.utils.UnZipFile;

public class UnZipFileHandler {

    public static void main(String[] args) {
        String zipFilePath = "C:\\CaoTest\\temp\\sdfl_context_model (1).zip";
        unZip(zipFilePath);
        
    }
    
    private static void unZip(String zipFilePath) {
        List<String> filePaths = UnZipFile.unZipFile(zipFilePath);
        List<String> reslutPaths = new ArrayList<>();
        if (filePaths != null) {
            // 解压成功,找到秘钥
            String secretPath = null;
            for (String path : filePaths) {
                File file1 = new File(path);
                if ("secret.txt".equals(file1.getName())) {
                    secretPath = path;
                    break;
                }
            }
            String secret_key = ReadFile.readeFile(secretPath, 0);
            // 解析模型文件，并且入库
            // 1.判断谁是模型文件
            for (String path : filePaths) {
                File file = new File(path);
                String fileName = file.getName();
                if (fileName.indexOf("_") != -1) {
                    fileName = fileName.split("\\.")[0].split("_")[1];
                }
                if ("model".equals(fileName)) {
                    // 这是模型文件
                    // 2.获取模型文件的名字
                    String name = file.getName();
                    if (!isEmpty(name)) {
                        // 3.将xml的内容保存到bus_model里面
                        String unpath = null;
                        try {
                            // 解密文件
                            File file1 = new File(path);
                            unpath = file1.getPath() + ".unpass.xml";
                            EncryptionAndDecryption.decryptFile(secret_key, path, unpath);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        // 删除密文文件
                        ReadFile.deleteFile(path);
                    }
                } else if ("context".equals(fileName)) {
                    // 解密上下文文件，
                    try {
                        String unContextPath = null;
                        File file1 = new File(path);
                        unContextPath = file1.getPath() + ".unpass.xml";
                        EncryptionAndDecryption.decryptFile(secret_key, path, unContextPath);
                        reslutPaths.add(unContextPath);
                        // 删除密文文件
                        ReadFile.deleteFile(path);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                //ReadFile.deleteFile(zipFilePath);
            }
        }
    }
    
    private static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }
}
