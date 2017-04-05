package com.mindtree.model;

import java.sql.Date;

public class ReserveBook {

	private int bookId;
	private String visitorId;
	private java.sql.Date reservationDate;
	private java.sql.Date expirationDate;

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getVisitorId() {
		return visitorId;
	}

	public void setVisitorId(String visitorId) {
		this.visitorId = visitorId;
	}

	public java.sql.Date getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(java.sql.Date reservationDate) {
		this.reservationDate = reservationDate;
	}

	public java.sql.Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(java.sql.Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public ReserveBook(int bookId, String visitorId, Date reservationDate, Date expirationDate) {
		super();
		this.bookId = bookId;
		this.visitorId = visitorId;
		this.reservationDate = reservationDate;
		this.expirationDate = expirationDate;
	}

}
