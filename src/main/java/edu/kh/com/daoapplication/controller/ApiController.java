package edu.kh.com.daoapplication.controller;

import edu.kh.com.daoapplication.dao.KHTProduct;
import edu.kh.com.daoapplication.dao.KHTUser;
import edu.kh.com.daoapplication.service.KHTProductService;
import edu.kh.com.daoapplication.service.KHTUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api") // 맨 앞에 공통으로 들어갈 URL API 명칭을 작성해주는 것
public class ApiController {

    @Autowired
    private KHTUserService khtUserService;

    @Autowired
    private KHTProductService khtProductService;

    // ajax url 을 이용해서 DB에 저장된 DB 불러오기
    @GetMapping("/users")
    public List<KHTUser> findAll() {
        List<KHTUser> users = khtUserService.findAll();
        log.info(users.toString());
        return users;
    }

    // ajax url 을 이용해서 DB에 회원 저장하기
    @PostMapping("/saveUser")
    public KHTUser saveUser(@RequestBody KHTUser khtUser) {
        return khtUserService.save(khtUser);
    }

    // 모든 제품 조회
    @GetMapping("/products")
    public List<KHTProduct> findAllProducts() {
        return khtProductService.findAll();
    }

    // 새로운 제품 생성
    @PostMapping("/saveProduct")
    public KHTProduct saveProduct(@RequestBody KHTProduct khtProduct) {
        return khtProductService.save(khtProduct);
    }
}
