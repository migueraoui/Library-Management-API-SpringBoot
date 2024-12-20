package sp.migr.librarymanagement.model;

import sp.migr.librarymanagement.model.Book;
import sp.migr.librarymanagement.model.Transaction;
import sp.migr.librarymanagement.model.User;
import sp.migr.librarymanagement.repository.BookRepository;
import sp.migr.librarymanagement.repository.TransactionRepository;
import sp.migr.librarymanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    public void borrowBook(Long userId, Long bookId) {
        User user = userRepository.findById(userId).orElse(null);
        Book book = bookRepository.findById(bookId).orElse(null);

        if (user != null && book != null && book.getCopiesAvailable() > 0) {
            Transaction transaction = new Transaction();
            transaction.setUserId(user.getId());
            transaction.setBookId(book.getId());
            transaction.setBorrowDate(LocalDate.now());
            transaction.setStatus("BORROWED");

            transactionRepository.save(transaction);

            book.setCopiesAvailable(book.getCopiesAvailable() - 1);
            bookRepository.save(book);
        }
    }

    public void returnBook(Long userId, Long bookId) {
        Transaction transaction = transactionRepository.findByUserIdAndBookId(userId, bookId);
        if (transaction != null && "BORROWED".equals(transaction.getStatus())) {
            transaction.setStatus("RETURNED");
            transaction.setReturnDate(LocalDate.now());
            transactionRepository.save(transaction);

            Book book = bookRepository.findById(bookId).orElse(null);
            if (book != null) {
                book.setCopiesAvailable(book.getCopiesAvailable() + 1);
                bookRepository.save(book);
            }
        }
    }




}