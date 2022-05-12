package com.example.users.controllers.user;

import com.example.users.core.dtos.FiltersDTO;
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

import java.util.List;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    private final UserUseCase userUseCase;

    private static final int DEFAULT_PAGE = 0;
    private static final int DEFAULT_PAGE_SIZE = 25;

    @PostMapping
    public ResponseEntity<UserDTO> save(@RequestBody final UserDTO dto) {

        log.info("[UserController - save] Requisição HTTP POST recebida");

        var result = userUseCase.createUser(dto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> find(@RequestParam(name = "page", required = false) final Integer page,
                                              @RequestParam(name = "page-size", required = false) final Integer pageSize) {

        log.info("[UserController - find] Requisição HTTP GET recebida");

        var filters = FiltersDTO
                                    .builder()
                                    .page(isNull(page) ? DEFAULT_PAGE : page)
                                    .pageSize(isNull(pageSize) ? DEFAULT_PAGE_SIZE : pageSize)
                                    .build();

        var result = userUseCase.findUsers(filters);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable final long id) {

        log.info("[UserController - update] Requisição HTTP PUT recebida");

        return null;
    }
}
