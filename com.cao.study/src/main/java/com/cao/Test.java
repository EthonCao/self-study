package com.cao;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
public static void main(String[] args) {
	 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	 String tString = df.format(new Date());
     System.out.println(tString);// new Date()为获取当前系统时间
}
}
