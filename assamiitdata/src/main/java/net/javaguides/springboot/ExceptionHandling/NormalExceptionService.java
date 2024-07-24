package net.javaguides.springboot.ExceptionHandling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NormalExceptionService {

	@Autowired
	NormalExceptionService2 NormalExceptionService2;
	
	public void mainServics(String s) {
		System.out.println("llsd");
	}

	public void putThrowsException(String string) throws ArithmeticException, NullPointerException, ClassNotFoundException  {
		NormalExceptionService2.putThrowsException(string);

	}
}
