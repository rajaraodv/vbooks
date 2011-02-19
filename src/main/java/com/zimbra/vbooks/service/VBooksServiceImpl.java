package com.zimbra.vbooks.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zimbra.vbooks.dao.VBooksDao;
import com.zimbra.vbooks.domain.BookAuthor;

public class VBooksServiceImpl implements VBooksService {
    private static final Log logger = LogFactory.getLog(VBooksServiceImpl.class);

	private VBooksDao dao;
	
	public void setDao(VBooksDao dao) {
		this.dao = dao;
	}
	
	public String saveBook(BookAuthor ba) {
		logger.debug(ba);
		return dao.saveBook(ba);
	}

	public String deleteBook(BookAuthor ba) {
		return dao.deleteBook(ba);
	}
	
	public String updateBookAuthor(BookAuthor ba) {
		return dao.updateBookAuthor(ba);
	}

	public List<BookAuthor> getBooks(int pageNumber) {
		return dao.getBooks(pageNumber);
	}
	
	

	public List<BookAuthor> getBooksByAuthor(String authorName) {
		return dao.getBooksByAuthor(authorName);
	}

}
