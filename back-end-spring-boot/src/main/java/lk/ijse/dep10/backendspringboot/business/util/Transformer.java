package lk.ijse.dep10.backendspringboot.business.util;

import lk.ijse.dep10.backendspringboot.dto.BookDTO;
import lk.ijse.dep10.backendspringboot.entity.Book;
import lombok.extern.apachecommons.CommonsLog;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class Transformer {
    private final ModelMapper mapper = new ModelMapper();
    public Book toBookEntity(BookDTO bookDTO){
        return mapper.map(bookDTO, Book.class);
    }
    public BookDTO fromBookEntity(Book book){
        return mapper.map(book, BookDTO.class);
    }
}
