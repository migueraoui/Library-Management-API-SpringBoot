package sp.migr.librarymanagement.repository;


import sp.migr.librarymanagement.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT t FROM Transaction t WHERE t.userId = :userId AND t.bookId = :bookId AND t.status = 'BORROWED'")
    Transaction findByUserIdAndBookId(Long userId, Long bookId);
}