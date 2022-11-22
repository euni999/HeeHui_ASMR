package com.backend.controller;

import com.backend.dto.UserDto;
import com.backend.service.UserService;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserController  {
    @Autowired
    private final UserService userService;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "/user", consumes = "application/json; charset=utf-8")
    public String save(@RequestBody UserDto userDto) {
        boolean isUser = userService.exists(userDto);
        System.out.println("회원 유무 : " + isUser);
        if (isUser) {
            return "로그인";
        }
        else {
            System.out.println("회원가입 : " + userDto.toString());
            return userService.save(userDto);
        }
    }

}