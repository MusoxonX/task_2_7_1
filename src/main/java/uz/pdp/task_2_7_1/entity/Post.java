package uz.pdp.task_2_7_1.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.task_2_7_1.entity.template.AbstractEntity;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post extends AbstractEntity {
    @NotBlank
    private String title;
    @NotBlank
    private String text;
    @NotBlank
    private String url;
}
