package com.onAir.organiser.controllers.organiserUser;

import com.onAir.organiser.domain.organiserUser.OrganiserUser;
import com.onAir.organiser.domain.todo.Todo;
import com.onAir.organiser.exception.ErrorResponse;
import com.onAir.organiser.repositories.organiserUser.OrganiserUserRepository;
import com.onAir.organiser.repositories.todo.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
public class OrganiserUserController {
    Instant now = Instant.now();
    @Autowired
    private OrganiserUserRepository organiserUserRepository;

    @Autowired
    private TodoRepository todoRepository;

    @QueryMapping
    public Iterable<OrganiserUser> findUsers() {
        return this.organiserUserRepository.findAll();
    }

    @QueryMapping
    public OrganiserUser findUserById(@Argument Long id) {
        OrganiserUser existOrganiserUser = new OrganiserUser();
        existOrganiserUser = organiserUserRepository.findById(id).orElseThrow();

        return existOrganiserUser;
    }

    @QueryMapping
    public OrganiserUser findUserByEmail(@Argument String email) {
        Optional<OrganiserUser> existUser = organiserUserRepository.findOrganiserUserByEmail(email);
        if (existUser.isPresent()) {
            return existUser.get();
        } else {
            return existUser.get();
        }
    }

    @MutationMapping
    public OrganiserUser createOrganiserUser(@Argument(name = "input") OrganiserUserInput organiserUserInput) {
        Optional<OrganiserUser> existUser = organiserUserRepository.findOrganiserUserByEmail(organiserUserInput.getEmail());
        if (existUser.isPresent()) {
            throw  new ErrorResponse("User already exist");
        } else {
            OrganiserUser organiserUser = new OrganiserUser();
            organiserUser.setFirstname(organiserUserInput.getFirstname());
            organiserUser.setLastname(organiserUserInput.getLastname());
            organiserUser.setEmail(organiserUserInput.getEmail());
            organiserUser.setPassword(organiserUserInput.getPassword());
            organiserUser.setCreatedAt(now);
            organiserUser.setTodoList(new ArrayList<>());
            return organiserUserRepository.save(organiserUser);
        }
    }
}
