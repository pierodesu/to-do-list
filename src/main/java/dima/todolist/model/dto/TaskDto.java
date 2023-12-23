package dima.todolist.model.dto;

import dima.todolist.model.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Builder
@Getter
@Setter
public class TaskDto {

    private Long id;
    private String title;
    private String description;
    private Boolean completed;
    private User user;
    private Date startDate;
    private Date dueDate;

}
