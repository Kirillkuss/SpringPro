package com.example.test.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.test.entity.User;
import com.example.test.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import java.lang.IllegalArgumentException;
import java.security.SecureRandom;
import java.util.Base64;
/**
 * Сервис для работы с пользователями
 */
@Service
@RequiredArgsConstructor
public class UserService {

    @Value("${spring.security.secret}")
    private String secret;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    /**
     * Генерация соли
     * @return String
     */
    private String generateSalt() {
        byte[] saltBytes = new byte[32];
        new SecureRandom().nextBytes(saltBytes);
        return Base64.getEncoder().encodeToString(saltBytes);
    }
    /**
     * Доавление пользователя 
     * @param user - пользователь 
     * @return User
     */
    public User addUser( User user ){
        validateUsername( user.getUsername() );
        validateEmail( user.getEmail());
        validatePassword( user.getPassword() );
        String salt = generateSalt();
        user.setPassword( passwordEncoder.encode( secret + user.getPassword() + salt ));
        user.setSalt(salt);
        return userRepository.save( user);
    }
    /**
     * Проверка пароля при авторизации
     * @param rawPassword
     * @param salt
     * @param encodedPassword
     * @return boolean
     */
    public boolean checkUserPassword(String rawPassword, String salt, String encodedPassword) {
        return passwordEncoder.matches( secret + rawPassword + salt, encodedPassword);
    }
    /**
     * Проверка размера и кол-во символов для пароля
     * @param password
     * @return boolean
     */
    private boolean isValidPassword(String password) {
        return password.length() >= 8 && password.matches(".*[A-Za-z].*") && password.matches(".*\\d.*");
    }
    /**
     * Провекра на корректность ввода почты
     * @param email  - почта
     * @return  boolean
     */
    private boolean isValidEmail(String email) {
        String emailRegex = "^[\\w-\\.]+@[\\w-]+\\.[a-zA-Z]{2,4}$";
        return email != null && email.matches(emailRegex);
    }
    /**
     * Валидация логина
     * @param username - логин
     */
    private void validateUsername( String username){
        if (userRepository.findByLogin(username).isPresent()) {
            throw new IllegalArgumentException("Not unique username, please specify another!");
        }
    }
    /**
     * Валидация почты
     * @param email - почта
     */
    private void validateEmail(String email) {
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format!");
        }
        if (userRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("Email already in use, please specify another!");
        }
    }
    /**
     * Валидация пароля
     * @param password - пароль
     */
    private void validatePassword(String password) {
        if (!isValidPassword(password)) {
            throw new IllegalArgumentException("Password does not meet complexity requirements!");
        }
    }
}
