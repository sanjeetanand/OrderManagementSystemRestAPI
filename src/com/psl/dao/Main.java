package com.psl.dao;

public class Main {

	public static void main(String[] args) {
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<5;i++) {
			sb.append(i);
			sb.append(":item,");
		}
		System.out.println(sb);
		
		sb.replace(sb.lastIndexOf(","), sb.length(), "");
		System.out.println(sb);
	}
}
