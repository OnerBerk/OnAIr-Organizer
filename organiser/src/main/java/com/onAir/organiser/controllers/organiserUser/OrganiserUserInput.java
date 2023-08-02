package com.onAir.organiser.controllers.organiserUser;

import com.onAir.organiser.domain.todo.Todo;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrganiserUserInput {
    private String firstname;
    private String lastname;
    @Email
    private String email;
    private String password;
    private Instant created_at;
    private List<Todo> todoList;
}
