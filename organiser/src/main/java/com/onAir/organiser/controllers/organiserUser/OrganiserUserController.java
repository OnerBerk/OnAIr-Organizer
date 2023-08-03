package com.onAir.organiser.controllers.organiserUser;

import com.onAir.organiser.domain.organiserUser.OrganiserUser;
import com.onAir.organiser.domain.organiserUser.UserResponseSuccess;
import com.onAir.organiser.domain.todo.Todo;
import com.onAir.organiser.exception.ErrorResponse;
import com.onAir.organiser.repositories.organiserUser.OrganiserUserRepository;
import com.onAir.organiser.repositories.todo.TodoRepository;
import com.onAir.organiser.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
public class OrganiserUserController {
    private Instant now = Instant.now();

    @Autowired
    private PasswordEncoder PasswordEncoder;

    @Autowired
    private OrganiserUserRepository organiserUserRepository;

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

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
    public UserResponseSuccess createOrganiserUser(@Argument(name = "input") OrganiserUserInput organiserUserInput) {
        Optional<OrganiserUser> existUser = organiserUserRepository.findOrganiserUserByEmail(organiserUserInput.getEmail());
        UserResponseSuccess response = new UserResponseSuccess();
        if (existUser.isPresent()) {
            throw new ErrorResponse("User already exist", 409);
        } else {
            OrganiserUser organiserUser = new OrganiserUser();
            organiserUser.setFirstname(organiserUserInput.getFirstname());
            organiserUser.setLastname(organiserUserInput.getLastname());
            organiserUser.setEmail(organiserUserInput.getEmail());
            organiserUser.setPassword(PasswordEncoder.encode(organiserUserInput.getPassword()));
            organiserUser.setCreatedAt(now);
            response.setOrganiserUser(organiserUser);
            response.setToken(jwtTokenUtil.getJWTToken(organiserUser));
            organiserUserRepository.save(organiserUser);
            return response;
        }
    }
}
