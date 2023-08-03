package com.onAir.organiser.domain.organiserUser;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseSuccess {
    private OrganiserUser organiserUser;
    private String token;
}
