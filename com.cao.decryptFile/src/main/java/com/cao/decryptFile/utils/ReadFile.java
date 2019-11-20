package com.cao.decryptFile.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ReadFile {
	
    /**
     * @param path
     * @param line
     * @return
     */
    public static String readeFile(String path,int line){
    	FileInputStream fis = null;
        InputStreamReader isr = null;
        //用于包装InputStreamReader,提高处理性能。因为BufferedReader有缓冲的，而InputStreamReader没有。
        BufferedReader br = null; 
        try {
            
        	String str = "";
        	//FileInputStream
            fis = new FileInputStream(path);
            //从文件系统中的某个文件中获取字节
            isr = new InputStreamReader(fis);// InputStreamReader 是字节流通向字符流的桥梁,
            //从字符输入流中读取文件中的内容,封装了一个new InputStreamReader的对象
            br = new BufferedReader(isr);
            int i=0;
            while ((str = br.readLine()) != null) {
                if( i==line ){
                    return str;
                }
                i++;
            }
        } catch (Exception e) {
            return "";
        } finally {
            try {
                br.close();
                isr.close();
                fis.close();
                // 关闭的时候最好按照先后顺序关闭最后开的先关闭所以先关s,再关n,最后关m
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    /**
     * 删除单个文件
     * @param   sPath    被删除文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String sPath) {
        try {
            if( sPath != null ){
                File file = new File(sPath);
                // 路径为文件且不为空则进行删除
                if (file.isFile() && file.exists()) {
                    return file.delete();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    
    /**
   	 * 读取文本文件(csv,txt)的文件流方法
   	 * @param url
   	 * @return
   	 */
   	public static Map<String, Object> readCsv(String url) {
   		File file = new File(url);
   		file.setReadable(true);
   		file.setWritable(true);
   		BufferedReader br = null;
   		Map<String, Object> detailMap = new HashMap<String, Object>();
   		try {
   			ArrayList<String> dataList = new ArrayList<>();
   			// 流读取数据并处理csv文件格式可能出现的乱码问题
   			br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
   			String column = br.readLine();
   			detailMap.put("column", column);
   			String line = "";
   			String everyLine = "";
   			// 读取到的内容给line变量
   			while ((line = br.readLine()) != null) {
   				everyLine = line;
   				dataList.add(everyLine);
   			}
   			detailMap.put("dataList", dataList);
   		} catch (Exception e) {
   			e.printStackTrace();
   		}
   		return detailMap;
   	}
}
