package com.example.users.controllers.user;

import com.example.users.core.dtos.UserDTO;
import com.example.users.usecase.UserUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    private final UserUseCase userUseCase;

    @PostMapping
    public ResponseEntity<UserDTO> save(@RequestBody final UserDTO dto) {

        log.info("[UserController - save] Requisição HTTP POST recebida");

        var result = userUseCase.createUser(dto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping
    private ResponseEntity<List<UserDTO>> find(@RequestParam(name = "min-date", required = false) final LocalDateTime minDate,
                                               @RequestParam(name = "max-date", required = false) final LocalDateTime maxDate) {

        log.info("[UserController - find] Requisição HTTP GET recebida");

        var result = userUseCase.findUsers(null, null);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    private ResponseEntity<HttpStatus> update(@PathVariable final long id) {

        log.info("[UserController - update] Requisição HTTP PUT recebida");

        return null;
    }
}
