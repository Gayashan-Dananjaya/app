package lk.ijse.dep10.backendspringboot.business;

import lk.ijse.dep10.backendspringboot.dto.BookDTO;

import java.util.List;

public interface BookBO {
    List<BookDTO> getAllBooks(String query) throws Exception;
    BookDTO saveBook(BookDTO bookDTO) throws Exception;
}
