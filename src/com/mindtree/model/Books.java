package com.mindtree.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder={"id","title","status"})
public class Books {
	
	private int id;
	private String title;
	private String status;
	public Books(int id, String title, String Status) {
		super();
		this.id = id;
		this.title = title;
		status = Status;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String Status) {
		status = Status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Books(int id, String title) {
		super();
		this.id = id;
		this.title = title;
	}
	public Books() {
		super();
		// TODO Auto-generated constructor stub
	}

}
