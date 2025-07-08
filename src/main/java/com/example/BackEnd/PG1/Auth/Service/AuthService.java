package com.example.BackEnd.PG1.Auth.Service;


import com.example.BackEnd.PG1.Auth.Controller.LoginRequest;
import com.example.BackEnd.PG1.Auth.Controller.RegisterRequest;
import com.example.BackEnd.PG1.Auth.Controller.TokenResponse;
import com.example.BackEnd.PG1.Auth.Repository.Token;
import com.example.BackEnd.PG1.Auth.Repository.TokenRepository;
import com.example.BackEnd.PG1.Entity.User;
import com.example.BackEnd.PG1.Repository.RepositoryUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService{

    private final RepositoryUser repositoryUser;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public TokenResponse register(RegisterRequest request){
        var user = User.builder()
                .name(request.name())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .build();

        var savedUser = repositoryUser.save(user);
        var jwtToken = jwtService.generateToken(savedUser);
        var refreshToken = jwtService.generateRefreshToken(savedUser);
        SaveUserToken(savedUser, jwtToken);
        return new TokenResponse(jwtToken,refreshToken);
    }

    private void SaveUserToken(User user,String jwtToken){
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(Token.TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    public TokenResponse login(LoginRequest loginRequest){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.email(),loginRequest.password())
        );
        var user = repositoryUser.findByEmail(loginRequest.email())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        SaveUserToken(user,jwtToken);
        return new TokenResponse(jwtToken,refreshToken);
    }

    private void revokeAllUserTokens(User user){
        final List<Token> validUserTokens = tokenRepository
                .findAllValidIsFalseOrRevokedIsFalseByUserId(user.getId());
        if (!validUserTokens.isEmpty()){
            for(final Token token: validUserTokens){
                token.setExpired(true);
                token.setRevoked(true);
            }
            tokenRepository.saveAll(validUserTokens);
        }
    }

    /*public TokenResponse refreshToken(final String authHeader){
        if (authHeader == null || !authHeader.startsWith("Bearer")){
            throw new IllegalArgumentException("Invalid Bearer Token");
        }


    }*/
}
