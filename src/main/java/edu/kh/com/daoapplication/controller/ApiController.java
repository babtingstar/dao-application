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
@RequestMapping("/api") //맨 앞에 공통으로 들어갈 url api 명칭 작성
public class ApiController {
    @Autowired
    private KHTUserService khtUserService;
    @Autowired
    private KHTProductService khtProductService;

    // ajax url 을 이용해서 DB에 저장된 DB 불러오기
    @GetMapping("/users")//    /api/users
    public List<KHTUser> findAll() {
        List<KHTUser> users = khtUserService.findAll();
        log.info(users.toString());
        return users;
    }

    // ajax url을 이용해서 DB에 회원 저장하기
    @PostMapping("/saveUser") //    /api/saveUser
    public KHTUser saveUser(@RequestBody KHTUser khtUser) {
        return khtUserService.save(khtUser);
    }

    // 모든 제품 조회   /api/products
    @GetMapping("/products") //    /api/products
    public List<KHTProduct> findAllProducts() {
        List<KHTProduct> products = khtProductService.findAll();
        log.info(products.toString());
        return products;

        //    return khtProductService.findAll();
    }

    // 제품 등록 /api/saveProduct
    @PostMapping("/saveProduct") //    /api/saveProduct
    public KHTProduct saveProduct(@RequestBody KHTProduct khtProduct) {
        KHTProduct savedProduct = khtProductService.save(khtProduct);
        log.info(savedProduct.toString());
        return savedProduct;
        // return khtProductService.save(khtProduct);
    }

    /**
     * @RequestParam 으로 전달받은 id 값을
     * URLSearchParams = URL 주소에서 parameters(파라미터들)을 search 검색해서
     * urlParams 라는 변수 이름에 ? 뒤에 오는 키=값 을 모두 담아둠
     * urlParam 에서 원하는 키의 값을 get 해서 가져옴
     * id 라는 변수 이름에 키에 해당하는 값을 저장
     *
     * const urlParams = new URLSearchParams(window.location.search);
     * const id = urlParams.get('id');
     *
     * @param id 는 get('id')로 가져온 값을 활용해서 ajax 로 db에 id 값에 해당하느 데이터를 가져오기
     * @return
     */
    @GetMapping("/user/{id}")
    public KHTUser getUser(@PathVariable("id") int id) {
        KHTUser user = khtUserService.findById(id);
        log.info(user.toString());
        return user; // 가져온 데이터가 있든 없든 html 에 전달
    }

    @GetMapping("/product/{id}")
    public KHTProduct getProduct(@PathVariable("id") int id) {
        KHTProduct product = khtProductService.findById(id);
        log.info(product.toString());
        return product;
    }
}