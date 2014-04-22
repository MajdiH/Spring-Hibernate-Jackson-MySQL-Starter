package com.mh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mh.exception.BookNotFoundException;
import com.mh.model.Book;
import com.mh.repository.BookRepository;

@Service
@Transactional(rollbackFor = { BookNotFoundException.class })
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	@Override
	public Book create(Book book) {
		return bookRepository.save(book);
	}

	@Override
	public Book get(Integer id) throws BookNotFoundException {
		Book book = null;
		if (id instanceof Integer)
			book = bookRepository.findOne(id);
		if (book != null)
			return book;
		throw new BookNotFoundException(id);
	}

	@Override
	public List<Book> getAll() {
		return bookRepository.findAll();
	}

	@Override
	public Book update(Book book) {
		Book sBookToUpdate = get(book.getId());
		sBookToUpdate.update(book);
		return sBookToUpdate;
	}

	@Override
	public Book delete(Integer id) {
		Book sBook = get(id);
		bookRepository.delete(id);
		return sBook;
	}

}
