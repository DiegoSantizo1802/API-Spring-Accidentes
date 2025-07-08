package com.example.BackEnd.PG1.Auth.Controller;

public record LoginRequest(
        String email,
        String password

) {
}
