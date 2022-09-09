package com.example.todoshpp.controller;

import com.example.todoshpp.model.RoleEntity;
import com.example.todoshpp.model.UserEntity;
import com.example.todoshpp.service.RoleService;
import com.example.todoshpp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * for swagger use -<a href="http://localhost:port/swagger-ui/index.html">...</a>
 */
@RestController
@RequestMapping("/api")
public class UserController {
    private final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
        log.info("book service was be autowired");
    }


    @Operation(summary = "GET request")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "All good done", content = {@Content(examples = {@ExampleObject(value = "Book", summary = "subject #1")})})})
    @GetMapping("/user")
    public ResponseEntity<Iterable<UserEntity>> getAllUsers() {
        log.info("used get mapping to /user");
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserEntity> getBookById(@PathVariable int id) {
        log.info("method get book by id used");
        Optional<UserEntity> book = userService.findById(id);
        return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/user/{id},{roleName}")
    public ResponseEntity<UserEntity> setCompleted(@PathVariable int id, @PathVariable String roleName) {
        log.info("method set or update user by id used");
        Optional<UserEntity> user = userService.findById(id);
        if (user.isEmpty()) {
            log.warn("{} - its incorrect id check input", id);
            return ResponseEntity.notFound().build();
        }
        UserEntity result = user.get();
        RoleEntity idRole = roleService.findIdRole(roleName);
        result.setRole(idRole);
        userService.save(result);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(result.getId())
                .toUri();
        log.info("method set or update user done");
        return ResponseEntity.ok().header(
                "Location", location.toString()
        ).build();
    }

    //    @RequestMapping(value = "/book", method = {RequestMethod.POST, RequestMethod.PUT})
//    public ResponseEntity<?> createBook(@Valid @RequestBody BookEntity bookEntity, Errors errors) {
//        if (errors.hasErrors()) {
//            log.warn("not created its has errors");
//            return ResponseEntity.badRequest()
//                    .body(BookValidationErrorBuilder.fromBindingErrors(errors));
//        }
//        BookEntity result = service.save(bookEntity);
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(result.getId())
//                .toUri();
//        log.info("success created book");
//        return ResponseEntity.created(location)
//                .build();
//    }
    @Operation(summary = "use post request we will be create and save model")
    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> saveUser(@Validated @RequestBody UserEntity userEntity) {
        log.info("used get mapping to /user method addUser");

        return userService.save(userEntity);
    }

//    @DeleteMapping("/book/{id}")
//    public ResponseEntity<BookEntity> deleteBook(@PathVariable int id) {
//        service.delete(BookBuilder.create().withId(id).build());
////        service.deleteWithId(id);
//        log.info("book with id - {} has deleted", id);
//        return ResponseEntity.noContent().build();
//    }
//
//    @DeleteMapping("/book")
//    public ResponseEntity<BookEntity> deleteBook(@RequestBody BookEntity bookEntity) {
//        service.delete(bookEntity);
//        log.info("book deleted {}", bookEntity);
//        return ResponseEntity.noContent().build();
//    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        log.info("method handleValidationExceptions");
        return errors;
    }

//    @ExceptionHandler
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    public BookValidationError handleException(Exception exception) {
//        log.error("handleException used");
//        return new BookValidationError(exception.getMessage());
//    }
}
