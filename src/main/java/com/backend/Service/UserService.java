package com.backend.Service;

import com.backend.Dto.UserDto;
import com.backend.Entity.UserEntity;
import com.backend.Repository.UserRepository;
import lombok.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

   //@Autowired
    private final UserRepository userRepository;

    @Transactional
    public String save(UserDto userDto) {
        userRepository.save(userDto.toEntity());
        return "SUCCESS";
    }


}