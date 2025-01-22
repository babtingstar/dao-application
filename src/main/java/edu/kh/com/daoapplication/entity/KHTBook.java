package edu.kh.com.daoapplication.entity;

import jakarta.persistence.*;
import lombok.*;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class KHTBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String title;
    //  @Column 작성안할 경우 기본값 (nullable = true, unique = false)
    @Column(nullable = false)
    private String author;
    @Column(nullable = false)
    private String genre;
}
