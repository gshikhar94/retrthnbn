package com.mindtree.controller;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import javax.ws.rs.Produces;

import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.mindtree.model.Books;
import com.mindtree.model.ReserveBook;
import com.mindtree.service.ListBook;

@Path("/library")
public class BookController {

	@Autowired
	ListBook bookService;

	private Logger logger = Logger.getLogger(BookController.class);

	@GET
	@Path("/books")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN })
	public Response getBookStatus() {

		logger.info("getting all the Books details !!!!!!");

		List<Books> bookDetails = new ArrayList<Books>();
		try {
			bookDetails = bookService.getBookStatus();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Books b : bookDetails) {
			System.out.println(b.getId() + " " + b.getTitle() + " " + b.getStatus());

		}

		if (bookDetails.isEmpty()) {

			logger.error("Error occured while reteriving Books  !!!!!!");
			throw new RuntimeException("No Books to be displayed");

		}
		System.out.println("Coming");
		GenericEntity<List<Books>> entity = new GenericEntity<List<Books>>(bookDetails) {
		};
		Response response = Response.ok(entity).build();
		return response;

	}

	@POST
	@Path("/addbook")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN })
	public Response addBook(@FormParam("title") String title) throws Exception {

		System.out.println(title);
		logger.info("adding book !!!!!!");
		if (bookService.addBook(title)) {
			return Response.status(200).entity("Book Added Successfully " + title).build();
		} else {

			return Response.status(200).entity("Book Alrady Exists " + title).build();

		}
	}

	@POST
	@Path("/deletebook")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN })
	public Response deleteBook(@FormParam("title") String title) throws Exception {

		System.out.println(title);
		logger.info("Deleting book !!!!!!");
		String result = bookService.deleteBook(title);
		return Response.status(200).entity(result + " " + title).build();

	}

	@POST
	@Path("/reserveBook")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN })
	public Response reserveBook(@FormParam("bookId") int bookId, @FormParam("bookTitle") String title,
			@FormParam("visitorId") String visitorId, @FormParam("reservationDate") Date reservationDate,
			@FormParam("expirationDate") Date expirationDate) throws Exception {
		
		System.out.println();
		System.out.println(bookId);
		System.out.println(visitorId);
		ReserveBook reserveBook = new ReserveBook(bookId, visitorId, reservationDate, expirationDate);
		String result = bookService.reserveBook(reserveBook);
		return Response.status(200).entity(result).build();

	}
}
