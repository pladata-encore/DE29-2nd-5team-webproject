package com.example.travelproject.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.travelproject.model.entity.UserEntity;
import com.example.travelproject.model.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuthUserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        log.info("[AuthUserService][loadUserByUsername] Start");
        UserEntity userDto = userRepository.getUserDtoById(name);
        log.info("[AuthUserService][loadUserByUsername] End");
        // username의 데이터가 database에 존재함(가입함)!!
        if(userDto != null) {
            return new AuthUserDto(userDto);
        }
        // 존재하지 않음
        return null;
    }    
}
