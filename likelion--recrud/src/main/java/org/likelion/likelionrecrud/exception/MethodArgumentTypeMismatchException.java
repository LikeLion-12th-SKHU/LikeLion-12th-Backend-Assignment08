package org.likelion.likelionrecrud.exception;

public class MethodArgumentTypeMismatchException extends CustomException{
	public MethodArgumentTypeMismatchException(Error error, String message){
		super(error,message);
	}

}
