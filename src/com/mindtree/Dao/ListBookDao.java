package com.mindtree.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mindtree.connection.JdbcConnection;
import com.mindtree.model.Books;
import com.mindtree.model.ReserveBook;

public class ListBookDao {

	public List<Books> getBookDetails() throws Exception {

		List<Books> bookList = new ArrayList<Books>();
		Connection con = JdbcConnection.getConnection();
		try {

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from Book");
			while (rs.next()) {
				Books book = new Books();
				book.setId(rs.getInt(1));
				book.setTitle(rs.getString(2));
				book.setStatus("Not Reserved");
				bookList.add(book);

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			con.close();
		}

		return bookList;
	}

	public List<Books> getStatus(List<Books> bookList) throws SQLException {

		Connection con = JdbcConnection.getConnection();
		try {

			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("select book_id from book_reservation where current_date() <= expiration_date");
			while (rs.next()) {
				for (Books bk : bookList) {
					if (bk.getId() == rs.getInt(1)) {
						bk.setStatus("Reserved");

					}

				}

			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			JdbcConnection.closeConnection(con);
		}

		return bookList;

	}

	public int addBook(String title) throws SQLException {
		Connection con = JdbcConnection.getConnection();
		int value = 0;
		try {
			String query = " insert into Book (title)" + " values (?)";

			// create the mysql insert preparedstatement
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1, title);

			// execute the preparedstatement
			value = preparedStmt.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			JdbcConnection.closeConnection(con);
		}
		return value;
	}

	public int deleteBook(String title) throws SQLException {
		Connection con = JdbcConnection.getConnection();
		int value = 0;
		try {
			String query = " delete from Book where title = ?";

			// create the mysql insert preparedstatement
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1, title);

			// execute the preparedstatement
			value = preparedStmt.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			JdbcConnection.closeConnection(con);
		}
		return value;
	}

	public String reserveBook(ReserveBook reserveBook) throws Exception {
		List<Books>bookList = getBookDetails();
		int value = 0;
		List<Books>bookListUpdated = getStatus(bookList);
		for (Books books : bookListUpdated) {
			System.out.println(books.getStatus());
			if(books.getId()==reserveBook.getBookId()&&books.getStatus().equalsIgnoreCase("reserved"))
			{
				value = 0;
			}
			else if(books.getId()==reserveBook.getBookId())
			{
				Connection con = JdbcConnection.getConnection();
				String query = " insert into book_reservation(book_id,visitor_id,reservation_date,expiration_date)"
						+ " values (?,?,?,?)";

				PreparedStatement preparedStmt = con.prepareStatement(query);
				preparedStmt.setInt(1, reserveBook.getBookId());
				preparedStmt.setString(2, reserveBook.getVisitorId());
				preparedStmt.setDate(3, reserveBook.getReservationDate());
				preparedStmt.setDate(4, reserveBook.getExpirationDate());
				value = preparedStmt.executeUpdate();
				JdbcConnection.closeConnection(con);
			}
			else
			{
				System.out.println("Book requested is already reserved");
			}
		}
		System.out.println(value);
		return value+"Hi";
//		
////		getStatus(bookList);
////		Connection con = JdbcConnection.getConnection();
////		int value = 0;
////		try {
////			// String query1 = "select book_id from book_reservation where
////			// book_id!="+reserveBook.getBookId();
////			Statement stmt = con.createStatement();
////			ResultSet rs = stmt
////					.executeQuery("select * from book_reservation where book_id!=" + reserveBook.getBookId());
////			while (rs.next()) {
////				System.out.println(rs.getInt(1));
////				if (rs.getInt(1) == reserveBook.getBookId()) {
//		
//					String query = " insert into book_reservation(book_id,visitor_id,reservation_date,expiration_date)"
//							+ " values (?,?,?,?)";
//
//					PreparedStatement preparedStmt = con.prepareStatement(query);
//					preparedStmt.setInt(1, reserveBook.getBookId());
//					preparedStmt.setString(2, reserveBook.getVisitorId());
//					preparedStmt.setDate(3, reserveBook.getReservationDate());
//					preparedStmt.setDate(4, reserveBook.getExpirationDate());
//					value = preparedStmt.executeUpdate();
//					JdbcConnection.closeConnection(con);
//				else {
//					value = 0;
//				}
//
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//
//		}
//		return value + "SuccessfulyAdded";
//
//	}
}
}
