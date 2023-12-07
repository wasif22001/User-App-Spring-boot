package com.app.ws.controller;

import com.app.ws.model.request.UserDetailRequestModel;
import com.app.ws.model.response.UserRest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("users") // http:localhost:8080/users
public class UserController {

    @GetMapping
    public String getUser(@RequestParam(value = "page", defaultValue = "1") int page,
                          @RequestParam(value = "limit", defaultValue = "1") int limit,
                          @RequestParam(value = "sort", required = false) String sort) {
        return "get user was called with page = " + page + " and limit = " + limit + " and sort = " + sort;
    }

    @GetMapping(path = "/{userId}",
            produces = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            })
    public ResponseEntity<UserRest> getUser(@PathVariable(value = "userId") String userId) {
        var user = UserRest.builder()
                .firstName("firstName")
                .lastName("lastName")
                .email("testEmail@test.com")
                .build();

        return new ResponseEntity<>(user, HttpStatus.OK);
        /* return ResponseEntity.ok(user); */
    }

    @PostMapping(consumes = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
    }, produces = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailRequestModel userDetails) {
        var user = UserRest.builder()
                .firstName(userDetails.getFirstName())
                .lastName(userDetails.getLastName())
                .email(userDetails.getEmail())
                .build();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping
    public String updateUser() {
        return "Update user was called.";
    }

    @DeleteMapping
    public String deleteUser() {
        return "Delete user was called.";
    }

}
