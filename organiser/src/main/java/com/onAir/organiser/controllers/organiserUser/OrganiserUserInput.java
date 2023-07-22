package com.onAir.organiser.controllers.organiserUser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrganiserUserInput {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Instant created_at;
}
