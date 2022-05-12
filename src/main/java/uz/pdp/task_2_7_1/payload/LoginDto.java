package uz.pdp.task_2_7_1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

    @NotNull(message = "username must be required")
    private String username;

    @NotNull(message = "password must be required")
    private String password;
}
