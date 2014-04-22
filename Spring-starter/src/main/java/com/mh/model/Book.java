package com.mh.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "books")
public class Book {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Length(min = 1, max = 20)
	private String title;
	
	@Length(min = 20, max = 500)
	private String description;
	
	@Range(min = 1, max = 1500)
	private double price;
	
	@Length(min = 1, max = 20)
	private String author;
	
	/**
	 * Method updates already existed {@link Book} object with values from the inputed argument.
	 * @param sPhone - Object which contains new Book values.
	 * @return {@link Book} object to which this method applied.
	 */
	public Book update(Book sBook) {
		this.title = sBook.title;
		this.description = sBook.description;
		this.price = sBook.price;
		this.author = sBook.author;
		return this;
	}
	
	@Override
	public String toString() {
		return title+": "+description+" with price "+price;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	
	

}
