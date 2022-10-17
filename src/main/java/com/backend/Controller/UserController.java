package com.backend.Controller;


import com.backend.Dto.UserDto;
import com.backend.Service.UserService;
import lombok.*;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @CrossOrigin("http://localhost:3000")
    @PostMapping(value = "/api/v1/user", consumes = "application/json")
    public String save(@RequestBody UserDto userDto) {
        return userService.save(userDto);
    }
}