package com.cao.decryptFile.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.model.FileHeader;


public class UnZipFile {
    public static List<String> unZipFile(String filePath) {
        //存放解压包下面的文件路径
        List<String> tempFilePaths = null;
        try {
            File file = new File(filePath);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            int i = (int)(Math.random()*900 + 100);
            String myStr = Integer.toString(i);
            //解压缩
            tempFilePaths = UnZipFile.unzip(filePath, file.getParent() + File.separator + formatter.format(new Date())+myStr);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return tempFilePaths;
    }
    
    /**
    *
    * @param source 需要解压的文件路径
    * @param dest  将文件解压到哪儿去
    * @return
    */
   private static List<String> unzip(String source,String dest){
       List<String> filsPath = new ArrayList<>();
       try {
           File zipFile=new File(source);

           // 首先创建ZipFile指向磁盘上的.zip文件  
           ZipFile zFile = new ZipFile(zipFile);
           zFile.setFileNameCharset("utf-8");

           // 解压目录 
           File destDir = new File(dest);
               if (zFile.isEncrypted()) {
                   // 设置密码   
                   zFile.setPassword("123456");
               }
               // 将文件抽出到解压目录(解压)   
               zFile.extractAll(dest);
               List<FileHeader > headerList = zFile.getFileHeaders();
               List<File> extractedFileList= new ArrayList<>();
               for(FileHeader fileHeader : headerList) {
                   if (!fileHeader.isDirectory()) {
                       extractedFileList.add(new File(destDir,fileHeader.getFileName()));
               }
           }
           File [] extractedFiles = new File[extractedFileList.size()];
           extractedFileList.toArray(extractedFiles);
           for(File f:extractedFileList){
               filsPath.add(f.getAbsolutePath());
           }
           return filsPath;
       }catch(Exception e) {
           e.printStackTrace();
           return null;
        }
   }
}
