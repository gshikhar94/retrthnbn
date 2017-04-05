package com.mindtree.controller;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import com.mindtree.model.Books;

@Provider
@Produces(MediaType.TEXT_HTML)
public class ListBookToHtmlWriter implements MessageBodyWriter<List<Books>>  {

	@Override
	public long getSize(List<Books> arg0, Class<?> arg1, Type arg2, Annotation[] arg3, MediaType arg4) {
		// TODO Auto-generated method stub
		return -1;
	}

	@Override
	public boolean isWriteable(Class<?> arg0, Type arg1, Annotation[] arg2, MediaType arg3) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void writeTo(List<Books> bookDetails, Class<?> arg1, Type arg2, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> headers, OutputStream out) throws IOException, WebApplicationException {
		

		writeTocontent(out,"<html><body>");
		writeTocontent(out,"<table border=1>");
		writeTocontent(out,"<tr><th>Book Id</th><th>Book Title</th><th>Status</th><th>Salary</th></tr>");
		for(Books bk :bookDetails)
		{
			writeTocontent(out,"<tr><td>"+bk.getId()+"</td>");
			writeTocontent(out,"<td>"+bk.getTitle()+"</td>");
			writeTocontent(out,"<td>"+bk.getStatus()+"</td></tr>");

		}
	}
	
private void writeTocontent(OutputStream out, String content) throws IOException {
		
		out.write(content.getBytes());
	}

}
