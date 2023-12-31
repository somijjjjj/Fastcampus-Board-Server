package com.fastcampus.boardserver.exception;

public class DuplicateIdException extends RuntimeException{//실제 런타임 시 예외 던질 수 있음

	private static final long serialVersionUID = -1406719092352762357L;

	public DuplicateIdException(String msg) {
		super(msg);
	}

}
