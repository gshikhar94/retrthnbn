package com.mindtree.service;

import java.sql.SQLException;
import java.util.List;

import com.mindtree.model.Books;
import com.mindtree.model.ReserveBook;

public interface ListBook {
	
	public List<Books> getBookStatus() throws SQLException;

	public boolean addBook(String title) throws SQLException, Exception;

	public String deleteBook(String title) throws Exception;

	public String reserveBook(ReserveBook reserveBook) throws Exception;

}
