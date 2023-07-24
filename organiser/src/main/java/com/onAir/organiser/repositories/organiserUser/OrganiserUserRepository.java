package com.onAir.organiser.repositories.organiserUser;

import com.onAir.organiser.domain.organiserUser.OrganiserUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganiserUserRepository extends JpaRepository<OrganiserUser, Long> {
    Optional<OrganiserUser> findOrganiserUserByEmail(String email);
}
