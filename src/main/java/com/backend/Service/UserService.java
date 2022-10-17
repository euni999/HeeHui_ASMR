package com.backend.Service;

import com.backend.Dto.UserDto;
import com.backend.Repository.UserRepository;
import lombok.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public String save(UserDto userDto) {
        return userRepository.save(userDto.toEntity()).getIdx();
    }
}