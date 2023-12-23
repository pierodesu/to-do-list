package dima.todolist.model;

import dima.todolist.model.core.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Task extends BaseEntity {

    private String title;
    private String description;
    private boolean completed;
    @ManyToOne
    @JoinColumn (name = "user_id", referencedColumnName = "id")
    private User user;
    private Date startDate;
    private Date dueDate;
}
