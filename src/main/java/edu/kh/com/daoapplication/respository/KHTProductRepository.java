package edu.kh.com.daoapplication.respository;

import edu.kh.com.daoapplication.dao.KHTProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KHTProductRepository extends JpaRepository<KHTProduct, Long> {

}
