package com.onAir.organiser.domain.todo;


import com.onAir.organiser.BaseEntity;
import com.onAir.organiser.domain.organiserUser.OrganiserUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Todo extends BaseEntity {
    private String description;

    @Enumerated(EnumType.STRING)
    private StateEnum state;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private OrganiserUser organiserUser;
}
