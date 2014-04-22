package com.mh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mh.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
