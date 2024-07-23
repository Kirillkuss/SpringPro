package com.example.test.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.test.entity.User;
import com.example.test.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import java.lang.IllegalArgumentException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    /**
     * Добавить юзера
     * @param user - юзер
     * @return User
     */
    public User addUser( User user ){
        if( userRepository.findByLogin( user.getUsername()).isPresent()) throw new IllegalArgumentException("Not unique username, please specify another!");
        user.setPassword( passwordEncoder.encode( user.getPassword()));
        return userRepository.save( user);
    }
    /**
     * Проверка пароля
     * @param rawPassword 
     * @param encodedPassword
     * @return boolean
     */
    public boolean checkUserPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
