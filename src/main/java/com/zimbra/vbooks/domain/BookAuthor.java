package com.zimbra.vbooks.domain;

public class BookAuthor {
	private String fullName;
	private String isbn;
	public BookAuthor(String isbn, String fullName) {
		this.fullName = fullName;
		this.isbn = isbn;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
}
