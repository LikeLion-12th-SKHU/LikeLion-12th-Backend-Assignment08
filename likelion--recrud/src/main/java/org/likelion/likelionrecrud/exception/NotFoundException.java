package org.likelion.likelionrecrud.exception;

public class NotFoundException extends CustomException{

	public NotFoundException(Error error, String message){
		super(error,message);
	}
}
