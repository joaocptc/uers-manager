package com.example.users.controllers.user;

import com.example.users.core.dtos.ErrorsDTO;
import com.example.users.core.dtos.FiltersDTO;
import com.example.users.core.dtos.UserDTO;
import com.example.users.usecase.UserUseCase;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.servers.ServerVariable;
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

@OpenAPIDefinition(
        info = @Info(
                title = "API User Manager",
                description = "API desenvolvida para realizar a gestão de usuários",
                version = "1.000",
                contact = @Contact(name = "@joãocarlos", email = "jcarlosptc@live.com")
        ),
        servers = @Server(
                url = "http://localhost:8080",
                description = "Servidor Local",
                variables = {
                        @ServerVariable(name = "serverUrl", defaultValue = "localhost"),
                        @ServerVariable(name = "serverHttpPort", defaultValue = "8080")
                }))

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    private final UserUseCase userUseCase;

    private static final int DEFAULT_PAGE = 0;
    private static final int DEFAULT_PAGE_SIZE = 25;

    @Operation(summary = "Permite o cadastro de usuários")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorsDTO.class)) }),
            @ApiResponse(responseCode = "422", description = "Unprocessable Entity",
                    content =  { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorsDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content =  { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorsDTO.class)) })
    })
    @PostMapping
    public ResponseEntity<UserDTO> save(@RequestBody final UserDTO dto) {

        log.info("[UserController - save] Requisição HTTP POST recebida");

        var result = userUseCase.createUser(dto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @Operation(summary = "Permite a consulta de usuários")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorsDTO.class)) }),
            @ApiResponse(responseCode = "422", description = "Unprocessable Entity",
                    content =  { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorsDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content =  { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorsDTO.class)) })
    })
    @GetMapping
    public ResponseEntity<List<UserDTO>> find(@Parameter(description = "Página desejada") @RequestParam(name = "page", required = false) final Integer page,
                                              @Parameter(description = "Quantidade de registros por página, máximo de 30") @RequestParam(name = "page-size", required = false) final Integer pageSize) {

        log.info("[UserController - find] Requisição HTTP GET recebida");

        var filters = FiltersDTO
                                    .builder()
                                    .page(isNull(page) ? DEFAULT_PAGE : page)
                                    .pageSize(isNull(pageSize) ? DEFAULT_PAGE_SIZE : pageSize)
                                    .build();

        var result = userUseCase.findUsers(filters);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Operation(summary = "Permite atualização dos dados do usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorsDTO.class)) }),
            @ApiResponse(responseCode = "422", description = "Unprocessable Entity",
                    content =  { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorsDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content =  { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorsDTO.class)) })
    })
    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@Parameter(description = "Código do usuário") @PathVariable final long id,
                                             @RequestBody final UserDTO dto) {

        log.info("[UserController - update] Requisição HTTP PUT recebida");

        userUseCase.updateUser(dto, id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
