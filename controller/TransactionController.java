package sp.migr.librarymanagement.controller;

import sp.migr.librarymanagement.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/borrow")
    public String borrowBook(@RequestParam Long userId, @RequestParam Long bookId) {
        transactionService.borrowBook(userId, bookId);
        return "Book borrowed successfully!";
    }

    @PostMapping("/return")
    public String returnBook(@RequestParam Long userId, @RequestParam Long bookId) {
        transactionService.returnBook(userId, bookId);
        return "Book returned successfully!";
    }

    // Additional endpoints for transaction management
}