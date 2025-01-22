package edu.kh.com.daoapplication.entity;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class KHTUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique=true, nullable=false) //nullable = null값 ok? false = null값 허용 안함
    private String username;

    // unique = 똑같은 값을 허용할 것인가? false = 똑같은 값이 한 컬럼에 여러가지 있는 것을 OK
    @Column(unique=false, nullable=false)
    private String password;
}
