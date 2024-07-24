package net.javaguides.springboot.controller;

public class ExceptionTest {

	public static void main(String[] args) {
		int a=0;
		int b=101;
		if(b==10) {
			 throw new ArithmeticException();
		}
//		try {
//			int c=b/a;
//			System.out.println(c);
//			System.out.println("ppp");
//		}catch(Exception e) {
//			System.out.println("ss");
//		}
	}
}
