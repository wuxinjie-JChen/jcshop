package com.yc.util;

import java.util.Random;

public class CodeUtil {

	public static String genCode(){
		char [] codeSeq={'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p',
				'q','r','s','t','u','v','w','x','y','z','0','1','2','3','4','5','6','7','8','9'};
		Random r=new Random();
		StringBuffer sb=new StringBuffer();
		int count=0;
		while(true){
			int index=r.nextInt(codeSeq.length);
			char c=codeSeq[index];
			sb.append(c);
			count++;
			if(count==6){
				break;
			}
		}
		return sb.toString();
	}
}
