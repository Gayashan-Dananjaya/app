package lk.ijse.dep10.backendspringboot.dao.impl;

import lk.ijse.dep10.backendspringboot.dao.BookDAO;
import lk.ijse.dep10.backendspringboot.entity.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import static lk.ijse.dep10.backendspringboot.dao.util.Mappers.BOOK_ROW_MAPPER;

@Repository
public class BookDAOImpl implements BookDAO {
    private final JdbcTemplate jdbcTemplate;

    public BookDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Book> getAllBooks(String query) throws Exception {
        return jdbcTemplate.query("SELECT * FROM book",BOOK_ROW_MAPPER);
    }

    @Override
    public Book saveBook(Book book) throws Exception {
        jdbcTemplate.update(con -> {
            PreparedStatement stm = con.prepareStatement("INSERT INTO book (isbn, title) VALUES (?,?)");
            stm.setString(1, book.getIsbn());
            stm.setString(2, book.getTitle());
            return stm;
        });
        return book;
    }

    @Override
    public boolean existsBookByIsbn(String isbn) throws Exception {
        return jdbcTemplate.queryForObject("SELECT * FROM book WHERE isbn=?",BOOK_ROW_MAPPER,isbn) != null;
    }
}
