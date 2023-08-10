package com.onAir.organiser.controllers.organiserUser;

import com.onAir.organiser.domain.organiserUser.OrganiserUser;
import com.onAir.organiser.domain.organiserUser.UserResponseSuccess;
import com.onAir.organiser.exception.ErrorResponse;
import com.onAir.organiser.repositories.organiserUser.OrganiserUserRepository;
import com.onAir.organiser.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import java.time.Instant;
import java.util.Optional;

@Controller
public class OrganiserUserController {
    private final Instant now = Instant.now();

    @Autowired
    private PasswordEncoder PasswordEncoder;

    @Autowired
    private OrganiserUserRepository organiserUserRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @QueryMapping
    public Iterable<OrganiserUser> findUsers() {
        return this.organiserUserRepository.findAll();
    }

    @QueryMapping
    public OrganiserUser findUserById(@Argument Long id) {
        OrganiserUser organiserUser = new OrganiserUser();
        Optional<OrganiserUser> optionalOrganiserUser = organiserUserRepository.findById(id);
        if (optionalOrganiserUser.isPresent()) {
            organiserUser = optionalOrganiserUser.get();
            return organiserUser;
        } else {
            System.out.println("no user herrre !!");
            throw new ErrorResponse("User with this ID doesn't exist", 404);
        }
    }

    @QueryMapping
    public OrganiserUser findUserByEmail(@Argument String email) {
        Optional<OrganiserUser> existUser = organiserUserRepository.findOrganiserUserByEmail(email);
        if (existUser.isEmpty()) {
            throw new ErrorResponse("No user with this email", 404);
        } else {
            return existUser.get();
        }
    }

    @MutationMapping
    public OrganiserUser createOrganiserUser(@Argument(name = "input") OrganiserUserInput organiserUserInput) {
        Optional<OrganiserUser> existUser = organiserUserRepository.findOrganiserUserByEmail(organiserUserInput.getEmail());
        if (existUser.isPresent()) {
            throw new ErrorResponse("User already exist", 409);
        } else {
            OrganiserUser organiserUser = new OrganiserUser();
            organiserUser.setFirstname(organiserUserInput.getFirstname());
            organiserUser.setLastname(organiserUserInput.getLastname());
            organiserUser.setEmail(organiserUserInput.getEmail());
            organiserUser.setPassword(PasswordEncoder.encode(organiserUserInput.getPassword()));
            organiserUser.setCreatedAt(now);
            organiserUserRepository.save(organiserUser);
            return organiserUser;
        }
    }

    @MutationMapping
    public UserResponseSuccess login(@Argument(name = "input") LoginInput loginInput) {
        Optional<OrganiserUser> existUser = organiserUserRepository.findOrganiserUserByEmail(loginInput.getEmail());
        UserResponseSuccess response = new UserResponseSuccess();
        if (existUser.isEmpty()) {
            throw new ErrorResponse("No user with this email", 404);
        } else {
            boolean passwordsMatches = PasswordEncoder.matches(loginInput.getPassword(), existUser.get().getPassword());
            if (!passwordsMatches) {
                throw new ErrorResponse("Password doesn't match", 401);
            }
            new OrganiserUser();
            OrganiserUser organiserUser;
            organiserUser = existUser.get();
            response.setOrganiserUser(organiserUser);
            response.setToken(jwtTokenUtil.getJWTToken(organiserUser));
            return response;
        }
    }
}
