package com.zimbra.vbooks.dao;

import java.util.List;

import com.zimbra.vbooks.domain.BookAuthor;

public interface VBooksDao {
	public String saveBook(BookAuthor ba);
	public String deleteBook(BookAuthor ba);
	public String updateBookAuthor(BookAuthor ba);

	public List<BookAuthor> getBooks(int pageNumber);

	public List<BookAuthor> getBooksByAuthor(String authorName);
}
