package com.example.BackEnd.PG1.Auth.Controller;

public record RegisterRequest(
        String email,
        String password,
        String name

) {
}
