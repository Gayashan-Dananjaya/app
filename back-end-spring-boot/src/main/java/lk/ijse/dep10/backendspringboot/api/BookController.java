package lk.ijse.dep10.backendspringboot.api;

import lk.ijse.dep10.backendspringboot.business.BookBO;
import lk.ijse.dep10.backendspringboot.dao.BookDAO;
import lk.ijse.dep10.backendspringboot.dto.BookDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@CrossOrigin
public class BookController {
    private final BookBO bookBO;

    public BookController(BookBO bookBO) {
        this.bookBO = bookBO;
    }
    @GetMapping
    public List<BookDTO> getAllBooks(@RequestParam(value = "q",required = false) String query) throws Exception{
        return bookBO.getAllBooks(query);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public BookDTO saveBook(@RequestBody BookDTO bookDTO) throws Exception{
        return bookBO.saveBook(bookDTO);
    }
}
