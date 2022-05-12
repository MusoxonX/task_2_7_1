package uz.pdp.task_2_7_1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    @NotNull(message = "title is required")
    private String title;
    @NotNull(message = "text is required")
    private String text;
    @NotNull(message = "url is required")
    private String url;
}
