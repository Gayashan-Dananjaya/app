package lk.ijse.dep10.backendspringboot.dao.util;

import lk.ijse.dep10.backendspringboot.entity.Book;
import org.springframework.jdbc.core.RowMapper;

public class Mappers {
    public static final RowMapper<Book> BOOK_ROW_MAPPER = ((rs, rowNum) -> {
        String isbn = rs.getString("isbn");
        String title = rs.getString("title");
        return new Book(isbn, title);
    });
}
