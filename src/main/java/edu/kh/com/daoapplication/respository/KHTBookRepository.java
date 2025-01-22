package edu.kh.com.daoapplication.respository;

import edu.kh.com.daoapplication.dao.KHTBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KHTBookRepository extends JpaRepository<KHTBook, Long> {
    // save
    // findAll
    KHTBook findById(int id);
}
