package uz.pdp.task_2_7_1.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrObject {
    private Integer status;
    private String message;
    private long timestamp;
}
