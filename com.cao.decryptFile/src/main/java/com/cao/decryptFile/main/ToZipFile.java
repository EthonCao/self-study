package com.cao.decryptFile.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.cao.decryptFile.utils.EncryptionAndDecryption;
import com.cao.decryptFile.utils.ZipUtils;


public class ToZipFile {
    public static void main(String[] args) throws Exception {
        //加密
        String contentPath = "C:\\CaoTest\\temp\\20191120170706396313\\20191120151126743_context.xmlpass.xml.unpass.xml";
        String modelPath =  "C:\\CaoTest\\temp\\20191120170706396313\\20191120153339521_model.xmlpass.xml.unpass.xml";
        
        String outputZipFilePath = "C:\\CaoTest\\temp\\mytest02.zip";
        
        File contextFile = new File(contentPath);
        String encryptContentPath = contextFile.getAbsolutePath() + "pass.xml";
        File modelFile = new File(modelPath);
        String encryptModelPath = modelFile.getAbsolutePath() + "pass.xml";
        System.out.println(encryptContentPath);
        System.out.println(encryptModelPath);
        
        //生成密钥
        String key = EncryptionAndDecryption.encryptFile(contentPath, encryptContentPath);
        String key1 = EncryptionAndDecryption.encryptFile(modelPath, encryptModelPath);
        System.out.println(key);
        System.out.println(key1);
        String keyPath = "";
        if (contentPath.lastIndexOf("\\") >= 0) {
            keyPath = contentPath.substring(0, contentPath.lastIndexOf("\\"))+"\\secret.txt";
        }  else {
            keyPath = contentPath.substring(0, contentPath.lastIndexOf("/"))+"/secret.txt";
        }
        createFile(keyPath, key);
        /** 测试压缩方法 */
        List<File> fileList = new ArrayList<>();
        fileList.add(new File(encryptContentPath));
        fileList.add(new File(encryptModelPath));
        fileList.add(new File(keyPath));
        FileOutputStream fos2= new FileOutputStream(new File(outputZipFilePath));
        ZipUtils.toZip(fileList, fos2);
    }
    
    /**
     * 生成制定文件
     * 
     * @param inputFile
     *            文件存储路径和名称
     * @param content
     *            文件内容
     * @return:boolean
     */
    private static boolean createFile(String inputFile, String content) {
        try {
            File file = new File(inputFile);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
            }
            // 创建文件
            file.createNewFile();
            // 写入内容
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.flush();
            bw.close();
            fw.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
