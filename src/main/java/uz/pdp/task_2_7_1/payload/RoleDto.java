package uz.pdp.task_2_7_1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.task_2_7_1.entity.enums.Permission;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {

    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "description is requrired")
    private String description;

    @NotEmpty(message = "permission must be")
    private List<Permission> permissionList;
}
