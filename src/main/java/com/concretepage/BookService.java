package com.concretepage;

import org.springframework.stereotype.Service;

@Service
public class BookService {
	public Boolean deleteBookById(int id) {
		System.out.println("Book deleted with id " + id);
		return true;
	}
	public Book updateBook(Book book) {
		return new Book(book.getId(), book.getName() +" - updated");
	}
}