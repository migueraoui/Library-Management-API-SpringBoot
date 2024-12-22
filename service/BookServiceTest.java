package sp.migr.librarymanagement.service;

import sp.migr.librarymanagement.model.Book;
import sp.migr.librarymanagement.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @Test
    void testAddBook() {
        Book book = new Book();
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        book.setGenre("Test Genre");
        book.setCopiesAvailable(5);

        Book savedBook = bookService.addBook(book);
        assertNotNull(savedBook.getId());
    }

}