package com.cao.xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Translate {
	public static void handle(String eString) throws IOException {
	    StringTokenizer st = new StringTokenizer(eString,",!' '.;");
	    while(st.hasMoreElements()) {
	      String sText;
	      sText = st.nextElement().toString();
	      
	      //System.out.println(sText);
	      String encoding="UTF-8";
	      String filepath="model-analysis\\src\\main\\resource\\translate.txt";
	      
	      File file=new File("C:\\Users\\Cao\\git\\DA\\back\\model-analysis\\src\\main\\resource\\translate.txt");
	      // System.out.println("2222");
	      if(file.isFile() && file.exists()){ //判断文件是否存在
	        //System.out.println("1111");
	        InputStreamReader read = new InputStreamReader(new FileInputStream(file),encoding);//考虑到编码格式
	        BufferedReader bufferedReader = new BufferedReader(read);
	        String lineTxt = null;
	        String result = "";
	        while((lineTxt = bufferedReader.readLine()) != null){
	          if(lineTxt.toString().equals(sText)){
	        	  result += bufferedReader.readLine();
	        	  //System.out.println("Result: " + bufferedReader.readLine());
	        	  //System.out.println(sText + bufferedReader.readLine());
	          }
	        }
	        result.replace("\n\t", " ");
	        System.out.print(result);
	        read.close();
	      }
	    }
	  }
	public static void main(String[] args) throws IOException {
	    Scanner sc = new Scanner(System.in);
	    System.out.println("请输入英文文本：");
	    String eText = sc.nextLine();
	    handle(eText);
	    //System.out.println(eText);
	  }
}
