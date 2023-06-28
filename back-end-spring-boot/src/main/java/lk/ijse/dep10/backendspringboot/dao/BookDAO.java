package lk.ijse.dep10.backendspringboot.dao;

import lk.ijse.dep10.backendspringboot.entity.Book;

import java.util.List;

public interface BookDAO {
    List<Book> getAllBooks(String query) throws Exception;
    Book saveBook(Book book) throws Exception;
    boolean existsBookByIsbn(String isbn) throws Exception;
}
