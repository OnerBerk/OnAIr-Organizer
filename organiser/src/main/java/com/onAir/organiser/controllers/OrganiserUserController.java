package com.onAir.organiser.controllers;

import com.onAir.organiser.domain.organiserUser.OrganiserUser;
import com.onAir.organiser.repositories.organiserUser.OrganiserUserRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class OrganiserUserController {
    private final OrganiserUserRepository organiserUserRepository;

    public OrganiserUserController(OrganiserUserRepository organiserUserRepository) {
        this.organiserUserRepository = organiserUserRepository;
    }

    @QueryMapping
    public Iterable<OrganiserUser> findUsers() {
        return this.organiserUserRepository.findAll();
    }

    @QueryMapping
    public OrganiserUser findUserById(@Argument Long id) {
        return this.organiserUserRepository.findById(id).orElseThrow();
    }

    @QueryMapping
    public OrganiserUser findUserByEmail(@Argument String email) {
        return this.organiserUserRepository.findOrganiserUserByEmail(email);
    }
}
