package lk.ijse.dep10.backendspringboot.business.impl;

import lk.ijse.dep10.backendspringboot.business.BookBO;
import lk.ijse.dep10.backendspringboot.business.exception.BusinessException;
import lk.ijse.dep10.backendspringboot.business.exception.BusinessExceptionType;
import lk.ijse.dep10.backendspringboot.business.util.Transformer;
import lk.ijse.dep10.backendspringboot.dao.BookDAO;
import lk.ijse.dep10.backendspringboot.dto.BookDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookBOImpl implements BookBO {
    private final Transformer transformer;
    private final BookDAO bookDAO;

    public BookBOImpl(Transformer transformer, BookDAO bookDAO) {
        this.transformer = transformer;
        this.bookDAO = bookDAO;
    }

    @Override
    public List<BookDTO> getAllBooks(String query) throws Exception {
        return bookDAO.getAllBooks(query).stream().map(transformer::fromBookEntity).collect(Collectors.toList());
    }

    @Override
    public BookDTO saveBook(BookDTO bookDTO) throws Exception {
        if (bookDAO.existsBookByIsbn(bookDTO.getIsbn())){
            throw new BusinessException(BusinessExceptionType.DUPLICATE_RECORD, "Save Failed: Isbn: " + bookDTO.getIsbn() + " already exists");
        }
        return transformer.fromBookEntity(bookDAO.saveBook(transformer.toBookEntity(bookDTO)));
    }
}
