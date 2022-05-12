package uz.pdp.task_2_7_1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @NotNull(message = "fullName must be required")
    private String fullName;

    @NotNull(message = "username must be required")
    private String username;

    @NotNull(message = "password must be required")
    private String password;

    @NotNull(message = "role id must be required")
    private Long roleId;
}
