package by.teachmeskills.bookstore.dao.service;

import by.teachmeskills.bookstore.dao.entity.Book;

import java.util.List;

public interface BookService {

    Book save(Book book);

    List<Book> findAll();

    Book findOne(Long id);

    List<Book> findByCategory(String category);

    List<Book> blurrySearch(String title);
}
