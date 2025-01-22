package edu.kh.com.daoapplication.service;


import edu.kh.com.daoapplication.entity.KHTBook;
import edu.kh.com.daoapplication.entity.KHTProduct;
import edu.kh.com.daoapplication.repository.KHTBookRepository;
import edu.kh.com.daoapplication.repository.KHTProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KHTBookService {

    @Autowired
    private KHTBookRepository bookRepository;


    public List<KHTBook> findAll() {
        return bookRepository.findAll();
    }

    public KHTBook save(KHTBook book) {
        return bookRepository.save(book);
    }

    public KHTBook findById(int id) {
        return bookRepository.findById(id);
    }
}
