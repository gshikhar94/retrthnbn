package com.mindtree.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mindtree.Dao.ListBookDao;
import com.mindtree.model.Books;
import com.mindtree.model.ReserveBook;


public class ListBookImpl implements ListBook {
	
	@Autowired
	ListBookDao bookDao;
	List<Books> bookList=new ArrayList<Books>();
	@Override
	public List<Books> getBookStatus() throws SQLException {
		try {
			bookList= bookDao.getBookDetails();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bookList=bookDao.getStatus(bookList);
		
		
		return bookList;
	}
	@Override
	public boolean addBook(String title) throws Exception {
		List<Books> bookList= bookDao.getBookDetails();
		for (Books b:bookList){
			if(b.getTitle().equalsIgnoreCase(title)){
				return false;
			}
		}
	int value=	bookDao.addBook(title);
	if(value==1){
		return true;
	}
	return false;
	

}
	@Override
	public String deleteBook(String title) throws Exception {
		List<Books> bookList= getBookStatus();
		int count=0;
		for (Books b:bookList){
			
			if(b.getTitle().equalsIgnoreCase(title)){
				count++;
				
				}
			
		
			if(b.getTitle().equalsIgnoreCase(title) && b.getStatus().equalsIgnoreCase("Reserved")){
				return "Book Cannot be Deleted Since it is Reserved";
				
				}
			}
		if(count==1){
				int value=	bookDao.deleteBook(title);
				if(value==1){
					return "Book Deleted Sucessfully";
				}
		}else{
			
			return "Book Does not Exists";
		}
		return "Some Error Occured While Deleting Book";

}
	@Override
	public String reserveBook(ReserveBook reserveBook) throws Exception {
		String result = bookDao.reserveBook(reserveBook);
		return result;
	}
}

