package com.app.ws.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDetailRequestModel {
    @NotNull(message = "First name cannot be null")
    @Size(min = 2, message = "first name must not be less than 2 characters")
    private String firstName;
    @NotNull(message = "last name cannot be null")
    @Size(min = 2, message = "last name must not be less than 2 characters")
    private String lastName;
}
