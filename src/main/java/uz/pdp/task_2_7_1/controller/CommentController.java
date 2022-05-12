package uz.pdp.task_2_7_1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task_2_7_1.aop.CheckPermission;
import uz.pdp.task_2_7_1.payload.ApiResponse;
import uz.pdp.task_2_7_1.payload.CommentDto;
import uz.pdp.task_2_7_1.service.CommentService;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @CheckPermission(value = "ADD_COMMENT")
    @PostMapping
    public HttpEntity<?> addComment(@RequestBody CommentDto commentDto){
        ApiResponse apiResponse =commentService.addComment(commentDto);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }

    @CheckPermission(value = "EDIT_COMMENT")
    @PutMapping("/{id}")
    public HttpEntity<?> editComment(@PathVariable(value = "id") Long id, @RequestBody CommentDto commentDto){
        ApiResponse apiResponse =commentService.editComment(id,commentDto);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }

    @CheckPermission(value = "DELETE_COMMENT")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteComment(@PathVariable Long id){
        ApiResponse apiResponse = commentService.deleteComment(id);
        return ResponseEntity.status(apiResponse.isSuccess()?409:404).body(apiResponse);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getAllCommentById(@PathVariable Long id){
        ApiResponse apiResponse = commentService.getAllCommentById(id);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping
    public HttpEntity<?> getAllComment(){
        ApiResponse apiResponse = commentService.getAllComment();
        return ResponseEntity.ok(apiResponse);
    }
}
