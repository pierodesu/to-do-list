package dima.todolist.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class LoginDto {

    private String login;
    private String password;
    private String firstName;
    private String lastName;



}
