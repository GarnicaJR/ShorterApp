package com.example.demo.utility;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class Bijective {
	
	
	private final String ALPHABET="abcdefghijklmnopqrstuvwxyz0123456789";
	private final int BASE  = ALPHABET.length();
	
	public String encode(int number) {
		if(number==0) return ALPHABET.charAt(0)+"";
		
		StringBuilder sb = new StringBuilder("");
		
		while(number > 0) {
			sb.append(ALPHABET.charAt(number % BASE));
			number = number / BASE;
		}
		return sb.reverse().toString();
	}
	
	public int decode(String source) {
		int i = 0;
		for(char c : source.toCharArray()) {
			i = (i*BASE) +  ALPHABET.indexOf(c+"");
		}
		return i;
	}
	
	/*
	 * public static void main(String[] args) { Bijective bj = new Bijective();
	 * String enc = bj.encode(5000); System.out.println(enc);
	 * System.out.println(bj.decode(enc));
	 * 
	 * 
	 * 
	 * }
	 */
}
