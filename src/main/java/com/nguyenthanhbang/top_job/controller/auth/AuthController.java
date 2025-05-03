package com.nguyenthanhbang.top_job.controller.auth;

import com.nguyenthanhbang.top_job.dto.request.CreateUserRequest;
import com.nguyenthanhbang.top_job.dto.request.LoginRequest;
import com.nguyenthanhbang.top_job.dto.response.ApiResponse;
import com.nguyenthanhbang.top_job.dto.response.AuthenticationResponse;
import com.nguyenthanhbang.top_job.model.InvalidToken;
import com.nguyenthanhbang.top_job.model.User;
import com.nguyenthanhbang.top_job.repository.InvalidTokenRepository;
import com.nguyenthanhbang.top_job.repository.UserRepository;
import com.nguyenthanhbang.top_job.service.UserService;
import com.nguyenthanhbang.top_job.utils.SecurityUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final InvalidTokenRepository invalidTokenRepository;
    @Value("${jwt.refresh-token-validity-in-seconds}")
    private long jwtRefreshExpiration;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserRepository userRepository;
    private final SecurityUtil securityUtil;
    private final UserService userService;
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthenticationResponse>> login(@RequestBody LoginRequest request) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(usernamePasswordAuthenticationToken);
        AuthenticationResponse response = new AuthenticationResponse();
        User user = userRepository.findByEmail(request.getEmail());
        AuthenticationResponse.UserLogin userLogin = new AuthenticationResponse.UserLogin();
        if(user != null) {
            userLogin.setId(user.getId());
            userLogin.setFullName(user.getFullName());
            userLogin.setEmail(user.getEmail());
            userLogin.setRole(user.getRole());
            response.setUser(userLogin);
        }
        String accessToken = securityUtil.createAccessToken(request.getEmail(), response);
        response.setAccessToken(accessToken);
        String refreshToken = securityUtil.createRefreshToken(request.getEmail(), response);
        userService.updateTokenOfUser(request.getEmail(), refreshToken);
        ResponseCookie springCookie = ResponseCookie.from("refreshToken", refreshToken)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(jwtRefreshExpiration)
                .build();
        SecurityContextHolder.getContext().setAuthentication(authentication);
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .message("Login Successful")
                .data(response)
                .build();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, springCookie.toString()).body(apiResponse);
    }
    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<AuthenticationResponse>> refresh(@CookieValue(name = "refreshToken", defaultValue = "default") String refreshToken) {
        if(refreshToken.equals("default")) {
            throw new IllegalArgumentException("Invalid refreshToken");
        }
        Jwt decodedToken = securityUtil.checkValidToken(refreshToken);
        String email = decodedToken.getSubject();
        User currentUser = userService.getUserByRefreshTokenAndEmail(refreshToken, email);
        if(currentUser == null) {
            throw new IllegalArgumentException("refresh token không hợp lệ");
        }
        AuthenticationResponse loginResponse = new AuthenticationResponse();
        AuthenticationResponse.UserLogin userLogin = new AuthenticationResponse.UserLogin();
        userLogin.setId(currentUser.getId());
        userLogin.setFullName(currentUser.getFullName());
        userLogin.setEmail(currentUser.getEmail());
        userLogin.setRole(currentUser.getRole());
        loginResponse.setUser(userLogin);
        String accessToken = securityUtil.createAccessToken(email, loginResponse);
        loginResponse.setAccessToken(accessToken);
        String refresh_token = securityUtil.createRefreshToken(email, loginResponse);
        userService.updateTokenOfUser(refresh_token, email);
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .message("Refresh Successful")
                .data(loginResponse)
                .build();
        ResponseCookie springCookie = ResponseCookie.from("refreshToken", refresh_token)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(jwtRefreshExpiration)
                .build();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, springCookie.toString()).body(apiResponse);
    }
    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logout(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Invalid token");
        }

        String token = authHeader.substring(7);
        InvalidToken invalidToken = InvalidToken.builder()
                .token(token)
                .build();
        invalidTokenRepository.save(invalidToken);
        String email = SecurityUtil.getCurrentUserLogin().isPresent() ? SecurityUtil.getCurrentUserLogin().get() : "";
        if(email.equals("")) {
            throw new IllegalArgumentException("User not found");
        }
        userService.updateTokenOfUser(email, null);
        SecurityContextHolder.clearContext();
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .message("Logout Successful")
                .data(null)
                .build();
        ResponseCookie springCookie = ResponseCookie.from("refreshToken", null)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(0)
                .build();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, springCookie.toString()).body(apiResponse);
    }
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<User>> create(@Valid @RequestBody CreateUserRequest request) {
        User savedUser = userService.createUser(request);
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.CREATED.value())
                .message("Register Successful")
                .data(savedUser)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }
}
