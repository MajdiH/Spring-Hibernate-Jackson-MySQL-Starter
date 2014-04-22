package com.mh.exception;

public class BookNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = -2859292084648724403L;
	private final int bookId;
	
	public BookNotFoundException(int id) {
		bookId = id;
	}
	
	public int getBookId() {
		return bookId;
	}

}
