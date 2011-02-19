package com.zimbra.vbooks.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.zimbra.vbooks.domain.BookAuthor;
import com.zimbra.vbooks.service.VBooksServiceImpl;

public class VBooksDaoJdbcImpl implements VBooksDao {
	private static final String CREATE_TABLE_SQL = "CREATE TABLE BOOK_AUTHOR(ISBN VARCHAR(15) NOT NULL, AUTHORNAME VARCHAR(30) NOT NULL, PRIMARY KEY (ISBN, AUTHORNAME))";
	private static final String INSERT_SQL = "INSERT INTO BOOK_AUTHOR (ISBN, AUTHORNAME) VALUES(?,?)";
	private static final String DELETE_SQL = "DELETE FROM BOOK_AUTHOR WHERE ISBN= ? AND AUTHORNAME = ?";
	
	// "DELETE FROM BOOK_AUTHOR WHERE ID=?";

	private static final String GET_ALL_ALERTS_SQL = "SELECT * FROM BOOK_AUTHOR";

    private static final Log logger = LogFactory.getLog(VBooksServiceImpl.class);
	
	private JdbcTemplate template;

	public void setJdbcTemplate(JdbcTemplate template) {
		this.template = template;
	}

	@SuppressWarnings("unused")
	private void createTables() {
		try {
			//this.template.execute("DROP TABLE BOOK_AUTHOR");
			this.template.update(CREATE_TABLE_SQL);
		} catch (org.springframework.jdbc.BadSqlGrammarException e) {
			// table already exists - log this but ignore
			logger.info(e);
		}
	}

	public String saveBook(BookAuthor ba) {
		Object[] params = new Object[] { ba.getIsbn(), ba.getFullName() };
		template.update(INSERT_SQL, params);
		return ba.getIsbn() + ":" + ba.getFullName();
	}

	public String deleteBook(BookAuthor ba) {
		Object[] params = new Object[] { ba.getIsbn(), ba.getFullName() };
		template.update(DELETE_SQL, params);
		return ba.getIsbn() + ":" + ba.getFullName();
	}
	public String updateBookAuthor(BookAuthor ba) {
		return saveBook(ba);
	}

	public List<BookAuthor> getBooks(int pageNumber) {
		//String q = GET_ALL_ALERTS_SQL.replaceAll("PAGE_NUMBER", String.valueOf(pageNumber));
		return template.query(GET_ALL_ALERTS_SQL, (Object[])null,
				new BookAuthorMapper());
	}

	public List<BookAuthor> getBooksByAuthor(String authorName) {
		// TODO Auto-generated method stub
		return null;
	}

	private static class BookAuthorMapper implements RowMapper<BookAuthor> {
		public BookAuthor mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new BookAuthor(rs.getString(1), rs.getString(2));
		}
	}
}
