package uz.pdp.task_2_7_1.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
