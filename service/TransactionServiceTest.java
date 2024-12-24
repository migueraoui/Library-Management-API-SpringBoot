package sp.migr.librarymanagement.service;

import sp.migr.librarymanagement.model.Book;
import sp.migr.librarymanagement.model.Transaction;
import sp.migr.librarymanagement.model.User;
import sp.migr.librarymanagement.repository.BookRepository;
import sp.migr.librarymanagement.repository.TransactionRepository;
import sp.migr.librarymanagement.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testBorrowBook() {
        User user = new User();
        user.setId(1L);

        Book book = new Book();
        book.setId(1L);
        book.setCopiesAvailable(5);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(transactionRepository.save(any(Transaction.class))).thenAnswer(i -> i.getArgument(0));

        transactionService.borrowBook(1L, 1L);
        assertEquals(4, book.getCopiesAvailable());
        verify(transactionRepository, times(1)).save(any(Transaction.class));
    }

    @Test
    public void testReturnBook() {
        User user = new User();
        user.setId(1L);

        Book book = new Book();
        book.setId(1L);
        book.setCopiesAvailable(4);

        Transaction transaction = new Transaction();
        transaction.setId(1L);
        transaction.setUserId(1L);
        transaction.setBookId(1L);
        transaction.setStatus("BORROWED");
        transaction.setBorrowDate(LocalDate.now());

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(transactionRepository.findByUserIdAndBookId(1L, 1L)).thenReturn(transaction);

        transactionService.returnBook(1L, 1L);
        assertEquals("RETURNED", transaction.getStatus());
        assertEquals(5, book.getCopiesAvailable());
        verify(transactionRepository, times(1)).save(transaction);
    }
}