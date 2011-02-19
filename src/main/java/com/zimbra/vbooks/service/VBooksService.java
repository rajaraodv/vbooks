package com.zimbra.vbooks.service;

import java.util.List;
import com.zimbra.vbooks.domain.BookAuthor;


public interface VBooksService {
	public String saveBook(BookAuthor ba);
	public String deleteBook(BookAuthor ba);
	public String updateBookAuthor(BookAuthor ba);
	public List<BookAuthor> getBooks(int pageNumber);
	public List<BookAuthor> getBooksByAuthor(String authorName);
}
