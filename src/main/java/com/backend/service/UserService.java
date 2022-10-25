package com.backend.service;

import com.backend.dto.UserDto;
import com.backend.repository.UserRepository;
import lombok.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public String save(UserDto userDto) {
        userRepository.save(userDto.toEntity());
        return "SUCCESS";
    }

    public boolean exists(UserDto userDto) {
        return userRepository.existsUser(userDto.getEmail());
    }

    public Integer userIdx(String email) {
        return userRepository.findByUser_idx(email);
    }


}