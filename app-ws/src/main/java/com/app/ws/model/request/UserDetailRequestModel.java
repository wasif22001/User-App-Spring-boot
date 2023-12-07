package com.app.ws.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailRequestModel {
    private String firstName;
    private String lastName;
    private String email;
    private String userId;
}
