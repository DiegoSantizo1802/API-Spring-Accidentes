package com.example.BackEnd.PG1.Entity;

import com.example.BackEnd.PG1.Auth.Repository.Token;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user_table")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @Column(unique = true)
    private String email;
    private String password;

    @OneToMany(mappedBy = "user" ,fetch = FetchType.LAZY)
    private List<Token> tokens;

}
