package com.zhidi.crm.exception;

@SuppressWarnings("serial")
public class MyException extends RuntimeException{

	public MyException(){
		super();
	}
	
	public MyException(String msg){
		super(msg);
	}
}
