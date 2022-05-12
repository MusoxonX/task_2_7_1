package uz.pdp.task_2_7_1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task_2_7_1.aop.CheckPermission;
import uz.pdp.task_2_7_1.payload.ApiResponse;
import uz.pdp.task_2_7_1.payload.PostDto;
import uz.pdp.task_2_7_1.service.PostService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    PostService postService;

    @CheckPermission(value = "ADD_POST")
    @PostMapping
    public HttpEntity<?> addPost(@Valid @RequestBody PostDto postDto){
        ApiResponse apiResponse = postService.addPost(postDto);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }

    @CheckPermission(value = "EDIT_POST")
    @PutMapping("/{id}")
    public HttpEntity<?> editPost(@PathVariable Long id,@RequestBody PostDto postDto){
        ApiResponse apiResponse = postService.editPost(id, postDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @GetMapping
    public HttpEntity<?> getAllPost(){
        ApiResponse apiResponse = postService.getAllPost();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getPostById(@PathVariable Long id){
        ApiResponse apiResponse = postService.getPostById(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @CheckPermission(value = "DELETE_POST")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deletePost(@PathVariable Long id){
        ApiResponse apiResponse =postService.deletePost(id);
        return ResponseEntity.status(apiResponse.isSuccess()?409:404).body(apiResponse);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
