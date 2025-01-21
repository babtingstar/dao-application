package edu.kh.com.daoapplication.dao;

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

    @Column(unique = true, nullable = false) // nullable = null 값 ok? = null 값 허용 안함
    private String username;

    @Column(unique = false, nullable = false) // unique = 중복 허용? false 중복 허용
    private String password;
}
