package com.backend.Controller;

import com.backend.Dto.UserDto;
import com.backend.Entity.UserEntity;
import com.backend.Service.UserService;
import lombok.*;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserController {

    @Autowired
    private final UserService userService;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "/user", consumes = "application/json; charset=utf-8")
    public String save(@RequestBody UserDto userDto) {
        System.out.println(userDto.toString());
        return userService.save(userDto);
    }

}