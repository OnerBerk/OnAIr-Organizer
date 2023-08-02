package com.onAir.organiser.domain.organiserUser;

import com.onAir.organiser.BaseEntity;
import com.onAir.organiser.domain.todo.Todo;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class OrganiserUser extends BaseEntity {
    private String firstname;
    private String lastname;
    @Email // dependence starter validation
    private String email;
    private String password;

    @OneToMany(mappedBy = "organiserUser")
    private List<Todo> todoList = new ArrayList<>();

}