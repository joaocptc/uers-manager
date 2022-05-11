package com.example.users.controllers.user;

import com.example.users.core.dtos.UserDTO;
import com.example.users.usecase.UserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserUseCase userUseCase;

    @PostMapping
    public ResponseEntity<UserDTO> save(@RequestBody final UserDTO dto) {

        return null;
    }

    @GetMapping
    private ResponseEntity<Page<UserDTO>> find(@RequestParam final LocalDateTime minDate,
                                               @RequestParam final LocalDateTime maxDate) {

        return null;
    }

    @PutMapping("/{id}")
    private ResponseEntity<HttpStatus> update(@PathVariable final long id) {

        return null;
    }
}
