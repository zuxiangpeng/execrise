package com.zuxp.network.netty;

public class Test {
	public static void main(String[] args) {
//		System.out.println(isPowerOfTwo(16));
//		chooser2();
		long startTime = System.currentTimeMillis();
		for(int i =0; i<100000000; i++){
			chooser();
		}
		
		System.out.println(System.currentTimeMillis() -startTime);
		
		long startTime2 = System.currentTimeMillis();
		for(int i =0; i<100000000; i++){
			chooser2();
		}


		Long a = 10L;
		a.intValue();

		System.out.println(System.currentTimeMillis() -startTime2);
		
	}
	   private static boolean isPowerOfTwo(int val) {
	        return (val & -val) == val;
	    }
	   
	   static void chooser(){
		   int i = 8 & (16-1);		  
	   }
	   
	   static void chooser2(){
		   int i = 8 % 16;	
	   }
}
