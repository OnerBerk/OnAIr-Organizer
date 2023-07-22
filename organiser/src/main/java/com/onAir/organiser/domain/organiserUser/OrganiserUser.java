package com.onAir.organiser.domain.organiserUser;

import com.onAir.organiser.BaseEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class OrganiserUser extends BaseEntity {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
