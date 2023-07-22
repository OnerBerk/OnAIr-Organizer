package com.onAir.organiser.repositories.organiserUser;

import com.onAir.organiser.domain.organiserUser.OrganiserUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganiserUserRepository extends JpaRepository<OrganiserUser, Long> {
    OrganiserUser findOrganiserUserByEmail(String email);
}
