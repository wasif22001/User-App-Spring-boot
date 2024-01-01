package com.app.ws.controller;

import com.app.ws.exceptions.UserServiceException;
import com.app.ws.model.request.UpdateUserDetailRequestModel;
import com.app.ws.model.request.UserDetailRequestModel;
import com.app.ws.model.response.UserRest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("users") // http:localhost:8080/users
public class UserController {

    Map<String, UserRest> users;

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

        if (true) {
            throw new UserServiceException("A user service Exception is thrown.");
        }

        if (users.containsKey(userId)) {
            return new ResponseEntity<>(users.get(userId), HttpStatus.OK);
        }

        return ResponseEntity.noContent().build();
    }

    @PostMapping(consumes = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
    }, produces = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailRequestModel userDetails) {
        var userId = UUID.randomUUID().toString();
        var user = UserRest.builder()
                .userId(userId)
                .firstName(userDetails.getFirstName())
                .lastName(userDetails.getLastName())
                .email(userDetails.getEmail())
                .build();
        if (users == null) {
            users = new HashMap<>();
        }

        users.put(userId, user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping(path = "/{userId}", consumes = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
    }, produces = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
    })
    public UserRest updateUser(@PathVariable(value = "userId") String userId, @Valid @RequestBody UpdateUserDetailRequestModel userDetails) {

        var storedUserDetails = users.get(userId);
        storedUserDetails.setFirstName(userDetails.getFirstName());
        storedUserDetails.setLastName(userDetails.getLastName());
        users.put(userId, storedUserDetails);
        return storedUserDetails;

    }

    @DeleteMapping(path = "/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        users.remove(userId);
        return ResponseEntity.noContent().build();
    }

}
