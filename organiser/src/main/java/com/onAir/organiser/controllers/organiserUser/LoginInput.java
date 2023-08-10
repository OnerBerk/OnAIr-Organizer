package com.onAir.organiser.controllers.organiserUser;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginInput {
    @Email
    private String email;
    private String password;
}
