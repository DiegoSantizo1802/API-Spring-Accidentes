package com.example.BackEnd.PG1.Auth.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TokenRepository extends JpaRepository<Token,Integer> {
    List<Token> findAllValidIsFalseOrRevokedIsFalseByUserId(Integer id);
}
