package com.yey.read.net;

public class FormData {
	
	public StringBuilder mBuilder = new StringBuilder();
	public void setValue(String key, String value){
		mBuilder.append(key+"="+value+"&");
	}
	public String toString(){
		String formStr = mBuilder.toString();
		return formStr.substring(0, formStr.length()-1);
	}
}
