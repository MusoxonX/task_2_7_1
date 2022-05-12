package uz.pdp.task_2_7_1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler
    ResponseEntity<ErrObject> handleResourceNotFoundException(ResourceNotFoundException ex){
        ErrObject object = new ErrObject();
        object.setStatus(HttpStatus.NOT_FOUND.value());
        object.setMessage(ex.getMessage());
        object.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<ErrObject>(object,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    ResponseEntity<ErrObject> handleForbiddenExeption(ForbiddenException ex){
        ErrObject object = new ErrObject();
        object.setStatus(HttpStatus.UNAUTHORIZED.value());
        object.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<ErrObject>(object,HttpStatus.UNAUTHORIZED);
    }
}
