package edu.kh.com.daoapplication.controller;

import edu.kh.com.daoapplication.model.entity.KHTBook;
import edu.kh.com.daoapplication.model.entity.KHTProduct;
import edu.kh.com.daoapplication.model.entity.KHTUser;
import edu.kh.com.daoapplication.model.vo.VerificationRequest;
import edu.kh.com.daoapplication.service.KHTBookService;
import edu.kh.com.daoapplication.service.KHTProductService;
import edu.kh.com.daoapplication.service.KHTUserService;
import edu.kh.com.daoapplication.service.VerificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api") //맨 앞에 공통으로 들어갈 url api 명칭 작성
public class ApiController {
    @Autowired
    private KHTUserService khtUserService;
    @Autowired
    private KHTProductService khtProductService;
    @Autowired
    private KHTBookService khtBookService;

    // ajax url 을 이용해서 DB에 저장된 DB 불러오기
    @GetMapping("/users")//    /api/users
    public List<KHTUser> findAll() {
        List<KHTUser> users = khtUserService.findAll();
        log.info(users.toString());
        return users;
    }

    // ajax url을 이용해서 DB에 회원 저장하기
//    @PostMapping("/saveUser") //    /api/saveUser
//    public KHTUser saveUser(@RequestBody KHTUser khtUser) {
//        return khtUserService.save(khtUser.getUsername(), khtUser.getPassword(), khtUser.getImagePath());
//    }

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
     * @param id 는    get('id' 로 가져온 값을 활용해서 ajax로 db에서 id값에 해당하는 데이터를 가져오기
     * @return
     * @RequestParam 으로 전달받은 값을
     * URLSearchParams = URL 주소에서 parameters(파라미터들)을 search 검색해서
     * urlParams 라는 변수 이름에 ? 뒤에 오는 키=값 을 모두 담아둠
     * urlParams에서 원하는 키의 값을 get 해서 가져옴
     * id라는 변수 이름에 키에 해당하는 값을 저장
     * <p>
     * const urlParams = new URLSearchParams(window.location.search);
     * const id = urlParams.get('id');
     */
    @GetMapping("/user/{id}")
    public KHTUser findById(@PathVariable("id") int id) {
        KHTUser khtUser = khtUserService.findById(id);
        log.info(khtUser.toString());
        return khtUserService.findById(id); // 가져온 데이터가 있든 없든 html에 전달
    }

    @GetMapping("/products/{id}")
    public KHTProduct findByIdProduct(@PathVariable("id") int id) {
        KHTProduct khtProduct = khtProductService.findById(id);
        log.info(khtProduct.toString());
        return khtProduct;
    }

    @GetMapping("/books")
    public List<KHTBook> books() {
        return khtBookService.findAll();
    }

    @GetMapping("/book/{id}")  //id 조회
    public KHTBook book(@PathVariable("id") int id) {
        return khtBookService.findById(id);
    }

    /* 기본 글자 타입만 한 번에 저장하기
     * 405 (Method Not Allowed) GET 으로는 DB 저장 X
     * Request method 'POST' is not supported
     * @param khtBook = Body = 통째로 바디 내 세부 설정없이 한 번에 가져온 그대로 전달
     * @return        = 저장역할을 하는 save로 데이터 그대로 전달

    @PostMapping("/bookSave")
    public KHTBook saveBook(@RequestBody KHTBook khtBook) {
        KHTBook savedBook = khtBookService.save(khtBook);
        log.info(savedBook.toString());
        return savedBook;
    }
     */
    @PostMapping("/bookSaveImg")
    public KHTBook saveBookImg(@RequestParam("title") String title,
                               @RequestParam("author") String author,
                               @RequestParam("genre") String genre,
                               @RequestParam("file") MultipartFile file) {
        return khtBookService.save(title, author, genre, file);
    }

    @PostMapping("/saveUserImage")
    public KHTUser saveUserImg(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               @RequestParam("file") MultipartFile file) {
        return khtUserService.save(username, password, file);
    }

    /********************** 이메일 인증 *******************************/
    @Autowired
    private VerificationService verificationService;

    @PostMapping("/sendCode")
    public String sendCode(@RequestBody VerificationRequest vr) {
        System.out.println("=== Request Controller /api/sendCode ===");
        String email = vr.getEmail();
        System.out.println("controller email : " + email);

        String code = verificationService.randomCode();
        System.out.println("controller code : " + code);

        verificationService.saveEmailCode(email, code);
        System.out.println("controller - Save method : " + email + "->" + code);

        verificationService.sendEmail(email, code);
        System.out.println("controller - 이메일을 성공적으로 보냄 :  " + code);
        return "이메일을 성공적으로 보냈습니다." + email;
    }

    // 인증번호 일치하는 지 확인
    @PostMapping("/checkCode")
    public String checkCode(@RequestBody VerificationRequest vr) {
        boolean isValid = verificationService.verifyCodeWithVO(vr);
        System.out.println("Controller - checkCode method isValid : " + isValid);
        if (isValid) {
            return "인증번호가 일치합니다.";
        } else {
            return "인증번호가 일치하지 않습니다.";
        }
    }
}
