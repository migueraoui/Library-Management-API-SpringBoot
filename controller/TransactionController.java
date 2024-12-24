package sp.migr.librarymanagement.controller;

import sp.migr.librarymanagement.request.BorrowRequest;
import sp.migr.librarymanagement.request.ReturnRequest;
import sp.migr.librarymanagement.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/borrow")
    public String borrowBook(@RequestBody BorrowRequest request) {
        transactionService.borrowBook(request.getUserId(), request.getBookId());
        return "Book borrowed successfully!";
    }

    @PostMapping("/return")
    public String returnBook(@RequestBody ReturnRequest request) {
        transactionService.returnBook(request.getUserId(), request.getBookId());
        return "Book returned successfully!";
    }
}