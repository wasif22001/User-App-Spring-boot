package com.app.ws.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailRequestModel {
    @NotNull(message = "First name cannot be null")
    private String firstName;
    @NotNull(message = "last name cannot be null")
    private String lastName;
    @NotNull(message = "email cannot be null")
    @Email
    private String email;
    @NotNull(message = "user id cannot be null")
    @Size(min = 8, max = 16, message = "password must be equal or greater than 8 characters and less than or equal to 16 characters")
    private String password;
}
