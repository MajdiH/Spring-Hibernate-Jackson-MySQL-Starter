package com.mh.service;

import java.util.List;

import com.mh.exception.BookNotFoundException;
import com.mh.model.Book;

public interface BookService {
	public Book create(Book book);
	public Book get(Integer id) throws BookNotFoundException;
	public List<Book> getAll();
	public Book update(Book book);
	public Book delete(Integer id);
}
