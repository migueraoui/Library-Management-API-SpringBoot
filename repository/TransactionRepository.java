package sp.migr.librarymanagement.repository;


import sp.migr.librarymanagement.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}