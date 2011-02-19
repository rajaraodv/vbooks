package com.zimbra.vbooks.domain;

public class Book {
	private String isbn;
	public Book(String isbn){
		this.isbn = isbn;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
}
