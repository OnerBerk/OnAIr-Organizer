package com.onAir.organiser.domain.organiserUser;

import com.onAir.organiser.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class OrganiserUser extends BaseEntity {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
}
