package com.onAir.organiser.controllers.organiserUser;

import com.onAir.organiser.domain.organiserUser.OrganiserUser;
import com.onAir.organiser.repositories.organiserUser.OrganiserUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.time.Instant;

@Controller
public class OrganiserUserController {
    Instant now = Instant.now();
    @Autowired
    private OrganiserUserRepository organiserUserRepository;

    @QueryMapping
    public Iterable<OrganiserUser> findUsers() {
        return this.organiserUserRepository.findAll();
    }

    @QueryMapping
    public OrganiserUser findUserById(@Argument Long id) {
        return organiserUserRepository.findById(id).orElseThrow();
    }

    @QueryMapping
    public OrganiserUser findUserByEmail(@Argument String email) {
        return organiserUserRepository.findOrganiserUserByEmail(email);
    }

    @MutationMapping
    public OrganiserUser createOrganiserUser(@Argument(name = "input") OrganiserUserInput organiserUserInput) {
        OrganiserUser organiserUser = new OrganiserUser();
        organiserUser.setFirstname(organiserUserInput.getFirstname());
        organiserUser.setLastname(organiserUserInput.getLastname());
        organiserUser.setEmail(organiserUserInput.getEmail());
        organiserUser.setPassword(organiserUserInput.getPassword());
        organiserUser.setCreatedAt(now);

        return organiserUserRepository.save(organiserUser);
    }
}
