package com.mh.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.mh.exception.helper.ErrorInfo;
import com.mh.model.Book;
import com.mh.service.BookService;

@Controller
@RequestMapping(value="/books")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public ModelAndView createBookPage() {
		ModelAndView mav = new ModelAndView("books/new-book");
		mav.addObject("sBook", new Book());
		return mav;
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Book createBook(@RequestBody @Valid Book book) {
		return bookService.create(book);
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public ModelAndView editBookPage(@PathVariable int id) {
		ModelAndView mav = new ModelAndView("books/edit-book");
		Book book = bookService.get(id);
		mav.addObject("sBook", book);
		return mav;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.PUT, 
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Book editBook(@PathVariable int id, @Valid @RequestBody Book book) {
		book.setId(id);
		return bookService.update(book);
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE, 
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Book deleteBook(@PathVariable int id) {
		return bookService.delete(id);
	}
	
	@RequestMapping(value="", method=RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Book> allBooks() {
		return bookService.getAll();
	}
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public ModelAndView allBooksPage() {
		ModelAndView mav = new ModelAndView("books/all-books");
		List<Book> books = new ArrayList<Book>();
		books.addAll(allBooks());
		mav.addObject("books", books);
		return mav;
	}
	
	@ExceptionHandler(TypeMismatchException.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorInfo handleTypeMismatchException(HttpServletRequest req, TypeMismatchException ex) {
		Locale locale = LocaleContextHolder.getLocale();
		String errorMessage = messageSource.getMessage("error.bad.book.id", null, locale);
		
		errorMessage += ex.getValue();
		String errorURL = req.getRequestURL().toString();
		
		return new ErrorInfo(errorURL, errorMessage);
	}

}
